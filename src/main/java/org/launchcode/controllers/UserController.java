package org.launchcode.controllers;

import org.launchcode.models.Course;
import org.launchcode.models.User;
import org.launchcode.models.data.CourseDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Erin DeVries on 4/11/2017.
 */
@Controller
@SessionAttributes("user")
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String homepage(Model model) {

        model.addAttribute("title", "Welcome to Our Site!");
        return "user/homepage";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(Model model, @RequestParam String username, @ModelAttribute @Valid User user, Errors errors) {

        for (User u : userDao.findAll()) {
            if ((u.getUsername().equals(username))) {
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
            model.addAttribute("title", "User Log In");
            return "user/login";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {

        model.addAttribute("title", "User Log In");
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam String password, @RequestParam String username,
                        HttpServletRequest request) {


        for (User u : userDao.findAll()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                model.addAttribute("title", "Welcome, " + u.getUsername() + "!");
                model.addAttribute("courses", u.getCourses());
                HttpSession session = request.getSession(true);
                session.setAttribute("username", u.getId());
                return "user/welcome";
            }
        }

        model.addAttribute("title", "User Log In");
        model.addAttribute("loginerror", "Invalid username/password");
        return "user/login";

    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:";
    }

    @RequestMapping(value = "enroll", method = RequestMethod.GET)
    public String enroll(Model model, HttpServletRequest request){

        model.addAttribute("title", "Enroll In a Course");
        model.addAttribute("courses", courseDao.findAll());
        String userId= request.getSession().getAttribute("username").toString();
        model.addAttribute("username", userId);

        return "user/enroll";
    }

    @RequestMapping(value = "enroll", method = RequestMethod.POST)
    public String enroll(Model model, @RequestParam int[] courseIds,
                         HttpServletRequest request){

        int userId = (int)request.getSession().getAttribute("username");
        User user = userDao.findOne(userId);

        for (int courseId : courseIds) {
            Course course = courseDao.findOne(courseId);
            user.addItem(course);
            userDao.save(user);
        }
        model.addAttribute("courses", user.getCourses());
        return "user/welcome";
    }
    @RequestMapping(value = "unenroll", method = RequestMethod.GET)
    public String unEnroll(Model model, HttpServletRequest request){

        model.addAttribute("title", "Unenroll In A Course");
        model.addAttribute("courses", courseDao.findAll());
        String userId= request.getSession().getAttribute("username").toString();
        model.addAttribute("username", userId);

        return "user/unenroll";
    }

    @RequestMapping(value = "unenroll", method = RequestMethod.POST)
    public String unEnroll(Model model, @RequestParam int[] courseIds,
                         HttpServletRequest request) {

        int userId = (int) request.getSession().getAttribute("username");
        User user = userDao.findOne(userId);

        for (int courseId : courseIds) {
            Course course = courseDao.findOne(courseId);
            user.removeItem(course);
            userDao.save(user);
        }
        model.addAttribute("courses", user.getCourses());
        return "user/welcome";
    }
    @RequestMapping(value = "myprofile", method = RequestMethod.GET)
    public String myProfile(Model model, HttpServletRequest request){

        int userId = (int) request.getSession().getAttribute("username");
        User user = userDao.findOne(userId);

        model.addAttribute("title", "Welcome, " + user.getUsername() + "!");
        model.addAttribute("courses", user.getCourses());

        return "user/welcome";
    }
}
