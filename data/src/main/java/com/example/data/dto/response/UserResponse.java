package com.example.data.dto.response;


import com.example.data.model.basic.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponse {

    private String login;
    private String email;

    private Set<Role> roles;
    private Integer culinaryNewsCount;

    public UserResponse(String login, String email, Set<Role> roles) {
        this.login = login;
        this.email = email;
        this.roles = roles;
    }
}
