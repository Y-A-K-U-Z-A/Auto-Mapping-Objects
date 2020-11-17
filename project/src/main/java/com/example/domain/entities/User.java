package com.example.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity{
    @Getter @Setter
    @Column(unique = true)
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String fullName;
    @Getter @Setter
    @Enumerated(value = EnumType.ORDINAL)
    private Role role;
    @ManyToMany @Getter @Setter
    @JoinTable(name = "users_games", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    private List<Game> games;

}
