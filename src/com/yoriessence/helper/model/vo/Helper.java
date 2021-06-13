package com.yoriessence.helper.model.vo;

public class Helper {
	private int number;
	private String title;
	private String content;
	
	public Helper() {
		// TODO Auto-generated constructor stub
	}

	public Helper(int number, String title, String content) {
		super();
		this.number = number;
		this.title = title;
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
