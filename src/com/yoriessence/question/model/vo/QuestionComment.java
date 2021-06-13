package com.yoriessence.question.model.vo;

public class QuestionComment {
	private String CommentNumber;
	private String CommentMemberId;
	private String CommentContent;
	private String CommentPic;
	
	public QuestionComment() {
		// TODO Auto-generated constructor stub
	}

	public QuestionComment(String commentNumber, String commentMemberId, String commentContent, String commentPic) {
		super();
		CommentNumber = commentNumber;
		CommentMemberId = commentMemberId;
		CommentContent = commentContent;
		CommentPic = commentPic;
	}

	public String getCommentNumber() {
		return CommentNumber;
	}

	public void setCommentNumber(String commentNumber) {
		CommentNumber = commentNumber;
	}

	public String getCommentMemberId() {
		return CommentMemberId;
	}

	public void setCommentMemberId(String commentMemberId) {
		CommentMemberId = commentMemberId;
	}

	public String getCommentContent() {
		return CommentContent;
	}

	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}

	public String getCommentPic() {
		return CommentPic;
	}

	public void setCommentPic(String commentPic) {
		CommentPic = commentPic;
	}

	
	
	
}
