//package com.reactive.webflux;
//
//import com.reactive.webflux.serviceimpl.FluxLearnService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//@SpringBootTest
//public class FluxLearnTest {
//
//    @Autowired
//    private FluxLearnService fluxLearnService;
//
//    // ctl + alt + L   for arrange the code in intellij
//    @Test
//    public void testing() {
//        this.fluxLearnService.FluxTestingService();
//    }
//
//    @Test
//    public void simpleFluxTesting() {
////        this.fluxLearnService.getFlux().subscribe(System.out::println);
//        this.fluxLearnService.fruitsFlux().subscribe(System.out::println);
//    }
//
//    @Test
//    public void mapTest() {
//        Flux<String> capFlux = this.fluxLearnService.mapExmapleFlux().log();
//        StepVerifier.create(capFlux).expectNext("Mango".toUpperCase(), "Apple".toUpperCase(), "Lichi".toUpperCase(), "banana".toUpperCase()).verifyComplete();
//    }
//
//    @Test
//    public void filterTest() {
//        Flux<String> filtered = this.fluxLearnService.filterFluxExample();
//        StepVerifier.create(filtered).expectNextCount(3).verifyComplete();
//
//    }
//
//    @Test
//    public void flatMapTest(){
//        Flux<String> flatmapFlux = this.fluxLearnService.flatmapExmapleFlux().log();
//        StepVerifier.create(flatmapFlux).expectNextCount(4).verifyComplete();
//    }
//
//    @Test
//    void getTransformElement() {
//        Flux element = this.fluxLearnService.getTransformElement();
//        StepVerifier.create(element).expectNextCount(4).verifyComplete();
//    }
//
//    @Test
//    void ifExample() {
//        Flux<String> res =this.fluxLearnService.ifExample(3);
//        StepVerifier.create(res).expectNextCount(4).verifyComplete();
//
//    }
//    @Test
//    public void concatExample(){
//        Flux<String> stringFlux = this.fluxLearnService.concatExample().log();
//        StepVerifier.create(stringFlux).expectNextCount(8).verifyComplete();
//    }
//
//}
