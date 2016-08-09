package com.dep.sspanel.control;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dep.sspanel.entity.User;


public class UserValidator implements Validator {
	private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z]\\w{4,19}");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9]{5,20}");
	private static final Set<String> FORBINDDDEN_WORD_SET = new HashSet<String>();
	static {
		FORBINDDDEN_WORD_SET.add("fuck");
		FORBINDDDEN_WORD_SET.add("admin");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class == arg0;// 只对指定对象验证
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "用户名不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "密码不能为空");
		User user = (User) target;
		if (!USERNAME_PATTERN.matcher(user.getEmail()).matches()) {
			errors.rejectValue("username",  "用户名不合法");// 如果用户名不合法
		}
		for (String forbiddenWord : FORBINDDDEN_WORD_SET) {
			if (user.getEmail().contains(forbiddenWord)) {
				errors.rejectValue("username",  "您的用户名包含非法关键词");// 用户名包含屏蔽关键字
				break;
			}
		}
		if (!PASSWORD_PATTERN.matcher(user.getPass()).matches()) {
			errors.rejectValue("password",  "密码不合法");// 密码不合法
		}
	}

}
