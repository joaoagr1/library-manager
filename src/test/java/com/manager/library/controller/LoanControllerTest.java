package com.manager.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.LoanRequestDTO;
import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.enums.Category;
import com.manager.library.model.enums.LoanStatus;
import com.manager.library.model.service.BookService;
import com.manager.library.model.service.LoanService;
import com.manager.library.model.service.UsersService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @MockBean
    private UsersService userService;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RestAssuredMockMvc.config = RestAssuredMockMvc.config().objectMapperConfig(
                RestAssuredMockMvc.config().getObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
        );
    }

    @Test
    @DisplayName("should create a loan successfully")
    public void testCreateLoanSuccessfully() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID bookId = UUID.randomUUID();
        LocalDate returnDate = LocalDate.now().plusDays(7);

        Users user = Users.builder()
                .id(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .build();

        Book book = Book.builder()
                .id(bookId)
                .title("Test Book")
                .author("Test Author")
                .isbn("1234567890")
                .publicationDate(LocalDate.now().minusYears(1))
                .category(Category.CLASSICS)
                .build();

        LoanRequestDTO requestDTO = new LoanRequestDTO(user.getId(), book.getId(), returnDate);
        Loan newLoan = Loan.builder()
                .user(user)
                .book(book)
                .returnDate(returnDate)
                .status(LoanStatus.ACTIVE)
                .build();

        when(userService.getUser(userId)).thenReturn(user);
        when(bookService.getBookById(bookId)).thenReturn(book);
        when(loanService.createLoan(requestDTO)).thenReturn(newLoan);

        mockMvc.perform(post("/library/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user.id").value(userId.toString()))
                .andExpect(jsonPath("$.book.id").value(bookId.toString()))
                .andExpect(jsonPath("$.returnDate").value(returnDate.toString()))
                .andExpect(jsonPath("$.status").value(LoanStatus.ACTIVE.toString()));
    }
}