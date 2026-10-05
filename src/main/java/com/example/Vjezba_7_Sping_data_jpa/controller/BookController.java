package com.example.Vjezba_7_Sping_data_jpa.controller;

import com.example.Vjezba_7_Sping_data_jpa.dto.BookDto;
import com.example.Vjezba_7_Sping_data_jpa.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.findAll().stream().toList();
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn){
        return bookService.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Book with ISBN " + isbn + " does not exist"
                ));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String createBook(@Valid @RequestBody BookDto book){
        bookService.saveBook(book);
        return "Book created successfully";
    }

    @PutMapping("/{book_id}")
    public ResponseEntity createBook(@Valid @PathVariable Long book_id, @RequestBody BookDto book){
        bookService.updateBook(book,book_id);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity createBook(@PathVariable Long book_id){
        bookService.daleteByIsbn(Long.valueOf(book_id));
        return ResponseEntity.ok("Book deleted successfully");


    }

}
