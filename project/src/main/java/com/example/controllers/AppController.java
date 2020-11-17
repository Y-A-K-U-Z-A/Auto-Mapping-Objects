package com.example.controllers;

import com.example.domain.dtos.GameAddDto;
import com.example.domain.dtos.UserLoginDto;
import com.example.domain.dtos.UserRegisterDto;
import com.example.domain.services.interfaces.GameService;
import com.example.domain.services.interfaces.UserService;
import com.example.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AppController implements CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader bufferedReader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Please enter command (RegisterUser/LoginUser/Logout/AddGame/EditGame/DeleteGame):");
        seedGamesToUser();
        String line = bufferedReader.readLine();
        while (!"".equals(line)) {
            String[] input = line.split("\\|");
            switch (input[0]) {
                case "RegisterUser":
                    if (!input[2].equals(input[3])) {
                        System.out.println("Passwords don't match.");
                        break;
                    }
                    registerUser(input);
                    break;
                case "LoginUser":
                    UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);
                    userService.loginUser(userLoginDto);
                    break;
                case "Logout":
                    this.userService.logout();
                    break;
                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(input[1], Double.parseDouble(input[2]), Double.parseDouble(input[3]), input[4],
                            input[5], input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                    if (validationUtil.isValid(gameAddDto)) {
                        gameService.addGame(gameAddDto);
                    } else {
                        this.validationUtil.getViolations(gameAddDto).forEach(e -> System.out.println(e.getMessage()));
                    }
                    break;
                case "EditGame":
                { int id = Integer.parseInt(input[1]);

                    if (id <= gameService.getAllGamesCount()) {
                        this.gameService.editGame(input);
                    } else {
                        System.out.println("Invalid game id.");
                        break;
                    }
                    break;}
                case "DeleteGame":
                    int id = Integer.parseInt(input[1]);
                    if (id <= gameService.getAllGamesCount()) {
                        this.gameService.delete(id);
                    } else {
                        System.out.println("Invalid game id.");
                        break;
                    }
                    break;
                case "AllGames":
                    this.gameService.getAllGames();
                    break;
                case "DetailGame":
                    this.gameService.getGameDetails(input[1]);
                    break;
                case "OwnedGames":
                    this.userService.getUserGames();
                    break;
            }
            System.out.println("Please enter command (RegisterUser/LoginUser/Logout/AddGame/EditGame/DeleteGame):");
            System.out.println("Press enter to exit.");
            line = bufferedReader.readLine();
        }
    }

    private void seedGamesToUser() {

        this.userService.addGameToUser(this.gameService.getGames());
    }

    private void registerUser(String[] input) {
        UserRegisterDto user = new UserRegisterDto(input[1], input[2], input[4]);
        if (this.validationUtil.isValid(user)) {
            this.userService.registerUser(user);
            System.out.println(input[1] + " was registered");
        } else {
            this.validationUtil.getViolations(user)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }


}
