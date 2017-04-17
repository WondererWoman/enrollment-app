package org.launchcode.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Erin DeVries on 4/11/2017.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 8, max = 15, message = "Username must be between 8 and 15 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[!@#$?%&*])(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
            message = "Password must have at least one capital letter, one number , and one special character")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verify;

    public User() {
    }

    private void checkPassword(String password, String verify){
        if(password != null && verify != null && !password.equals(verify)){
            this.verify = null;
        }
    }
    public User(String username, String email, String password, String verify) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.verify = verify;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword(password, verify);
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
        checkPassword(password, verify);
    }
}
