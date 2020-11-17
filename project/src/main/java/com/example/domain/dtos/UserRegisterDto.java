package com.example.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email.")
    private String email;
    @NotNull
    @Size(min = 6, message = "Password length must be at least 6 characters.")
    @Pattern(regexp = "([A-Z]+[a-z]+[0-9]+)", message = "Invalid password.")
    private String password;
    @NotNull(message = "Name must not be null.")
    private String fullName;

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public UserRegisterDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
