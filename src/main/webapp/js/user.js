function moveCity(path){
	var provinceid = $("#liveProvince").val();
	
	$.get(path, {provinceid : provinceid}, function(
			data) {
		$("#liveCity option").remove();
		$("#liveArea option").remove();
		$("#liveCity").append("<option value='0'>请选择城市</option>");
		$("#liveArea").append("<option value='0'>请选择县区</option>");
		for(var i = 0 ; i < data.length;i++){
			$("#liveCity").append("<option value='"+data[i].cityid+"'>"+data[i].city+"</option>");
		}
	});
}

function moveArea(path){
	var cityid = $("#liveCity").val();
	
	$.post(path, {cityid : cityid}, function(
			data) {
		var areaJsonData = eval(data);
		$("#liveArea option").remove();
		for(var i = 0 ; i < areaJsonData.length;i++){
			$("#liveArea").append("<option value='"+areaJsonData[i].areaid+"'>"+areaJsonData[i].area+"</option>");
		}
	});
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
