package com.manager.library.adapter;

import com.manager.library.domain.Book;
import com.manager.library.dtos.BookRequestDTO;
import com.manager.library.dtos.GoogleBooksDetailResponseDTO;
import com.manager.library.dtos.GoogleBooksResponseDTO;
import com.manager.library.enums.Category;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.time.format.DateTimeParseException;

@Component
public class BookAdapter {

    public static Book toEntity(BookRequestDTO dto) {

        return Book.builder().title(dto.title())
                .author(dto.author())
                .isbn(dto.isbn())
                .publicationDate(dto.publicationDate())
                .category(dto.category())
                .build();

    }

    public static Book googleApiToBook(GoogleBooksResponseDTO dto) {

        GoogleBooksResponseDTO.Item item = dto.getItems().get(0);
        GoogleBooksResponseDTO.Item.VolumeInfo volumeInfo = item.getVolumeInfo();

        Year publishedYear = parsePublishedYear(volumeInfo.getPublishedDate());

        return Book.builder()
                .title(volumeInfo.getTitle())
                .author(volumeInfo.getAuthors().get(0))
                .isbn(volumeInfo.getIndustryIdentifiers()
                        .get(1)
                        .getIdentifier())
                .category(Category.valueOf(volumeInfo.getCategories()
                        .get(0).toUpperCase()))
                .publicationDate(publishedYear)
                .build();
    }


    public static GoogleBooksDetailResponseDTO from(GoogleBooksResponseDTO.Item item) {

        GoogleBooksResponseDTO.Item.VolumeInfo volumeInfo = item.getVolumeInfo();

        GoogleBooksDetailResponseDTO.VolumeInfo dtoVolumeInfo = new GoogleBooksDetailResponseDTO.VolumeInfo(
                volumeInfo.getTitle(),
                volumeInfo.getAuthors(),
                volumeInfo.getPublishedDate(),
                volumeInfo.getIndustryIdentifiers().stream()
                        .map(industryIdentifier -> new GoogleBooksDetailResponseDTO.IndustryIdentifier(
                                industryIdentifier.getType(), industryIdentifier.getIdentifier())).toList(),
                volumeInfo.getCategories());
        return GoogleBooksDetailResponseDTO.builder().id(item.getId()).volumeInfo(dtoVolumeInfo).build();
    }


    private static Year parsePublishedYear(String publishedDateStr) {

        try {

            String yearStr = publishedDateStr.substring(0, 4);
            return Year.parse(yearStr);

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException("Invalid Date: " + publishedDateStr, e);

        }

    }

}
