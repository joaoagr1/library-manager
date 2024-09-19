package com.manager.library.model.adapter;

import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.dtos.GoogleBooksResponse;
import com.manager.library.model.enums.Category;
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

    public static Book googleApiToBook(GoogleBooksResponse dto){


        GoogleBooksResponse.Item item = dto.getItems().get(0);

        GoogleBooksResponse.Item.VolumeInfo volumeInfo = item.getVolumeInfo();

        Book book = new Book();
        book.setTitle(volumeInfo.getTitle());
        book.setAuthor(volumeInfo.getAuthors().get(0));
        book.setIsbn(volumeInfo.getIndustryIdentifiers().get(1).getIdentifier());
        book.setPublicationDate(Year.parse(volumeInfo.getPublishedDate()));
        book.setCategory(Category.valueOf(volumeInfo.getCategories().get(0).toUpperCase()));

        return book;

    }
}
