package com.manager.library.repository;

import com.manager.library.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
