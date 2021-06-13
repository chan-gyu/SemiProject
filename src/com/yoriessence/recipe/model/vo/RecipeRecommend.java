package com.yoriessence.recipe.model.vo;

import java.sql.Date;

public class RecipeRecommend {
	
	private int recipeEnrollNo;
	private int recommendCount;
	private Date recipeRecommendDate;
	private String memberId;
	
	public RecipeRecommend() {
		// TODO Auto-generated constructor stub
	}

	public RecipeRecommend(int recipeEnrollNo, int recommendCount, Date recipeRecommendDate, String memberId) {
		super();
		this.recipeEnrollNo = recipeEnrollNo;
		this.recommendCount = recommendCount;
		this.recipeRecommendDate = recipeRecommendDate;
		this.memberId = memberId;
	}

	public int getRecipeEnrollNo() {
		return recipeEnrollNo;
	}

	public void setRecipeEnrollNo(int recipeEnrollNo) {
		this.recipeEnrollNo = recipeEnrollNo;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public Date getRecipeRecommendDate() {
		return recipeRecommendDate;
	}

	public void setRecipeRecommendDate(Date recipeRecommendDate) {
		this.recipeRecommendDate = recipeRecommendDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "RecipeRecommend [recipeEnrollNo=" + recipeEnrollNo + ", recommendCount=" + recommendCount
				+ ", recipeRecommendDate=" + recipeRecommendDate + ", memberId=" + memberId + "]";
	}

	
	

}
