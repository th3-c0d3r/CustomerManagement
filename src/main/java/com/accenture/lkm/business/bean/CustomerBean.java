package com.accenture.lkm.business.bean;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.accenture.lkm.custom.validation.BillDateValidator;

public class CustomerBean{
	
	private int customerId;
	@NotEmpty(message="{NotEmpty.customerBean.customerName}")
	private String customerName;
	@Range(min=1000, max=100000,message = "Range.customerBean.billAmount")
	private Double billAmount;
	@BillDateValidator(message = "{BilldateValidator.customerBean.billDate}")
	private Date billDate;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

}
	