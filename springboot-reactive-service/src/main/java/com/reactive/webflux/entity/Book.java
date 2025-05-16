package com.reactive.webflux.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    private int bookId;
    @Column("book_name")
    private String  bookName;
    @Column("book_desc")
    private String  description;
    private String  publisher;
    private String  author;
}
