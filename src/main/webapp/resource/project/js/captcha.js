function captchaRefresh(captcha){
	var src=captcha.attr("src").substring(0,captcha.attr("src").indexOf('?'));
	if(!src){
		src=captcha.attr("src");
	}
	captcha.attr("src",src+"?"+new Date().getTime());
}

$(function($) {
	$("#captchaimg").click(function(){
		captchaRefresh($(this));
  });
});