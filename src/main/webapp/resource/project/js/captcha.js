function captchaRefresh(captcha){
	captcha.attr("src",globalURL+"/images/captcha.jpg?"+new Date().getTime());
}

$(function($) {
	$("#captchaimg").click(function(){
		captchaRefresh($(this));
  });
});