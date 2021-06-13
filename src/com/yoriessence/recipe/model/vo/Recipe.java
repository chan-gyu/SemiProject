package com.yoriessence.recipe.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Recipe implements Serializable {
	
	private static final long serialVersionUID = 8884638138653679186L;
	
	private int recipeEnrollNo;
	private String memberId;
	private String recipeTitle;
	private String recipeIntro;
	private String representPicture;
	private String recipeVideoAddress;
	private String recipeCategory;
	private int recipeInfoHowmany;
	private int recipeInfoTime;
	private String recipeDifficult;
	private String recipeProcedure;
	private String recipeTip;
	private int recipeViewCount;
	private Date recipeEnrollDate;
	private String mainIngredient;
	private int recommendCount;
	private int commentCount;

	public Recipe() {
		// TODO Auto-generated constructor stub
	}




	public Recipe(int recipeEnrollNo, String memberId, String recipeTitle, String recipeIntro, String representPicture,
			String recipeVideoAddress, String recipeCategory, int recipeInfoHowmany, int recipeInfoTime,
			String recipeDifficult, String recipeProcedure, String recipeTip, int recipeViewCount,
			Date recipeEnrollDate, String mainIngredient, int recommendCount, int commentCount) {
		super();
		this.recipeEnrollNo = recipeEnrollNo;
		this.memberId = memberId;
		this.recipeTitle = recipeTitle;
		this.recipeIntro = recipeIntro;
		this.representPicture = representPicture;
		this.recipeVideoAddress = recipeVideoAddress;
		this.recipeCategory = recipeCategory;
		this.recipeInfoHowmany = recipeInfoHowmany;
		this.recipeInfoTime = recipeInfoTime;
		this.recipeDifficult = recipeDifficult;
		this.recipeProcedure = recipeProcedure;
		this.recipeTip = recipeTip;
		this.recipeViewCount = recipeViewCount;
		this.recipeEnrollDate = recipeEnrollDate;
		this.mainIngredient = mainIngredient;
		this.recommendCount = recommendCount;
		this.commentCount = commentCount;
	}




	public int getRecipeEnrollNo() {
		return recipeEnrollNo;
	}




	public void setRecipeEnrollNo(int recipeEnrollNo) {
		this.recipeEnrollNo = recipeEnrollNo;
	}




	public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	public String getRecipeTitle() {
		return recipeTitle;
	}




	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}




	public String getRecipeIntro() {
		return recipeIntro;
	}




	public void setRecipeIntro(String recipeIntro) {
		this.recipeIntro = recipeIntro;
	}




	public String getRepresentPicture() {
		return representPicture;
	}




	public void setRepresentPicture(String representPicture) {
		this.representPicture = representPicture;
	}




	public String getRecipeVideoAddress() {
		return recipeVideoAddress;
	}




	public void setRecipeVideoAddress(String recipeVideoAddress) {
		this.recipeVideoAddress = recipeVideoAddress;
	}




	public String getRecipeCategory() {
		return recipeCategory;
	}




	public void setRecipeCategory(String recipeCategory) {
		this.recipeCategory = recipeCategory;
	}




	public int getRecipeInfoHowmany() {
		return recipeInfoHowmany;
	}




	public void setRecipeInfoHowmany(int recipeInfoHowmany) {
		this.recipeInfoHowmany = recipeInfoHowmany;
	}




	public int getRecipeInfoTime() {
		return recipeInfoTime;
	}




	public void setRecipeInfoTime(int recipeInfoTime) {
		this.recipeInfoTime = recipeInfoTime;
	}




	public String getRecipeDifficult() {
		return recipeDifficult;
	}




	public void setRecipeDifficult(String recipeDifficult) {
		this.recipeDifficult = recipeDifficult;
	}




	public String getRecipeProcedure() {
		return recipeProcedure;
	}




	public void setRecipeProcedure(String recipeProcedure) {
		this.recipeProcedure = recipeProcedure;
	}




	public String getRecipeTip() {
		return recipeTip;
	}




	public void setRecipeTip(String recipeTip) {
		this.recipeTip = recipeTip;
	}




	public int getRecipeViewCount() {
		return recipeViewCount;
	}




	public void setRecipeViewCount(int recipeViewCount) {
		this.recipeViewCount = recipeViewCount;
	}




	public Date getRecipeEnrollDate() {
		return recipeEnrollDate;
	}




	public void setRecipeEnrollDate(Date recipeEnrollDate) {
		this.recipeEnrollDate = recipeEnrollDate;
	}




	public String getMainIngredient() {
		return mainIngredient;
	}




	public void setMainIngredient(String mainIngredient) {
		this.mainIngredient = mainIngredient;
	}




	public int getRecommendCount() {
		return recommendCount;
	}




	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}




	public int getCommentCount() {
		return commentCount;
	}




	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Recipe) {
			Recipe r=(Recipe)obj;
			if(recipeEnrollNo==r.recipeEnrollNo&&memberId.equals(r.memberId)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(recipeEnrollNo, memberId);
	}
	
}
