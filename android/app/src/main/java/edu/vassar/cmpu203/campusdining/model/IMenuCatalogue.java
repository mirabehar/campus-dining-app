package edu.vassar.cmpu203.campusdining.model;

import java.util.HashMap;

import edu.vassar.cmpu203.campusdining.controller.IMenuCatalogueCallback;

public interface IMenuCatalogue {
    Menu getMenu(MenuIdentifier identifier);
    void getMenu(MenuIdentifier identifier, IMenuCatalogueCallback callback);
    HashMap<MenuIdentifier, Menu> getBasicMenus();
}
