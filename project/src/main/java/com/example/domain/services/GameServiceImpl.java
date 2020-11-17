package com.example.domain.services;

import com.example.domain.dtos.GameAddDto;
import com.example.domain.dtos.GameDto;
import com.example.domain.dtos.GameViewDto;
import com.example.domain.entities.Game;
import com.example.domain.services.interfaces.GameService;
import com.example.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (this.userService.isLoggedUserAdmin()) {
            Game game = this.modelMapper.map(gameAddDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            System.out.println(gameAddDto.getTitle() + " Added");
        } else {
            System.out.println("Users cannot add/edit/delete game.");
        }
    }

    @Override
    public int getAllGamesCount() {
        return (int) gameRepository.count();
    }

    @Override
    @Transactional
    public void editGame(String[] input) {
        if (this.userService.isLoggedUserAdmin()) {
            Game gameToChange = this.gameRepository.getOne(Long.parseLong(input[1]));
            try {
                for (int i = 2; i < input.length; i++) {
                    String[] command = input[i].split("=");
                    String value = command[1];
                    switch (command[0]) {
                        case "title":
                            gameToChange.setTitle(value);
                            break;
                        case "price":
                            gameToChange.setPrice(Double.parseDouble(value));
                            break;
                        case "size":
                            gameToChange.setSize(Double.parseDouble(value));
                            break;
                        case "trailer":
                            gameToChange.setTrailerId(value);
                            break;
                        case "thumbnailURL":
                            gameToChange.setThumbNailURL(value);
                            break;
                        case "description":
                            gameToChange.setDescription(value);
                            break;
                    }
                }
                this.gameRepository.saveAndFlush(gameToChange);
                System.out.println("Edited " + gameToChange.getTitle());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("Users cannot add/edit/delete game.");
        }
    }

    @Override
    public void delete(int id) {
        if (this.userService.isLoggedUserAdmin()) {
            Game one = this.gameRepository.getOne((long) id);
            System.out.println("Deleted " + one.getTitle());
            this.gameRepository.deleteById((long) id);
        }else {
            System.out.println("Users cannot add/edit/delete game.");
        }
    }

    @Override
    public void getAllGames() {
        List<Game> all = this.gameRepository.findAll();
        for (Game game : all) {
            GameViewDto gameViewDto = this.modelMapper.map(game, GameViewDto.class);
            System.out.println(gameViewDto.toString());
        }

    }
@Transactional
    @Override
    public void getGameDetails(String title) {
        try{
            Game game = this.gameRepository.findByTitle(title);
            GameDto gameViewDto = this.modelMapper.map(game, GameDto.class);
            System.out.println(gameViewDto.toString());
        }catch (Exception e){
            System.out.println("Invalid title.");
        }
    }
    public List<Game> getGames(){
        return this.gameRepository.findAll();
    }
}
