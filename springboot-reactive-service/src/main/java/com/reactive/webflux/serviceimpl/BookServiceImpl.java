package com.reactive.webflux.serviceimpl;

import com.reactive.webflux.entity.Book;
import com.reactive.webflux.exception.IdNotFoundException;
import com.reactive.webflux.repository.BookRepository;
import com.reactive.webflux.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    @Override
    public Mono<Book> create(Book book) {
        System.out.println("current thread before create book: " + Thread.currentThread().getName());
        return bookRepository.save(book).doOnNext(data -> {
            System.out.println("current thread after create book: " + Thread.currentThread().getName());
        });
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll().delayElements(Duration.ofSeconds(2));
    }

    @Override
    public Mono<Book> get(int bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new IdNotFoundException("Id " + bookId + " is not available")));
    }

    @Override
    public Mono<Book> update(int bookId, Book book) {
        Mono<Book> oldBook = bookRepository.findById(bookId);
        return oldBook.flatMap(book1 -> {
            book1.setBookName(book.getBookName());
            book1.setDescription(book.getDescription());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            return bookRepository.save(book1);
        });
    }

    @Override
    public Mono<Void> delete(int bookId) {
//    bookRepository.deleteById(bookId);
        return bookRepository.findById(bookId)
                .onErrorMap(e->new IdNotFoundException("Id "+bookId+" is not available"))
                .flatMap(bookRepository::delete);
    }


    @Override
    public Flux<Book> search(String query) {
        return this.bookRepository.searchBooks(query);
    }
}
