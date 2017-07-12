package de.beinlich.markus.pizzaservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = Menu.findAll, query = "SELECT m FROM Menu m")
public class Menu implements Serializable{
    private static final long serialVersionUID = 9220765761231182677L;
    public static final String findAll = "Menu.findAll";
    
    @Id
    @GeneratedValue
    private Integer menuId;
    @OneToMany(mappedBy = "menu", cascade = CascadeType.PERSIST)
    private List<MenuItem> menuItems;

    public Menu() {
        if (menuItems == null) {
            menuItems = new ArrayList<>();
//            DaoMenu daoMenu = new DaoMenu();
//            menuItems = daoMenu.getMenu();
        }
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    

    public static void main(String[] args) {
        Menu m = new Menu();
        for (MenuItem mItem : m.getMenuItems()) {
            System.out.println(" " + mItem.getName());
        }

    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuId=" + menuId + ", menuItems=" + menuItems + '}';
    }
    
    
}
