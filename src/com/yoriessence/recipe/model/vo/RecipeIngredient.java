package com.yoriessence.recipe.model.vo;

public class RecipeIngredient {
	private int recipeEnrollNo;
	private String ingredientName;
	private String ingredientAmount;
	private String ingredientCategory;
	
	public RecipeIngredient() {
		// TODO Auto-generated constructor stub
	}

	public RecipeIngredient(int recipeEnrollNo, String ingredientName, String ingredientAmount,
			String ingredientCategory) {
		super();
		this.recipeEnrollNo = recipeEnrollNo;
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.ingredientCategory = ingredientCategory;
	}

	public int getRecipeEnrollNo() {
		return recipeEnrollNo;
	}

	public void setRecipeEnrollNo(int recipeEnrollNo) {
		this.recipeEnrollNo = recipeEnrollNo;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(String ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public String getIngredientCategory() {
		return ingredientCategory;
	}

	public void setIngredientCategory(String ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}

	@Override
	public String toString() {
		return "RecipeIngredient [recipeEnrollNo=" + recipeEnrollNo + ", ingredientName=" + ingredientName
				+ ", ingredientAmount=" + ingredientAmount + ", ingredientCategory=" + ingredientCategory + "]";
	}
	
	
	
}
