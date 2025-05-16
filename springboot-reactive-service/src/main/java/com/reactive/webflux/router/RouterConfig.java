package com.reactive.webflux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.webflux.handler.CustomerHandler;
import com.reactive.webflux.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private CustomerHandler handler;

	@Autowired
	private CustomerStreamHandler streamHandler;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions.route()
				.GET("/router/customers", handler::loadCustomers)
				.GET("/router/customers/stream", streamHandler::loadCustomers)
				.build();
	}
}
