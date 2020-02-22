function Share() {
	if (typeof Share._initialized == "undefined") {
		Share.prototype.shareTqq = function(url,title,description,pic,type,id,from){
			var p = {
				_t:"#网易邮箱用户俱乐部# "+"【"+title+"】",
				_url:url,
				_appkey:"",
				_pic:pic,//（例如：var _pic='图片url1|图片url2|图片url3....）
				_u:""//你的网站地址
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"tqq");
			return "http://v.t.qq.com/share/share.php?"+s.join("&");
		};
		
		Share.prototype.shareT163 = function(url,title,description,pic,type,id,from) {
			var p = {
				source:"网易邮箱用户俱乐部",
				info:"#网易邮箱用户俱乐部# 【"+title+"】"+" " +description+" "+url,
				togImg:true,
				images:pic
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"t163");
			return "http://t.163.com/article/user/checkLogin.do?"+s.join("&");
		};
		
		Share.prototype.shareKaixin = function(url,title,description,pic,type,id,from) {
			var p = {
				rtitle:"#网易邮箱用户俱乐部# "+"【"+title+"】",
				// rurl:encodeURIComponent(url),
				rcontent:" " + description + " " + url
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"kaixin");
			return "http://www.kaixin001.com/repaste/share.php?"+s.join("&");
		};
		
		Share.prototype.shareTsina = function(url,title,description,pic,type,id,from){
			var p = {
				url:url,
				type:'3',
				count:1,
				appkey:"",
				title:"#网易邮箱用户俱乐部# "+"【"+title+"】" +" "+ description,
				pic:pic,
				relateUid:"",
				rnd:new Date().valueOf()
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"tsina");
			return "http://service.weibo.com/share/share.php?"+s.join('&');
		};
		
		Share.prototype.shareRenren = function(url,title,description,pic,type,id,from){
			var p = {
				resourceUrl:url,
				title:"#网易邮箱用户俱乐部# "+"【"+title+"】",
				description:description,
				pic:pic
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"renren");
			return "http://widget.renren.com/dialog/share?"+s.join('&');
		};
		
		Share.prototype.shareQQ = function(url,title,description,pic,type,id,from){
			var p = {
				url:url,
				desc:description,/*默认分享理由(可选)*/
				summary:"#网易邮箱用户俱乐部# "+"【"+title+"】",/*摘要(可选)*/
				title:"#网易邮箱用户俱乐部# "+"【"+title+"】",/*分享标题(可选)*/
				site:"",/*分享来源 如：腾讯网(可选)*/
				pics:pic /*分享图片的路径(可选)*/
			};
			var s = [];
			for(var i in p){
				s.push(i + '=' + encodeURIComponent(p[i]||''));
			}
			this.statistics(type,id,from,"qqzone");
			return "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?"+s.join('&');
		};
		
		Share.prototype.statistics  = function (type,id,from,to){
			$.ajax({
					url : "/jifen/statistics/share.do",
					// context : document.body,
					// contentType : "application/json; charset=utf-8",
					type : "GET",
					data : {
						type : type,
						id : id,
						from : from,
						to : to
					}
			});
		};

		Share._initialized = true;
	}
}