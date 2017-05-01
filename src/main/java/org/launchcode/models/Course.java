package org.launchcode.models;


import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Erin DeVries on 4/9/2017.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, message = "Name must not be empty")
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Difficulty difficulty;

    @ManyToOne
    private Instructor instructor;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public Course() {
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<User> getUsers() {
        return users;
    }

    public void clearUsers(List<User> users, Course course) {
        for (User user : users) {
            user.removeItem(course);
        }
    }
}


