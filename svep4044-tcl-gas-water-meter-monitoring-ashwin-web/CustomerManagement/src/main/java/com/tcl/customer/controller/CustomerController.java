/*
 * @category CustomerManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.customer.dto.CustomerDeleteReqDto;
import com.tcl.customer.dto.CustomerRegRequestDto;
import com.tcl.customer.dto.CustomerRegResponseDto;
import com.tcl.customer.dto.CustomerResponseDto;
import com.tcl.customer.dto.CustomerStatusReqDto;
import com.tcl.customer.dto.CustomerUpdateReqDto;
import com.tcl.customer.dto.CustomerUpdateResponseDto;
import com.tcl.customer.dto.CustomerUtilityResponseDto;
import com.tcl.customer.response.ListResponse;
import com.tcl.customer.response.Response;
import com.tcl.customer.service.CustomerService;

/*
 * This controller takes care of the customer management API. Every request
 * mentioned here gets mapped and the respective functions are called. The
 * user(whoever has logged in) can get the list of customers and add , delete,
 * edit customers.
 * 
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

	/*
	 * dependency injection and object initializtion using Autowired annotation
	 */
	@Autowired
	private CustomerService customerService;

	/*
	 * Test method to see if the API service is working or not
	 */
	@GetMapping("/test")
	public @ResponseBody String test(@RequestParam String userId) {
		return userId;
	}

	/*
	 * This method when called sends the request received to the service and calls
	 * that service method. It is used to add a new customer .
	 */
	@PostMapping
	public @ResponseBody Response<CustomerRegResponseDto> createCustomer(
			@Valid @RequestBody CustomerRegRequestDto requestDto, HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return customerService.createCustomer(requestDto);
	}

	/*
	 * This method when called, calls the method that updates the customer details.
	 * And sends the request in the method call.
	 */
	@PutMapping
	public @ResponseBody Response<CustomerUpdateResponseDto> updateCustomer(@Valid @RequestBody CustomerUpdateReqDto requestDto) {
		return customerService.updateCustomer(requestDto);
	}

	/*
	 * This method when called, calls the delete method. And then returns the status
	 * of the delete process.
	 */

	@DeleteMapping
	public @ResponseBody Response<Boolean> deleteCustomer(@Valid @RequestBody CustomerDeleteReqDto requestDto) {
		return customerService.deleteCustomer(requestDto);
	}

	/*
	 * This method when called, calls the method that lists the customers. If any
	 * search queries are given in the url, it will filter the search result and
	 * return. Else all the customers that have true status for IsActive field will
	 * be listed and sent in the response.
	 */
	@GetMapping
	public @ResponseBody ListResponse<List<CustomerResponseDto>> listCustomers(
			@RequestParam(required = false) String search, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		return customerService.listCustomers(search, page, size);
	}

	/*
	 * This method when called,returns the customer details of the ID thats passed
	 * in the URL.
	 */
	@GetMapping("/profile/{customerId}")
	public @ResponseBody Response<CustomerResponseDto> viewCustomerDetails(
			@PathVariable("customerId") String customerId) {
		return customerService.viewCustomers(customerId);
	}

	/*
	 * This method when called, edits and updates the status of the customer, and
	 * then passes the response in return.
	 */
	@PutMapping("/status")
	public @ResponseBody Response<Boolean> updateCustomerStatus(@Valid @RequestBody CustomerStatusReqDto requestDto,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return customerService.updateCustomerStatus(requestDto);
	}

	/*
	 * This method when called, returns the utility type of the customerId that gets
	 * passed in the URL.
	 */
	@GetMapping("/utility/{customerId}")
	public @ResponseBody Response<CustomerUtilityResponseDto> viewCustomerUtilityType(
			@PathVariable("customerId") String customerId) {
		return customerService.viewCustomerUtilityType(customerId);
	}
	@GetMapping("/list")
	public @ResponseBody ListResponse<List<CustomerResponseDto>> filterListCustomers(@RequestParam(required = false) List<String> filters,@RequestParam(required = false) String sortByFieldName,@RequestParam(required = false) String sortType, @RequestParam("page") Integer page,@RequestParam("size") Integer size) {
		return customerService.filterListCustomers(filters,sortByFieldName,sortType, page, size);
	}
}
