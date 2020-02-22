<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	 color:#5c5c5c;
}
em {
	font-style: normal;
	color: #fff;
}
.txt {
	color: #fff;
}
.STYLE1 {
	color: #fff;
	font-size: 16px;
}
.STYLE6 {
	color: #000000;
	font-size: 12;
}
.STYLE10 {
	color: #000000;
	font-size: 12px;
}
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
	position:relative;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.id,.phone,.user{
	width:45%;
	height:22px;
	

}
.date{height:22px; width:15%;}
.money{ height:22px;  width:15%;}
#btn {
	width: 86px;
	height: 31px;
	background: url(images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
.cx{ width:59px; height:22px; background:url(images/cx_btn.png) no-repeat; cursor:pointer; border:none; color:#fff;}
a.jsxg{ width:15px; text-align:center; cursor:pointer; color:#0cfec2; margin-left:5px;}
.selected td{ background:#e1f6f1;}
.tck{ width:363px; height:268px; position:absolute; background:#fff; right:180px; border:1px solid #cacaca; z-index:2; top:265px;}
.tck .tck_t{width:363px; height:40px; background:#3b3a3f; margin-top:0px; float:left; }
.tck_xx{ width:120px; color:#fff; height:40px; line-height:40px;font-size:16px; float:left;}
.del{ float:right; height:40px; width:40px; background:url(images/del.png); cursor:pointer;}
.tck form{ width:343px; text-align:left; color:#3b3a3f; font-size:12px;}
.tck_list{width:343px; line-height:18px; }
.tck_la{ width:80px; text-align:right; float:left; height:18px; line-height:18px; margin-right:5px;}

.czmm{ cursor:pointer;}
.czmm:hover{ text-decoration:underline;}
-->
</style>
</head>

<body style="padding:25px;">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      
      
        <tr>
          <td height="62" bgcolor="#3b3a3f" style="border-bottom:1px solid  #d5d1c8;border-top:1px solid  #d5d1c8; padding:0 20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="39"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  </table></td>
                <td><label class="txt">用户ID：</label>
                  <input class="id" type="text" name="text"></td>
                  <td><label class="txt">手机号码：</label>
                  <input class="phone" type="text" name="text"></td>
                   <td><label class="txt">用户名：</label>
                  <input class="user" type="text" name="text"></td>
                <td width="215"><div align="center" style="height:32px; line-height:32px;"><span class="STYLE1"> <em>创建时间：</em>
                    <input type="text" name="text" class="date" />－<input type="text" name="text"  class="date" />
                    </span></div></td>
                <td width="215"><div align="center" style="height:32px; line-height:32px;"><span class="STYLE1"> <em>余额：</em>
                    <input type="text" name="text" class="money" />－<input type="text" name="text" class="money" />
                    </span></div></td>
                    <td><input type="button" value="查询" class="cx"/></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e2e2e2" onmouseover="changeto()"  onmouseout="changeback()">
        <tr >
          <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">
              序号
            </div></td>
          <td width="15%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">用户ID</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">姓名</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">手机号码</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">密码</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">当前金额</span></div></td>
               <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">积分</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
             1
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr class="selected">
          <td height="40" bgcolor="#FFFFFF" ><div align="center">
             2
            </div></td>
         <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
        
          <td height="40" bgcolor="#FFFFFF"><div align="center">
             3
            </div></td>
         <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>><div class="tck">
            <p class="tck_t"><label class="tck_xx">详细内容</label>  <em class="del"></em></p>
            <form>
            <p class="tck_list"><label class="tck_la">用户ID:</label>SEHTYKL-FDSHSTJ-R-MFHKF2365</p>
             <p class="tck_list"><label class="tck_la"> 姓名:</label>思宇</p>
              <p class="tck_list"><label class="tck_la">手机号码:</label>13456789900</p>
               <p class="tck_list"><label class="tck_la">用户名:</label>不惜不悲</p>
                <p class="tck_list"><label class="tck_la">当前金额:</label>10000元</p>
                 <p class="tck_list"><label class="tck_la">积分:</label>2222积分</p>
                  <p class="tck_list"><label class="tck_la">创建时间:</label>2012-11－23 12:34:00</p>
            </form>
            </div></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
           4
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
            5
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
              6
            </div></td>
         <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
              7
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
              8
            </div></td>
         <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
  
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
              9
            </div></td>
         <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">admin</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">系统管理员</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">13913612548</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm">重置密码</a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">SARA</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >1000元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">200</div></td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-10－15 15:09:25</div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">角色修改<a  class="jsxg"style="color:#00ce9b;">>></a></div></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="50" style="background:#fafafb; border:1px solid #e5e6e6;border-top:none; padding-left:20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          <td width="25%"><div align="left"><span style="color:#959595;" >共有<strong> 243</strong> 条记录，当前第<strong> 1</strong> 页，共 <strong>10</strong> 页</span></div></td>
          <td width="75%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td width="49"><div align="center" style="color:#959595;cursor:pointer;">首页</div></td>
                <td width="49"><div align="center" style="color:#959595;cursor:pointer;">上一页</div></td>
                <td width="49"><div align="center" style="color:#4391ba; cursor:pointer;">下一页</div></td>
                <td width="49"><div align="center" style="color:#959595;cursor:pointer;">尾页</div></td>
                <td width="37" ><div align="center" style="color:#959595;">转到</div></td>
                <td width="22"><div align="center">
                    <input type="text" name="textfield" id="textfield"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
                  </div></td>
                <td width="22"><div align="center" style="color:#fff;">页</div></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>
</body>
</html>
