package org.hh99.gradation.domain.entity;

import org.hh99.gradation.domain.UserAuthEnum;
import org.hh99.gradation.domain.dto.UserDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserAuthEnum author;

    public User(UserDto userRequestDto) {
        this.email = userRequestDto.getEmail();
        this.password = userRequestDto.getPassword();
        this.gender = userRequestDto.getGender();
        this.phone = userRequestDto.getPhone();
        this.address = userRequestDto.getAddress();
        this.author = userRequestDto.getAuthor();
    }
}