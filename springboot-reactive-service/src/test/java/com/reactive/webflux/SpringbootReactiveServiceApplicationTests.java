package com.reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ConcurrentHashMap;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;


@Slf4j
@SpringBootTest
class SpringbootReactiveServiceApplicationTests {

	@Test
	void contextLoads() {
//		ConcurrentHashMap<String,Integer> map= new ConcurrentHashMap<>();
//		map.put("A",1);
//		map.put("B",2);
//		map.put("C",3);
//		map.put("D",4);
//		System.out.println(map.get("B"));
//		System.out.println(map.putIfAbsent("E",100));
//		map.forEach((k,v)-> System.out.println(k+" : "+v));
//		Mono<?> monoString= Mono.just("eidiko")
//				.then(Mono.error(new RuntimeException("Exception occured")))
//				.log();
//		monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
	}
//
//	@Test
//	void testMono() {
//		Mono<?> monoString= Mono.just("eidiko").log();
//		monoString.subscribe(System.out::println);
//	}
//
//
//	@Test
//	void testFlux() {
//		Flux<String> fluxString= Flux.just("eidiko","java","python","local")
//				.log();
//		fluxString.subscribe(System.out::println);
//	}
//	public static boolean isEven(int i){
//		return i%2==0;
//	}
//	@Test
//	void exampleTest(){
////		Mono<String> m1	 = Mono.just("Learn code with durgesh");
////		m1.subscribe(System.out::println);
////		Mono<String> map = m1.map(String::toUpperCase);
////		map.subscribe(System.out::println);
//
////		Flux<Integer> num = Flux.range(1, 20);
////		num.filter(SpringbootReactiveServiceApplicationTests::isEven).log()
////		.subscribe(System.out::println);
//
//		Flux<String> flux1 = Flux.just("string", "Integer", "object", "boolean").log();
//		flux1.map(String::toUpperCase).subscribe(System.out::println);
//		List<String> list = Arrays.asList("string", "Integer", "object", "boolean");
////		list.stream().flatMap(word->Arrays.stream(word.toCharArray())).collect(Collectors.toList());
//		List<Integer> list1 = list.stream().map(word -> word.length()).collect(Collectors.toList());
//	}
//
//	public Flux<Integer> asyncWordLengths(Flux<String> words){
//		return words.flatMap(word->Mono.just(word.length())).delayElements(Duration.ofMillis(500));
//	}


}
