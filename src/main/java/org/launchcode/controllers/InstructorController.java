package org.launchcode.controllers;

import org.launchcode.models.Instructor;
import org.launchcode.models.data.CourseDao;
import org.launchcode.models.data.InstructorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by Erin DeVries on 4/9/2017.
 */
@Controller
@RequestMapping("instructor")
public class InstructorController {

    @Autowired
    private InstructorDao instructorDao;

    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("title", "Meet Our Instructors!");
        model.addAttribute("instructors", instructorDao.findAll());

        return "instructor/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("instructor", new Instructor());
        model.addAttribute("title", "Add Instructor");

        return "instructor/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Instructor instructor,
                      Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("instructor", new Instructor ());
            model.addAttribute("title", "Add Instructor");
            return "instructor/add";
        }else {
            instructorDao.save(instructor);
            return "redirect:";
        }

    }
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model){

        Instructor inst = new Instructor();

        model.addAttribute("title", "Remove Instructor");
        model.addAttribute("instructors", instructorDao.findAll());

        return "instructor/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int[] instructorIds){

        for (int instructorId : instructorIds){
            instructorDao.delete(instructorId);
        }

        return "redirect:";
    }









}
