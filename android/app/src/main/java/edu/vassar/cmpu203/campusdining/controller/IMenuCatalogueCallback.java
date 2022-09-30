package edu.vassar.cmpu203.campusdining.controller;

import edu.vassar.cmpu203.campusdining.model.FoodItem;
import edu.vassar.cmpu203.campusdining.model.MenuIdentifier;

public interface IMenuCatalogueCallback {
    void signalMenuCreated(MenuIdentifier menuIdentifier);
    FoodItem saveOrRetrieveItem(FoodItem item);
}
