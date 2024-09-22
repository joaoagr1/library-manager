package com.manager.library.service;

import com.manager.library.adapter.UserAdapter;
import com.manager.library.domain.Users;
import com.manager.library.dtos.UserRequestDTO;
import com.manager.library.exceptions.EntityNotFoundException;
import com.manager.library.repository.LoanRepository;
import com.manager.library.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;
    @Autowired
    private LoanRepository loanRepository;

    public Users createUser(UserRequestDTO userRequestDTO) {

        validateUniqueFields(userRequestDTO);

        Users user = UserAdapter.toEntity(userRequestDTO);
        return repository.save(user);
    }


    @Transactional
    public Users updateUser(UserRequestDTO userRequestDTO, UUID id) {

        Users existingUser = repository.findById(id).orElseThrow( () -> new EntityNotFoundException("User not found"));
        UserAdapter.updateEntity(existingUser, userRequestDTO);
        return repository.save(existingUser);

    }


    @Transactional
    public void deleteUser(UUID id) {

        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!loanRepository.findAllByUserId(id).isEmpty()) {
            throw new IllegalArgumentException("User has active loans");
        }

        repository.deleteById(id);

    }


    public Users getUser(UUID id) {

        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

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
