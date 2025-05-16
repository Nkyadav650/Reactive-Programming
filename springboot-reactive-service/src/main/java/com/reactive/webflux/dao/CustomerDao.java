package com.reactive.webflux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.reactive.webflux.entity.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Customer> getListCustomers(){
		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDao::sleepExecution)
				.peek(i->System.out.println("Processing count in list : "+i))
				.mapToObj(i-> new Customer(i,"List Customer"+i))
				.collect(Collectors.toList());
	}
	public Flux<Customer> getStreamCustomers(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i->System.out.println("Processing count in stream : "+i))
				.map(i-> new Customer(i,"Stream Customer"+i));
	}
	
	public Flux<Customer> getCustomers(){
		return Flux.range(1, 50)
				.doOnNext(i->System.out.println("Processing count : "+i))
				.map(i-> new Customer(i,"Customer"+i));
	}
}
