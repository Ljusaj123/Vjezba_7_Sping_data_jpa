package com.example.Vjezba_7_Sping_data_jpa.dto;

import com.example.Vjezba_7_Sping_data_jpa.domain.Book;
import com.example.Vjezba_7_Sping_data_jpa.domain.Publisher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Isbn is required")
    private String isbn;

    @NotNull(message = "Number of pages are required")
    @Positive(message = "Number of pages must be bigger than 0")
    private Integer pages;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be bigger than 0")
    private BigDecimal price;

    @NotNull(message = "Publisher is required")
    private Publisher publisher;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
    }
}
