package com.example.domain.services.interfaces;

import com.example.domain.dtos.GameAddDto;
import com.example.domain.entities.Game;

import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
    int getAllGamesCount();
    void editGame(String[] input);
    void delete(int id);
    void getAllGames();
    void getGameDetails(String s);
    List<Game> getGames();
}
