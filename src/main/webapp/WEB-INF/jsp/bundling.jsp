	<%@ page language="java" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width,initial-scale=1.0,user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<script src="${ctx}/js/jquery/jquery-1.6.4.js" type="text/javascript"></script>
	<meta charset="UTF-8">
	<!--
	Created using JS Bin
	http://jsbin.com
	
	Copyright (c) 2014 by marvin1023 (http://jsbin.com/ceqexapa/12/edit)
	
	Released under the MIT license: http://jsbin.mit-license.org
	-->
	<meta name="viewport"
		content="width=device-width, initial-scale=1, user-scalable=no,  minimal-ui">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta name="format-detection" content="telephone=no, email=no">
	<title>微信绑定蛙宝账号</title>
	<style id="jsbin-css">
	/**
	 * 1. Set default font family to sans-serif.
	 * 2. Prevent iOS text size adjust after orientation change, without disabling
	 *    user zoom.
	 * 0. sandal's style
	 */
	html {
		font-family: sans-serif;
		-ms-text-size-adjust: 100%;
		-webkit-text-size-adjust: 100%;
		font-size: 62.5%;
	}
	/**
	 * 1. Remove default margin
	 * 0. sandal's style.
	 */
	body {
		margin: 0;
		font-size: 1.4rem;
		line-height: 1.5;
		color: #333333;
		background-color: white;
		height: 100%;
		overflow-x: hidden;
		-webkit-overflow-scrolling: touch;
	}
	/* HTML5 display definitions
	   ========================================================================== */
	/**
	 * Correct `block` display not defined for any HTML5 element in IE 8/9.
	 * Correct `block` display not defined for `details` or `summary` in IE 10/11 and Firefox.
	 * Correct `block` display not defined for `main` in IE 11.
	 */
	article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary
		{
		display: block;
	}
	/**
	 * 1. Correct `inline-block` display not defined in IE 8/9.
	 * 2. Normalize vertical alignment of `progress` in Chrome, Firefox, and Opera.
	 */
	audio,canvas,progress,video {
		display: inline-block;
		vertical-align: baseline;
	}
	/**
	 * Prevent modern browsers from displaying `audio` without controls.
	 * Remove excess height in iOS 5 devices.
	 */
	audio:not ([controls] ) {
		display: none;
		height: 0;
	}
	
	/**
	 * Address `[hidden]` styling not present in IE 8/9/10.
	 * Hide the `template` element in IE 8/9/11, Safari, and Firefox < 22.
	 */
	[hidden],template {
		display: none;
	}
	/* Links
	   ========================================================================== */
	/**
	 * 1. Remove the gray background color from active links in IE 10.
	 * 2. Improve readability when focused and also mouse hovered in all browsers.
	 * 0. sandal's style.
	 */
	a {
		background: transparent;
		text-decoration: none;
		-webkit-tap-highlight-color: transparent;
		color: #0088cc;
	}
	
	a:active {
		outline: 0;
	}
	
	a:active {
		color: #006699;
	}
	/* Text-level semantics
	   ========================================================================== */
	/**
	 * Address styling not present in IE 8/9/10/11, Safari, and Chrome.
	 */
	abbr[title] {
		border-bottom: 1px dotted;
	}
	/**
	 * Address style set to `bolder` in Firefox 4+, Safari, and Chrome.
	 */
	b,strong {
		font-weight: bold;
	}
	/**
	 * Address styling not present in Safari and Chrome.
	 */
	dfn {
		font-style: italic;
	}
	/**
	 * Address styling not present in IE 8/9.
	 */
	mark {
		background: #ff0;
		color: #000;
	}
	/**
	 * Address inconsistent and variable font size in all browsers.
	 */
	small {
		font-size: 80%;
	}
	/**
	 * Prevent `sub` and `sup` affecting `line-height` in all browsers.
	 */
	sub,sup {
		font-size: 75%;
		line-height: 0;
		position: relative;
		vertical-align: baseline;
	}
	
	sup {
		top: -0.5em;
	}
	
	sub {
		bottom: -0.25em;
	}
	/* Embedded content
	   ========================================================================== */
	/**
	 * 1. Remove border when inside `a` element in IE 8/9/10.
	 * 2. Improve image quality when scaled in IE 7.
	 * 0. sandal's style.
	 */
	img {
		border: 0;
		vertical-align: middle;
	}
	/**
	 * Correct overflow not hidden in IE 9/10/11.
	 */
	svg:not (:root ) {
		overflow: hidden;
	}
	/* Grouping content
	   ========================================================================== */
	/**
	 * Address differences between Firefox and other browsers.
	 */
	hr {
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		height: 0;
	}
	/**
	 * 1. Contain overflow in all browsers.
	 * 2. Improve readability of pre-formatted text in all browsers.
	 */
	pre {
		overflow: auto;
		white-space: pre;
		white-space: pre-wrap;
		word-wrap: break-word;
	}
	/**
	 * 1. Address odd `em`-unit font size rendering in all browsers.
	 */
	code,kbd,pre,samp {
		font-family: monospace, monospace;
		font-size: 1em;
	}
	/* Forms
	   ========================================================================== */
	/**
	 * Known limitation: by default, Chrome and Safari on OS X allow very limited
	 * styling of `select`, unless a `border` property is set.
	 */
	/**
	 * 1. Correct color not being inherited.
	 *    Known issue: affects color of disabled elements.
	 * 2. Correct font properties not being inherited.
	 * 3. Address margins set differently in Firefox 4+, Safari, and Chrome.
	 */
	button,input,optgroup,select,textarea {
		color: inherit;
		font: inherit;
		margin: 0;
	}
	/**
	 * Address `overflow` set to `hidden` in IE 8/9/10/11.
	 */
	button {
		overflow: visible;
	}
	/**
	 * Address inconsistent `text-transform` inheritance for `button` and `select`.
	 * All other form control elements do not inherit `text-transform` values.
	 * Correct `button` style inheritance in Firefox, IE 8/9/10/11, and Opera.
	 * Correct `select` style inheritance in Firefox.
	 */
	button,select {
		text-transform: none;
	}
	/**
	 * 1. Avoid the WebKit bug in Android 4.0.* where (2) destroys native `audio`
	 *    and `video` controls.
	 * 2. Correct inability to style clickable `input` types in iOS.
	 * 3. Improve usability and consistency of cursor style between image-type
	 *    `input` and others.
	 */
	button,html input[type="button"],input[type="reset"],input[type="submit"]
		{
		-webkit-appearance: button;
		cursor: pointer;
	}
	/**
	 * Re-set default cursor for disabled elements.
	 */
	button[disabled],html input[disabled] {
		cursor: default;
	}
	
	/**
	 * Remove inner padding and border in Firefox 4+.
	 */
	button::-moz-focus-inner,input::-moz-focus-inner {
		border: 0;
		padding: 0;
	}
	/**
	 * Address Firefox 4+ setting `line-height` on `input` using `!important` in
	 * the UA stylesheet.
	 */
	input {
		line-height: normal;
	}
	/**
	 * It's recommended that you don't attempt to style these elements.
	 * Firefox's implementation doesn't respect box-sizing, padding, or width.
	 *
	 * 1. Address box sizing set to `content-box` in IE 8/9/10.
	 * 2. Remove excess padding in IE 8/9/10.
	 */
	input[type="checkbox"],input[type="radio"] {
		box-sizing: border-box;
		padding: 0;
	}
	
	/**
	 * Fix the cursor style for Chrome's increment/decrement buttons. For certain
	 * `font-size` values of the `input`, it causes the cursor style of the
	 * decrement button to change from `default` to `text`.
	 */
	input[type="number"]::-webkit-inner-spin-button,input[type="number"]::-webkit-outer-spin-button
		{
		height: auto;
	}
	/**
	 * 1. Address `appearance` set to `searchfield` in Safari and Chrome.
	 * 2. Address `box-sizing` set to `border-box` in Safari and Chrome
	 *    (include `-moz` to future-proof).
	 */
	input[type="search"] {
		-webkit-appearance: textfield;
		-moz-box-sizing: border-box;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}
	
	/**
	 * Remove inner padding and search cancel button in Safari and Chrome on OS X.
	 * Safari (but not Chrome) clips the cancel button when the search input has
	 * padding (and `textfield` appearance).
	 */
	input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration
		{
		-webkit-appearance: none;
	}
	/**
	 * Define consistent border, margin, and padding.
	 */
	fieldset {
		border: 1px solid #fff;
		margin: 0 2px;
		padding: 0.35em 0.625em 0.75em;
	}
	/**
	 * 1. Correct `color` not being inherited in IE 8/9/10/11.
	 * 2. Remove padding so people aren't caught out if they zero out fieldsets.
	 */
	legend {
		border: 0;
		padding: 0;
	}
	/**
	 * 1. Remove default vertical scrollbar in IE 8/9/10/11.
	 * 0. sandal's style
	 */
	textarea {
		overflow: auto;
		resize: vertical;
	}
	/**
	 * Don't inherit the `font-weight` (applied by a rule above).
	 * NOTE: the default cannot safely be changed in Chrome and Safari on OS X.
	 */
	optgroup {
		font-weight: bold;
	}
	/* Tables
	   ========================================================================== */
	/**
	 * Remove most spacing between table cells.
	 */
	table {
		border-collapse: collapse;
		border-spacing: 0;
	}
	
	td,th {
		padding: 0;
	}
	
	html,button,input,select,textarea {
		font-family: "Helvetica Neue", Helvetica, STHeiTi, Arial, sans-serif;
	}
	
	h1,h2,h3,h4,h5,h6,p,figure,form,blockquote {
		margin: 0;
	}
	
	ul,ol,li,dl,dd {
		margin: 0;
		padding: 0;
	}
	
	ul,ol {
		list-style: none outside none;
	}
	
	h1,h2,h3 {
		line-height: 2;
		font-weight: normal;
	}
	
	h1 {
		font-size: 1.8rem;
	}
	
	h2 {
		font-size: 1.6rem;
	}
	
	h3 {
		font-size: 1.4rem;
	}
	
	input:-moz-placeholder,textarea:-moz-placeholder {
		color: #cccccc;
	}
	
	input::-moz-placeholder,textarea::-moz-placeholder {
		color: #cccccc;
	}
	
	input:-ms-input-placeholder,textarea:-ms-input-placeholder {
		color: #cccccc;
	}
	
	input::-webkit-input-placeholder,textarea::-webkit-input-placeholder {
		color: #cccccc;
	}
	
	* {
		-webkit-box-sizing: border-box;
		-moz-box-sizing: border-box;
		box-sizing: border-box;
	}
	
	@font-face {
		font-family: icomoon;
		font-weight: normal;
		font-style: normal;
		src: url("../fonts/icomoon.eot");
		src: url("../fonts/icomoon.eot?#iefix") format("eot"),
			url("../fonts/icomoon.svg#icomoon") format("svg"),
			url("../fonts/icomoon.woff") format("woff"),
			url("../fonts/icomoon.ttf") format("truetype");
	}
	
	.if-wifi::before,.if-comment::before,.if-user::before,.if-map::before,.if-cart::before,.if-date::before,.if-music::before,.if-home::before,.if-image::before,.if-video::before,.if-tags::before,.if-qrcode::before,.if-forward::before,.if-back::before,.if-loading::before,.if-refresh::before,.if-search::before,.if-con::before,.if-gift::before,.if-trashcan::before,.if-logout::before,.if-files::before,.if-question::before,.if-info::before,.if-alert::before,.if-arrow-up::before,.if-arrow-right::before,.if-arrow-down::before,.if-arrow-left::before,.if-checkbox-checked::before,.if-checkbox::before,.if-radio-checked::before,.if-radio::before,.if-mail::before,.if-heart::before,.if-star::before,.if-voice::before,.if-voice-no::before,.if-ellipsis::before,.if-pencil::before,.if-list::before,.if-lock::before,.if-phone::before,.if-target::before,.if-card::before,.if-checkmark::before,.if-cross::before,.if-plus::before,.if-minus::before,.if-angle-up::before,.if-angle-right::before,.if-angle-down::before,.if-angle-left::before
		{
		display: inline-block;
		vertical-align: -2px;
		font-family: icomoon;
		font-size: 1.6rem;
		line-height: 1;
		speak: none;
		font-style: normal;
		font-weight: normal;
		font-variant: normal;
		text-transform: none;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}
	
	.if-wifi::before {
		content: "\e62f";
	}
	
	.if-comment::before {
		content: "\e601";
	}
	
	.if-user::before {
		content: "\e632";
	}
	
	.if-map::before {
		content: "\e61b";
	}
	
	.if-cart::before {
		content: "\e606";
	}
	
	.if-date::before {
		content: "\e607";
	}
	
	.if-music::before {
		content: "\e60f";
	}
	
	.if-home::before {
		content: "\e600";
	}
	
	.if-image::before {
		content: "\e602";
	}
	
	.if-video::before {
		content: "\e61d";
	}
	
	.if-tags::before {
		content: "\e61f";
	}
	
	.if-qrcode::before {
		content: "\e605";
	}
	
	.if-forward::before {
		content: "\e608";
	}
	
	.if-back::before {
		content: "\e609";
	}
	
	.if-loading::before {
		content: "\e60a";
	}
	
	.if-refresh::before {
		content: "\e60b";
	}
	
	.if-search::before {
		content: "\e60c";
	}
	
	.if-con::before {
		content: "\e60e";
	}
	
	.if-gift::before {
		content: "\e620";
	}
	
	.if-trashcan::before {
		content: "\e621";
	}
	
	.if-logout::before {
		content: "\e622";
	}
	
	.if-files::before {
		content: "\e624";
	}
	
	.if-question::before {
		content: "\e616";
	}
	
	.if-info::before {
		content: "\e617";
	}
	
	.if-alert::before {
		content: "\e60d";
	}
	
	.if-arrow-up::before {
		content: "\e604";
	}
	
	.if-arrow-right::before {
		content: "\e623";
	}
	
	.if-arrow-down::before {
		content: "\e611";
	}
	
	.if-arrow-left::before {
		content: "\e612";
	}
	
	.if-checkbox-checked::before {
		content: "\e625";
	}
	
	.if-checkbox::before {
		content: "\e626";
	}
	
	.if-radio-checked::before {
		content: "\e627";
	}
	
	.if-radio::before {
		content: "\e628";
	}
	
	.if-mail::before {
		content: "\f003";
	}
	
	.if-heart::before {
		content: "\f08a";
	}
	
	.if-star::before {
		content: "\f006";
	}
	
	.if-voice::before {
		content: "\f130";
	}
	
	.if-voice-no::before {
		content: "\f131";
	}
	
	.if-ellipsis::before {
		content: "\f142";
	}
	
	.if-pencil::before {
		content: "\e61c";
	}
	
	.if-list::before {
		content: "\e61e";
	}
	
	.if-lock::before {
		content: "\e62c";
	}
	
	.if-phone::before {
		content: "\e610";
	}
	
	.if-target::before {
		content: "\e603";
	}
	
	.if-card::before {
		content: "\e613";
	}
	
	.if-checkmark::before {
		content: "\e614";
	}
	
	.if-cross::before {
		content: "\e615";
	}
	
	.if-plus::before {
		content: "\e619";
	}
	
	.if-minus::before {
		content: "\e618";
	}
	
	.if-angle-up::before {
		content: "\e636";
	}
	
	.if-angle-right::before {
		content: "\e62a";
	}
	
	.if-angle-down::before {
		content: "\e629";
	}
	
	.if-angle-left::before {
		content: "\e61a";
	}
	
	html,body,.wrap-page {
		height: 100%;
	}
	
	.fixed-top {
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		z-index: 960;
	}
	
	.fixed-bottom {
		position: fixed;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 940;
	}
	
	.header,.footer {
		height: 44px;
	}
	
	.header-sub {
		position: fixed;
		left: 0;
		right: 0;
		top: 44px;
		height: 44px;
	}
	
	.header+.wrap-page {
		padding-top: 44px;
	}
	
	.header+.header-sub+.wrap-page {
		padding-top: 88px;
	}
	
	.footer+.wrap-page>.page {
		padding-bottom: 44px;
	}
	
	.header+.footer+.wrap-page {
		padding-top: 44px;
	}
	
	.header-sub+.footer+.wrap-page {
		padding-top: 88px;
	}
	
	.overlay {
		z-index: 980;
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background-color: rgba(0, 0, 0, 0.8);
		-webkit-box-pack: center;
		-ms-flex-pack: center;
		-webkit-justify-content: center;
		justify-content: center;
		-webkit-box-align: center;
		-ms-flex-align: center;
		-webkit-align-items: center;
		align-items: center;
	}
	
	.fl {
		float: left;
	}
	
	.fr {
		float: right;
	}
	
	input:focus {
		outline: 0 none;
	}
	
	.header {
		background: url(images/logo_bg.png) repeat-x;
		color: #fff;
		text-align: center;
	}
	
	.footer {
		background: #0078e7;
		color: #fff;
		text-align: center;
	}
	
	.header {
		line-height: 44px;
	}
	
	.header .page-title {
		font-size: 18px;
		margin-left: 50px;
		margin-right: 50px;
		line-height: 50px;
	}
	
	.header .page-title--tabs {
		margin-top: 5px;
		margin-bottom: 5px;
		display: inline-block;
		border: 1px solid #005eb4;
		border-radius: 5px;
		line-height: 32px;
	}
	
	.header .page-title--tabs span {
		padding: 0 10px;
		display: inline-block;
		background-color: #fff;
		color: #0078e7;
	}
	
	.header .page-title--tabs span.active {
		background-color: #0078e7;
		color: #fff;
	}
	
	.header .page-title--tabs span:first-child {
		border-radius: 5px 0 0 5px;
	}
	
	.header .page-title--tabs span:last-child {
		border-radius: 0 5px 5px 0;
	}
	
	.header .header-icon {
		color: #fff;
		width: 44px;
		height: 44px;
		background-color: #006bce;
	}
	
	.icon-back::before {
		content: "";
		display: inline-block;
		display: inline-block;
		width: 10px;
		height: 10px;
		vertical-align: middle;
		border-left: 1px solid white;
		border-bottom: 1px solid white;
		-webkit-transform: rotate(45deg);
		transform: rotate(45deg);
	}
	
	.icon-label-orange {
		border: 1px solid #FF9A14;
		color: #FF9A14;
		font-size: 12px;
		padding: 2px;
		font-style: normal;
		line-height: 1;
	}
	
	.icon-toggle {
		height: 28px;
		width: 56px;
		position: relative;
		display: block;
		background-color: #CCCCCC;
		border-radius: 12px;
		overflow: hidden;
		z-index: 1;
	}
	
	.icon-toggle::before {
		content: "";
		height: 28px;
		width: 56px;
		position: absolute;
		left: -56px;
		background-color: #099FDE;
		border-radius: 14px;
		transition: left 0.2s ease 0s;
		z-index: 2;
	}
	
	.icon-toggle.active::before {
		left: 0;
	}
	
	.icon-toggle::after {
		content: "";
		height: 24px;
		width: 24px;
		background-color: #fff;
		border-radius: 14px;
		position: absolute;
		left: 2px;
		top: 2px;
		transition: left 0.2s ease 0s;
		z-index: 3;
	}
	
	.icon-toggle.active::after {
		left: 30px;
	}
	
	.header-sub {
		background-color: #fff;
		z-index: 940;
	}
	
	.search-block {
		border-bottom: 1px solid #cccccc;
		padding: 5px 10px;
		position: relative;
	}
	
	.search-block .form-text {
		border-radius: 15px;
		border: 1px solid #cccccc;
		padding: 4px 30px 4px 10px;
		height: 30px;
		width: 100%;
	}
	
	.search-block .if-search {
		position: absolute;
		color: #cccccc;
		right: 10px;
		top: 5px;
		width: 30px;
		height: 30px;
		line-height: 30px;
		text-align: center;
	}
	
	.search-block .if-search:before {
		font-size: 20px;
	}
	
	.guide-title {
		padding-left: 10px;
		color: #0078e7;
	}
	
	.nav-primary {
		display: -webkit-box;
		display: -ms-flexbox;
		display: -webkit-flex;
		display: flex;
	}
	
	.nav-primary li {
		-webkit-box-flex: 1;
		-ms-flex: 1;
		-webkit-flex: 1;
		flex: 1;
		text-align: center;
		line-height: 44px;
		color: #fff;
	}
	
	.nav-primary li:hover {
		background-color: #006bce;
	}
	
	.nav-primary li a {
		color: #fff;
		display: block;
	}
	
	.nav-primary li i[class^="if-"] {
		display: block;
	}
	
	.nav-primary--mix li {
		line-height: 22px;
	}
	
	.box-group {
		margin-bottom: 20px;
		border: 1px solid #cccccc;
		background-color: #fff;
		border-radius: 5px;
	}
	
	.box-group>div:first-child,.box-group>li:first-child {
		border-top: none;
		border-radius: 5px 5px 0 0;
	}
	
	.box-group>div:last-child,.box-group>li:last-child {
		border-bottom: none;
		border-radius: 0 0 5px 5px;
	}
	
	.line-list {
		margin-bottom: 10px;
	}
	
	.line-list li {
		border-bottom: 1px solid #cccccc;
		background-color: #fff;
		line-height: 32px;
		padding: 5px 10px;
		position: relative;
	}
	
	.line-list li:first-child {
		border-top: 1px solid #cccccc;
	}
	
	.line-list li:active,.line-list li:hover {
		background-color: whitesmoke;
	}
	
	.line-list li .title {
		line-height: 1.3;
		margin-bottom: 4px;
	}
	
	.line-list li .intro {
		line-height: 1.3;
		font-size: 1.2rem;
	}
	
	.line-list li i[class^="if-"] {
		margin-right: 10px;
	}
	
	.line-list .divider {
		padding-left: 10px;
		line-height: 22px;
		font-weight: bold;
		background-color: whitesmoke;
	}
	
	.wrap-page {
		-webkit-overflow-scrolling: touch;
	}
	
	ieldset {
		border: 0;
		padding: 0;
		margin: 0;
		margin-left: 30px;
	}
	/*--------------------*/
	#inputs input {
		background: #fff;
		padding: 15px 15px 15px 30px;
		margin: 0 0 40px 0;
		width: 100%; /* 353 + 2 + 45 = 400 */
		border: 1px solid #ccc;
		-moz-border-radius: 5px;
		-webkit-border-radius: 5px;
		border-radius: 5px;
		font-size: 18px;
	}
	
	#username {
		background-position: 5px -2px !important;
	}
	
	#password {
		background-position: 5px -52px !important;
	}
	
	#inputs input:focus {
		background-color: #fff;
		border: 2px solid #429acd;
		outline: none;
	}
	
	#submit {
		background-color: #ffb94b;
		background-image: -webkit-gradient(linear, left top, left bottom, from(#429acd),
			to(#2372a0));
		background-image: -webkit-linear-gradient(top, #429acd, #2372a0);
		background-image: -moz-linear-gradient(top, #429acd, #2372a0);
		background-image: -ms-linear-gradient(top, #429acd, #2372a0);
		background-image: -o-linear-gradient(top, #429acd, #2372a0);
		background-image: linear-gradient(top, #429acd, #2372a0);
		-moz-border-radius: 5px;
		-webkit-border-radius: 5px;
		border-radius: 5px;
		border-width: 1px;
		border-style: solid;
		float: left;
		height: 60px;
		padding: 0;
		width: 100%;
		cursor: pointer;
		font: bold 15px Arial, Helvetica;
		color: #fff;
		font-size: 20px;
	}
	
	#submit:hover,#submit:focus {
		background-color: #fddb6f;
		background-image: -webkit-gradient(linear, left top, left bottom, from(#2372a0),
			to(#429acd));
		background-image: -webkit-linear-gradient(top, #2372a0, #429acd);
		background-image: -moz-linear-gradient(top, #2372a0, #429acd);
		background-image: -ms-linear-gradient(top, #2372a0, #429acd);
		background-image: -o-linear-gradient(top, #2372a0, #429acd);
		background-image: linear-gradient(top, #2372a0, #429acd);
	}
	
	#submit:active {
		outline: none;
		-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
		-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
		box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	}
	
	#submit::-moz-focus-inner {
		border: none;
	}
	
	#actions a {
		color: #3151A2;
		float: right;
		line-height: 35px;
		margin-left: 10px;
	}
	
	form {
		margin-top: 3%
	}
	/*--------------------*/
	/*--------------------*/
	.header,.footer,.wrap-page {
		background-color: #FFFFFF;
		left: 0;
		position: absolute;
		right: 0;
	}
	
	.header,.footer {
		background-color: #FFFFFF;
		height: 60px;
		line-height: 44px;
		text-align: center;
		z-index: 900;
	}
	
	.header {
		background-image: -webkit-gradient(linear, left top, left bottom, from(#429acd),
			to(#2372a0));
		background-image: -webkit-linear-gradient(top, #429acd, #2372a0);
		background-image: -moz-linear-gradient(top, #429acd, #2372a0);
		background-image: -ms-linear-gradient(top, #429acd, #2372a0);
		background-image: -o-linear-gradient(top, #429acd, #2372a0);
		background-image: linear-gradient(top, #429acd, #2372a0);
	}
	
	.header {
		top: 0;
	}
	
	.footer {
		border-top: 1px solid #FF0000;
		bottom: 0;
	}
	
	.page-title {
		line-height: 44px;
	}
	
	.fl {
		float: left;
	}
	
	.fr {
		float: right;
	}
	
	.wrap-page {
		bottom: 0;
		overflow-y: auto;
		top: 44px;
	}
	
	.page {
		padding: 10px;
		position: relative;
	}
	
	.page p {
		margin-bottom: 10px;
	}
	
	.modal-link {
		background-color: #FF0000;
		border-radius: 3px;
		color: #FFFFFF;
		cursor: pointer;
		display: inline-block;
		padding: 10px;
	}
	
	.overlay,.modal .modal-ft {
		display: flex;
	}
	
	.overlay {
		align-items: center;
		background-color: rgba(0, 0, 0, 0.8);
		bottom: 0;
		justify-content: center;
		left: 0;
		position: fixed;
		right: 0;
		top: 0;
		z-index: -1;
	}
	
	.overlay.active {
		z-index: 980;
	}
	
	.modal {
		transition: all 0.3s ease-in-out 0s;
	}
	
	.modal {
		background-color: #FFFFFF;
		border-radius: 5px;
		margin: 0 10px;
		opacity: 0;
		overflow: hidden;
		transform: translate3d(0px, 0px, 0px) scale(0.815);
		transition-property: transform, opacity;
	}
	
	.modal.modal-in {
		opacity: 1;
		transform: translate3d(0px, 0px, 0px) scale(1);
	}
	
	.modal .modal-hd {
		background-color: #0078E7;
		color: #FFFFFF;
		line-height: 40px;
		text-align: center;
	}
	
	.modal .modal-bd {
		padding: 15px;
	}
	
	.modal .modal-ft {
		border-top: 1px solid #CCCCCC;
		text-align: center;
	}
	
	.modal .modal-ft .btn-modal {
		background-color: #FEFEFE;
		color: #0078E7;
		flex: 1 1 0;
		line-height: 40px;
		text-align: center;
	}
	
	.modal .modal-ft .btn-modal:first-child {
		border-right: 1px solid #CCCCCC;
	}
	
	.modal .modal-ft .btn-modal:last-child {
		border-right: medium none;
	}
	
	.modal .modal-ft .btn-modal:hover,.modal .modal-ft .btn-modal:active {
		background-color: #D9D9D9;
	}
	</style>
	<script type="text/javascript">
	function changecode(path, id) {
		document.getElementById(id).src = path + "&now=" + new Date();
	}
	var imgPath_session = '${ctx}' + '/images/alertsweixin';

	function checkValidateCode(path, vid) {
		var checkCode = $("#yzm").val();
		$.ajax({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			url : path + "/" + checkCode + "/validateLoginCheckCode",
			success : function(data) {
				if (data.resultCode == 0) {
					$("#ts_message").css("display", "none");
					var id = $("#moblieNumber").val();
					var openid = $("#openid").val();
					var password = $("#password").val();
					$.ajax({
						type : "POST",
						async : false,
						cache : false,
						dataType : "json",
						data : {
							id : id,
							openid : openid,
							password : password
						},
						url : "${ctx}/weixin/bundlingUser",
						success : function(data) {
							$("#msg").html(data.msg);
   							$("#overlay").show();
						}
					});

				} else {
					$("#ts_message").css("display", "block");
					document.getElementById(vid).src = document.getElementById(vid).src + "&now=" + new Date();
				}
			}
		});

	}
	 
	function closeView(){
		$("#msg").html("");
		$("#overlay").hide();
	}

	</script>
	</head>
	<body>
		<header id="header" class="header fixed-top">
			<h1 class="page-title">
				<img src="${ctx}/images/weixin/h1_bg.png"
					style="text-align: center; vertical-align: middle;">
			</h1>
		</header>
		<div class="wrap-page">
			<section class="page">
	
				<form >
					<fieldset id="inputs">
						<input id="moblieNumber"  type="text" placeholder="蛙宝帐号" autofocus required> 
						<input type="password" id="password"  placeholder="密码" required> 
					    <input type="hidden" readonly="readonly" name="openid" id="openid" value="${openid }" />
						<input type="text" value="" placeholder="验证码" style="width: 60%" name="userpassword2" id="yzm" />
						<a style="display: inline-block;"> <img
							id="bundlingcheckcodeimag" style="height: 30px"
							src="${ctx}/getCheckCodeByWeixin?codeName=loginCode"
							onclick="changecode('${ctx}/getCheckCodeByWeixin?codeName=loginCode','bundlingcheckcodeimag')">
						</a>
						
						<span style="display: none" id="ts_message">
								<div style="color:red">请正确输入验证码</div><span>
					</fieldset>
					<fieldset id="actions">
						<input type="button" id="submit" onclick="checkValidateCode('${ctx}','bundlingcheckcodeimag');" value="绑定蛙宝网微信">
					</fieldset>
				</form>
			</section>
		</div>
	
		<div id="overlay" class="overlay active" style="display: none;">
			<section class="modal modal-test modal-in" style="margin-top: 100px">
				<div class="modal-hd">提示</div>
				<div class="modal-bd" >
					 <div id="msg"></div>
				</div>
				<div class="modal-ft">
					<span class="btn-modal" onclick="closeView()">确认</span>
				</div>
			</section>
		</div>
	</html>
