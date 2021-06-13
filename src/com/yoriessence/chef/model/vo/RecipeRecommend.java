package com.yoriessence.chef.model.vo;

public class RecipeRecommend {
    private String memberId;
    private String memberName;
    private String memberNickName;
    private String recipeTitle;
    private String representPicture;
    private String profileName;
    private String profileSelfIntro;
    private String profilePic;
    private String profileSNSUrl1;
    private String ProfileSNSUrl2;
    private String recipeIntro;
    private int recipeViewCount;
    private int recipeRecommendNum;
    private int recipeEnrollNum;

    public RecipeRecommend() {
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

    public int getRecipeRecommendNum() {
        return recipeRecommendNum;
    }

    public void setRecipeRecommendNum(int recipeRecommendNum) {
        this.recipeRecommendNum = recipeRecommendNum;
    }

    public int getRecipeEnrollNum() {
        return recipeEnrollNum;
    }

    public void setRecipeEnrollNum(int recipeEnrollNum) {
        this.recipeEnrollNum = recipeEnrollNum;
    }

    public int getRecipeViewCount() {
        return recipeViewCount;
    }

    public void setRecipeViewCount(int recipeViewCount) {
        this.recipeViewCount = recipeViewCount;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileSelfIntro() {
        return profileSelfIntro;
    }

    public void setProfileSelfIntro(String profileSelfIntro) {
        this.profileSelfIntro = profileSelfIntro;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfileSNSUrl1() {
        return profileSNSUrl1;
    }

    public void setProfileSNSUrl1(String profileSNSUrl1) {
        this.profileSNSUrl1 = profileSNSUrl1;
    }

    public String getProfileSNSUrl2() {
        return ProfileSNSUrl2;
    }

    public void setProfileSNSUrl2(String profileSNSUrl2) {
        ProfileSNSUrl2 = profileSNSUrl2;
    }

    public String getRecipeIntro() {
        return recipeIntro;
    }

    public void setRecipeIntro(String recipeIntro) {
        this.recipeIntro = recipeIntro;
    }
}
