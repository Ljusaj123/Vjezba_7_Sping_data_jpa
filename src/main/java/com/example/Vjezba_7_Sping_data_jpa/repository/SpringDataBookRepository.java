package com.example.Vjezba_7_Sping_data_jpa.repository;

import com.example.Vjezba_7_Sping_data_jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
