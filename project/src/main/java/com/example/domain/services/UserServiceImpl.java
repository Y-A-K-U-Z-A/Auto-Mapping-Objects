package com.example.domain.services;

import com.example.domain.dtos.UserDto;
import com.example.domain.dtos.UserLoginDto;
import com.example.domain.dtos.UserRegisterDto;
import com.example.domain.entities.Game;
import com.example.domain.entities.Role;
import com.example.domain.entities.User;
import com.example.domain.services.interfaces.UserService;
import com.example.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public void loginUser(UserLoginDto loginDto) {
        String email = loginDto.getEmail();
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("Incorrect username or password.");
            return;
        } else if (!user.getPassword().equals(loginDto.getPassword())) {
            System.out.println("Incorrect username or password.");
            return;
        }

        this.userDto = this.modelMapper.map(user, UserDto.class);

        System.out.println("Successful logged in " + user.getFullName());

    }

    @Override
    public void logout() {
        if (this.userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }

        String name = this.userDto.getFullName();
        this.userDto = null;
        System.out.println("User " + name + " successfully logged out");
    }

    @Override
    public boolean isLoggedUserAdmin() {
        try {
            return "ADMIN".equals(this.userDto.getRole().name());
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void getUserGames() {
        User user = this.userRepository.findByEmail(this.userDto.getEmail());
        try {
            for (Game game : user.getGames()) {
                    System.out.println(game.getTitle());
            }
        }catch (Exception e){
            System.out.println("No games found for this user.");
        }
    }

    @Override
    public void addGameToUser(List<Game> games) {
        User one = this.userRepository.getOne((long) 1);
        one.setGames(games);
        this.userRepository.saveAndFlush(one);
        System.out.println(one.getFullName() + " owns " + one.getGames().size());
    }


}
