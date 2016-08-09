package com.dep.sspanel.control;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.dep.sspanel.util.ServerUtil;


/**
 * 国际化类
 * @author Maclaine
 *
 */
@Controller
public class SystemControl {
	private static final Logger logger = LoggerFactory.getLogger(SystemControl.class);
	
	@ResponseBody
	@RequestMapping(value=URIConstants.POST_LANGUAGE+"/{langType}",method=RequestMethod.POST)
	public Map<String,Object> language(HttpServletRequest request,HttpServletResponse response,@PathVariable String langType){
		Map<String, Object> map = new HashMap<String, Object>();
		String[] languages=new String[]{"zh","en","ja"};
		if(langType==null){
			//session方式
			//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,LocaleContextHolder.getLocale());
			//cookie方式
			(new CookieLocaleResolver()).setLocale (request, response, LocaleContextHolder.getLocale());
		}else{
			for (String string : languages) {
				if(string.equalsIgnoreCase(langType)){
					//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale(string));
					(new CookieLocaleResolver()).setLocale (request, response, new Locale(string));
					break;
				}
			}
		}
        logger.debug("lan:{}", ServerUtil.i18n(request, "title"));
        map.put("status", "y");
		return map;
	}
	
	@RequestMapping(value=URIConstants.POST_LANGUAGE+"/{langType}",method=RequestMethod.GET)
	public String language(HttpServletRequest request,HttpServletResponse response,@PathVariable String langType,@RequestParam(required = false,defaultValue="/index")String url){
		language(request,response,langType);
		return "redirect:"+url;
	}
	
	@RequestMapping(value={URIConstants.GET_DEFAULT,URIConstants.GET_INDEX})
	public String index(){
		return "index";
	}
}
