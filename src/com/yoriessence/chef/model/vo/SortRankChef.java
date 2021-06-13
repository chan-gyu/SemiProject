package com.yoriessence.chef.model.vo;

public class SortRankChef {
    private int recommendCount;
    private String memberId;
    private String profilePic;
    private String profileName;
    private String memberName;
    private String memberNickName;

    public SortRankChef(){}

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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
}
