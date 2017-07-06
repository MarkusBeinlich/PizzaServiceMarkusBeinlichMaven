package de.beinlich.markus.pizzaservice.model;

import de.beinlich.markus.pizzaservice.dao.*;
import java.io.*;
import java.util.List;


public class Menu implements Serializable{

    private static final long serialVersionUID = 9220765761231182677L;

    private List<MenuItem> menuItems;

    public Menu() {
        if (menuItems == null) {
            DaoMenu daoMenu = new DaoMenu();
            menuItems = daoMenu.getMenu();
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
}
