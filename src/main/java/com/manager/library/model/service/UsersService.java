package com.manager.library.model.service;

import com.manager.library.model.adapter.UserAdapter;
import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.UserRequestDTO;
import com.manager.library.model.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    public Users createUser(UserRequestDTO userRequestDTO) {

        validateUniqueFields(userRequestDTO);

        Users user = UserAdapter.toEntity(userRequestDTO);
        return repository.save(user);
    }


    @Transactional
    public Users updateUser(UserRequestDTO userRequestDTO, UUID id) {

        Users existingUser = repository.findById(id).orElseThrow();
        UserAdapter.updateEntity(existingUser, userRequestDTO);
        return repository.save(existingUser);

    }


    @Transactional
    public void deleteUser(UUID id) {

        repository.deleteById(id);

    }


    public Users getUser(UUID id) {

        return repository.findById(id).orElseThrow();
    }


    public List<Users> getAllUsers() {

        return repository.findAll();

    }


    private void validateUniqueFields(UserRequestDTO userRequestDTO) {

        if (repository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (repository.existsByPhone(userRequestDTO.phone())) {
            throw new IllegalArgumentException("Phone already in use");
        }
    }

}
