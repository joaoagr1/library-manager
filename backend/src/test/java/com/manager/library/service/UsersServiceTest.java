package com.manager.library.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.manager.library.domain.Users;
import com.manager.library.dtos.UserRequestDTO;
import com.manager.library.exceptions.EntityNotFoundException;
import com.manager.library.repository.UsersRepository;
import com.manager.library.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
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

        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();


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

        assertEquals(userRequestDTO.name(), createdUser.getName(),
                "The created user name should be the same as the request");
        assertEquals(userRequestDTO.email(), createdUser.getEmail(),
                "The created user email should be the same as the request");
        assertEquals(userRequestDTO.phone(), createdUser.getPhone(),
                "The created user phone should be the same as the request");


        verify(repository, times(1)).save(any(Users.class));

    }


    @Test
    @DisplayName("Should update a user")
    void shouldUpdateAUser() {

        when(repository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(repository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = usersService.updateUser(userRequestDTO, userId);

        assertNotNull(updatedUser, "The updated user should not be null");

        assertEquals(userRequestDTO.name(), updatedUser.getName(),
                "The updated user name should be the same as the request");
        assertEquals(userRequestDTO.email(), updatedUser.getEmail(),
                "The updated user email should be the same as the request");
        assertEquals(userRequestDTO.phone(), updatedUser.getPhone(),
                "The updated user phone should be the same as the request");

        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).save(any(Users.class));
    }


    @Test
    @DisplayName("Should delete a user")
    void shouldDeleteAUser() {

        when(repository.findById(userId)).thenReturn(java.util.Optional.of(user));

        usersService.deleteUser(userId);

        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).deleteById(userId);
    }



    @Test
    @DisplayName("Should get a user")
    void shouldGetAUser() {

        when(repository.findById(userId)).thenReturn(java.util.Optional.of(user));

        Users foundUser = usersService.getUser(userId);

        assertNotNull(foundUser, "The found user should not be null");

        assertEquals(user.getName(), foundUser.getName(),
                "The found user name should be the same as the request");
        assertEquals(user.getEmail(), foundUser.getEmail(),
                "The found user email should be the same as the request");
        assertEquals(user.getPhone(), foundUser.getPhone(),
                "The found user phone should be the same as the request");

        verify(repository, times(1)).findById(userId);
    }


    @Test
    @DisplayName("Should get all users")
    void shouldGetAllUsers() {
        List<Users> userList = List.of(Users.builder()
                        .id(UUID.randomUUID())
                        .name("User1")
                        .email("user1@example.com")
                        .phone("12345")
                        .build(),
                Users.builder()
                        .id(UUID.randomUUID())
                        .name("User2")
                        .email("user2@example.com")
                        .phone("67890").build()
        );

        when(repository.findAll()).thenReturn(userList);

        List<Users> allUsers = usersService.getAllUsers();

        assertNotNull(allUsers, "The list of users should not be null");
        assertEquals(2, allUsers.size(), "The list should contain exactly 2 users");

        assertEquals("User1", allUsers.get(0).getName());
        assertEquals("user1@example.com", allUsers.get(0).getEmail());
        assertEquals("User2", allUsers.get(1).getName());
        assertEquals("user2@example.com", allUsers.get(1).getEmail());

        verify(repository, times(1)).findAll();
    }


    @Test
    public void testCreateUser_EmailAlreadyInUse() {
        when(repository.existsByEmail(userRequestDTO.email())).thenReturn(true);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> usersService.createUser(userRequestDTO));

        assertEquals("Email already in use", exception.getMessage());
    }

    @Test
    public void testCreateUser_PhoneAlreadyInUse() {
        when(repository.existsByEmail(userRequestDTO.email())).thenReturn(false);
        when(repository.existsByPhone(userRequestDTO.phone())).thenReturn(true);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> usersService.createUser(userRequestDTO));

        assertEquals("Phone already in use", exception.getMessage());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        when(repository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> usersService.updateUser(userRequestDTO, userId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        when(repository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> usersService.deleteUser(userId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testGetUser_UserNotFound() {
        when(repository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> usersService.getUser(userId));

        assertEquals("User not found", exception.getMessage());
    }




}
