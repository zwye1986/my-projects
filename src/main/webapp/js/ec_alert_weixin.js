(function($) {
	$.alerts = {
		verticalOffset: 0, 
		horizontalOffset: 0, 
		repositionOnResize: true, /*中央*/
		overlayOpacity: .01,
		overlayColor: '#FFF', 
		draggable: true, 
		okButton: '&nbsp;确认&nbsp;',
		cancelButton: '&nbsp;取消&nbsp;', 
		dialogClass: null,
		
		changeMessage:function(message){
			$("#popup_message").text(message);
		},
		// Public methods
		
		loading: function(message, title, callback) {
			if( title == null ) title = 'loading';
			$.alerts._show(title, message, null, 'loading', function(result) {
				if( callback ) 
				{
					try{callback(result);}catch(err){}
				}
			},null,null,null,null);
		},
		alert: function(message, title, callback) {
			if( title == null ) title = '提示';
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			},null,null,null,null);
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = '请确认';
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			},null,null,null,null);
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = '请填写';
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			},null,null,null,null);
		},
		openBox:function(obj,title,w,h,submit,cancel,callback) {
			if( title == null ) title = '提示';
			$.alerts._show(title, obj, null, 'openBox', function(result) {
				if( callback ) callback(result);
			},w,h,submit,cancel);
		},
		overAlert:function(message,overTime) {
			$.alerts._overShow(message,overTime);
		},
		_overShow:function (msg,t) {
			if (t==null) t=3000;
			var u=t+600;
			$("body").append(
			  '<div id="over_container" style="display:none">' +
			      '<div id="over_message"></div>' +
			  '</div>');
			$("#over_message").text(msg).html( $("#over_message").text().replace(/\n/g, '<br />') );
			if( $.alerts.dialogClass ) $("#over_container").addClass($.alerts.dialogClass);
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			$("#over_container").css({
				position: pos,
				zIndex: 99999,
				width:350,
				padding: 0,
				margin: 0
			}).show("fast");
			$("#over_container").css({
				minWidth: $("#over_container").outerWidth(),
				maxWidth: $("#over_container").outerWidth()
			});
			$.alerts._overReposition();
			
			setTimeout(function (){$("#over_container").hide("fast");},t);
			setTimeout(function(){$("#over_container").remove();},u);

		},
		_overReposition: function() {
			var top =4;
			var left = (($(window).width() / 2) - ($("#over_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) left = left-175;			
			$("#over_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		// 私有方法
		_show: function(title, msg, value, type, callback,w,h,submit,cancel) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			$("body").append(
			  '<div id="popup_container" style="display:none ;width:83%">' +
			    '<h1 id="popup_title" style="width:93%"></h1>' +
				'<em id="ctl"></em><em id="cbl"></em>'+		//corners
				'<em id="ctr"></em><em id="cbr"></em>'+		//corners 
			    '<div id="popup_content" style=\"background-image: url('+imgPath_session+'/info.gif); width:100%;\" >' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			//if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			//var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			var pos = 'absolute';
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			}).fadeIn("fast");
			$("#popup_title").text(title);
			//$("#popup_content").addClass(type);		
			if(type!='openBox')$("#popup_message").text(msg).html( $("#popup_message").text().replace(/\n/g, '<br />') );
			/**			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			*/			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);

			
			switch( type ) {
				case 'loading':
					$("#popup_message").after('<div id="popup_panel"><img src="'+imgPath_session+'/loading-2.gif"></div>');
				break;
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width()-10);
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
				case 'openBox':
					$("#popup_message").append($(msg).html());
					if(w) {$("#popup_container").css({width:w+'px'});}
					if(h) {$("#popup_container").css({height:h+'px'});
							$("#popup_message").css({height:(h-48)+'px'});
						}
					$.alerts._reposition();

					if(submit){$(submit).click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});}
					if(cancel)$(cancel).click( function() {
						$.alerts._hide();return false;
						if( callback ) callback(false);
					});
				break;
			}

			$("#popup_close").click( function() {
				$.alerts._hide();
				if( callback ) callback(false);
			});	
					
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
			/*DIV增加IFRAM*/
			if( $.browser.msie && parseInt($.browser.version) <= 6 )
				$('#popup_container').bgiframe();
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		

		/*锁屏*/
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var vtop=$(document).scrollTop();
			var top = (($(window).height() / 2) - ($("#popup_container").height() / 2)) + $.alerts.verticalOffset+vtop;
			var left = (($(window).width() / 2) - ($("#popup_container").width() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
	}
})(jQuery);