package com.mycompany.dbRepository;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class CallSummaryVO implements Serializable {
	private static final long serialVersionUID = 1L;
private String firstPartyPhoneNo;
private String name;
private String secondPartyPhoneNo;
private Date dateOfCall;
private int count;



public CallSummaryVO(){}


public CallSummaryVO(String firstPartyPhoneNo, String name, String secondPartyPhoneNo, Date dateOfCall, int count) {
	super();
	this.firstPartyPhoneNo = firstPartyPhoneNo;
	this.name = name;
	this.secondPartyPhoneNo = secondPartyPhoneNo;
	this.dateOfCall = dateOfCall;
	this.count = count;
}





public String getFirstPartyPhoneNo() {
	return firstPartyPhoneNo;
}
public void setFirstPartyPhoneNo(String firstPartyPhoneNo) {
	this.firstPartyPhoneNo = firstPartyPhoneNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSecondPartyPhoneNo() {
	return secondPartyPhoneNo;
}
public void setSecondPartyPhoneNo(String secondPartyPhoneNo) {
	this.secondPartyPhoneNo = secondPartyPhoneNo;
}
public Date getDateOfCall() {
	return dateOfCall;
}
public void setDateOfCall(Date dateOfCall) {
	this.dateOfCall = dateOfCall;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
@Override
public String toString() {
	return "CallSummaryVO [firstPartyPhoneNo=" + firstPartyPhoneNo + ", name=" + name + ", secondPartyPhoneNo="
			+ secondPartyPhoneNo + ",  count=" + count + "]";
}



}
