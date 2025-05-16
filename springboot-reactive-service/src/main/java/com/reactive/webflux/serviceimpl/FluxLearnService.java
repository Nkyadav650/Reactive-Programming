package com.reactive.webflux.serviceimpl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Service
public class FluxLearnService {

    public void FluxTestingService() {
        System.out.println("Flux Testing service");
    }

    public Flux<String> getFlux() {
        return Flux.just("Ankit", "Sandip", "rajesh", "nand");
    }

    public Flux<String> fruitsFlux() {
        List<String> fruits = List.of("Mango", "Apple", "Lichi", "banana");
        return Flux.fromIterable(fruits);
    }

    public Flux<Void> emptyFlux() {
        return Flux.empty();
    }

    //Map
    public Flux<String> mapExmapleFlux() {
        return this.fruitsFlux().map(String::toUpperCase);
    }

    //    filter
    public Flux<String> filterFluxExample() {
        return this.getFlux().filter(str -> str.length() > 4).log();
    }

    //    Flatmap
    public Flux<String> flatmapExmapleFlux() {
        return this.getFlux().flatMap(name -> Flux.just("flatmap testing")).delayElements(Duration.ofSeconds(2));
    }

    // transform element
    public Flux getTransformElement() {
        Function<Flux<String>, Flux<String>> funInterface = (name) -> name.map(String::toUpperCase);
        return getFlux().transform(funInterface).log();
    }

    // defaultIfEmpty()   and    switchIfEmpty()
    public Flux<String> ifExample(int length) {
        return this.getFlux().filter(name -> name.length() > length)
//                .defaultIfEmpty("No Name")
                .switchIfEmpty(this.fruitsFlux())
                .log();
    }


    //    concat(static) / concatWith(instance)
    public Flux<String> concatExample() {
        return this.getFlux().concatWith(this.fruitsFlux()).delayElements(Duration.ofSeconds(2));
    }
}

