package com.reactive.webflux.repository;

import com.reactive.webflux.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {

   public Mono<Book> findByBookName(String bookName);

   public Flux<Book> findByPublisher(String publisher);

   public Flux<Book> findByAuthor(String author);

   public Flux<Book> findByBookNameAndAuthor(String bookName,String  author);

//   @Query("select * from book_details where author=:author and book_name=:bookName;")
//    public Flux<Book> findbooksByAuthor(String author,  String bookName);

    @Query("select * from book_details where author=:author and book_name=:name;")
    public Flux<Book> findbooksByAuthor(String author, @Param("name") String bookName);

    @Query("select * from book_details where lower(book_desc) like lower(concat('%', :search,'%')) or lower(book_name) like lower(concat('%', :search,'%')) or lower(author) like lower(concat('%', :search,'%')) or lower(publisher) like lower(concat('%', :search,'%'))")
    public Flux<Book> searchBooks(String search);
}
