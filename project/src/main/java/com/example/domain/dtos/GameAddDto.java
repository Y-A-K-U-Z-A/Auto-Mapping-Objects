package com.example.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class GameAddDto {
    @Pattern(regexp = "[A-Z][a-z]{2,100}", message = "Invalid title.")
    private String title;
    @Positive(message = "Price must be positive number.")
    private double price;
    @Positive(message = "Size must be positive number.")
    private double size;
    @Size(min = 11, max = 11, message = "Trailer is not valid.")
    private String trailerId;
    @Pattern(regexp = "^http:\\/\\/.+|https:\\/\\/.+", message = "Invalid url.")
    private String thumbNailURL;
    @Size(min = 20, message = "Invalid description.")
    private String description;
    private LocalDate releasedDate;

    public GameAddDto(){}

    public GameAddDto(String title, double price, double size, String trailerId, String thumbNailURL, String description, LocalDate releasedDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailerId = trailerId;
        this.thumbNailURL = thumbNailURL;
        this.description = description;
        this.releasedDate = releasedDate;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getThumbNailURL() {
        return thumbNailURL;
    }

    public void setThumbNailURL(String thumbNailURL) {
        this.thumbNailURL = thumbNailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }
}
