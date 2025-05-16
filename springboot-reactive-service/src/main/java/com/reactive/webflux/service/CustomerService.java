package com.reactive.webflux.service;

import java.util.List;

import com.reactive.webflux.entity.Customer;

import reactor.core.publisher.Flux;

public interface CustomerService {
	public List<Customer> loadAllListCustomers();
	public Flux<Customer> loadAllStreamCustomers();
}
