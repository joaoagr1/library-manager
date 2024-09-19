package com.manager.library.model.adapter;

import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class BookAdapter {

    public static Book toEntity(BookRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn());
        book.setPublicationDate(dto.publicationDate());
        book.setCategory(dto.category());
        return book;
    }
}
