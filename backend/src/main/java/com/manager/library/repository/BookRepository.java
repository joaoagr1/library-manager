package com.manager.library.repository;

import com.manager.library.domain.Book;
import com.manager.library.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIsbn(String isbn);

    Book findFirstByCategory(Category category);
}
