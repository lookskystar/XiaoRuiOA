package com.xiaorui.common.bean;

/**
 * �û�����Bean�࣬�ڱ������һ���õıȽ���
 * @author dell
 *
 */
public class TestBean {
	//ID
	private Long id;
	//�û�����
	private String name;
	//�û�����
	private String password;
	//�û��Ա�
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
