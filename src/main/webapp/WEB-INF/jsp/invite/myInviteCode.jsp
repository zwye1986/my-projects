<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description"
	content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。" />
<meta name="keywords"
	content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<script type="text/javascript" src="${ctx }/js/ZeroClipboard.js"></script>
<script type="text/javascript">
	function copyToClipboard(id, buttonid) {
		//引入 Zero Clipboard flash文件   
		var txt = document.getElementById(id).defaultValue;
		ZeroClipboard.setMoviePath("${ctx }/js/ZeroClipboard.swf");
		//新建对象   
		clip = new ZeroClipboard.Client();
		//设置指向光标为手型   
		clip.setHandCursor(true);
		//通过传入的参数设置剪贴板内容   
		clip.setText(txt);
		//添加监听器，完成点击复制后弹出警告   
		clip.addEventListener("complete", function(client, text) {
			alert("已复制好，可贴粘。");
		});
		//绑定触发对象按钮ID   
		clip.glue(buttonid);
	}
</script>
</head>

<!--main-->
	<div class="invite_top">
		<div class="invite_top_l">
			<img width="605" height="270"
				src="${ctx}/showActivityPic?id=1ab3f5c5-23c1-406c-b045-a6bd35176363" />
		</div>
		<div class="invite_top_r">
			<h2 style="margin-top: 100px;">我的邀请号</h2>
			<h2>
				<em class="txt-2"> <c:out
						value="${sessionScope.inviteCodeSelf}" /></em>
			</h2>
		</div>
	</div>
	<div class="model-box">
		<div class="invite_way">
			<div class="w1">
				<div class="icon"></div>
				<div class="link">
					<h4>通过QQ、旺旺等邀请好友</h4>
					<p>
						<input type="text" id="recom_url1" class="ls2" readonly=""
							value="http://www.wowpower.cn/index.html?inviteCode=<c:out value="${sessionScope.inviteCodeSelf}"/>">
					</p>
					<div class="copy-button">
						<input type="button" name="复制" value="复制" id="b_clip_button1"
							onClick="copyToClipboard('recom_url1','b_clip_button1');">
					</div>
					<p></p>
				</div>
			</div>
			<div class="w2">
				<div class="icon"></div>
				<div class="link">
					<h4>在自己常去的论坛上挂签名</h4>
					<p>
						<textarea id="recom_url2" class="ls2" readonly="" type="text">[url=http://www.wowpower.cn/index.html?inviteCode=<c:out
								value="${sessionScope.inviteCodeSelf}" />]蛙宝网，轻轻松松拿返利[/url]</textarea>

					</p>
					<div class="copy-button">
						<input type="button" name="复制" value="复制" id="b_clip_button2"
							onClick="copyToClipboard('recom_url2','b_clip_button2');">
					</div>
					<p></p>

				</div>
			</div>
			<div class="w3">
				<div class="icon"></div>
				<div class="link">
					<h4>在自己的博客上加友情链接或任何支持HTML的地方</h4>
					<p>
						<input id="recom_url3" class="ls2" readonly type="text"
							value="<a href='http://www.wowpower.cn/index.html?inviteCode=<c:out value="${sessionScope.inviteCodeSelf}"/>'>蛙宝网，轻轻松松拿返利</a>" />
					</p>
					<div class="copy-button">
						<input type="button" name="复制" value="复制" id="b_clip_button3"
							onClick="copyToClipboard('recom_url3','b_clip_button3');">
					</div>
					<p></p>
				</div>
			</div>
			<%-- <div class="w4">
				<div class="icon"></div>
				<div class="link">
					<h4>在QQ空间、新浪微博上分享</h4>
					<div class="share">
						<!-- Baidu Button BEGIN -->
						<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"
							data="{'url':'http://www.wowpower.cn/index.html?inviteCode=<c:out value="${sessionScope.inviteCodeSelf}"/>'}">
							<a class="bds_tsina"></a> <a class="bds_qzone"></a> <a
								class="bds_tqq"></a> <a class="bds_renren"></a> <a
								class="bds_t163"></a> <a class="bds_sqq"></a> <a
								class="bds_baidu"></a> <a class="bds_tsohu"></a> <a
								class="bds_tqf"></a> <span class="bds_more"></span> <a
								class="shareCount"></a>
						</div>
						<script type="text/javascript" id="bdshare_js"
							data="{'url':'http://www.wowpower.cn/index.html?inviteCode=<c:out value="${sessionScope.inviteCodeSelf}"/>'}"></script>
						<script type="text/javascript" id="bdshell_js"></script>
						<script type="text/javascript">
							document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion="
									+ Math.ceil(new Date() / 3600000)
						</script>
						<!-- Baidu Button END -->
					</div>
				</div>
			</div> --%>
			<div class="w5">
				<div class="icon"></div>
				<div class="link">
					<h4>在QQ空间或者其他博客网站上发文章</h4>
					<div class="content">
						<p>您可以在自己的QQ空间、人人网、豆瓣网或其它地方，推荐您的朋友加入蛙宝网，一起分享网赚乐趣！</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="bid-info  model-box-c">
		<h2>注意事项：</h2>
		<ul class="items">
			<li>1、不要为了获得小小的邀请奖励而失掉了自己的诚信，我们会人工核查，对于查实的 <em class="txt-2">作弊行为</em>，我们将收回该帐号全部的邀请奖励，严重者将冻结所有收入并永久封号；
			</li>
			<li>2、当好友通过您的邀请链接访问蛙宝网后，只要TA在1天内注册，均为有效；</li>
			<li><em class="txt-2">*作弊行为：包括但不限于使用相同的电脑、相同的IP地址在同一天内注册多个帐号，以骗取邀请奖励的行为。</em></li>

		</ul>
	</div>
