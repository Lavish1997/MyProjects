package com.ecomsec.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
}
