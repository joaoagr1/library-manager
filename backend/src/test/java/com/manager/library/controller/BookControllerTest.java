package com.manager.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.enums.Category;
import com.manager.library.model.service.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Year;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {

        objectMapper.registerModule(new JavaTimeModule());
        RestAssuredMockMvc.config = RestAssuredMockMvc.config().objectMapperConfig(
                RestAssuredMockMvc.config().getObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
        );
    }


    @Test
    public void testCreateBook_Success() throws Exception {

        BookRequestDTO requestDTO = new BookRequestDTO("Test Title", "Test Author", "1234567890", Year.of(2019), Category.FICTION);
        Book newBook = Book.builder()
                .title(requestDTO.title())
                .author(requestDTO.author())
                .isbn(requestDTO.isbn())
                .publicationDate(requestDTO.publicationDate())
                .category(requestDTO.category())
                .build();

        when(bookService.createBook(requestDTO)).thenReturn(newBook);


        mockMvc.perform(post("/library/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(requestDTO.title()))
                .andExpect(jsonPath("$.author").value(requestDTO.author()))
                .andExpect(jsonPath("$.isbn").value(requestDTO.isbn()))
                .andExpect(jsonPath("$.publicationDate").isNotEmpty())
                .andExpect(jsonPath("$.category").value(requestDTO.category().toString()));
    }


}