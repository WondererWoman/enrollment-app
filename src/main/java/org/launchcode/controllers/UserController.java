package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.data.CourseDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by Erin DeVries on 4/11/2017.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    CourseDao courseDao;

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(Model model, @RequestParam String username, @ModelAttribute @Valid User user, Errors errors) {

        for (User u : userDao.findAll()){
            if((u.getUsername().equals(username))) {
                model.addAttribute("title", "User Signup");
                model.addAttribute(user);
                model.addAttribute("usernameerror", "Username unavailable");
                return "user/signup";
            }
        }


        if (errors.hasErrors()) {
            model.addAttribute("title", "User Signup");
            model.addAttribute(user);
            model.addAttribute("errors", errors);
            return "user/signup";

        } else {
            userDao.save(user);
            model.addAttribute("title", "Welcome, " + user.getUsername() + "!");
            model.addAttribute("courses", user.getCourses());
            return "user/welcome";
        }
    }

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signin(Model model){

        model.addAttribute("title", "User Sign In");
        return null;
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public String signin(Model model, @RequestParam String password, @RequestParam String username){

        return null;
    }
}
