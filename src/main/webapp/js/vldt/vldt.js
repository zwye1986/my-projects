var jsPath_session = "../../js";

var basePath="";

/**
 *默认字符个数
 */
var length=20;

/**
 *默认最大数字
 */
var max=9999;


/**
 *当前提示信息下标
 */
var currentMsgIndex=-1;

/**
 *默认提示信息
 */
var arrMsg = new Array( );
arrMsg[0] = $common_contentCannotNull;
arrMsg[1] = $common_StringTooLong;
arrMsg[2] = $common_stringTooLong2;
arrMsg[3] = $common_contentOnlyChinese;
arrMsg[4] = $common_contentOnlyEnglish;
arrMsg[5] = $common_contentOnlyInteger;
arrMsg[6] = $common_phoneNumberFormatError;
arrMsg[7] = $common_contentContantIlligalChar;
arrMsg[8] = $common_IPAdressError;
arrMsg[9] = $common_dateFormatEorror;
arrMsg[10] = $common_zipCodeError;
arrMsg[11] = $common_emailFormatError;
arrMsg[12] = $common_identifyFormatError;
arrMsg[13] = $common_contentOnlyNumber;
arrMsg[14] = $common_avoirdupoisError;
arrMsg[15] = $common_statureError;
arrMsg[16] = $common_ageError;

arrMsg[17] = $common_inputCorrectDateFormat;
arrMsg[18] = $common_inputCorrectDateFormat2;
arrMsg[19] = $common_endTimeMustBeGTStartTime;
arrMsg[20] = $common_endTimeMustBeEQStartTimeAtLeast;

arrMsg[21] = $common_contentOnlyNumber;

arrMsg[22] = $common_trimBlank;
arrMsg[23] = $common_callCustomValidFunction;

arrMsg[24] = $common_shoeSizeCannotGT50;

//临时提示信息
arrMsg[25]="";

arrMsg[26]=$common_inputCorrectDateFormat;

arrMsg[27]=$common_contentCannotLittleThanCurrentDate;

arrMsg[28]=$common_numberSizeCannotGT20;

//验证账号或编号
arrMsg[29]=$common_contentOnlyNumberOrWord;

//验证URL
arrMsg[30]=$common_UrlMustBeStartWith;

//自定义非法字符验证
arrMsg[31]=$common_contentcontainIllegalChar;

//附件验证
arrMsg[32]=$common_getFileFailed;

arrMsg[33]=$common_priceOnlyNumberAndPoint;
//校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”   
arrMsg[34]=$common_telOrFaxError;
//选择的时间不能大于当前系统时间;
arrMsg[171]=$common_timeYourSelectedCannotGTCurrentTime;

var monthdays = new Array(12);
monthdays[1]=31;
monthdays[2]=28;
monthdays[3]=31;
monthdays[4]=30;
monthdays[5]=31;
monthdays[6]=30;
monthdays[7]=31;
monthdays[8]=31;
monthdays[9]=30;
monthdays[10]=31;
monthdays[11]=30;
monthdays[12]=31;
	


/**
vt:验证方式
1 及时验证
2 提交表单时验证

ct:提示方式
1 bgColor 以背景颜色方式提醒
2 asterisk 以高亮的星号提醒
3 tailMsg  在尾部显示提示信息
4 pop      弹出提示信息

vm:提示信息

lth:指定长度，不指定默认为20
min:类型为数字时指定最小值，不指定默认为0
max:类型为数字时指定最大值，不指定默认为9999

vr:(validate rule)验证规则
0 不能为空
1 字符长度小于指定值 (注:需要指定lth属性)
2 字节长度小于指定值 (注:需要指定lth属性)
3 只能为汉字
4 只能为字母
5 验证一个数字是否在指定范围之内 (注:需要指定min属性和max属性)
6 手机号码
7 非法字符
8 IP
9 验证日期
10 验证邮编
11 验证邮箱
12 验证身份证
13 只能为数字
14 验证体重
15 验证身高
16 验证年龄
99 {}正则表达式
*/
function vadty(base,needSubmit){
	if(needSubmit ==undefined) needSubmit='Y';
    basePath=base;
    var forms=document.forms;
    for(var i=0;i<forms.length;i++){
	        forms[i].onsubmit=function(){
		        var flag = checkForm(this);
		        if(needSubmit=='Y' && flag)
	        	{
	        		//this.submit();
	        	}
		        return flag;
	        };
        
       		forms[i].onreset=function(){
             for(i=0;i<showMsgObj.length;i++){
             	  //modfiy by zhao
	    		  //修改原因：结束时间的提示出现，但是框变灰，无论如何重置都无法回到原来
                  //showMsgObj[i].inputObj.style.background="";
                  showMsgObj[i].spanObj.style.visibility="hidden";
             }
        };
    }
}

//异步验证返回的结果
var checkMsg=new Array();

function checkForm(formObj){
	var flag = true;
	for(var k=0;k<formObj.length;k++){
	    var inptObj=formObj[k];
		
		//过滤验证项
		if(typeof inptObj.getAttribute("vr")=="string" || typeof inptObj.getAttribute("ac")=="string"){
		     inptObj.value=vTrim(inptObj.value);
		     if(!inptObj.id){
		         alert($common_vldtTips1+inptObj.outerHTML);
		     }
		     if(inptObj.style.display !='none')
		     {
			     //异步验证
			     if(typeof inptObj.ac=="string"){
			          var oldValue="";
			          var tableName="";
			          var column="";
			          var column1="";
			          var value1="";
			          var isUpdate=false;
			          inptObj.value=vTrim(inptObj.value);
			          var checkUrl=vTrim(inptObj.ac);
			          
			          if(/^([\w\d]+),([\w\d/]+),?$/.test(checkUrl)){
			               tableName=RegExp.$1;
			               column=RegExp.$2;
			          }else if(/^([\w\d]+),([\w\d/]+),(.+)$/.test(checkUrl)){
			               isUpdate=true;
			               tableName=RegExp.$1;
			               column=RegExp.$2;
			               oldValue=RegExp.$3;
			               var v = checkUrl.split(",");
			               if(v.length>=5){
			                  oldValue= v[2];
			               	  column1 = v[3];
			                  value1  = v[4];
			               }
			          }else{
			              alert($common_vldtTips2+inptObj.outerHTML);
			              return false;
			          }
			          if(inptObj.value=="")
			          {
			          	tsf(true,inptObj);
			          }
			          if(oldValue==inptObj.value){
				          checkMsg[inptObj.id]="SUCCESS";
			          }else{
			                jQuery.ajax({
							   type: "POST",async: false,cache: false,
							   url: "../main/checkExist.action",
							   data: "t="+tableName+"&c="+column+"&v="+inptObj.value+"&c1="+column1+"&v1="+value1+"&flag=1",
							   complete: function(msg){
									if(msg.responseText=="SUCCESS"){
										tsf(true,inptObj);
										checkMsg[inptObj.id]="SUCCESS";
						            }else{
						            	tsf(false,inptObj,$common_vldtTips3);
						                checkMsg[inptObj.id]=$common_vldtTips3;
						                flag = false;
						            }
							   }
							}); 
			          }
			       //alert("异步结果:"+flag);   
				 }
				 
				 if(typeof inptObj.getAttribute("vr")=="string")//存在规则验证
			     {
			     	var f = qiCheck(inptObj,true);
				     //alert("规则结果:"+f);   
				     flag = flag && f;
			     }
		     }
		}
	}
	//alert("最终结果:"+flag);  
	return flag;
}

/*基本验证规则*/
function qiCheck(obj,checkAC){
     //alert('规则验证:'+obj.outerHTML);
    var vr="";
    if(obj.getAttribute("vr")){
        vr=obj.getAttribute("vr");
    }
    var arr=vr.split("&&");
    var result=true;
    for(j=0;j<arr.length;j++){
        
        //大括号括起来的表示正则表达式
		if(/^{(.*)}$/.test(arr[j])){
		    var reg=new RegExp(RegExp.$1);
		    var flag=true;
		    if(obj.value!=""){
		       flag= reg.test(obj.value);
		    }
		    if(flag==false){result=false;}
		    tsf(flag,obj,getMsg(j,obj,99));
		}else{
			var str=obj.value;
		    //验证结果
		    var flag=false;
		    switch (parseInt(arr[j])) {
	            case 0: //为空验证
	            	if(obj.type == 'checkbox'){
	 					var eles=document.getElementsByName(obj.name); 
						var j=0;
						for (var i=0;i<eles.length;i++){
						  if(eles[i].checked) j++;
						}
						if (j==0){
							tsf(false,obj,getMsg(j,obj,0));
							result=false;
						 	break;
						}
	 				}else{
	 				 	flag=checkEmpty(obj);
		                tsf(flag,obj,getMsg(j,obj,0));
		                if(flag==false){result=false;}
		                break;
	 				}
	            case 1: //字符长度验证
	                flag=checkCharLTH(obj);
	                tsf(flag,obj,getMsg(j,obj,1));
	                if(flag==false){result=false;}
	                break;
	            case 2: //字节长度验证
	                flag=checkByteLTH(obj);
	                tsf(flag,obj,getMsg(j,obj,2));
	                if(flag==false){result=false;}
	                break;
	            case 3: //验证是否全为汉字
	                flag=isChinese(obj);
	                tsf(flag,obj,getMsg(j,obj,3));
	                if(flag==false){result=false;}
	                break;
	            case 4: //验证是否全为字母
	                flag=isLetter(obj);
	                tsf(flag,obj,getMsg(j,obj,4));
	                if(flag==false){result=false;}
	                break;
	            case 5: //验证数字是否在指定范围
	                flag=checkNumberVldt(obj);
	                tsf(flag,obj,getMsg(j,obj,5));
	                if(flag==false){result=false;}
	                break;
	            case 6: //手机号码验证
	                flag=checkMobile(obj);
	                tsf(flag,obj,getMsg(j,obj,6));
	                if(flag==false){result=false;}
	                break;
	            case 7: //非法字符验证
	                flag=checkVLD(obj);
	                tsf(flag,obj,getMsg(j,obj,7));
	                if(flag==false){result=false;}
		                break;
	            case 8: //IP验证
	                flag=checkIP(obj);
	                tsf(flag,obj,getMsg(j,obj,8));
	                if(flag==false){result=false;}
	                break;
	            case 9: //日期验证
	                flag=checkDate(obj);
	                tsf(flag,obj,getMsg(j,obj,9));
	                if(flag==false){result=false;}
	                break;
	            case 10: //邮编验证
	           
	                flag=checkPostalcode(obj);
	                tsf(flag,obj,getMsg(j,obj,10));
	                if(flag==false){result=false;}
	                break;
	            case 11: //邮箱验证
	                flag=checkEmail(obj);
	                tsf(flag,obj,getMsg(j,obj,11));
	                if(flag==false){result=false;}
	                break;
	            case 12: //身份证验证
	                flag=checkID(obj);
	                tsf(flag,obj,getMsg(j,obj,12));
	                if(flag==false){result=false;}
	                break;
	            case 13: //只能为数字
	                flag=checkMustNum(obj);
	                tsf(flag,obj,getMsg(j,obj,13));
	                if(flag==false){result=false;}
	                break;
	            case 14: //体重验证
	                flag=checkAvoirdupois(obj);
	                tsf(flag,obj,getMsg(j,obj,14));
	                if(flag==false){result=false;}
	                break;
	            case 15: //验证身高
	                flag=checkStature(obj);
	                tsf(flag,obj,getMsg(j,obj,15));
	                if(flag==false){result=false;}
	                break;
	            case 16: //验证身高
	                flag=checkAge(obj);
	                tsf(flag,obj,getMsg(j,obj,16));
	                if(flag==false){result=false;}
	                break;
                case 17: //验证日期 "yyyy-mm-dd"
	                flag=checkDate(obj,17);
	                tsf(flag,obj,getMsg(j,obj,17));
	                if(flag==false){result=false;}
	                break;
	            case 18: //验证日期 "yyyy-mm-dd hh:mm:ss"
	                flag=checkDate(obj,18);
	                tsf(flag,obj,getMsg(j,obj,18));
	                if(flag==false){result=false;}
	                break;
	            case 21: //验证只能为数字
	                flag=checkOnlyNum(obj);
	                tsf(flag,obj,getMsg(j,obj,21));
	                if(flag==false){result=false;}
	                break;
	            case 22: //仅用作去空格
	                vObjTrim(obj);
	                result=true;
	                break;
	            case 23: //调用自定义验证方法
	                flag=callSelfCheck(obj);
	                if(flag==false){result=false;}
	                break;
	            case 24: //鞋码只能为30-50码的数字
	                flag=checkShoeSize(obj);
	                tsf(flag,obj,getMsg(j,obj,24));
	                if(flag==false){result=false;}
	                break;
	           case 26: //验证日期 "yyyy-mm-dd hh:mm"
	                flag=checkDate(obj,26);
	                tsf(flag,obj,getMsg(j,obj,26));
	                if(flag==false){result=false;}
	                break;
	           case 27: //内容不能小于当前时间
	                flag=checkDate2(obj);
	                tsf(flag,obj,getMsg(j,obj,27));
	                if(flag==false){result=false;}
	                break;
	           case 28://数字的长度不能大于20
	                flag=checkNumberLen(obj);
	                tsf(flag,obj,getMsg(j,obj,28));
	                if(flag==false){result=false;}
	                break;
	           case 29://验证staffId
	                flag=checkStaffId(obj);
	                tsf(flag,obj,getMsg(j,obj,29));
	                if(flag==false){result=false;}
	                break;
	           case 30://验证URL
	                flag=checkURL(obj);
	                tsf(flag,obj,getMsg(j,obj,30));
	                if(flag==false){result=false;}
	                break;
	           case 31://自定义非法字符验证
	                flag=checkLawless(obj);
	                tsf(flag,obj,getMsg(j,obj,31));
	                if(flag==false){result=false;}
	                break;
	           case 32://自定义非法字符验证
	                flag=checkVLDTFile(obj);
	                tsf(flag,obj,getMsg(j,obj,32));
	                if(flag==false){result=false;}
	                break;
	           case 33://价格验证
	           		flag=checkPrice(obj);
	                tsf(flag,obj,getMsg(j,obj,33));
	                if(flag==false){result=false;}
	                break;  
	           case 34://验证固定电话或传真号码
	           		flag=checkTelOrFax(obj); 
	           		tsf(flag,obj,getMsg(j,obj,34));
	           		if(flag==false){result=false;}
	           		break;
	           case 171: //验证日期 "yyyy-mm-dd"
	                flag=checkDate(obj,171);
	                tsf(flag,obj,getMsg(j,obj,171));
	                if(flag==false){result=false;}
	                break;
            }
		}
	    if(result==false){
            return false;
        }
    }
    if(checkAC && obj.ac){
        if("SUCCESS"==checkMsg[obj.id]){
            tsf(true,obj,checkMsg[obj.id]);
            result=true;
        }else{
            tsf(false,obj,checkMsg[obj.id]);
            result=false;
        }
        checkMsg[obj.id]=undefined;
    }
    
    return result;
}


/**
 * 方法调用
 * @param vldRst 验证结果
 * @param obj 被验证的对象
 */
function tsf(vldRst,obj,msg){
	if(vldRst){
		hideMsg(obj);
    }else{
        showMsg(obj,msg);
        checkMsg[obj.id]=undefined;
    }
}

/**
 * 获取提示信息
 * @param index 所在验证规则位置
 * @param obj 被验证的对象
 * @param dindex 默认提示信息数组下标
 */
function getMsg(index,obj,dindex){
	var msg="";
	if(currentMsgIndex>-1){
	    msg=arrMsg[currentMsgIndex];
	    currentMsgIndex=-1;
	    return msg;
	}
	switch (dindex) {
	    case 1: //字符长度验证
	         //字符个数应在#以内
	         msg=arrMsg[1].split("#")[0]+getlth(obj)+arrMsg[1].split("#")[1];
	         break;
	    case 2: //字节长度验证
	         //字符个数应在#汉字或#英文以内
	         msg=arrMsg[2].split("#")[0]+((getlth(obj)-getlth(obj)%3)/3)+arrMsg[2].split("#")[1]+getlth(obj)+arrMsg[2].split("#")[2];
	         break;
	    case 5: //验证数字是否在指定范围
	         //数字必须在#之间
	         msg=arrMsg[5].split("#")[0]+getMin(obj)+"-"+getMax(obj)+arrMsg[5].split("#")[1];
	         break;
	    case 28://验证数字的长度不能大于20
	         msg=arrMsg[28];
	         break;
	    default: //验证身高
	         msg=arrMsg[dindex];
	         break;
	}

	if(undefined!=obj.vm && ""!=obj.vm){
		 var arrVms=obj.vm.split("&&");
		 if(obj.vr.split("&&").length==arrVms.length){
		 	 msg=arrVms[index];
		 }else{
		 	 var arr=new Array();
		 	 for(j=0;j<arrVms.length;j++){
		 	 	 if(((index+1)+"=")==arrVms[j].substr(0,2)){
		 	 	 	 msg=arrVms[j].substr(2);
		 	 	 }
		 	 }
		 }
	}

	try{
	    var tempmsg=eval(msg);
	    if(tempmsg!=undefined){
	        msg=tempmsg;
	    }
	}catch(e){}
	
	return msg;
}

var showMsgObj=new Array();
/**
 * 显示提示信息
 * obj 要验证的输入框
 * msg 提示信息
 */
function showMsg(obj,msg){
    try{
	    var spanid="span_"+obj.id;
	    var oPopup = window.createPopup();
	    var msgSpan=document.getElementById(spanid);
	    if(msgSpan==null){
	    	 msgSpan=document.createElement("span");
	    	 msgSpan.id=spanid;
	    	 msgSpan.name="showMsgSpan";
	    	 var vlpn=getnfptd(obj).parentNode;
		    // vlpn.children[vlpn.children.length-1].insertAdjacentElement("afterEnd",msgSpan);
	    	 vlpn.appendChild(msgSpan);
			 msgSpan.style.width="16px";
			 msgSpan.style.height="16px";
			 msgSpan.style.left=(objLoc(msgSpan).left+2)+"px";
			 msgSpan.style.top=(objLoc(msgSpan).top-5)+document.documentElement.scrollTop+"px";
			 msgSpan.className="mustWrite";
	    	// msgSpan.style.position ="absolute";
	    	 msgSpan.style.display="inline-block";
	    	 msgSpan.style.background="url("+jsPath_session+ "/vldt/img/exclamation.gif) 0 0 no-repeat";
	    	 msgSpan.onmouseover=function(){
	    	    var oPopBody = oPopup.document.body;
			    oPopBody.innerHTML = getMsgBox(msg);
	    	    oPopup.show((objLoc(msgSpan).left+16), (objLoc(msgSpan).top+16), 180, 50, document.body);
	    	    oPopup.show((objLoc(msgSpan).left+16), (objLoc(msgSpan).top+16), oPopBody.scrollWidth, oPopBody.scrollHeight, document.body);
	    	 }
	    	 
	    	 msgSpan.onmouseout=function(){
		    	oPopup.hide();
	    	 }
	    	 
	    	 showMsgObj[showMsgObj.length]={"inputObj":obj,"spanObj":msgSpan};
	    }else{
	        msgSpan.onmouseover=function(){
	    	    var oPopBody = oPopup.document.body;
			    oPopBody.innerHTML = getMsgBox(msg);
	    	    oPopup.show((objLoc(msgSpan).left-2), (objLoc(msgSpan).top-2), 180, 50, document.body);
	    	    oPopup.show((objLoc(msgSpan).left-2), (objLoc(msgSpan).top-2), oPopBody.scrollWidth, oPopBody.scrollHeight, document.body);
	    	 }
	    	 
	    	 msgSpan.onmouseout=function(){
		    	oPopup.hide();
	    	 }
	    	msgSpan.style.width="16px";
			msgSpan.style.height="16px"; 
	    	msgSpan.style.background="url("+jsPath_session+ "/vldt/img/exclamation.gif) 0 0 no-repeat";
	    	msgSpan.name="showMsgSpan";
	    	msgSpan.className="mustWrite";
	    	msgSpan.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;";
	    	msgSpan.style.visibility="visible";
			msgSpan.style.top=(objLoc(obj).top+5)+document.documentElement.scrollTop+"px";
	    }
    }catch(e){
        var errormsg="";
        if(obj){
            try{
                errormsg=obj.outerHTML;
            }catch(ee){
                errormsg=$common_vldtTips4;
            }
        }else{
            errormsg=$common_vldtTips5;
        }
        
        alert($common_vldtTips6+errormsg);
    }
}

function getMsgBox(msg){
    var htmlStr ="<table id=vldtmsg style='position:absolute;background-color:#fff;' border=0 cellspacing=0  cellpadding=0>";
		htmlStr+="<tr>";
		htmlStr+="<td width=7 height=7 style='background:url("+jsPath_session+ "/vldt/img/lefttop.gif) 0 0 no-repeat;'></td>";
		htmlStr+="<td height=7 style='background:url("+jsPath_session+ "/vldt/img/horizontaltop.gif) 0 0 repeat-x;'></td>";
		htmlStr+="<td width=7 height=7 style='background:url("+jsPath_session+ "/vldt/img/righttop.gif) 0 0 no-repeat;'></td>";
		htmlStr+="</tr>";
		htmlStr+="<tr>";
		htmlStr+="<td width=7 style='background:url("+jsPath_session+ "/vldt/img/lefterect.gif) 0 0 repeat-y;'></td>";
		htmlStr+="<td style='font-size:12px;color:red'><img src='"+jsPath_session+ "/vldt/img/exclamation.gif' style='margin-right:7px' align='absmiddle'>"+msg+"</td>";
		htmlStr+="<td width=7 style='background:url("+jsPath_session+ "/vldt/img/righterect.gif) right 0 repeat-y;'></td>";
		htmlStr+="</tr>";
		htmlStr+="<tr>";
		htmlStr+="<td width=7 height=7 style='background:url("+jsPath_session+ "/vldt/img/leftbottom.gif) 0 0 no-repeat;'></td>";
		htmlStr+="<td height=7 style='background:url("+jsPath_session+ "/vldt/img/horizontalbottom.gif) 0 bottom repeat-x;'></td>";
		htmlStr+="<td width=7 height=7 style='background:url("+jsPath_session+ "/vldt/img/rightbottom.gif) 0 0 no-repeat;'></td>";
		htmlStr+="</tr>";
		htmlStr+="</table>";
    return htmlStr;
}

/**
 * 隐藏提示信息
 */
function hideMsg(obj){
    try{
	    var spanid="span_"+obj.id;
	    var msgSpan=document.getElementById(spanid);
	    if(msgSpan){
	        msgSpan.style.visibility="hidden";
	        //modfiy by zhao
	    	//修改原因：结束时间的提示出现，但是框变灰，无论如何重置都无法回到原来
	        //obj.style.background="";
	        if(oPopup){
	            oPopup.hide();
	        }
	    }
    }catch(e){}
}

function getMin(obj){
    var imin=0;
    if(obj.min){
        try{
            imin=myParseInt(obj.min);
        }catch(e){
            imin=0;
        }
    }else{
        imin=0;
    }

	return imin;
}

function getMax(obj){
    var imax=0;
    if(obj.max){
        try{
            imax=myParseInt(obj.max);
        }catch(e){
            imax=max;
        }
    }else{
        imax=max;
    }

	return imax;
}
////////////////////////////验证方法///////////////////////////

//注：合法的返回true，非法的返回false


/**
 * 为空验证
 */
function checkEmpty(obj){
     var str=obj.value;
     str=str.replace(/^\s/,"");
     str=str.replace(/\s+$/,"");
     obj.value=str
     
	 if(str == ""){
	  	 return false;
	 }else{
	 	return true;
	 }
}

/**
 * 验证一个数字是否是在指定范围之内
 */
function checkNumberVldt(obj)
{
	if(obj.value==""){
	   return true;
	}
	
	if(/^0+$/.test(obj.value)){
	    obj.value=0;
	}else{
	    obj.value=obj.value.replace(/^0+/,"");
	}
	
	var minValue=getMin(obj);
	var maxValue=getMax(obj);
	var reg=/^[-\+]?\d+$/;
	if(!reg.test(obj.value)){
		return false;
	}
	if(!isNaN(obj.value)||!isNaN(minValue)||!isNaN(maxValue)){
		if(minValue>obj.value){
			return false;
		}
		if(obj.value>maxValue){
			return false;
		}
	}
	return true;
}

function checkOnlyNum(obj){
    if(obj.value==""){
        return true;
    }
    return /^\d+$/.test(obj.value);
}

/**
 *调用自定义验证方法
 */
function callSelfCheck(obj){
    var result=false;
    try{
        if(obj.getAttribute("code")){
            result=eval(obj.getAttribute("code"));
        }else{
            if(obj.outerHTML){
                 alert($common_vldtTips7+obj.outerHTML);
            }else{
                 alert($common_vldtTips8);
            }
        }
    }catch(e){
        alert($common_vldtTips9);
    }
    if(result){
        return true;
    }else{
        return false;
    }
}

function checkShoeSize(obj){
    if(obj.value==""){
        return true;
    }
    return /(^[34]\d$)|(^50$)/.test(obj.value);
}

/**
 * 验证非法字符
 */
function checkVLD(obj){
    if(obj.value==""){
        return true;
    }
    return !/[-'"><~!#$%^&*\/\\+=?|:]/.test(obj.value);
}

/**
 * 验证字符个数
 */
function checkCharLTH(obj){
    if(obj.value==""){
        return true;
    }
    return obj.value.length<=getlth(obj);
}

function getlth(obj){
    if(obj.getAttribute("lth")){
        return obj.getAttribute("lth");
    }else{
        return length;
    }
}

/**
 * 验证字节个数
 */
function checkByteLTH(obj){
    if(obj.value==""){
        return true;
    }
	return lth(obj.value)<=getlth(obj);
}
/**
* 验证数字的个数不能大于20
*/
function checkNumberLen(obj){
    if(obj.value==""){
        return true;
    }
	return lth(obj.value)<=getlth(obj);
}
/**
 *验证是否全是汉字
 */
function isChinese(obj){
    if(obj.value==""){
        return true;
    }

    return /^[\u4E00-\u9FA5]*$/.test(obj.value);
}

/**
 *验证是否全是字母
 */
function isLetter(obj){
    if(obj.value==""){
        return true;
    }
    return /^[a-zA-Z]+$/.test(obj.value);
}

/**
 *验证手机号码
 */
function checkMobile(obj){
    if(obj.value==""){
        return true;
    }
    return /^1\d{10}$/.test(obj.value);
}

/**
 *验证IP
 */
function checkIP(obj){
    if(obj.value==""){
        return true;
    }
    var reg = /^(?:[01]?\d\d?|2[0-4]\d|25[0-5])\.(?:[01]?\d\d?|2[0-4]\d|25[0-5])\.(?:[01]?\d\d?|2[0-4]\d|25[0-5])\.(?:[01]?\d\d?|2[0-4]\d|25[0-5])$/;
    return reg.test(obj.value);
}

/**
 *验证邮箱
 */
function checkEmail(obj)
{
    if(obj.value==""){
        return true;
    }
    var reg=/^[a-zA-Z0-9_.]+@\w+\-?\w+(\.\w+)+\s*$/;
    return reg.test(obj.value);
}

/**
 *验证邮政编码
 */
function checkPostalcode(obj)
{
    if(obj.value==""){
        return true;
    }
    
    return /^\d{6}$/.test(obj.value);
}

/**
 *验证身份证
 */
function checkID(obj){
    if(obj.value==""){
        return true;
    }

	if(/^\d{17}[xX\d]$/.test(obj.value))
	{
	    var year=obj.value.substr(6,4);
	    var month=obj.value.substr(10,2);
	    var day=obj.value.substr(12,2);
	    if(!cYear(year)){
	        return false;
	    }
	    if(!cMonth(month)){
	        return false;
	    }
	    if(!cDay(day)){
	        return false;
	    }
	    if(myParseInt(month)==2 && myParseInt(day)>29){
	       return false;
	    }
		return true;
	}else if(/^\d{15}$/.test(obj.value))
	{
	    var year=obj.value.substr(6,2);
	    var month=obj.value.substr(8,2);
	    var day=obj.value.substr(10,2);
	    if(!/^\d\d$/.test(year)){
	        return false;
	    }
	    if(!cMonth(month)){
	        return false;
	    }
	    if(!cDay(day)){
	        return false;
	    }
	    if(myParseInt(month)==2 && myParseInt(day)>29){
	       return false;
	    }
		return true;
	}
	return false;
}

/**
 * 验证身高
 */
function checkStature(obj){
    if(obj.value==""){
        return true;
    }
    return /^([89]\d|[12]\d\d)(\.\d{0,2})?$/.test(obj.value);
}

/**
 * 验证体重
 */
function checkAvoirdupois(obj){
    if(obj.value==""){
        return true;
    }
    return /^([3-9]\d|1\d\d)$/.test(obj.value);
}

function checkAge(obj){
    if(obj.value==""){
        return true;
    }
   return /^([1-9]|[1-9]\d?)$/.test(obj.value);
}

/**
 * 只能为数字
 */
function checkMustNum(obj){
    if(obj.value==""){
        return true;
    }
    return /^\d+$/.test(obj.value);
}

/**
 * 价格验证，2位小数
 */
function checkPrice(obj){
    if(obj.value==""){
        return true;
    }
    return /^\d{1,9}(\.\d{0,2})?$/.test(obj.value);
}

/**
 * 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”   
 */
function checkTelOrFax(obj)
{
//国家代码-区号-电话号码-分机号"

 var s =obj.value; 
 if(s=="")
 {
 	return true;
 }
 //var pattern =;
 //var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
 return /^(((\d{4}|\d{3})(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(s);
 
}

/**
 * 验证日期
 */
function checkDate(obj,pattern){
    if(obj.value==""){
        return true;
    }
    var reg=/^\d{2,4}-\d{1,2}-\d{1,2}/;
    if(!reg.test(obj.value)){
        return false;
    }
    var date=obj.value.split(" ")[0];
    var year=date.split("-")[0];
    var month=date.split("-")[1];
    var day=date.split("-")[2];
    if(!cYear(year)){
        return false;
    }
    if(!cMonth(month)){
        return false;
    }
    if(!cDay(day)){
        return false;
    }
    
    if(month==2){
        if(day>getFebDays(year)){
             currentMsgIndex=25;
             arrMsg[25]=year+$common_vldtTips10+day+$common_vldtTips11;
             return false;
        }
    }else{
        if(day>monthdays[myParseInt(month)]){
             currentMsgIndex=25;
             arrMsg[25]=myParseInt(month)+$common_vldtTips12+day+$common_vldtTips11;
             return false;
        }
    }
    
    var result=false;
    if(pattern==17)
    {
        result = true;
    }
    else if(pattern==171)
    {
        var sysTime=new Date().getTime();
        var selTime=new Date(year,month-1,day,0,0,0,0).getTime();
        if(selTime>sysTime){
             result = false;
        }else{
             result = true;
        }
    }
    else if(pattern==18)
    {
        reg=/^\d{2,4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}$/;
	    if(!reg.test(obj.value)){
	        return false;
	    }
	    var time=obj.value.split(" ")[1];
	    var hour=time.split(":")[0];
        var minute=time.split(":")[1];
        var second=time.split(":")[2];
	    if(!cHour(hour)){
	        return false;
	    }
	    if(!cMinute(minute)){
	        return false;
	    }
	    if(!cSecond(second)){
	        return false;
	    }
        result = true;
    }
    else if(pattern==26)
    {
    
        reg=/^\d{2,4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}$/;
	    if(!reg.test(obj.value)){
	        return false;
	    }
	    var time=obj.value.split(" ")[1];
	    var hour=time.split(":")[0];
        var minute=time.split(":")[1];
	    if(!cHour(hour)){
	        return false;
	    }
	    if(!cMinute(minute)){
	        return false;
	    }
        result = true;
    }
    
    if(result){
	    if(obj.begin){
	        var beginObj=document.getElementById(obj.begin);
	        if(beginObj){
	            
	            beginObj.value=vTrim(beginObj.value);
	            if(""==beginObj.value){
	                 return true;
	            }else{
	                var beginDate=gdate(beginObj.value);
		            var endDate=gdate(obj.value);
		            if(">"==obj.cmp){
		                if(beginDate.valueOf()>=endDate.valueOf()){
		                   currentMsgIndex=19;
		                   return false;
		                }
		            }else if(beginDate.valueOf()>endDate.valueOf()){
		                currentMsgIndex=20;
		                return false;
		            }
	            }
	        }
	    }
	    return true;
    }
	return false; 
}

//验证员工账号
function checkStaffId(obj){
    if(obj.value==""){
        return true;
    }
    if(/\W/.test(obj.value)){
          arrMsg[29]=$common_contentOnlyNumberOrWord;
          return false;
    }
   
    if(lth(obj.value)>20){
          arrMsg[29]=$common_contentSizeCannotGT20;
          return false;
    }
    return true;
}

//验证URL
function checkURL(obj){
    if(obj.value==""){
        return true;
    }
    
    if(!/^(((http|https|ftp):\/\/)|www\.)/i.test(obj.value)){
          arrMsg[25]=arrMsg[30];
          currentMsgIndex=25;
          return false;
    }
    
    if(!/^(((http|https|ftp):\/\/)|www\.)[\w]+/i.test(obj.value)){
          arrMsg[25]=$common_UrlFormatError;
          currentMsgIndex=25;
          return false;
    }
    
    if(/%/.test(obj.value)){
          arrMsg[25]=$common_UrlContainIllegalChar;
          currentMsgIndex=25;
          return false;
    }
        
    if(lth(obj.value)>200){
          arrMsg[25]=$common_UrlCannotGT200;
          currentMsgIndex=25;
          return false;
    }
    
    return true;
}

//自定义非法字符验证
function checkLawless(obj){
    if(obj.value==""){
        return true;
    }
    
    if(obj.chr != null){
        for(i=0;i<obj.chr.length;i++){
            var cr=obj.chr.charAt(i);
            if(obj.value.indexOf(cr)>-1){
                 arrMsg[25]=$common_contentcontainIllegalChar+"“"+cr+"”";
                 currentMsgIndex=25;
                 return false;
            }
        }
    }
    
    return true;
}
//验证附件
function checkVLDTFile(obj){
    if(obj.value==""){
        return true;
    }
    
    var file=getVLDTFile(obj.value);

	if(file==null){
	     arrMsg[25]=$common_getFileFailed;
         currentMsgIndex=25;
         return false;
	}
	
	if(/ /.test(file.name)){
	     arrMsg[25]=$common_fileNameCannotContainSpace;
         currentMsgIndex=25;
		 return false;
	}
	
	if(file.size==0){
		 arrMsg[25]=$common_fileSizeCannot0K;
         currentMsgIndex=25;
		 return false;
	}
	var fsize=1024;
	if(obj.fsize){
	    fsize=1024*obj.fsize;
	}
	
	if(file.size>fsize){
		 arrMsg[25]=$common_fileSizeCannotGT+obj.fsize+"M";
         currentMsgIndex=25;
		 return false;
	}
	
	if(obj.ftype){
	    var vres=false;
	    var ftypes=obj.ftype.split(",");
	    for(z=0;z<ftypes.length;z++){
	        if(upCase(ftypes[z])==upCase(file.type)){
	            vres=true;
	            break;
	        }
	    }
	    
	    if(!vres){
	        arrMsg[25]=$common_fileTypeError+ obj.ftype;
            currentMsgIndex=25;
		    return false;
	    }
	}
	
	return true;
}

function upCase(str){
    if(str==null){
        return null;
    }else{
        str=str.toUpperCase();
    }
    return str;
}

/**
 * 验证日期
 */
function checkDate2(obj){
    var str=vTrim(obj.value);
	if(str==""){
	   return true;
	}
	
	str =str.replace(/[-:]/g,",");
	str =str.replace(/ +/,",");
    var arr=[];
	arr[0]=0;
	arr[1]=1;
	arr[2]=0;
	arr[3]=0;
	arr[4]=0;
	arr[5]=0;
	var arr1=str.split(",");
	
	var nowtime=new Date();
	
	var arr2=[];
	arr2[0]=nowtime.getFullYear();
	arr2[1]=nowtime.getMonth();
	arr2[2]=nowtime.getDate();
	arr2[3]=nowtime.getHours();
	arr2[4]=nowtime.getMinutes();
	arr2[5]=nowtime.getSeconds();
	for(i=0;i<arr.length;i++){
	   if(i<arr1.length){
	       arr[i]=arr1[i];
	   }else{
	       arr2[i]=0;
	   }
	}
	
    nowtime=new Date(arr2[0],arr2[1],arr2[2],arr2[3],arr2[4],arr2[5]);
	var date=new Date(arr[0],arr[1]-1,arr[2],arr[3],arr[4],arr[5]);
	var l1=nowtime.getTime();
	var l2=date.getTime();
	return l2>l1;
}

function cYear(year){
    return /^[1-9]\d{3}$/.test(year);
}

function cMonth(month){
    return /^(0?[1-9]|10|11|12)$/.test(month);
}

function cDay(day){
    return /^(31|[123]0|[012]?[1-9])$/.test(day);
}

function cHour(hour){
    return /^([01]?[0-9]|2[0-3])$/.test(hour);
}

function cMinute(minute){
    return /^([0-4]?[0-9]|5\d)$/.test(minute);
}

function cSecond(second){
     return /^([0-4]?[0-9]|5\d)$/.test(second);
}

/**
 * 检测一个字符串的真实长度（一个汉字算2个字节）
 */
function lth(str){
	if(str==null){
	    return 0;
	}
    var lgth = str.replace(/[^\x00-\xFF]/g,"aaa").length;
    return lgth;
}



function vTrim(str){
    if(str==null){
        return "";
    }
    str =str.replace(/ +$/,"");
    str =str.replace(/^ +/,"");
    return str;
}

function vObjTrim(obj){
    obj.value=vTrim(obj.value);
}

//获取日期
function gdate(str){
    str=vTrim(str);
    var dReg=/^\d{2,4}-\d{1,2}-\d{1,2}$/;
    var tReg=/^\d{2,4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}$/;
    var reg26=/^\d{2,4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}$/;
    var date=new Date();
    if(dReg.test(str)){
        var date=str.split("-");
	    var year=date[0];
	    var month=myParseInt(date[1])-1;
	    var day=date[2];
        date = new Date(year,month,day,0,0,0,0);
    }
    else if(tReg.test(str))
    {
        var date=str.split(" ")[0];
        var time=str.split(" ")[1];
	    var year=date.split("-")[0];
	    var month=myParseInt(date.split("-")[1])-1;
	    var day=date.split("-")[2];
	    var hour=time.split(":")[0];
        var minute=time.split(":")[1];
        var second=time.split(":")[2];
        date = new Date(year,month,day,hour,minute,second);
    }
    else if(reg26.test(str))
    {
        var date=str.split(" ")[0];
        var time=str.split(" ")[1];
	    var year=date.split("-")[0];
	    var month=myParseInt(date.split("-")[1])-1;
	    var day=date.split("-")[2];
	    var hour=time.split(":")[0];
        var minute=time.split(":")[1];
        date = new Date(year,month,day,hour,minute,0);
    }
    return date;
}

function objLoc(obj){
         var ltwh={"left":0,"top":0,"width":0,"height":0};
		 ltwh.left=$(obj).offset().left+$(obj).width()  ;
		 ltwh.top=$(obj).offset().top;
         ltwh.width=obj.offsetWidth;
         ltwh.height=obj.offsetHeight;
         return ltwh;
}


/**
 *获取第一个非td父元素
*/
function getnfptd(obj){
   if(obj==null){
        return null;
   }
   
   if(obj.parentNode.tagName=="TD"){
       return obj;
   }else{
       return getnfptd(obj.parentNode)
   }
}

function getLineNum(realWidth,lWidth){
    if(lWidth>=realWidth){
        return 1;
    }
    if(realWidth/lWidth==0){
         return realWidth/lWidth;
    }else{
         return myParseInt(realWidth/lWidth)+1;
    }
}

//获取2月天数
function getFebDays(year){
    return (((year%4 == 0) && (year%100 != 0) || (year%400 == 0))? 29: 28);
}

//计算年龄
function calAge(id){
    var age=-1;
    var obj=document.getElementById(id);
    if(/ /.test(obj.value)){
        obj.value=vTrim(obj.value);
    }
    if(checkDate(obj,17)){
        var arr=obj.value.split("-");
	    var year=arr[0];
	    var month=arr[1];
        var day=arr[2];
        var curDate=new Date();
        age=curDate.getFullYear()-myParseInt(year);
    }
    return age;
}

function myParseInt(str){
    if(str==null){
         return 0;
    }else{
        if("0"==str){
             return 0;
        }else{
             str=str.replace(/^0+/, "");
             return parseInt(str);
        }
    }
}

//获取本地文件对象
function getVLDTFile(path){
    var info={
             "exist":true,
             "size":0,
             "type":"",
			 "name":""
     };
     
	 var oas=null;
	 try{
	     oas=new ActiveXObject("Scripting.FileSystemObject");
	 }catch(e){
	 	 alert($common_vldtTips13);
	 }
	 if(oas==null){
	 	alert($common_vldtTips14);
	 	return null;
	 }
	 
	 try{
	      var file = oas.getFile(path);
	      info.size = parseInt(file.size/1024,10);
		  info.type=path.substr(path.lastIndexOf('.')+1);
		  var p1=path.lastIndexOf('/');
		  var p2=path.lastIndexOf('\\');
		  if(p2>p1){
		       p1=p2;
		  }
		  info.name=path.substr(p1+1);
	 }catch(e){
	      return null;
	 }
	return info;
}

if (!window.createPopup) {
	var __createPopup = function() {
		var SetElementStyles = function( element, styleDict ) {
			var style = element.style ;
			for ( var styleName in styleDict )style[ styleName ] = styleDict[ styleName ] ; 
		}
		var eDiv = document.createElement( 'div' ); 
		SetElementStyles( eDiv, { 'position': 'absolute', 'top': 0 + 'px', 'left': 0 + 'px', 'width': 0 + 'px', 'height': 0 + 'px', 'zIndex': 9999999, 'display' : 'none', 'overflow' : 'hidden' } ) ;
		eDiv.body = eDiv ;
		var opened = false ;
		var setOpened = function( b ) {
			opened = b; 
		}
		var getOpened = function() {
			return opened ; 
		}
		var getCoordinates = function( oElement ) {
			var coordinates = {x:0,y:0} ; 
			while( oElement ) {
				coordinates.x += oElement.offsetLeft ;
				coordinates.y += oElement.offsetTop ;
				oElement = oElement.offsetParent ;
			}
			return coordinates ;
		}
		return {htmlTxt : '', document : eDiv, isOpen : getOpened(), isShow : false,
			hide : function() { SetElementStyles( eDiv, { 'top': 0 + 'px', 'left': 0 + 'px', 'width': 0 + 'px', 'height': 0 + 'px', 'display' : 'none' } ) ; eDiv.innerHTML = '' ; this.isShow = false ; }, 
			show : function(  iY,iX, iWidth, iHeight, oElement ) {
				if (!getOpened()) { 
					document.body.appendChild( eDiv ) ;
					setOpened( true ) ;
				} ; 
				this.htmlTxt = eDiv.innerHTML ; 
				if (this.isShow) {
					this.hide() ;
				} ; 
				eDiv.innerHTML = this.htmlTxt ; 
				var coordinates = getCoordinates ( oElement ) ; 
				eDiv.style.top = ( iX + coordinates.x ) + 'px' ;
				eDiv.style.left = ( iY + coordinates.y ) + 'px' ;
				eDiv.style.width = iWidth + 'px' ; 
				eDiv.style.height = iHeight + 'px' ;
				eDiv.style.display = 'block' ;
				this.isShow = true ; 
				} 
			}
	}
	window.createPopup = function() {
		return __createPopup(); 
	}
}
