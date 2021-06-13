package com.yoriessence.chef.model.vo;

import java.util.Objects;

public class UserRecommend {
    private String memberId;
    private int recommendCount;

    public UserRecommend() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecommend that = (UserRecommend) o;
        return recommendCount == that.recommendCount && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, recommendCount);
    }
}
