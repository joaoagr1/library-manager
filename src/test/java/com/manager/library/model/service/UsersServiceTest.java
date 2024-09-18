package com.manager.library.model.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.UserRequestDTO;
import com.manager.library.model.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class UsersServiceTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private UsersService usersService;

    private UUID userId;
    private Users user;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();
        user = new Users();
        user.setId(userId);
        user.setEmail("joao.rollo1@teste.com");
        user.setPhone("1234567890");
        user.setName("João Rolo");

        userRequestDTO = new UserRequestDTO(
                "João Rolo",
                "joao.rollo1@teste.com",
                "1234567890"
        );
    }

    @Test
    @DisplayName("Should create a user")
    void shouldCreateAUser() {

        when(repository.save(any(Users.class))).thenReturn(user);


        Users createdUser = usersService.createUser(userRequestDTO);


        assertNotNull(createdUser, "The created user should not be null");


        assertEquals(userRequestDTO.name(), createdUser.getName(), "The created user name should be the same as the request");
        assertEquals(userRequestDTO.email(), createdUser.getEmail(), "The created user email should be the same as the request");
        assertEquals(userRequestDTO.phone(), createdUser.getPhone(), "The created user phone should be the same as the request");


        verify(repository, times(1)).save(any(Users.class));
    }
}
