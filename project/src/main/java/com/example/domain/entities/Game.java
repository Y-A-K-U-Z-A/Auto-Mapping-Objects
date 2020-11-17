package com.example.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {
    @Getter
    @Setter
    private String title;
    @Getter @Setter @Positive
    private double price;
    @Getter @Setter @Positive
    private double size;
    @Getter @Setter
    private String trailerId;
    @Getter @Setter
    private String thumbNailURL;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private LocalDate releasedDate;
}