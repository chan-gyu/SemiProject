package com.yoriessence.chef.model.vo;

public class Profile {
    private String memberId;
    private String profileName;
    private String selfIntro;
    private String profilePic;
    private String profileSnsUrl1;
    private String profileSnsUrl2;
    private String memberNickName;



    public Profile() {
    }
    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfileSnsUrl1() {
        return profileSnsUrl1;
    }

    public void setProfileSnsUrl1(String profileSnsUrl1) {
        this.profileSnsUrl1 = profileSnsUrl1;
    }

    public String getProfileSnsUrl2() {
        return profileSnsUrl2;
    }

    public void setProfileSnsUrl2(String profileSnsUrl2) {
        this.profileSnsUrl2 = profileSnsUrl2;
    }
}
