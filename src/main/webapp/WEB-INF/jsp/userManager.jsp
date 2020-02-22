<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function(){
	$(".tab").click(function(){
		$(".tab").removeClass("active");
		$("#mainForm").attr("action",this.id).submit();
		$(this).addClass("active");
	});
	$(".active").click();
	//$("#mainForm").submit();
});
</script>

<form action="${ctx }/user/manager/userDetail" method="post" target="contentFrame" name="mainForm" id="mainForm">
</form>
<div class="contentR fr">
    <div class="RTitle"></div>
    <div class="RC">
        <div class="tabbed_box" id="tabbed_box_1">
            <div class="tabbed_area">
                <ul class="tabs doit_bd">
                    <li><a class="tab ${index == 1 ? 'active' : '' }" title="content_1" id="${ctx }/user/manager/userDetail">基本资料</a>
                    </li>
                    <li><a class="tab ${index == 2 ? 'active' : '' }" title="content_2" id="${ctx }/user/2/manager/photoUpload">头像管理</a>
                    </li>
                    <li><a class="tab ${index == 3 ? 'active' : '' }" title="content_3" id="${ctx }/user/manager/bindCard">银行卡绑定</a>
                    </li>
                    <li><a class="tab ${index == 4 ? 'active' : '' }" title="content_4"  id="${ctx }/user/security/center">会员中心</a>
                    </li>
                    <li><a class="tab ${index == 5 ? 'active' : '' }" title="content_5" id="${ctx }/user/manager/resetPassword">修改密码</a>
                    </li>
                     <li><a class="tab ${index == 6 ? 'active' : '' }" title="content_6" id="${ctx }/user/manager/setMessageRule">短信提醒</a>
                    </li>
                </ul>
            </div>
            <div class="content" style="height: 830px;">
                <iframe name="contentFrame" id="contentFrame" style="width: 98%; height:99%; background-color: transparent"
					allowTransparency="true" frameborder="0" scrolling="no"  >
				</iframe>
            </div>
        </div>
    </div>
    <div class="RB"></div>
</div>