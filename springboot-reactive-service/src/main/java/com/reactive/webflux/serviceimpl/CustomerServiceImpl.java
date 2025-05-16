package com.reactive.webflux.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.webflux.dao.CustomerDao;
import com.reactive.webflux.entity.Customer;
import com.reactive.webflux.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CustomerServiceImpl implements CustomerService{


	@Autowired
	private CustomerDao dao;
	
	public List<Customer> loadAllListCustomers(){
		long start=System.currentTimeMillis();
		List<Customer> customers=dao.getListCustomers();
		long end= System.currentTimeMillis();
		System.out.println("Total execution time: "+(end-start));
		return customers;
	}

	@Override
	public Flux<Customer> loadAllStreamCustomers() {
		long start=System.currentTimeMillis();
		Flux<Customer> customers=dao.getStreamCustomers();
		long end= System.currentTimeMillis();
		System.out.println("Total execution time: "+(end-start));
		return customers;
	}
	
	public void callSubscribe() {
		Mono<String> monoPublisher = Mono.just("Testing");
		
		monoPublisher.subscribe(System.out::println);
	}
	
}
