package com.venada.efinance.controller;


import com.venada.efinance.business.AdvertiseImagBusiness;
import com.venada.efinance.business.UserDetailBusiness;
import com.venada.efinance.pojo.AdvertiseImag;
import com.venada.efinance.pojo.User;
import com.venada.efinance.pojo.UserDetail;
import com.venada.efinance.security.SpringSecurityUtil;
import com.venada.efinance.util.ImageCut;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {
	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	@Autowired
	private UserDetailBusiness userDetailBusiness;
	@Autowired
	private AdvertiseImagBusiness advertiseImagBusiness;
	
	@RequestMapping(value="/{id}/getPhoto")
	public void getPhotoByUserId(@PathVariable String id ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
			ServletOutputStream  op = response.getOutputStream();
			UserDetail userDetail = userDetailBusiness.findUserDetailByUserId(id);
			if(userDetail != null && userDetail.getPhoto() != null){
				response.reset();
				op.write(userDetail.getPhoto());
				op.flush();
				op.close();
			}
		
	
	}
	
	@RequestMapping(value="/{advertiseId}/getAdvertiseImg")
	public void getAdvertiseImg(@PathVariable String advertiseId ,HttpServletRequest request,HttpServletResponse response){
		try{
			ServletOutputStream  op = response.getOutputStream();
			AdvertiseImag advertiseImag = advertiseImagBusiness.getAdvertiseImagByAdvertiseId(advertiseId);
			response.reset();
			op.write(advertiseImag.getFile());
			op.flush();
			op.close();
		}catch(Exception e){
			logger.error("展示文件失败，主键Id为:"+advertiseId +"错误原因为:"+ e.getMessage());
			
		}
	}
	
	@RequestMapping(value="/manager/showImag")
	public void showImag(HttpServletRequest request,HttpServletResponse response){
		try{
			ServletOutputStream  op = response.getOutputStream();
			String id = request.getParameter("id");
			if(!"".equals(id) && id != null){
			response.reset();
			AdvertiseImag advertiseImag = advertiseImagBusiness.getAdvertiseImagById(id);
			op.write(advertiseImag.getFile());
			}
			op.flush();
			op.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}		
	}

    @RequestMapping(value="/user/manager/cutImag" , method = RequestMethod.POST)
    public @ResponseBody Object cutImage(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            User user = SpringSecurityUtil.getCurrentUser();
            String ctxPath = "/tmp/userPic/" + user.getMobileNumber();
            File[] f = new File(ctxPath).listFiles();
            if(f != null && f.length > 0){
                ImageCut imageCut = new ImageCut();
                String imageName = f[0].getName();
                int x1 = Integer.parseInt(request.getParameter("x"));
                int y1 = Integer.parseInt(request.getParameter("y"));
                int w = Integer.parseInt(request.getParameter("w"));
                int h = Integer.parseInt(request.getParameter("h"));
                imageCut.cutImage(ctxPath+"/"+imageName, x1, y1, w, h);
                File cuttedFile = new File(ctxPath+"/"+imageName);
                File newFile = new File(ctxPath+"/N"+imageName);
                Thumbnails.of(cuttedFile).size(500, 500).toFile(newFile);
                UserDetail userDetail = userDetailBusiness.findUserDetailByUserId(SpringSecurityUtil.getCurrentUser().getId());
                userDetail.setPhoto(IOUtils.toByteArray(new FileInputStream(ctxPath+"/N"+imageName)));
                userDetailBusiness.updateUserDetailByMobileNumber(userDetail);
            }else{
                resultMap.put("resultCode", 0);
                resultMap.put("resultMsg", "操作失败，无效的文件！");
                return resultMap ;
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "系统错误，请稍后再试！");
            return resultMap ;
        }
        resultMap.put("resultCode", 1);
        resultMap.put("resultMsg", "修改头像成功！");
        return resultMap ;
    }
	
}
