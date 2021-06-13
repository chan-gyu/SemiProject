package com.yoriessence.point.model.vo;

import java.sql.Date;
import java.util.Objects;

public class MemberPoint {
	
	private int pointNo;
	private String memberId;
	private int point;
	private String pointInfo;
	private Date pointDate;
	
	public MemberPoint() {
		// TODO Auto-generated constructor stub
	}

	public MemberPoint(int pointNo, String memberId, int point, String pointInfo, Date pointDate) {
		super();
		this.pointNo = pointNo;
		this.memberId = memberId;
		this.point = point;
		this.pointInfo = pointInfo;
		this.pointDate = pointDate;
	}

	public int getPointNo() {
		return pointNo;
	}

	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPointInfo() {
		return pointInfo;
	}

	public void setPointInfo(String pointInfo) {
		this.pointInfo = pointInfo;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}

	@Override
	public String toString() {
		return "MemberPoint [pointNo=" + pointNo + ", memberId=" + memberId + ", point=" + point + ", pointInfo="
				+ pointInfo + ", pointDate=" + pointDate + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MemberPoint) {
			MemberPoint mp=(MemberPoint)obj;
			if(mp.pointNo==pointNo && mp.memberId.equals(memberId)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pointNo, memberId);
	}

}
