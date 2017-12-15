package com.tgy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.SourceTree;

import junit.framework.Assert;

public class LoginLogoutTest {
	
	@Test
	public void testHelloWorld(){
		//1.获取SecurityManager工厂,此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager>factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//2.得到SecurityManager实例,并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager=
				factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3.得到Subject及创建用户名/密码身份验证Token(即用户身份/凭证)
		Subject subject=SecurityUtils.getSubject();
//		Subject subject2=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
//		UsernamePasswordToken token2=new UsernamePasswordToken("wang","123");
		try{
			//4.登录,验证身份
			subject.login(token);
//			subject2.login(token2);
		}catch(AuthenticationException e){
			//5.身份验证失败
		}
	    System.out.println(subject.isAuthenticated()==true?"用户zhang登录成功":"用户zhang登录失败！用户名/密码错误！");
//	    System.out.println(subject2.isAuthenticated()==true?"用户wang登录成功":"用户wang登录失败！用户名/密码错误！");
	    Assert.assertEquals(true, subject.isAuthenticated());
	    	//6.退出
	    subject.logout();
	}
}
