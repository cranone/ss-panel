/*
 * sspanel:global.js v1.0
 */
var curWwwPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
var localhostPaht=curWwwPath.substring(0,pos);
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var globalURL=localhostPaht+projectName;
var resourceURL="http://cdn.bootcss.com";

$(function($) {
	var localURL=window.location.href;
	$(".i18n").each(function(){
		$(this).attr("href",$(this).attr("data-lan")+"?url="+localURL);
	});
	
});