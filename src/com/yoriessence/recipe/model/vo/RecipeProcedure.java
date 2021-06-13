package com.yoriessence.recipe.model.vo;

public class RecipeProcedure {
	
	private int recipeEnrollNo;
	private int procedureNo;
	private String procedureContent;
	private String procedurePicture;
	
	public RecipeProcedure() {
		// TODO Auto-generated constructor stub
	}
	
	public RecipeProcedure(int recipeEnrollNo, int procedureNo, String procedureContent, String procedurePicture) {
		super();
		this.recipeEnrollNo = recipeEnrollNo;
		this.procedureNo = procedureNo;
		this.procedureContent = procedureContent;
		this.procedurePicture = procedurePicture;
	}

	public int getRecipeEnrollNo() {
		return recipeEnrollNo;
	}

	public void setRecipeEnrollNo(int recipeEnrollNo) {
		this.recipeEnrollNo = recipeEnrollNo;
	}

	public int getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(int procedureNo) {
		this.procedureNo = procedureNo;
	}

	public String getProcedureContent() {
		return procedureContent;
	}

	public void setProcedureContent(String procedureContent) {
		this.procedureContent = procedureContent;
	}

	public String getProcedurePicture() {
		return procedurePicture;
	}

	public void setProcedurePicture(String procedurePicture) {
		this.procedurePicture = procedurePicture;
	}

	@Override
	public String toString() {
		return "RecipeProcedure [recipeEnrollNo=" + recipeEnrollNo + ", procedureNo=" + procedureNo
				+ ", procedureContent=" + procedureContent + ", procedurePicture=" + procedurePicture + "]";
	}
	
	
	
	

}
