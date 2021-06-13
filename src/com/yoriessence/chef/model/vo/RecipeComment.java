package com.yoriessence.chef.model.vo;

public class RecipeComment{
    private String memberId;
    private String memberName;
    private String memberNickName;
    private String recipeTitle;
    private String representPicture;
    private int recipeEnrollNo;
    private int countRecipeComment;

    public RecipeComment() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRepresentPicture() {
        return representPicture;
    }

    public void setRepresentPicture(String representPicture) {
        this.representPicture = representPicture;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public int getRecipeEnrollNo() {
        return recipeEnrollNo;
    }

    public void setRecipeEnrollNo(int recipeEnrollNo) {
        this.recipeEnrollNo = recipeEnrollNo;
    }

    public int getCountRecipeComment() {
        return countRecipeComment;
    }

    public void setCountRecipeComment(int countRecipeComment) {
        this.countRecipeComment = countRecipeComment;
    }
}
