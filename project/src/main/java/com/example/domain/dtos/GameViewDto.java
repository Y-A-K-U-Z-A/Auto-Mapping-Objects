package com.example.domain.dtos;

public class GameViewDto {
    private String title;
    private double price;

    public GameViewDto(){}

    public GameViewDto(String title, double price) {
        this.title = title;
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("%s %.2f", this.title, this.price);
    }
}
