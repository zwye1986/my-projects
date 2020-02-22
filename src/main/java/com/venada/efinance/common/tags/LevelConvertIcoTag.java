package com.venada.efinance.common.tags;

import com.venada.efinance.util.LevelUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LevelConvertIcoTag extends BodyTagSupport {
	private static final long serialVersionUID = 59491232929096995L;

	private int level = 0;
	
	private String vipTag="0";

	public int doStartTag() throws JspException {
		try {
			@SuppressWarnings("unused")
			String ctx = this.pageContext.getServletContext().getContextPath();
			String levelStr="<a class='vip_level1'  target='_blank'  href='"+ctx+"/grow_system.html'><span class='level_txt' style='color:#f00;'>1</span></a>";
			
			StringBuffer sb = new StringBuffer();
			String vipStr="";
		
			
		    int b=(int)  Math.ceil(LevelUtils.getLevel(level/100.0));
		        int level=b/10+1;
		        if(vipTag.equals("1")){	
		        	if(level>10){
		        		vipStr="  <a   target='_blank' href='"+ctx+"/grow_system.html' title='已经开通会员'><img width='26' height='26' src='"+ctx+"/images/vip/level11.gif'/></a> ";
		        	}else{
		        		vipStr="  <a  class='vip_level"+level+"_n' target='_blank' href='"+ctx+"/grow_system.html' title='已经开通会员'><span class='vip_level_txt'>"+b+"</span></a> ";
		        	}
				}else{
					if(level>10){
						levelStr="<a class='vip_level11'  target='_blank' href='"+ctx+"/grow_system.html'><img width='26' height='26' src='"+ctx+"/images/vip/level11.gif'/></a>";sb.append(levelStr);
					}else{
				    	switch (level){
						case 1:levelStr="<a class='vip_level1'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt' style='color:#666;'>"+b+"</span></a>";sb.append(levelStr);break;
						case 2:levelStr="<a class='vip_level2'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#666; font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 3:levelStr="<a class='vip_level3'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#666;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 4:levelStr="<a class='vip_level4'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#fff;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 5:levelStr="<a class='vip_level5'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#fff;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 6:levelStr="<a class='vip_level6'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 7:levelStr="<a class='vip_level7'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 8:levelStr="<a class='vip_level8'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 9:levelStr="<a class='vip_level9'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						case 10:levelStr="<a class='vip_level10'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
						default :levelStr="<a class='vip_level1'  target='_blank' href='"+ctx+"/grow_system.html'><span class='level_txt_1' style='color:#f00;font-size: 6pt'>"+b+"</span></a>";sb.append(levelStr);break;
				    	}
			    	}
				}
		    	sb.append(vipStr);
			
			this.pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	public String getVipTag() {
		return vipTag;
	}

	public void setVipTag(String vipTag) {
		this.vipTag = vipTag.trim();
	}

	
}