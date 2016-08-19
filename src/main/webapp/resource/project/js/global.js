/*
 * sspanel:global.js v1.0
 */
var fullURL=window.location.href;
var globalURL = fullURL.indexOf("?")>0?fullURL.substring(fullURL.indexOf("?"),0):fullURL;
var resourceURL="http://cdn.bootcss.com";

$(function($) {
	var localURL=window.location.href;
	$(".i18n").each(function(){
		$(this).attr("href",$(this).attr("data-lan")+"?url="+localURL);
	});
});