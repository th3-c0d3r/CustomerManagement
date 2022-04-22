package com.accenture.lkm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "cust/controller/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCustomer( @Valid @RequestBody CustomerBean customerBean, Errors err) throws Exception {

		if (err.hasErrors())
			return new ResponseEntity<String>(err.getAllErrors() + " - ", HttpStatus.BAD_REQUEST);
		else {

			CustomerBean inserted = customerService.addCustomer(customerBean);
			return new ResponseEntity<String>("Customer added successfully with id :" + inserted.getCustomerId(),
					HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "cust/controller/updateCustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerBean> updateCustomer(@RequestBody CustomerBean customerBean) throws Exception {
		CustomerBean custBean = customerService.getCustomerById(customerBean.getCustomerId());
		if (custBean != null) {
			CustomerBean updated = customerService.updateCustomer(customerBean);
			return new ResponseEntity<CustomerBean>(updated, HttpStatus.OK);
		} else
			return new ResponseEntity<CustomerBean>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "cust/controller/deleteDetailsById/{custId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerBean> deleteCustomerById(@PathVariable("custId") int custId) {
		CustomerBean custBean = customerService.getCustomerById(custId);
		if (custBean != null) {
			CustomerBean deleted = customerService.deleteCustomer(custBean);
			return new ResponseEntity<CustomerBean>(deleted, HttpStatus.OK);
		} else
			return new ResponseEntity<CustomerBean>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "cust/controller/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getAllCustomers() {
		List<CustomerBean> custList = new ArrayList<CustomerBean>(customerService.getAllCustomers());
		if (custList.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custList, HttpStatus.OK);
		else
			return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "cust/controller/getDetailsById/{custId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerBean> getCustomerById(@PathVariable("custId") int custId) {
		CustomerBean custBean = customerService.getCustomerById(custId);
		if (custBean != null)
			return new ResponseEntity<CustomerBean>(custBean, HttpStatus.FOUND);
		else
			return new ResponseEntity<CustomerBean>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "cust/controller/getDetailsByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getCustomerByName() {
		List<CustomerBean> custBean = customerService.findAllCustomersBycustomerNameStartsWith();
		if (custBean.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.FOUND);
		else
			return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "cust/controller/getDetailsByBillAmount/{billAmt1}/{billAmt2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getCustomerByBillAmount(@PathVariable("billAmt1") double billAmt1, @PathVariable("billAmt2") double billAmt2) {
		List<CustomerBean> custBean = customerService.findAllCustomersByBillAmountBetween(billAmt1, billAmt2);
		if (custBean.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.FOUND);
		else
			return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
	}

}
