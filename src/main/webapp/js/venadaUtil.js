/** 强度规则
+ ------------------------------------------------------- +
    1) 任何少于6个字符的组合，弱；任何字符数的同类字符组合，弱；
    2) 任何字符数的两类字符组合，中；
    3) 12位字符数以下的三类或四类字符组合，强；
    4) 12位字符数以上的三类或四类字符组合，非常好。
+ ------------------------------------------------------- +
**/
//检测密码强度
function checkStrong(sValue)
{
    var modes = 0;
    if (sValue.length < 6) return modes;
    if (/\d/.test(sValue)) modes++; //数字
    if (/[a-z]/.test(sValue)) modes++; //小写
    if (/[A-Z]/.test(sValue)) modes++; //大写     
    if (/\W/.test(sValue)) modes++; //特殊字符

    switch (modes)
    {
        case 1:
            return 1;
            break;
        case 2:
            return 2;
        case 3:
        case 4:
            return sValue.length < 12 ? 3 : 4;
            break;
    }
}
//js获取网站根路径(站点及虚拟目录)，获得网站的根目录或虚拟目录的根地址
function getRootPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return(prePath+postPath);
  }

/* 验证用户名
* （大小写英文字母、汉字、数字、下划线组成的长度3-12个字节）
*
* @parameter string str 字符串
* @return boolean
*/
function checkString(str){ 
	var reg = /^([\u4e00-\u9fa5a-zA-Z0-9_]){1,20}$/;
	return reg.test(str);
}

function isEmpty(s) {
	return ((s == undefined || s == null || s == "") ? true : false);
}
/*
 * 验证手机号码
 */
function checkMobileNumber(mobileNumber){
	var reg = /^1\d{10}$/;
	return reg.test(mobileNumber);
}