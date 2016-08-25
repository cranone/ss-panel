$(function($) {
	var li=$("#user_container").data("nav");
	$("#left_nav li").removeClass("active");
	$("#left_nav #left_"+li).addClass("active");
});