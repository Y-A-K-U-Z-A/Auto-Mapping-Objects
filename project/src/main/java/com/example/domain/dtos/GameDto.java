package com.example.domain.dtos;

import java.time.LocalDate;

public class GameDto {
    private String title;
    private double price;
    private LocalDate releaseDate;
    private String description;

    public GameDto(){}

    public GameDto(String title, double price, LocalDate releaseDate, String description) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s.\n" +
                "Release date: %s\n", title, price, description, releaseDate);
    }
}
