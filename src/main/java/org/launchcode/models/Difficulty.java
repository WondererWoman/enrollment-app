package org.launchcode.models;

import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erin DeVries on 4/9/2017.
 */
@Entity
public class Difficulty {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String level;

    @OneToMany
    @JoinColumn(name = "difficulty_id")
    private List<Difficulty> difficulties = new ArrayList<>();

    public Difficulty() {
    }

    public Difficulty(String level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
