//package com.reactive.webflux;
//
//import com.reactive.webflux.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import reactor.test.StepVerifier;
//
//@SpringBootTest
//public class RepositoryTest {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    public void repoTest(){
//        bookRepository
////                .findByBookName("Basic reactive programming ")
////                .findByPublisher("xyz publisher")
////                .findByAuthor("jams")
////                .findByBookNameAndAuthor("Basic Spring boot","jams")
////                .findbooksByAuthor("jams","Basic Spring boot")
//                .searchBooks("basic")
//                .log()
//                .as(StepVerifier::create)
//                .expectNextCount(3)
//                .verifyComplete();
//    }
//}
