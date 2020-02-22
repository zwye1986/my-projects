(function ($) {
    $.showMask = function () {
        if (!$("#MaskDiv").length) {
            $(document.body).append("<div id=\"MaskDiv\" style=\"display:none\"></div>");
        }
        $("#MaskDiv").css({ width: "100%", height: $(document).height() + "px", position: "absolute", left: "0px", top: "0px", opacity: 0.3, background: "#999999", zIndex: 8000 });
        $("select").attr("disabled", "disabled");
        $("#MaskDiv").show();
    };
    $.hideMask = function () {
        $("select").removeAttr("disabled");
        $("#MaskDiv").hide();
    };
    $.showWaitting = function (Tip,path) {
        if (!$("#WaittingDiv").length) {
            $(document.body).append("<div id=\"WaittingDiv\"><div id=\"WaittingTitle\" class=\"WaittingTitle\"></div><div class=\"WaittingContent\"><img src=\"" + path +"/images/jquery_maklon_loadding2.gif\" width=\"220\" height=\"19\" alt=\"waitting...\" /></div></div>");
        }
        $("#WaittingTitle").html("&nbsp;" + Tip + "……");
        $("#WaittingDiv").css({ left: ($(window).width() - $("#WaittingDiv").width()) / 2 + "px", top: ($(window).height() - $("#WaittingDiv").height()) / 2 + $(window).scrollTop() + "px" });
        $("#WaittingDiv").show();
        $(window).scroll(function () {
            if ($("#WaittingDiv").css("display") == "block") {
                $("#WaittingDiv").css("top", ($(window).height() - $("#WaittingDiv").height()) / 2 + $(window).scrollTop() + "px");
            }
        });
    };
    $.hideWaitting = function () {
        $("#WaittingDiv").hide();
    };
    $.showDialog = function (dialogTitle, dialogContent, Masked, fn) {
        if (!$("#DialogDiv").length) {
            if (fn) {
                $(document.body).append("<div id=\"DialogDiv\"><div id=\"DialogTitle\"><div id=\"DialogTitleText\"><\/div><div id=\"DialogTitleButton\"><img src=\"/images\/btn_close.png\" width=\"25\" height=\"15\" style=\"cursor:pointer\" onClick=\"$.closeDialog();\" \/><\/div><\/div><div id=\"DialogContent\"><\/div><div id=\"DialogButtons\"><input name=\"btn_dialogclose\" type=\"submit\" class=\"Dialog_Button\" id=\"btn_dialogclose\" value=\"确定\" onClick=\"" + fn + "();\" \/><\/div><\/div>");
            } else {
                $(document.body).append("<div id=\"DialogDiv\"><div id=\"DialogTitle\"><div id=\"DialogTitleText\"><\/div><div id=\"DialogTitleButton\"><img src=\"/images\/btn_close.png\" width=\"25\" height=\"15\" style=\"cursor:pointer\" onClick=\"$.closeDialog();\" \/><\/div><\/div><div id=\"DialogContent\"><\/div><div id=\"DialogButtons\"><input name=\"btn_dialogclose\" type=\"submit\" class=\"Dialog_Button\" id=\"btn_dialogclose\" value=\"确定\" onClick=\"$.closeDialog();\" \/><\/div><\/div>");
            }
        }
        $("#DialogTitleText").html(dialogTitle);
        $("#DialogContent").html(dialogContent);
        $("#DialogDiv").css({ left: ($(window).width() - $("#DialogDiv").width()) / 2 + "px", top: ($(window).height() - $("#DialogDiv").height()) / 2 + $(window).scrollTop() + "px" });
        if (Masked) $.showMask();
        $("#DialogDiv").show();
        $(window).scroll(function () {
            if ($("#DialogDiv").css("display") == "block") {
                $("#DialogDiv").css("top", ($(window).height() - $("#DialogDiv").height()) / 2 + $(window).scrollTop() + "px");
            }
        });
    };
    $.closeDialog = function () {
        $.hideMask();
        $("#DialogDiv").hide();
    };
    $.showConfirm = function (confirmTitle, confirmContent, fn, Masked) {
        if (!$("#ConfirmDiv").length) {
            $(document.body).append("<div id=\"ConfirmDiv\"><div id=\"ConfirmTitle\"><div id=\"ConfirmTitleText\"><\/div><div id=\"ConfirmTitleButton\"><img src=\"/images\/btn_close.png\" width=\"25\" height=\"15\" style=\"cursor:pointer\" onClick=\"$.closeConfirm();\" \/><\/div><\/div><div id=\"ConfirmContent\"><\/div><div id=\"ConfirmButtons\"><input name=\"btn_Confirmclose\" type=\"submit\" class=\"Button\" id=\"btn_Confirmclose\" value=\"确定\" onClick=\"" + fn + "();\" \/>&nbsp;<input name=\"btn_Confirmclose\" type=\"submit\" class=\"Button\" id=\"btn_Confirmclose\" value=\"取消\" onClick=\"$.closeConfirm();\" \/><\/div><\/div>");
        }
        $("#ConfirmTitleText").html(confirmTitle);
        $("#ConfirmContent").html(confirmContent);
        $("#ConfirmDiv").css({ left: ($(window).width() - $("#ConfirmDiv").width()) / 2 + "px", top: ($(window).height() - $("#ConfirmDiv").height()) / 2 + $(window).scrollTop() + "px" });
        if (Masked) $.showMask();
        $("#ConfirmDiv").show();
        $(window).scroll(function () {
            if ($("#ConfirmDiv").css("display") == "block") {
                $("#ConfirmDiv").css("top", ($(window).height() - $("#ConfirmDiv").height()) / 2 + $(window).scrollTop() + "px");
            }
        });
    };
    $.closeConfirm = function () {
        $.hideMask();
        $("#ConfirmDiv").hide();
    };
    $.showPanel = function (panelTitle, panelSrc, panelWidth, panelHeight, Masked, BaseURl) {
        if (!$("#PanelDiv").length) {
            $(document.body).append("<div id=\"PanelDiv\" style=\" background:#fff;  border:1px solid #cecece; width:" + panelWidth + "px; height:" + (panelHeight + 30) + "px;\"><div id=\"PanelDivTitleButtons\"><div id=\"PanelDivTitle\" style=\"width:" + (panelWidth - 55) + "px;\"><em>" + panelTitle + "</em></div><div id=\"PanelDivButton\" onclick=\"$.closePanel();\"><img src=\""+BaseURl+"/images/btn_close.png\"  alt=\"关闭\" /></div></div><div style=\"clear:both; width:" + panelWidth + "px; height:" + panelHeight + "px;\"><iframe id=\"PaneliFrame\" src=\"about:blank\" width=\"" + panelWidth + "\" height=\"" + panelHeight + "\" frameborder=\"0\" scrolling=\"no\"></iframe></div></div>");
        } else {
            $("#PanelDiv").empty();
            $("#PanelDiv").remove();
            $(document.body).append("<div id=\"PanelDiv\" style=\" background:#fff;  border:1px solid #cecece; width:" + panelWidth + "px; height:" + (panelHeight + 30) + "px;\"><div id=\"PanelDivTitleButtons\"><div id=\"PanelDivTitle\" style=\"width:" + (panelWidth - 55) + "px;\"><em>" + panelTitle + "</em></div><div id=\"PanelDivButton\" onclick=\"$.closePanel();\"><img src=\""+BaseURl+"/images/btn_close.png\"  alt=\"关闭\" /></div></div><div style=\"clear:both; width:" + panelWidth + "px; height:" + panelHeight + "px;\"><iframe id=\"PaneliFrame\" src=\"about:blank\" width=\"" + panelWidth + "\" height=\"" + panelHeight + "\" frameborder=\"0\" scrolling=\"no\"></iframe></div></div>");
        }
        $("#PanelDiv").css({ left: ($(window).width() - $("#PanelDiv").width()) / 2 + "px", top: ($(window).height() - $("#PanelDiv").height()) / 2 + $(window).scrollTop() + "px" });
        if (Masked) $.showMask();
        $("#PanelDiv").show();
        $("#PaneliFrame").attr("src", panelSrc);
        $(window).scroll(function () {
            if ($("#PanelDiv").css("display") == "block") {
                $("#PanelDiv").css("top", ($(window).height() - $("#PanelDiv").height()) / 2 + $(window).scrollTop() + "px");
            }
        });
    };
    
    
    $.showPanelVip = function (panelTitle, panelSrc, panelWidth, panelHeight, Masked, BaseURl) {
        if (!$("#PanelDiv").length) {
            $(document.body).append("<div id=\"PanelDiv\" style=\" background:#fff;  border:5px solid rgb(138, 138, 138); width:" + panelWidth + "px; height:" + (panelHeight + 30) + "px;\"><div id=\"PanelDivTitleButtons\" style=\"border-bottom: 1px solid rgb(209, 209, 209);background:#fff\"><div id=\"PanelDivTitle\" style=\"width:" + (panelWidth - 55) + "px;background:#fff;\"><em style=\"color:rgb(0,0,0)\">" + panelTitle + "</em></div><div id=\"PanelDivButton\" onclick=\"$.closePanel();\"><img src=\""+BaseURl+"/images/vip/close_btn.png\"  alt=\"关闭\" /></div></div><div style=\"clear:both; width:" + panelWidth + "px; height:" + panelHeight + "px;\"><iframe id=\"PaneliFrame\" src=\"about:blank\" width=\"" + panelWidth + "\" height=\"" + panelHeight + "\" frameborder=\"0\" scrolling=\"no\"></iframe></div></div>");
        } else {
            $("#PanelDiv").empty();
            $("#PanelDiv").remove();
            $(document.body).append("<div id=\"PanelDiv\" style=\" background:#fff;  border:5px solid rgb(138, 138, 138); width:" + panelWidth + "px; height:" + (panelHeight + 30) + "px;\"><div id=\"PanelDivTitleButtons\" style=\"border-bottom: 1px solid rgb(209, 209, 209);background:#fff\"><div id=\"PanelDivTitle\" style=\"width:" + (panelWidth - 55) + "px;background:#fff;\"><em style=\"color:rgb(0,0,0)\">" + panelTitle + "</em></div><div id=\"PanelDivButton\" onclick=\"$.closePanel();\"><img src=\""+BaseURl+"/images/vip/close_btn.png\"  alt=\"关闭\" /></div></div><div style=\"clear:both; width:" + panelWidth + "px; height:" + panelHeight + "px;\"><iframe id=\"PaneliFrame\" src=\"about:blank\" width=\"" + panelWidth + "\" height=\"" + panelHeight + "\" frameborder=\"0\" scrolling=\"no\"></iframe></div></div>");
        }
        $("#PanelDiv").css({ left: ($(window).width() - $("#PanelDiv").width()) / 2 + "px", top: ($(window).height() - $("#PanelDiv").height()) / 2 + $(window).scrollTop() + "px" });
        if (Masked) $.showMask();
        $("#PanelDiv").show();
        $("#PaneliFrame").attr("src", panelSrc);
        $(window).scroll(function () {
            if ($("#PanelDiv").css("display") == "block") {
                $("#PanelDiv").css("top", ($(window).height() - $("#PanelDiv").height()) / 2 + $(window).scrollTop() + "px");
            }
        });
    };
    
    
    $.closePanel = function () {
        $.hideMask();
        $("#PanelDiv").hide();
    };
    $.showPanelBaseOnjQueryUI = function (params) {
        if (!$("#PanelDiv_jQueryUI").length) {
            $(document.body).append("<div id=\"PanelDiv_jQueryUI\" title=\"" + (params.title || "") + "\"><div>");
        }
        $("#PanelDiv_jQueryUI").html("<iframe src=\"" + (params.src || "about:blank")
            + "\" width=\"" + (params.iframewidth || params.width - 30 || "auto")
            + "\" height=\"" + (params.iframeheight || params.height - 30 || "auto")
            + "\" frameborder=\"0\" scrolling=\"no\"></iframe>");
        $("#PanelDiv_jQueryUI").dialog(params);
    };
    $.closePanelBaseOnjQueryUI = function () {
        $("#PanelDiv_jQueryUI").dialog("close");
    };
    $.showLoadding1 = function (obj, text) {
        $("#" + obj).html("<div class=\"LoaddingDiv\">" + text + "</div>");
    }
})(jQuery);