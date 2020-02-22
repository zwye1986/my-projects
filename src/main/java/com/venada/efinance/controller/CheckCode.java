package com.venada.efinance.controller;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.venada.efinance.common.util.RandomValidateCode;
import com.venada.efinance.util.SystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CheckCode {
	@RequestMapping(value="/getCheckCode",method = RequestMethod.GET)
	public void getCheckCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		response.setHeader("Pragma","No-Cache");
		response.setDateHeader("Expires",0);
		response.setHeader("Cache-Control","no-Cache");
		ServletOutputStream out = response.getOutputStream();
		/*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		String rcode = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",4);
		String codeName = request.getParameter("codeName");
		session.setAttribute(codeName, rcode);
		BufferedImage images = SystemUtils.drawImage(rcode, 20);
		encoder.encode(images);*/
		String rcode = SystemUtils.randomCheckcode("23456789ABCDEFGHJKLMNPRSTUVWXYZ",4);
		String codeName = request.getParameter("codeName");
		BufferedImage images = SystemUtils.drawImage(rcode, 20);

		ImageIO.write(images,"jpg",out);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/getCheckCodeByWeixin",method = RequestMethod.GET)
	public void getCheckCodeByWeixin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
