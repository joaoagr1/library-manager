package com.manager.library.model.adapter;

import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.UserRequestDTO;

public class UserAdapter {

    public static Users toEntity(UserRequestDTO dto) {
        Users user = new Users();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        return user;
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
