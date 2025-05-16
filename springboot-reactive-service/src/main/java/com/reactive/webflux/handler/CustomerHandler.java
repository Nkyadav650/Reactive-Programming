package com.reactive.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.webflux.dao.CustomerDao;
import com.reactive.webflux.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao dao;

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerList = dao.getCustomers();
		return ServerResponse.ok().body(customerList, Customer.class);
	}
}
