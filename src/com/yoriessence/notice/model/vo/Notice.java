package com.yoriessence.notice.model.vo;

public class Notice {
	private int number;
	private String title;
	private String content;
	private String filePath;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int number, String title, String content, String filePath) {
		super();
		this.number = number;
		this.title = title;
		this.content = content;
		this.filePath = filePath;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
