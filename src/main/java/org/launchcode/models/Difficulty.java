package org.launchcode.models;

import javax.persistence.Id;

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
    private List<Course> courses = new ArrayList<>();

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

    public List<Course> getCourses() {
        return courses;
    }
}
