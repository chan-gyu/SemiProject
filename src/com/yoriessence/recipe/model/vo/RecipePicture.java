package com.yoriessence.recipe.model.vo;

public class RecipePicture {
	
	private int recipeImageNo;
	private int recipeEnrollNo;
	private String recipeEnrollPicture;
	
	public RecipePicture() {
		// TODO Auto-generated constructor stub
	}

	public RecipePicture(int recipeImageNo, int recipeEnrollNo, String recipeEnrollPicture) {
		super();
		this.recipeImageNo = recipeImageNo;
		this.recipeEnrollNo = recipeEnrollNo;
		this.recipeEnrollPicture = recipeEnrollPicture;
	}

	public int getRecipeImageNo() {
		return recipeImageNo;
	}

	public void setRecipeImageNo(int recipeImageNo) {
		this.recipeImageNo = recipeImageNo;
	}

	public int getRecipeEnrollNo() {
		return recipeEnrollNo;
	}

	public void setRecipeEnrollNo(int recipeEnrollNo) {
		this.recipeEnrollNo = recipeEnrollNo;
	}

	public String getRecipeEnrollPicture() {
		return recipeEnrollPicture;
	}

	public void setRecipeEnrollPicture(String recipeEnrollPicture) {
		this.recipeEnrollPicture = recipeEnrollPicture;
	}
	
	//test

	

}
