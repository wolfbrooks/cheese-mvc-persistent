package org.launchcode.models.forms;

import org.launchcode.models.Checklist;
import org.launchcode.models.Favorite;

import javax.validation.constraints.NotNull;

/**
 * Created by brian on 6/19/2017.
 */
public class AddFavoriteItemForm {

    private Favorite favorite;

    private Iterable<Checklist> checklists;

    @NotNull
    private int favoriteId;

    @NotNull
    private int checklistId;

    public AddFavoriteItemForm(Iterable<Checklist> checklists, Favorite favorite) {
        this.favorite = favorite;
        this.checklists = checklists;
    }

    public AddFavoriteItemForm() { }

    public Favorite getFavorite() {
        return favorite;
    }

    public Iterable<Checklist> getChecklists() {
        return checklists;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setMenuId(int menuId) {
        this.favoriteId = favoriteId;
    }

    public int getChecklistId() {
        return getChecklistId();
    }

    public void setChecklistId(int checklistId) {
        this.checklistId = checklistId;
    }
}
