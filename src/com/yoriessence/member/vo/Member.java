package com.yoriessence.member.vo;

public class Member {
	private String userId;
	private String userName;
	private String password;
	private String nickName;
	private String email;
	private String address;
	private String membergrade;
	private int point;
	private String phone;
	private String snsconn;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String userId, String userName, String password, String nickName, String email, String address,
			String membergrade, int point, String phone, String snsconn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.address = address;
		this.membergrade = membergrade;
		this.point = point;
		this.phone = phone;
		this.snsconn= snsconn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMembergrade() {
		return membergrade;
	}

	public void setMembergrade(String membergrade) {
		this.membergrade = membergrade;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSnsconn() {
		return snsconn;
	}

	public void setSnsconn(String snsconn) {
		this.snsconn = snsconn;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userName=" + userName + ", password=" + password + ", nickName="
				+ nickName + ", email=" + email + ", address=" + address + ", membergrade=" + membergrade + ", point="
				+ point + ", phone=" + phone + ", snsconn=" + snsconn+"]";
	}
	
	
	
	
}
