package com.example.Vjezba_7_Sping_data_jpa.domain;

import com.example.Vjezba_7_Sping_data_jpa.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private Integer pages;
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    public Book(BookDto book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
    }
}
