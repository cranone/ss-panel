package com.dep.sspanel.control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码
 * 
 * @author Maclaine
 *
 */
@Controller
public class CaptchaControl {
	private Properties props = new Properties();

	private Producer kaptchaProducer = null;

	private String sessionKeyValue = null;

	private String sessionKeyDateValue = null;

	public CaptchaControl() {
		ImageIO.setUseCache(false);

		this.props.put("kaptcha.border", "no");
		this.props.put("kaptcha.textproducer.font.color", "black");
		this.props.put("kaptcha.textproducer.char.space", "5");
		this.props.put("kaptcha.textproducer.char.length", "4");
//		this.props.put("kaptcha.image.width", "120");
//		this.props.put("kaptcha.image.height", "30");

		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
		this.sessionKeyDateValue = config.getSessionDate();
	}

	@RequestMapping(value = URIConstants.CAPTCHA)
	public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-store, no-cache");

		resp.setContentType("image/jpeg");

		String capText = this.kaptchaProducer.createText();

		req.getSession().setAttribute(this.sessionKeyValue, capText);

		req.getSession().setAttribute(this.sessionKeyDateValue, new Date());

		BufferedImage bi = this.kaptchaProducer.createImage(capText);

		ServletOutputStream out = resp.getOutputStream();

		ImageIO.write(bi, "jpg", out);

		req.getSession().setAttribute(this.sessionKeyValue, capText);

		req.getSession().setAttribute(this.sessionKeyDateValue, new Date());
	}

	public String getGeneratedKey(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return ((String) session.getAttribute("KAPTCHA_SESSION_KEY"));
	}
}
