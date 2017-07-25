package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by brian on 6/19/2017.
 */
@Entity
public class Favorite {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany
    private List<Checklist> checklists;

    public Favorite(String name) {
        this.name = name;
    }

    public Favorite() { }

    public void addItem(Checklist item) {
        checklists.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

}
