package com.example.domain.services.interfaces;

import com.example.domain.dtos.UserLoginDto;
import com.example.domain.dtos.UserRegisterDto;
import com.example.domain.entities.Game;

import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto loginDto);
    void logout();
    boolean isLoggedUserAdmin();
    void getUserGames();
    void addGameToUser(List<Game> games);
}
