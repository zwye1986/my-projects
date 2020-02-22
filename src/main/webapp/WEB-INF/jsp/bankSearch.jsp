<%@page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html>
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#search").click(function(){
		  $.ajax({
		        cache: false,
		        async: false,
		        type: "POST",
		        url: "${ctx}/doSearch.html",
		        success: function(data) {
		        
		        	$('#payForm').html(data.data);
					$('#payForm form').submit();
					
					
		        }
		    });
	});
});
</script>
<div style="display: none" id="payForm"> </div>
<TABLE cellSpacing=0 cellPadding=0 width=790 align=center border=0>
  <TBODY>
    <TR> 
      <TD vAlign=top> <FORM method="post" action="${ctx }/doSearch.html">
          <br>
          <table width="481" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#000000">
            <tr> 
              <td bgcolor="#FFFFFF">¶©µ¥²éÑ¯</td>
            </tr>
            <tr> 
              <td bgcolor="#FFFFFF"> <TABLE class=waikuan cellSpacing=0 cellPadding=0 
                        width=481 align=center border=0>
                  <TBODY>
                    <TR> 
                      <TD width="479" vAlign=top> <TABLE cellSpacing=0 cellPadding=0 width="100%" 
                              border=0>
                          <TBODY>
                            <TR bgColor=#f1f8fd> 
                              <TD class=landr width="30%" height=30>&nbsp;&raquo; 
                                <FONT color=#666666>¶©µ¥ºÅ£º</FONT></TD>
                              <TD height=30>&nbsp; <INPUT size=30 name=v_oid value=""> 
                                <FONT 
                                color=#666666>&nbsp;</FONT> </TD>
                            </TR>

                           
                            <TR align=middle bgColor=#f1f8fd> 
                              <TD colSpan=2 height=40> <INPUT type=button value=search id="search" name=Submit2> 
                               
                              </TD>
                            </TR>
                          </TBODY>
                        </TABLE></TD>
                    </TR>
                  </TBODY>
                </TABLE></td>
            </tr>
          </table>
        </FORM></TD>
    </TR>
  </TBODY>
</TABLE>
</html>