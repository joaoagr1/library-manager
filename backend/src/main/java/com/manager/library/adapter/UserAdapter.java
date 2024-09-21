package com.manager.library.adapter;

import com.manager.library.domain.Users;
import com.manager.library.dtos.UserRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {

    public static Users toEntity(UserRequestDTO dto) {

        return Users.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .build();

    }

    public static void updateEntity(Users existingUser, UserRequestDTO dto) {

        if (dto.name() != null && !dto.name().isEmpty()) {
            existingUser.setName(dto.name());
        }
        if (dto.email() != null && !dto.email().isEmpty()) {
            existingUser.setEmail(dto.email());
        }

        if (dto.phone() != null && !dto.phone().isEmpty()) {
            existingUser.setPhone(dto.phone());
        }

    }
}
