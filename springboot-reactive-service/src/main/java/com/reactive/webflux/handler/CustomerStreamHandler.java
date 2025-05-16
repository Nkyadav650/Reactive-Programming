package com.reactive.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.webflux.dao.CustomerDao;
import com.reactive.webflux.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

	@Autowired
	private CustomerDao dao;

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerStream = dao.getStreamCustomers();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerStream, Customer.class);
	}
}
