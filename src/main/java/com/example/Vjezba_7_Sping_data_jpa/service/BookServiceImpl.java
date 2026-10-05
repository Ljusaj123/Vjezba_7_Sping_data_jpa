package com.example.Vjezba_7_Sping_data_jpa.service;

import com.example.Vjezba_7_Sping_data_jpa.domain.Book;
import com.example.Vjezba_7_Sping_data_jpa.domain.Publisher;
import com.example.Vjezba_7_Sping_data_jpa.dto.BookDto;
import com.example.Vjezba_7_Sping_data_jpa.repository.SpringDataBookRepository;
import com.example.Vjezba_7_Sping_data_jpa.repository.SpringDataPublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private SpringDataBookRepository springDataBookRepository;
    private SpringDataPublisherRepository springDataPublisherRepository;


    @Override
    public List<BookDto> findAll() {
        return springDataBookRepository.findAll().stream().map(BookDto::new).toList();
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        return springDataBookRepository.findByIsbn(isbn).map(BookDto::new);
    }

    @Override
    public Optional<BookDto> saveBook(BookDto bookDto) {
        //        Book book = new Book(bookDto);

        Book book = convertBookDtoToBook(bookDto);

        Book savedBook = springDataBookRepository.save(book);

        return Optional.of(new BookDto(savedBook));
    }

    @Override
    public Optional<BookDto> updateBook(BookDto bookDto, Long id) {
        Optional<Book> bookToUpdate = springDataBookRepository.findById(id);

        if (bookToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with Id" + id + "does not exist");
        }

        Book book = bookToUpdate.get();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPages(bookDto.getPages());

        //        book.setPublisher(bookDto.getPublisher());

        Publisher publisher = springDataPublisherRepository.findByName(bookDto.getPublisherName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Publisher with name " + bookDto.getPublisherName() + " does not exist"
                ));

        book.setPublisher(publisher);

        Book savedBook = springDataBookRepository.save(book);

        return Optional.of(new BookDto(savedBook));
    }

    @Override
    public void daleteByIsbn(Long id) {

        Optional<Book> book = springDataBookRepository.findById(id);

        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with Id" + id + "does not exist");
        }

        springDataBookRepository.deleteById(id);
    }

    private Book convertBookDtoToBook(BookDto bookDto){

        Publisher publisher = springDataPublisherRepository.findByName(bookDto.getPublisherName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Publisher with name " + bookDto.getPublisherName() + " does not exist"
                ));

        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getIsbn(),
                bookDto.getPages(),
                bookDto.getPrice(),
                publisher
        );

        //ILI

//        Book book = Book.builder()
//                .id(bookDto.getId())
//                .title(bookDto.getTitle())
//                .isbn(bookDto.getIsbn())
//                .price(bookDto.getPrice())
//                .pages(bookDto.getPages())
//                .publisher(publisher)
//                .build();
    }
}
