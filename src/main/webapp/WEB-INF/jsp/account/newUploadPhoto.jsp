<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!-- uploadify -->
<link rel="stylesheet" type="text/css"
      href="${ctx}/js/upload/uploadify.css"/>

<script type="text/javascript"
        src="${ctx}/js/upload/jquery.uploadify.min.js"></script>
<!-- Jcrop -->
<link rel="stylesheet" href="${ctx}/css/Jcrop/jquery.Jcrop.css"
      type="text/css" />
<script type="text/javascript" src="${ctx}/js/upload/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/Jcrop/jquery.Jcrop.js"></script>
<style type="text/css">
    .self_class {
        color:#FFFFFF;
        background: #00a7f5;
        cursor: pointer;
    }
    #local{
        margin-left :240px;
    }

    #local-queue{
        position: absolute;
        z-index: 999;
    }

    object {
        right: 0px;
    }

    .local_pic01 a{
        width:100%;
        height:100%;
        /*保证a总是在img之上*/
        position:relative;
        z-index:998;
    }
    .local_pic01,.local_pic01 a{
        overflow:hidden;
        display:block;
    }

    .local_pic01{
        width: 50%;
        float: left;
        margin-left: 180px
    }
    /*头像上传*/
    .avatar-230{ width:62%; float:left;text-align:center; margin:20px 0;}
    .avatar-230 dl{ width:100%;text-align:center;}
    .avatar-230 dt{ width:100%; text-align:center; height:36px; line-height:36px; }
    .avatar-230 dt a{ font-size:13px; font-weight:200; text-decoration:underline; color:#366792; font-weight:bold; }
    .avatar-230 dd a{ border:3px solid #dce1e3; border-radius:3px; width:230px; height:230px; display:inline-block;}
    .avatar-230 dd a img{ }
    .avatar-115{width:25%; float:left;text-align:center; margin:20px 0; margin-top:110px;}
    .avatar-115 dd a{ width:115px; height:115px; display:inline-block;}
    .avatar-115 dd a img{ }
    .avatar-btn{ margin:0 auto; margin-left:85px;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $("#local")
                .uploadify(
                {
                    'multi' : false,//不允许用户添加多个文件到上传队列。
                    'buttonClass' : 'self_class',
                    'buttonText' : '选择图片',
                    'swf' : '${ctx}/js/upload/uploadify.swf?v='
                            + Math.random(),
                    'uploader' : '${ctx}/user/manager/dealUpload;jsessionid='
                            + $("#sessionUID").val()
                            + '?method=upload',
                    'auto' : true,
                    'fileSizeLimit' : '60720KB',
                    'fileTypeExts' : ' *.jpg; *.png;*.gif',
                    'onUploadSuccess' : function(file,
                                                 data, response) {
                        var msg = $.parseJSON(data);
                        if (msg.resultCode == 0) {
                            alert(msg.resultMsg);
                            return;
                        }
                        $("#target").attr("src","${ctx}/getTmpImg?fileName="+msg.resultMsg);
                        $("div .jcrop-holder img").attr("src", "${ctx}/getTmpImg?fileName="+msg.resultMsg);
                        $("#preview").attr("src","${ctx}/getTmpImg?fileName="+msg.resultMsg);
                        $('#target').Jcrop({
                                    setSelect : [ 0, 0, 200, 200 ],
                                    onChange : updatePreview,
                                    onSelect : updateCoords,
                                    allowResize:true,
                                    aspectRatio:1,
                                    allowSelect:false,
                                    maxSize : [229,229],
                                    bgOpacity : 0.45,
                                    minSize : [200,200],
                                    /*  boxWidth : 315,
                                     boxHeight : 315, */
                                    aspectRatio : 1
                                },
                                function() {
                                    // Use the API to get the real image size
                                    var bounds = this.getBounds();
                                    boundx = bounds[0];
                                    boundy = bounds[1];
                                    // Store the API in the jcrop_api variable
                                    jcrop_api = this;
                                });
                    }
                });
        //头像裁剪
        var jcrop_api, boundx, boundy;

        function updateCoords(c)
        {
            $('#x').val(c.x);
            $('#y').val(c.y);
            $('#w').val(c.w);
            $('#h').val(c.h);
        };
        function checkCoords()
        {
            if (parseInt($('#w').val())) return true;
            alert('请选择图片上合适的区域');
            return false;
        };
        function updatePreview(c){
            if (parseInt(c.w) > 0){
                var rx = 115 / c.w;
                var ry = 115 / c.h;
                $('#preview').css({
                    width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
            }

        };

        $("#save").click(function(){
            var x = $("#x").val();
            var y = $("#y").val();
            var w = $("#w").val();
            var h = $("#h").val();
            if( checkCoords() ){
                $.post("${ctx}/user/manager/cutImag", {x:x,y:y,w:w,h:h}, function(data) {
                    if(data.resultCode==1){
                        alert(data.resultMsg);
                        window.location.href = "${ctx }/user/manager/upload";
                        return;
                    }else{
                        alert(data.resultMsg);
                        return;
                    }
                });
            }
        });
    });
</script>
<div id="main">
    <div class="model-box">
        <!--选项卡开始-->
        <div class="tab_box">
            <div class="lyz_tab_left">
                <div class="pro_con111">
                    <ul>
                        <li onclick="window.location.href='${ctx}/user/manager/userDetail'">基本资料</li>
                        <li onclick="window.location.href='${ctx}/user/manager/bindCard'">银行卡绑定</li>
                        <li onclick="window.location.href='${ctx}/user/manager/resetPassword'">修改密码</li>
                        <li onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
                        <li onclick="window.location.href='${ctx}/user/manager/upload'"  class="hover">上传头像</li>
                        <li onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
                    </ul>
                </div>
            </div>
            <div class="lyz_tab_right">
            </div>
            <div class="hover" id="con_one_5" style="display: block;">
                <div class="model-box r3">

                    <ul class="avatar">
                        <li class="avatar-230">
                            <dl>
                                <dt>
                                    <a href="###" id="local">上传头像</a>
                                    <input id='sessionUID' value='<%=session.getId()%>' type="hidden" />
                                </dt>
                                <dd>
        				<span class="local_pic01">
        					<a class="pic"><img id="target"  src="${ctx }/img/common/mrtx01.jpg"></a>
        				</span>
                                </dd>
                            </dl>
                        </li>
                        <li class="avatar-115">
                            <dl>
                                <dt></dt>
                                <dd>
                                    <div style="overflow: hidden;width: 115px;height: 115px;border: 3px solid #DCE1E3; border-radius: 3px;margin-left:50px;margin-top:10px;">
                                        <a class="pic"><img id="preview" src="${ctx }/img/common/mrtx02.jpg"></a>
                                    </div>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                    <div class="bd-button avatar-btn" >
                        <input type="hidden" id="x" name="x" />
                        <input type="hidden" id="y" name="y" />
                        <input type="hidden" id="w" name="w" />
                        <input type="hidden" id="h" name="h" />
                        <input type="button" name="保存" value="保存" style="width:113px; height:38px;line-height:38px;margin-left:-430px; margin-top:310px;;" id="save"/>
                    </div>
                </div>

            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--选项卡结束-->
</div>