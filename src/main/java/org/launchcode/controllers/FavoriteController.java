package org.launchcode.controllers;

import org.launchcode.models.Checklist;
import org.launchcode.models.Favorite;
import org.launchcode.models.data.ChecklistDao;
import org.launchcode.models.data.FavoriteDao;
import org.launchcode.models.forms.AddFavoriteItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by brian on 6/19/2017.
 */

@Controller
@RequestMapping(value = "favorite")
public class FavoriteController {

    @Autowired
    private ChecklistDao checklistDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("favorites", favoriteDao.findAll());
        model.addAttribute("title", "Favorites");

        return "favorite/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Favorite");
        model.addAttribute(new Favorite());
        return "favorite/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Favorite favorite,
                                       Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Checklist");
            return "favorite/add";
        }
        favoriteDao.save(favorite);
        return "redirect:view/" + favorite.getId();
    }

    @RequestMapping(value = "view/{favoriteId}", method = RequestMethod.GET)
    public String viewFavorite(Model model, @PathVariable int favoriteId) {

        Favorite favorite = favoriteDao.findOne(favoriteId);
        model.addAttribute("title", favorite.getName());
        model.addAttribute("checklists", favorite.getChecklists());
        model.addAttribute("favoriteId", favorite.getId());

        return "favorite/view";
    }

    @RequestMapping(value = "add-item/{favoriteId}", method = RequestMethod.GET)
    public String addItem(@PathVariable int favoriteId, Model model) {

        Favorite favorite = favoriteDao.findOne(favoriteId);

        AddFavoriteItemForm form = new AddFavoriteItemForm(checklistDao.findAll(), favorite);

        model.addAttribute("title", "Add item to favorite: " + favorite.getName());
        model.addAttribute("form", form);

        return "favorite/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddFavoriteItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "favorite/add-item";
        }

        Checklist theChecklist = checklistDao.findOne(form.getChecklistId());
        Favorite theFavorite = favoriteDao.findOne(form.getFavoriteId());
        theFavorite.addItem(theChecklist);
        favoriteDao.save(theFavorite);

        return "redirect:/favorite/view/" + theFavorite.getId();
    }
}
