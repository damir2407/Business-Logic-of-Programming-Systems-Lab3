package com.example.data.dto;


import com.example.data.dto.response.UserResponse;
import com.example.data.model.basic.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class UserDTOMapper implements Function<User, UserResponse> {

    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getLogin(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
