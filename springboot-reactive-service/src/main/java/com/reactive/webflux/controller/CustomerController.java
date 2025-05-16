package com.reactive.webflux.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.webflux.entity.Customer;
import com.reactive.webflux.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public List<Customer> getListCustomers() {
		return customerService.loadAllListCustomers();
	}

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getFluxCustomers() {
		return customerService.loadAllStreamCustomers();
	}
}
