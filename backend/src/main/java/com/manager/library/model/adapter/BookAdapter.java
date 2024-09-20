package com.manager.library.model.adapter;

import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.dtos.GoogleBooksResponse;
import com.manager.library.model.enums.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class BookAdapter {

    public static Book  toEntity(BookRequestDTO dto) {
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

    public static Book googleApiToBook(GoogleBooksResponse dto) {
        GoogleBooksResponse.Item item = dto.getItems().get(0);
        GoogleBooksResponse.Item.VolumeInfo volumeInfo = item.getVolumeInfo();

        Book book = new Book();
        book.setTitle(volumeInfo.getTitle());
        book.setAuthor(volumeInfo.getAuthors().get(0));
        book.setIsbn(volumeInfo.getIndustryIdentifiers().get(1).getIdentifier());
        book.setCategory(Category.valueOf(volumeInfo.getCategories().get(0).toUpperCase()));

        String publishedDateStr = volumeInfo.getPublishedDate();
        try {

            String yearStr = publishedDateStr.substring(0, 4); // Sempre pegar os 4 primeiros caracteres
            Year publishedYear = Year.parse(yearStr);


            book.setPublicationDate(publishedYear);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data de publicação inválida: " + publishedDateStr, e);
        }

        return book;
    }
}
