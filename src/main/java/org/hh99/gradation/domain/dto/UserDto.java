package org.hh99.gradation.domain.dto;

import org.hh99.gradation.domain.UserAuthEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDto {
    private String email;
    @Setter
    private String password;
    private String gender;
    private String phone;
    private String address;
    @Setter
    private UserAuthEnum author;
}
