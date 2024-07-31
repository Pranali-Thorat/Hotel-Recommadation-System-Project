package org.HotelRecommand.model;

public class CustomerModel 
{
 private int custId;
 private String CustName;
 private String custNo;
 private String custEmail;
 private int cityid;
 private int areaid;
 private String ftype;
public int getCustId() {
	return custId;
}
public void setCustId(int custId) {
	this.custId = custId;
}
public String getCustName() {
	return CustName;
}
public void setCustName(String custName) {
	CustName = custName;
}
public String getCustNo() {
	return custNo;
}
public void setCustNo(String custNo) {
	this.custNo = custNo;
}
public String getCustEmail() {
	return custEmail;
}
public void setCustEmail(String custEmail) {
	this.custEmail = custEmail;
}
public int getCityid() {
	return cityid;
}
public void setCityid(int cityid) {
	this.cityid = cityid;
}
public int getAreaid() {
	return areaid;
}
public void setAreaid(int areaid) {
	this.areaid = areaid;
}
public String getFtype() {
	return ftype;
}
public void setFtype(String ftype) {
	this.ftype = ftype;
}

 
}