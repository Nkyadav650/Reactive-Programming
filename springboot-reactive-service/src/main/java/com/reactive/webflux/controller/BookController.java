package com.reactive.webflux.controller;

import com.reactive.webflux.entity.Book;
import com.reactive.webflux.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {


    private final BookService bookService;

    @PostMapping("/")
    public Mono<Book> create(@RequestBody Book book){
    return bookService.create(book);
    }
//    @GetMapping(value = "/get-all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping("/get-alln")
    public Flux<Book> getBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Mono<Book> getBook(@PathVariable int bookId){
        return bookService.get(bookId);
    }

    @PutMapping("/update/{bookId}")
    public Mono<Book> updateBook(@PathVariable int bookId,@RequestBody Book book){
        return bookService.update(bookId,book);
    }
    @DeleteMapping("/delete/{bookId}")
    public Mono<Void> deleteBook(@PathVariable int bookId){
        return this.bookService.delete(bookId);
    }
    @GetMapping("/search")
    public Flux<Book> searchBooks(@RequestParam("key") String key){
        return this.bookService.search(key);
    }
}
