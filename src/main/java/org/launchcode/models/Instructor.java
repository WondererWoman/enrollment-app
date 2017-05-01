package org.launchcode.models;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erin DeVries on 4/9/2017.
 */
@Entity
public class Instructor {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 1, message = "Bio must not be empty")
    private String bio;

    private int phone_number;

    @OneToMany
    @JoinColumn(name = "instructor_id")
    private List<Course> courses = new ArrayList<>();

    public Instructor() {
    }

    public Instructor(String name, String bio, int phone_number) {
        this.name = name;
        this.bio = bio;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
