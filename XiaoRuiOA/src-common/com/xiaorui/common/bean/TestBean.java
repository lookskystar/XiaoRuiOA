package com.xiaorui.common.bean;

/**
 * 用户操作Bean类，在本框架中一般用的比较少
 * @author dell
 *
 */
public class TestBean {
	//ID
	private Long id;
	//用户名称
	private String name;
	//用户密码
	private String password;
	//用户性别
	private String sex;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
