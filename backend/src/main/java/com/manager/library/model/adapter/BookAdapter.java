package com.manager.library.model.adapter;

import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.dtos.GoogleBooksDetailResponseDTO;
import com.manager.library.model.dtos.GoogleBooksResponseDTO;
import com.manager.library.model.enums.Category;
import org.springframework.stereotype.Component;

import java.time.Year;
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

    public static Book googleApiToBook(GoogleBooksResponseDTO dto) {
        GoogleBooksResponseDTO.Item item = dto.getItems().get(0);
        GoogleBooksResponseDTO.Item.VolumeInfo volumeInfo = item.getVolumeInfo();

        Book book = new Book();
        book.setTitle(volumeInfo.getTitle());
        book.setAuthor(volumeInfo.getAuthors().get(0));
        book.setIsbn(volumeInfo.getIndustryIdentifiers().get(1).getIdentifier());
        book.setCategory(Category.valueOf(volumeInfo.getCategories().get(0).toUpperCase()));

        String publishedDateStr = volumeInfo.getPublishedDate();
        try {

            String yearStr = publishedDateStr.substring(0, 4);
            Year publishedYear = Year.parse(yearStr);


            book.setPublicationDate(publishedYear);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Date: " + publishedDateStr, e);
        }

        return book;
    }

    public static GoogleBooksDetailResponseDTO from(GoogleBooksResponseDTO.Item item) {
        GoogleBooksResponseDTO.Item.VolumeInfo volumeInfo = item.getVolumeInfo();
        GoogleBooksDetailResponseDTO.VolumeInfo dtoVolumeInfo = new GoogleBooksDetailResponseDTO.VolumeInfo(
                volumeInfo.getTitle(),
                volumeInfo.getAuthors(),
                volumeInfo.getPublishedDate(),
                volumeInfo.getIndustryIdentifiers().stream()
                        .map(industryIdentifier -> new GoogleBooksDetailResponseDTO.IndustryIdentifier(
                                industryIdentifier.getType(),
                                industryIdentifier.getIdentifier()
                        )).toList(),
                volumeInfo.getCategories()
        );
        return GoogleBooksDetailResponseDTO.builder()
                .id(item.getId())
                .volumeInfo(dtoVolumeInfo)
                .build();
    }

}
