package com.yoriessence.question.model.vo;

public class Question {
	private int questionNumber;
	private String memberId;
	private String questionContent;
	private String questionTitle;
	private String questionPic;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int questionNumber, String memberId, String questionContent, String questionTitle,
			String questionPic) {
		super();
		this.questionNumber = questionNumber;
		this.memberId = memberId;
		this.questionContent = questionContent;
		this.questionTitle = questionTitle;
		this.questionPic = questionPic;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionPic() {
		return questionPic;
	}

	public void setQuestionPic(String questionPic) {
		this.questionPic = questionPic;
	}
	
	
	
}
