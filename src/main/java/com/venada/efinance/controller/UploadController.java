package com.venada.efinance.controller;

import com.venada.efinance.business.*;
import com.venada.efinance.pojo.*;
import com.venada.efinance.security.SpringSecurityUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {
	/** Logger */
	private static final Logger logger = LoggerFactory
			.getLogger(UploadController.class);
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private AdvertiseImagBusiness advertiseImagBusiness;
	@Autowired
	private GameBusiness gameBusiness;
	@Autowired
	private ProjectBusiness projectBusiness;
	@Autowired
	private UserBusiness userBusiness;



	@RequestMapping(value = "manager/dealUpload", method = RequestMethod.POST)
	public @ResponseBody
	String uploadAdvertise(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String advertiseId = request.getParameter("advertiseId");
		String id = UUID.randomUUID().toString().toUpperCase();
		int c = advertiseImagBusiness.getCountByAdvertiseId(advertiseId);
		if (c != 0) {
			id = advertiseImagBusiness.getAdvertiseImagByAdvertiseId(
					advertiseId).getId();
		}

		try {
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				AdvertiseImag advertiseImag = new AdvertiseImag();

				advertiseImag.setId(id);
				advertiseImag.setFile(mf.getBytes());
				advertiseImag.setAdvertiseId(advertiseId);
				if (c == 0) {
					advertiseImagBusiness.saveAdvertiseImag(advertiseImag);
				} else {
					advertiseImagBusiness.updateAdvertiseImg(advertiseImag);
				}

			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return id;
	}

	@RequestMapping(value = "upload.do", params = "gameId", method = RequestMethod.POST)
	public @ResponseBody
	String uploadGamePic(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String gameId = request.getParameter("gameId");
		String type = request.getParameter("type");

		int picType = 1;
		try {
			picType = Integer.parseInt(type);
		} catch (Exception e) {
			logger.error("确认游戏数量转换为数字失败：" + type);
			picType = 1;
		}

		String id = ""; // 游戏图片主键

		try {
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				id = UUID.randomUUID().toString();
				GamePic gamePic = new GamePic();
				gamePic.setId(id);
				gamePic.setGameId(gameId);
				gamePic.setType(picType);
				gamePic.setCreateTime(sdf.parse(sdf.format(new Date())));
				gamePic.setGamePic(mf.getBytes());
				if (picType == 1 || picType == 2) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("gameId", gameId);
					map.put("type", picType);
					if(!"".equals(gameId) && gameId != null){
						gameBusiness.delGamePic(map);
					}
					
				}
				gameBusiness.addGamePic(gamePic);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return id;
	}

	@RequestMapping(value = "uploadProject.do", method = RequestMethod.POST)
	public @ResponseBody
	String uploadProject(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String id = request.getParameter("id");
		Project project = null;
		if (!"".equals(id) && id != null) {
			project = projectBusiness.getProjectById(id);
		}
		try {
			if (project != null) {

				for (Map.Entry<String, MultipartFile> entity : fileMap
						.entrySet()) {
					MultipartFile mf = entity.getValue();
					project.setProjectPic(mf.getBytes());
					projectBusiness.updateProject(project);
				}
			}
		} catch (Exception e) {
			logger.error("数字转换错误:" + e.getMessage());
		}

		return id;
	}

	@RequestMapping(value = "/{mobileNumber}/{password}/mobileUploadImg", method = RequestMethod.POST)
	public @ResponseBody
	Object mobileUploadImg(HttpServletRequest request,@PathVariable String mobileNumber ,@PathVariable String password ) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User user = userBusiness.findUserByMoblieNumberAndPasswordNotCoded(mobileNumber, password);
		if(user == null){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg","上传文件失败，用户名不存在或者密码错误！");
		}else{
			try {
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

					MultipartFile mf = entity.getValue();

					UserDetail userDetail = userDetailBusiness
							.findUserDetailByUserId(user.getId());
					userDetail.setPhoto(mf.getBytes());
					userDetailBusiness.updateUserDetailByMobileNumber(userDetail);

				}
				resultMap.put("resultCode", 1);
				resultMap.put("resultMsg",
						request.getContextPath() + "/" + user.getId() + "/getPhoto");
			} catch (IOException e) {
				logger.error("客户端上传头像失败：" + e.getMessage());
				resultMap.put("resultCode", 3);
				resultMap.put("resultMsg","上传失败，网络异常！");
			}

			
		}
		
		return resultMap;

	}
	
	private InputStream getImageStream(BufferedImage bi,String formatName){
        InputStream is = null;  
          
          
        bi.flush();  
          
        ByteArrayOutputStream bs = new ByteArrayOutputStream();   
          
        ImageOutputStream imOut;  
        try {  
            imOut = ImageIO.createImageOutputStream(bs);  
              
            ImageIO.write(bi, formatName,imOut);  
              
            is= new ByteArrayInputStream(bs.toByteArray());  
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
        return is;  
    }  
	
	@RequestMapping(value="/getTmpImg",method = RequestMethod.GET)
	public void getCheckCode(HttpServletRequest request,HttpServletResponse response,String filePath) throws IOException{
		ServletOutputStream out = response.getOutputStream();
		String fileName = request.getParameter("fileName");
		User user = SpringSecurityUtil.getCurrentUser();
		InputStream is = new FileInputStream(new File("/tmp/userPic/"+user.getMobileNumber()+"/"+fileName));
		byte[] byt = new byte[is.available()];
	    is.read(byt);
		out.write(byt);
		out.flush();
		is.close();
		out.close();
	}

    @RequestMapping(value="/user/manager/upload")
    public String upload(Model model){
        model.addAttribute("item", 1);
        return ".newUploadPhoto";
    }

    @RequestMapping(value = "user/manager/dealUpload",produces = {"text/plain;charset=UTF-8"}
    )
    public @ResponseBody
    String upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        User user = SpringSecurityUtil.getCurrentUser();
        String ctxPath = "/tmp/userPic/"+ user.getMobileNumber();
        // 创建文件夹
        File file = new File(ctxPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            File[] f = file.listFiles();
            for (int i = 0; i < f.length; i++) {
                f[i].delete();
            }
        }
        File f ;
        try {
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

                MultipartFile mf = entity.getValue();

                String suffix = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")).replace(".", "");
                fileName = user.getMobileNumber()+mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
                BufferedImage bi = ImageIO.read(mf.getInputStream());
                int height = bi.getHeight();
                int width = bi.getWidth();
                if (height < 250 || width < 250) {
                    return "{\"resultCode\":0,\"resultMsg\":\"图片不符合要求，至少250*250\"}";
                }else if(height > 250 || width > 250){
                    BigDecimal rate = new BigDecimal(1);
                    if(height > width){
                        rate = new BigDecimal(250).divide(new BigDecimal(width),2,BigDecimal.ROUND_DOWN);
                    }else if(width > height){
                        rate = new BigDecimal(250).divide(new BigDecimal(height),2,BigDecimal.ROUND_DOWN);
                    }
                    OutputStream ops = new ByteArrayOutputStream();

                    InputStream is = getImageStream(bi, suffix);
                    Thumbnails.of(is).scale(rate.doubleValue()).toOutputStream(ops);

                    f = new File(ctxPath, fileName);
                    FileCopyUtils.copy(((ByteArrayOutputStream)ops).toByteArray(), f);
                    ops.close();
                }else{
                    f = new File(ctxPath, fileName);
                    FileCopyUtils.copy(mf.getBytes(), f);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return "{\"resultCode\":0,\"resultMsg\":\"系统出现异常，请联系系统管理员。\"}";
        }

        String successMsg = "\""+ fileName;
        return "{\"resultCode\":1,\"resultMsg\":"+successMsg+"\"}";

    }
}
