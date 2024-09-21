package com.manager.library.repository;

import com.manager.library.domain.Users;
import com.manager.library.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Should save a user")
    public void shouldSaveAUser() {

        Users user = Users.builder()
                .name("João Rolo")
                .email("joao.rollo1@gmail.com")
                .registrationDate(LocalDate.of(2021, 10, 10))
                .phone("44933000755")
                .build();

        usersRepository.save(user);

        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("João Rolo");
        assertThat(user.getEmail()).isEqualTo("joao.rollo1@gmail.com");
        assertThat(user.getRegistrationDate()).isEqualTo(LocalDate.of(2021, 10, 10));
        assertThat(user.getPhone()).isEqualTo("44933000755");

    }

    @Test
    @DisplayName("Should delete a user")
    public void shouldDeleteAUser() {

        Users user = Users.builder()
                .name("João Rolo")
                .email("joao.rollo1@gmail.com")
                .registrationDate(LocalDate.of(2021, 10, 10))
                .phone("44933000755")
                .build();

        usersRepository.save(user);

        usersRepository.delete(user);

        assertThat(usersRepository.findById(user.getId())).isEmpty();

    }


    @Test
    @DisplayName("Should find a user by email")
    public void shouldFindAUserByEmail() {

        Users user = Users.builder()
                .name("João Rolo")
                .email("joao.rollo1@gmail.com")
                .registrationDate(LocalDate.of(2021, 10, 10))
                .phone("44933000755")
                .build();

        usersRepository.save(user);

        boolean userFound = usersRepository.existsByEmail("joao.rollo1@gmail.com");

        assertThat(userFound).isTrue();

    }

    @Test
    @DisplayName("Should find a user by phone")
    public void shouldFindAUserByPhone() {

        Users user = Users.builder()
                .name("João Rolo")
                .email("joao.rollo1@gmail.com")
                .registrationDate(LocalDate.of(2021, 10, 10))
                .phone("44933000755")
                .build();

        usersRepository.save(user);

        boolean userFound = usersRepository.existsByPhone("44933000755");

        assertThat(userFound).isTrue();

    }

}