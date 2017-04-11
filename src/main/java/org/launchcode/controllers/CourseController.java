package org.launchcode.controllers;

import org.launchcode.models.Course;
import org.launchcode.models.Difficulty;
import org.launchcode.models.Instructor;
import org.launchcode.models.data.CourseDao;
import org.launchcode.models.data.DifficultyDao;
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
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private DifficultyDao difficultyDao;

    @Autowired
    private InstructorDao instructorDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "List of Courses");
        model.addAttribute("courses", courseDao.findAll());

        return "course/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCourseForm(Model model){
        model.addAttribute("title", "Add New Course");
        model.addAttribute(new Course());
        model.addAttribute("difficulties", difficultyDao.findAll());
        model.addAttribute("instructors", instructorDao.findAll());

        return "course/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCourseForm(@ModelAttribute @Valid Course newCourse,
                                       Errors errors, @RequestParam int difficultyId,
                                       @RequestParam int instructorId, Model model){

        Difficulty dif = difficultyDao.findOne(difficultyId);
        newCourse.setDifficulty(dif);

        Instructor inst = instructorDao.findOne(instructorId);
        newCourse.setInstructor(inst);

        if (errors.hasErrors()){
            model.addAttribute("title", "Add New Course");
            return "course/add";
        }

        courseDao.save(newCourse);
        return "redirect:";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCourseForm(Model model) {
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("title", "Remove Course");

        return "course/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCourseForm(@RequestParam int[] courseIds) {

        for (int courseId : courseIds) {
            courseDao.delete(courseId);
        }
        return "redirect:";
    }

}
