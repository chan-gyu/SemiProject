package com.yoriessence.shopping.vo;

public class Refund {
	private int ordernumber;
	private String memberid;
	private String productname;
	private String refundinfo;
	private String refundreason;
	private String refundpic;
	private String recomment;
	
	public Refund() {
		// TODO Auto-generated constructor stub
	}

	public Refund(int ordernumber, String memberid, String productname, String refundinfo, String refundreason,
			String refundpic, String recomment) {
		super();
		this.ordernumber = ordernumber;
		this.memberid = memberid;
		this.productname = productname;
		this.refundinfo = refundinfo;
		this.refundreason = refundreason;
		this.refundpic = refundpic;
		this.recomment = recomment;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getRefundinfo() {
		return refundinfo;
	}

	public void setRefundinfo(String refundinfo) {
		this.refundinfo = refundinfo;
	}

	public String getRefundreason() {
		return refundreason;
	}

	public void setRefundreason(String refundreason) {
		this.refundreason = refundreason;
	}

	public String getRefundpic() {
		return refundpic;
	}

	public void setRefundpic(String refundpic) {
		this.refundpic = refundpic;
	}

	public String getRecomment() {
		return recomment;
	}

	public void setRecomment(String recomment) {
		this.recomment = recomment;
	}

	
	
	
	

}
