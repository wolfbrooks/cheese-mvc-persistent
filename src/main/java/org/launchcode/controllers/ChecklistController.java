package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Checklist;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.ChecklistDao;
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
 * Created by LaunchCode
 */
@Controller
@RequestMapping("checklist")
public class ChecklistController {

    @Autowired
    private ChecklistDao checklistDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("checklists", checklistDao.findAll());
        model.addAttribute("title", "My Lists");

        return "checklist/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddListForm(Model model) {
        model.addAttribute("title", "Add Checklist");
        model.addAttribute(new Checklist());
        model.addAttribute("categories", categoryDao.findAll());
        return "checklist/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddListForm(@ModelAttribute  @Valid Checklist newChecklist,
                                       Errors errors, @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Checklist");
            model.addAttribute("categories", categoryDao.findAll());
            return "checklist/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newChecklist.setCategory(cat);

        checklistDao.save(newChecklist);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveListForm(Model model) {
        model.addAttribute("checklists", checklistDao.findAll());
        model.addAttribute("title", "Remove Checklist");
        return "checklist/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveListForm(@RequestParam int[] checklistIds) {

        for (int checklistId : checklistIds) {
            checklistDao.delete(checklistId);
        }

        return "redirect:";
    }

}
