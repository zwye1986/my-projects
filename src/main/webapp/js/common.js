/**
 *  此JS文件用于封装常用的方法,加载此JS，需先加载jquery的支持
 *  时间：2013.6.22创建
 *  作者：yma
 *  
 */

dynamicPost = function(url,datas){
	var dynamicForm = $("<form action="+ url+" method='post'></form>");
	if(datas){
		$.each( datas, function(key, value){ 
			dynamicForm.append("<input type='hidden' name='"+ key +"' value='"+value+"' />");
		}); 
	}
	 
	dynamicForm.appendTo(document.body).submit();
};

dynamicGet = function(url,datas){
	var dynamicForm = $("<form action="+ url+" method='get'></form>");
	if(datas){
		$.each( datas, function(key, value){ 
			dynamicForm.append("<input type='hidden' name='"+ key +"' value='"+value+"' />");
		}); 
	}
	 
	dynamicForm.appendTo(document.body).submit();
};

