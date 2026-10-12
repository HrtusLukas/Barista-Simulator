package controllers;

import entities.Customer;
import entities.enums.Recipe;
import entities.ui.TicketUI;

import java.util.Random;

public class OrderManager {
    private Customer customer;
    private Recipe recipe;
    private static OrderManager instance;

    public OrderManager(Customer customer) {
        this.customer = customer;
        this.recipe = customer.getCoffee();
        instance = this;
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager(new Customer("Jozo", Recipe.CAPPUCCINO));
        }
        return instance;
    }

    public void newOrder() {
        Recipe[] recipes = Recipe.values();
        Random rnd =new Random();
        int index = rnd.nextInt(recipes.length);
        Recipe newRecipe = recipes[index];

        TicketUI.getInstance().changeTicket(newRecipe);
    }
}
