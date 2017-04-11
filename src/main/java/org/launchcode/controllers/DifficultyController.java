package org.launchcode.controllers;

import org.launchcode.models.Difficulty;
import org.launchcode.models.data.DifficultyDao;
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
@RequestMapping("difficulty")
public class DifficultyController {

    @Autowired
    private DifficultyDao difficultyDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("title", "Difficulty Levels");
        model.addAttribute("difficulties", difficultyDao.findAll());

        return "course/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("difficulty", new Difficulty());
        model.addAttribute("title", "Add Difficulty Level");

        return "difficulty/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Difficulty difficulty, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("difficulty", new Difficulty());
            model.addAttribute("title", "Add Difficulty Level");
            return "difficulty/add";
        }else {
            difficultyDao.save(difficulty);
            return "redirect:/course/";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model){

        model.addAttribute("title", "Remove Difficulty");
        model.addAttribute("difficulties", difficultyDao.findAll());

        return "difficulty/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int[] difficultyIds){

        for (int difficultyId : difficultyIds) {
            difficultyDao.delete(difficultyId);
        }

        return "redirect:";
    }
}
