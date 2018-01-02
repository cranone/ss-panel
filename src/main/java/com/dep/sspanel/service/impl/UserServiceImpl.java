package com.dep.sspanel.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.Code;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.exception.SystemException;
import com.dep.sspanel.service.CodeService;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.shiro.SecurityUtil;
import com.dep.sspanel.util.type.ErrorCodeType;
import com.dep.sspanel.util.type.UserType;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{
	private UserDao userDao;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		this.genericDao=userDao;
	}

	@Override
	public User findUserByName(String name) {
		Map<String,Object> map=new HashMap<>();
		map.put("username", name);
		return this.findOne(map);
	}
	
	@Override
	public User findUserByNameOrEmail(String condition) {
		return userDao.findUserByNameOrEmail(condition);
	}

	@Override
	public Set<String> findRolesByNameOrEmail(String condition) {
		User user=findUserByNameOrEmail(condition);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRoleNameSet();
	}

	@Override
	public Set<String> findPermissionsByNameOrEmail(String condition) {
		User user=findUserByNameOrEmail(condition);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRolePermissionSet();
	}

	@Override
	public boolean changePassword(String username, String oldpassword, String password) {
		User user=findUserByName(username);
		if(!user.getPass().equals(SecurityUtil.encrypt(user.getUsername(), oldpassword))){
			return false;
		}
		user.setPass(SecurityUtil.encrypt(user.getUsername(), password));
		return true;
	}

	@Override
	public Integer checkExpires() {
		return userDao.updateAllOutDate();
	}

	@Override
	public boolean recharge(String code, String userName) {
		Code eCode = codeService.findByCodeActive(code);
		User user=findUserByName(userName);
		if(eCode==null){
			return false;
		}
		switch (eCode.getCodeType()) {
		case bandwidth:
			long temp=eCode.getAmount();
			temp=temp*1024*1024*1024;
			user.setTransferEnable(user.getTransferEnable()+temp);//GB转换成B
			break;
		case time:
			Calendar cal = Calendar.getInstance();
			if(!user.isTimeout()){//如果没有过期
				cal.setTime(user.getExpiresDate());
			}
			cal.add(Calendar.MONTH, eCode.getAmount());
			user.setExpiresDate(cal.getTime());
			user.setEnable(true);
			break;
		default:
			return false;
		}
		userDao.update(user);
		eCode.setConsumer(user);
		eCode.setConsumeDate(new Date());
		codeService.update(eCode);
		return true;
	}
	
    @Override
    public boolean isExist(User user) {
        User result=userDao.findUserByNameOrEmail(user.getUsername(), user.getEmail());
        return result!=null;
    }

    @Override
    public String addUser(User user) {
        if(isExist(user)) {
            throw new SystemException(ErrorCodeType.data_error);
        }
        User last=userDao.findLastUser();
        String pass=user.getPass();
        user.setPasswd(pass);
        user.setPass(SecurityUtil.encrypt(user.getUsername(), pass));
        user.setPort(last.getPort()+1);
        user.setEnable(false);
        user.setExpiresDate(new Date());
        user.setTime(0);
        user.setUpload(0L);
        user.setDownload(0L);
        user.setTransferEnable(0L);
        user.setUpdateDate(Calendar.getInstance().getTime());
        user.setStatus(UserType.normal.getId());
        return (String) save(user);
    }


}
