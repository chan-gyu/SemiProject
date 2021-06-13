package com.yoriessence.shopping.vo;

import java.util.Date;

public class OrderDetails {
	private int Ordernumber;
	private String memberid ;
	private int Orderamount;
	private Date orderdate;
	private Date paymentdate;
	private String productname;
	private int productamount;
	private String shippingstatus;
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}
	
	

	public OrderDetails(int ordernumber, String memberid, int orderamount, Date orderdate, Date paymentdate,
			String productname, int productamount, String shippingstatus) {
		super();
		this.Ordernumber = ordernumber;
		this.memberid = memberid;
		this.Orderamount = orderamount;
		this.orderdate = orderdate;
		this.paymentdate = paymentdate;
		this.productname = productname;
		this.productamount = productamount;
		this.shippingstatus = shippingstatus;
	}



	public int getOrdernumber() {
		return Ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		Ordernumber = ordernumber;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getOrderamount() {
		return Orderamount;
	}

	public void setOrderamount(int orderamount) {
		Orderamount = orderamount;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductamount() {
		return productamount;
	}

	public void setProductamount(int productamount) {
		this.productamount = productamount;
	}

	public String getShippingstatus() {
		return shippingstatus;
	}

	public void setShippingstatus(String shippingstatus) {
		this.shippingstatus = shippingstatus;
	}

	
	
	
	
	
	
	
	
	
	

}
