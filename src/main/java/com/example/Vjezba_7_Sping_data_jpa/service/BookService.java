package com.example.Vjezba_7_Sping_data_jpa.service;

import com.example.Vjezba_7_Sping_data_jpa.domain.Book;
import com.example.Vjezba_7_Sping_data_jpa.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> findAll();
    Optional<BookDto> findByIsbn(String isbn);
    Optional<BookDto> saveBook(BookDto book);
    Optional<BookDto> updateBook(BookDto book, Long id);
    void daleteByIsbn(Long id);

}
