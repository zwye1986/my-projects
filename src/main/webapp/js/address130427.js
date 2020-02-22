function Address() {

	var citysHtml = [
			'<option value="0" >广州市</option><option value="1">韶关市</option><option value="2">深圳市</option><option value="3">珠海市</option><option value="4">汕头市</option><option value="5">佛山市</option><option value="6">江门市</option><option value="7">湛江市</option><option value="8">茂名市</option><option value="9">肇庆市</option><option value="10">惠州市</option><option value="11">梅州市</option><option value="12">汕尾市</option><option value="13">河源市</option><option value="14">阳江市</option><option value="15">清远市</option><option value="16">东莞市</option><option value="17">中山市</option><option value="18">潮州市</option><option value="19">揭阳市</option><option value="20">云浮市</option>',
			'<option value="0" >南宁市</option><option value="1">柳州市</option><option value="2">桂林市</option><option value="3">梧州市</option><option value="4">北海市</option><option value="5">防城港市</option><option value="6">钦州市</option><option value="7">贵港市</option><option value="8">玉林市</option><option value="9">百色市</option><option value="10">贺州市</option><option value="11">河池市</option><option value="12">来宾市</option><option value="13">崇左市</option>',
			'<option value="0" >东城区</option><option value="1">西城区</option><option value="2">崇文区</option><option value="3">宣武区</option><option value="4">朝阳区</option><option value="5">丰台区</option><option value="6">石景山区</option><option value="7">海淀区</option><option value="8">门头沟区</option><option value="9">房山区</option><option value="10">通州区</option><option value="11">顺义区</option><option value="12">昌平区</option><option value="13">大兴区</option><option value="14">怀柔区</option><option value="15">平谷区</option><option value="16">密云县</option><option value="17">延庆县</option>',
			'<option value="0" >海口市</option><option value="1">三亚市</option><option value="2">五指山市</option><option value="3">琼海市</option><option value="4">儋州市</option><option value="5">文昌市</option><option value="6">万宁市</option><option value="7">东方市</option><option value="8">定安县</option><option value="9">屯昌县</option><option value="10">澄迈县</option><option value="11">临高县</option><option value="12">白沙黎族自治县</option><option value="13">昌江黎族自治县</option><option value="14">乐东黎族自治县</option><option value="15">陵水黎族自治县</option><option value="16">保亭黎族苗族自治县</option><option value="17">琼中黎族苗族自治县</option><option value="18">西沙群岛</option><option value="19">南沙群岛</option><option value="20">中沙群岛的岛礁及其海域</option>',
			'<option value="0" >福州市</option><option value="1">厦门市</option><option value="2">莆田市</option><option value="3">三明市</option><option value="4">泉州市</option><option value="5">漳州市</option><option value="6">南平市</option><option value="7">龙岩市</option><option value="8">宁德市</option>',
			'<option value="0" >和平区</option><option value="1">河东区</option><option value="2">河西区</option><option value="3">南开区</option><option value="4">河北区</option><option value="5">红桥区</option><option value="6">塘沽区</option><option value="7">汉沽区</option><option value="8">大港区</option><option value="9">东丽区</option><option value="10">西青区</option><option value="11">津南区</option><option value="12">北辰区</option><option value="13">武清区</option><option value="14">宝坻区</option><option value="15">宁河县</option><option value="16">静海县</option><option value="17">蓟县</option>',
			'<option value="0" >长沙市</option><option value="1">株洲市</option><option value="2">湘潭市</option><option value="3">衡阳市</option><option value="4">邵阳市</option><option value="5">岳阳市</option><option value="6">常德市</option><option value="7">张家界市</option><option value="8">益阳市</option><option value="9">郴州市</option><option value="10">永州市</option><option value="11">怀化市</option><option value="12">娄底市</option><option value="13">湘西土家族苗族自治州</option>',
			'<option value="0" >武汉市</option><option value="1">黄石市</option><option value="2">十堰市</option><option value="3">宜昌市</option><option value="4">襄樊市</option><option value="5">鄂州市</option><option value="6">荆门市</option><option value="7">孝感市</option><option value="8">荆州市</option><option value="9">黄冈市</option><option value="10">咸宁市</option><option value="11">随州市</option><option value="12">恩施土家族苗族自治州</option><option value="13">仙桃市</option><option value="14">潜江市</option><option value="15">天门市</option><option value="16">神农架林区</option>',
			'<option value="0" >郑州市</option><option value="1">开封市</option><option value="2">洛阳市</option><option value="3">平顶山市</option><option value="4">安阳市</option><option value="5">鹤壁市</option><option value="6">新乡市</option><option value="7">焦作市</option><option value="8">濮阳市</option><option value="9">许昌市</option><option value="10">漯河市</option><option value="11">三门峡市</option><option value="12">南阳市</option><option value="13">商丘市</option><option value="14">信阳市</option><option value="15">周口市</option><option value="16">驻马店市</option>',
			'<option value="0" >石家庄市</option><option value="1">唐山市</option><option value="2">秦皇岛市</option><option value="3">邯郸市</option><option value="4">邢台市</option><option value="5">保定市</option><option value="6">张家口市</option><option value="7">承德市</option><option value="8">沧州市</option><option value="9">廊坊市</option><option value="10">衡水市</option>',
			'<option value="0" >济南市</option><option value="1">青岛市</option><option value="2">淄博市</option><option value="3">枣庄市</option><option value="4">东营市</option><option value="5">烟台市</option><option value="6">潍坊市</option><option value="7">济宁市</option><option value="8">泰安市</option><option value="9">威海市</option><option value="10">日照市</option><option value="11">莱芜市</option><option value="12">临沂市</option><option value="13">德州市</option><option value="14">聊城市</option><option value="15">滨州市</option><option value="16">菏泽市</option>',
			'<option value="0" >太原市</option><option value="1">大同市</option><option value="2">阳泉市</option><option value="3">长治市</option><option value="4">晋城市</option><option value="5">朔州市</option><option value="6">晋中市</option><option value="7">运城市</option><option value="8">忻州市</option><option value="9">临汾市</option><option value="10">吕梁市</option>',
			'<option value="0" >哈尔滨市</option><option value="1">齐齐哈尔市</option><option value="2">鸡西市</option><option value="3">鹤岗市</option><option value="4">双鸭山市</option><option value="5">大庆市</option><option value="6">伊春市</option><option value="7">佳木斯市</option><option value="8">七台河市</option><option value="9">牡丹江市</option><option value="10">黑河市</option><option value="11">绥化市</option><option value="12">大兴安岭地区</option>',
			'<option value="0" >沈阳市</option><option value="1">大连市</option><option value="2">鞍山市</option><option value="3">抚顺市</option><option value="4">本溪市</option><option value="5">丹东市</option><option value="6">锦州市</option><option value="7">营口市</option><option value="8">阜新市</option><option value="9">辽阳市</option><option value="10">盘锦市</option><option value="11">铁岭市</option><option value="12">朝阳市</option><option value="13">葫芦岛市</option>',
			'<option value="0" >黄浦区</option><option value="1">卢湾区</option><option value="2">徐汇区</option><option value="3">长宁区</option><option value="4">静安区</option><option value="5">普陀区</option><option value="6">闸北区</option><option value="7">虹口区</option><option value="8">杨浦区</option><option value="9">闵行区</option><option value="10">宝山区</option><option value="11">嘉定区</option><option value="12">浦东新区</option><option value="13">金山区</option><option value="14">松江区</option><option value="15">青浦区</option><option value="16">南汇区</option><option value="17">奉贤区</option><option value="18">崇明县</option>',
			'<option value="0" >兰州市</option><option value="1">嘉峪关市</option><option value="2">金昌市</option><option value="3">白银市</option><option value="4">天水市</option><option value="5">武威市</option><option value="6">张掖市</option><option value="7">平凉市</option><option value="8">酒泉市</option><option value="9">庆阳市</option><option value="10">定西市</option><option value="11">陇南市</option><option value="12">临夏回族自治州</option><option value="13">甘南藏族自治州</option>',
			'<option value="0" >西宁市</option><option value="1">海东地区</option><option value="2">海北藏族自治州</option><option value="3">黄南藏族自治州</option><option value="4">海南藏族自治州</option><option value="5">果洛藏族自治州</option><option value="6">玉树藏族自治州</option><option value="7">海西蒙古族藏族自治州</option>',
			'<option value="0" >乌鲁木齐市</option><option value="1">克拉玛依市</option><option value="2">吐鲁番地区</option><option value="3">哈密地区</option><option value="4">昌吉回族自治州</option><option value="5">博尔塔拉蒙古自治州</option><option value="6">巴音郭楞蒙古自治州</option><option value="7">阿克苏地区</option><option value="8">克孜勒苏柯尔克孜自治州</option><option value="9">喀什地区</option><option value="10">和田地区</option><option value="11">伊犁哈萨克自治州</option><option value="12">塔城地区</option><option value="13">阿勒泰地区</option><option value="14">石河子市</option><option value="15">阿拉尔市</option><option value="16">图木舒克市</option><option value="17">五家渠市</option>',
			'<option value="0" >拉萨市</option><option value="1">昌都地区</option><option value="2">山南地区</option><option value="3">日喀则地区</option><option value="4">那曲地区</option><option value="5">阿里地区</option><option value="6">林芝地区</option>',
			'<option value="0" >银川市</option><option value="1">石嘴山市</option><option value="2">吴忠市</option><option value="3">固原市</option><option value="4">中卫市</option>',
			'<option value="0" >成都市</option><option value="1">自贡市</option><option value="2">攀枝花市</option><option value="3">泸州市</option><option value="4">德阳市</option><option value="5">绵阳市</option><option value="6">广元市</option><option value="7">遂宁市</option><option value="8">内江市</option><option value="9">乐山市</option><option value="10">南充市</option><option value="11">眉山市</option><option value="12">宜宾市</option><option value="13">广安市</option><option value="14">达州市</option><option value="15">雅安市</option><option value="16">巴中市</option><option value="17">资阳市</option><option value="18">阿坝藏族羌族自治州</option><option value="19">甘孜藏族自治州</option><option value="20">凉山彝族自治州</option>',
			'<option value="0" >昆明市</option><option value="1">曲靖市</option><option value="2">玉溪市</option><option value="3">保山市</option><option value="4">昭通市</option><option value="5">丽江市</option><option value="6">思茅市</option><option value="7">临沧市</option><option value="8">楚雄彝族自治州</option><option value="9">红河哈尼族彝族自治州</option><option value="10">文山壮族苗族自治州</option><option value="11">西双版纳傣族自治州</option><option value="12">大理白族自治州</option><option value="13">德宏傣族景颇族自治州</option><option value="14">怒江傈僳族自治州</option><option value="15">迪庆藏族自治州</option>',
			'<option value="0" >长春市</option><option value="1">吉林市</option><option value="2">四平市</option><option value="3">辽源市</option><option value="4">通化市</option><option value="5">白山市</option><option value="6">松原市</option><option value="7">白城市</option><option value="8">延边朝鲜族自治州</option>',
			'<option value="0" >呼和浩特市</option><option value="1">包头市</option><option value="2">乌海市</option><option value="3">赤峰市</option><option value="4">通辽市</option><option value="5">鄂尔多斯市</option><option value="6">呼伦贝尔市</option><option value="7">巴彦淖尔市</option><option value="8">乌兰察布市</option><option value="9">兴安盟</option><option value="10">锡林郭勒盟</option><option value="11">阿拉善盟</option>',
			'<option value="0" >西安市</option><option value="1">铜川市</option><option value="2">宝鸡市</option><option value="3">咸阳市</option><option value="4">渭南市</option><option value="5">延安市</option><option value="6">汉中市</option><option value="7">榆林市</option><option value="8">安康市</option><option value="9">商洛市</option>',
			'<option value="0" >合肥市</option><option value="1">芜湖市</option><option value="2">蚌埠市</option><option value="3">淮南市</option><option value="4">马鞍山市</option><option value="5">淮北市</option><option value="6">铜陵市</option><option value="7">安庆市</option><option value="8">黄山市</option><option value="9">滁州市</option><option value="10">阜阳市</option><option value="11">宿州市</option><option value="12">巢湖市</option><option value="13">六安市</option><option value="14">亳州市</option><option value="15">池州市</option><option value="16">宣城市</option>',
			'<option value="0" >贵阳市</option><option value="1">六盘水市</option><option value="2">遵义市</option><option value="3">安顺市</option><option value="4">铜仁地区</option><option value="5">黔西南布依族苗族自治州</option><option value="6">毕节地区</option><option value="7">黔东南苗族侗族自治州</option><option value="8">黔南布依族苗族自治州</option>',
			'<option value="0" >南京市</option><option value="1">无锡市</option><option value="2">徐州市</option><option value="3">常州市</option><option value="4">苏州市</option><option value="5">南通市</option><option value="6">连云港市</option><option value="7">淮安市</option><option value="8">盐城市</option><option value="9">扬州市</option><option value="10">镇江市</option><option value="11">泰州市</option><option value="12">宿迁市</option>',
			'<option value="0" >万州区</option><option value="1">涪陵区</option><option value="2">渝中区</option><option value="3">大渡口区</option><option value="4">江北区</option><option value="5">沙坪坝区</option><option value="6">九龙坡区</option><option value="7">南岸区</option><option value="8">北碚区</option><option value="9">万盛区</option><option value="10">双桥区</option><option value="11">渝北区</option><option value="12">巴南区</option><option value="13">黔江区</option><option value="14">长寿区</option><option value="15">綦江县</option><option value="16">潼南县</option><option value="17">铜梁县</option><option value="18">大足县</option><option value="19">荣昌县</option>'
					+ '<option value="20">璧山县</option><option value="21">梁平县</option><option value="22">城口县</option><option value="23">丰都县</option><option value="24">垫江县</option><option value="25">武隆县</option><option value="26">忠县</option><option value="27">开县</option><option value="28">云阳县</option><option value="29">奉节县</option><option value="30">巫山县</option><option value="31">巫溪县</option><option value="32">石柱土家族自治县</option><option value="33">秀山土家族苗族自治县</option><option value="34">酉阳土家族苗族自治县</option><option value="35">彭水苗族土家族自治县</option><option value="36">江津市</option><option value="37">合川市</option><option value="38">永川市</option><option value="39">南川市</option>',
			'<option value="0" >杭州市</option><option value="1">宁波市</option><option value="2">温州市</option><option value="3">嘉兴市</option><option value="4">湖州市</option><option value="5">绍兴市</option><option value="6">金华市</option><option value="7">衢州市</option><option value="8">舟山市</option><option value="9">台州市</option><option value="10">丽水市</option>',
			'<option value="0" >南昌市</option><option value="1">景德镇市</option><option value="2">萍乡市</option><option value="3">九江市</option><option value="4">新余市</option><option value="5">鹰潭市</option><option value="6">赣州市</option><option value="7">吉安市</option><option value="8">宜春市</option><option value="9">抚州市</option><option value="10">上饶市</option>',
			'', '<option value="0" >台湾省</option>',
			'<option value="0" >香港特别行政区</option>',
			'<option value="0" >澳门特别行政区</option>'];

	if (typeof Address._initialized == "undefined") {
		oMod = this;
		Address.prototype.checkDate = function(strDate) {
			if (strDate.length != 6 && strDate.length != 8) {
				return false;
			}
		}

		Address.prototype.isName = function(name) {
			if (name == "" || name == " " || name.indexOf("<") > -1
					|| name.indexOf(">") > -1) {
				return false;
			}
			if (name.length > 16) {
				return false;
			}
			return true;
		}

		Address.prototype.isAddress = function(address) {
			if (address == "" || address == " " || address.indexOf("<") > -1
					|| address.indexOf(">") > -1) {
				return false;
			} else if (address.length > 100) {
				return false;
			}
			return true;
		}

		Address.prototype.isMobile = function(mobile) {
			if (mobile == "")
				return false;
			if (/^13\d{9}$/.test(mobile) || /^15\d{9}$/.test(mobile)
					|| /^18\d{9}$/.test(mobile) || /^14\d{9}$/.test(mobile))
				return true;
			return false;
		}

		Address.prototype.isPostcode = function(postcode) {
			var oRegOther = new RegExp(/^[\d-]*$/);
			if (postcode == "") {
				return false;
			} else if (!oRegOther.test(postcode) || postcode.length > 10) {
				// postcodeError.style.display = "";
				return false
			} else {
				return true;
			}
		}
		Address.prototype.isPassport = function(number) {
			var str = number;
			// 在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
			var Expression = /(P\d{7})|(G\d{8})/;
			var objExp = new RegExp(Expression);
			if (objExp.test(str) == true) {
				return true;
			} else {
				return false;
			}
		};

		Address.prototype.isOther = function(number) {
			var str = number;
			var Expression = /^[a-zA-Z0-9]{1,}$/;
			var objExp = new RegExp(Expression);
			if (str == "" || str.length > 50) {
				return false;
			} else if (objExp.test(str) == false) {
				return false;
			} else {
				return true;
			}
		};
		Address.prototype.isIdCardNo = function(num) {
			var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
					5, 8, 4, 2, 1);
			var error;
			var varArray = new Array();
			var intValue;
			var lngProduct = 0;
			var intCheckDigit;
			var intStrLen = num.length;
			var idNumber = num;
			if ((intStrLen != 15) && (intStrLen != 18)) {
				return false;
			}
			for (i = 0; i < intStrLen; i++) {
				varArray[i] = idNumber.charAt(i);
				if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
					return false;
				} else if (i < 17) {
					varArray[i] = varArray[i] * factorArr[i];
				}
			}
			if (intStrLen == 18) {
				var date8 = idNumber.substring(6, 14);
				if (oMod.checkDate(date8) == false) {
					return false;
				}
				// calculate the sum of the products
				for (i = 0; i < 17; i++) {
					lngProduct = lngProduct + varArray[i];
				}
				// calculate the check digit
				intCheckDigit = 12 - lngProduct % 11;
				switch (intCheckDigit) {
					case 10 :
						intCheckDigit = 'X';
						break;
					case 11 :
						intCheckDigit = 0;
						break;
					case 12 :
						intCheckDigit = 1;
						break;
				}
				// check last digit
				if (varArray[17].toUpperCase() != intCheckDigit) {
					// error = "身份证效验位错误!正确为： " + intCheckDigit + ".";
					// alert(error);
					return false;
				}
			} else { // length is 15
				// check date
				var date6 = idNumber.substring(6, 12);
				if (oMod.checkDate(date6) == false) {
					// alert("身份证日期信息有误！.");
					return false;
				}
			}
			// alert ("Correct.");
			return true;
		}

		Address.prototype.changeProvince = function() {
			var pId = 0;
			var cId = 1;
			if (arguments.length == 0) {
				pId = document.getElementById("province").selectedIndex;
			} else if (arguments.length == 1) {
				// var pId = document.getElementById("province").selectedIndex;
				pId = arguments[0];
			} else if(arguments.length == 2){
				pId = arguments[0];
				cId = arguments[1];
			}
			if (citysHtml[pId] == "") {
				cId = 0;
			}
			var cityHtml = "<option value='-1'>其它</option>" + citysHtml[pId];
			jQuery("#city").empty();
			jQuery("#city").append(cityHtml);
			document.getElementById("city").selectedIndex = cId;
		}

		Address.prototype.changeCity = function() {

		}

		Address.prototype.submit = function() {
			var successUrl = "/jifen/ucenter/address.do";
			if (arguments.length > 0) {
				successUrl = arguments[0];
			}
			var name = jQuery("#name").val();
			if (oMod.isName(name) == false) {
				jQuery("#nameError").show();
				jQuery("#name").focus();
				return;
			} else {
				jQuery("#nameError").hide();
			}
			var identityType = jQuery("#identityType").val();
			var identityNo = jQuery("#identityNo").val();
			if ((identityType == "0" && oMod.isIdCardNo(identityNo) == false && identityNo != "")
					|| (identityType == "1" && oMod.isPassport(identityNo) == false)
					|| (identityType == "2" && oMod.isOther(identityNo) == false)) {
				jQuery("#identityNoError").show();
				jQuery("#identityNo").focus();
				return;
			} else {
				jQuery("#identityNoError").hide();
			}
			var province = jQuery("#province").val();
			var city = jQuery("#city").val();
			var address = jQuery("#address").val();
			if (oMod.isAddress(address) == false) {
				jQuery("#addressError").show();
				jQuery("#address").focus();
				return;
			} else {
				jQuery("#addressError").hide();
			}
			var postcode = jQuery("#postcode").val();
			if (oMod.isPostcode(postcode) == false) {
				jQuery("#postcodeError").show();
				jQuery("#postcode").focus();
				return;
			} else {
				jQuery("#postcodeError").hide();
			}
			var mobile = jQuery("#mobile").val();

			if (oMod.isMobile(mobile) == false) {
				jQuery("#mobileError").show();
				jQuery("#mobile").focus();
				return;
			} else {
				jQuery("#mobileError").hide();
			}
			var imgPath = jQuery("#imgPath").val();
			
			var token = jQuery("#token").val();

			$.ajax({
				url : "/jifen/ucenter/setaddress.do",
				//context : document.body,
				//contentType : "application/json; charset=utf-8",
				dataType : "json",
				type : "POST",
				data : {
					uid : "",
					name : name,
					identityType : identityType,
					identityNo : identityNo,
					provinceId : province,
					cityId : city,
					address : address,
					postcode : postcode,
					mobile : mobile,
					imgPath : imgPath,
					
					token : token
				},
				success : function(data) { 
					if (data.result == "success") {						
				        if (typeof(gFrom) != undefined && typeof(gStage) != undefined && gFrom != null && gFrom != "" && gStage != null && gStage != "") {
				            successUrl  = (successUrl + "?from=" + gFrom  + "&stage=" + gStage);
				        }				    
					    window.location.href = successUrl;
					} else {
						if (data.msg == "login_required") {
							window.location.href = "/jifen/login.do?url=" + data.referer;
						}
					}
				}
			});
		}

		Address._initialized = true;
	}

}