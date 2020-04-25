package com.github.snkotv.restaurant;

import com.github.snkotv.Client;
import com.github.snkotv.restaurant.dishes.Dish;
import com.github.snkotv.restaurant.dishes.Plov;
import com.github.snkotv.restaurant.recipes.*;

import java.util.*;
import java.util.concurrent.Callable;

public class Restaurant {
    private String name;
    private ArrayList<Cook> cooks;
    private Queue<Client> clients;
    private static final LinkedList<String> menu = new LinkedList<>(){
        {
            add("Barbecue");
            add("Borscht");
            add("Greek salad");
            add("Plov");
        }
    };
    private static Map<String, Callable<Recipe>> recipes = Collections.unmodifiableMap(new TreeMap<>(){
        {
            put("Barbecue", () -> new BarbecueRecipe());
            put("Borscht", () -> new BorschtRecipe());
            put("Greek salad", () -> new GreekSaladRecipe());
            put("Plov", () -> new PlovRecipe());
        }
    });

    public Restaurant(String name) {
        this.name = name;
        cooks = new ArrayList<>();
        clients = new LinkedList<>();
    }

    public void meetNewClient(Client client) {
        clients.add(client);
        serveClients();
    }

    public void meetNewClient(LinkedList<Client> clients) {
        for (Client client: clients) {
            this.clients.add(client);
        }
        serveClients();
    }

    private void serveClients() {
        if (clients.isEmpty()) {
            return;
        }
        try {
            System.out.println("Good appetite for " + clients.peek().getName() + '\n');
            Recipe orderedDish = recipes.get(takeOrder(clients.peek())).call();
            if (cooks.isEmpty()) {
                cooks.add(new Cook(orderedDish));
            } else {
                cooks.get(0).changeRecipe(orderedDish);
            }
            Dish cookedDish = cooks.get(0).cookDish();
            clients.peek().getOrderedDish(cookedDish);

        } catch (Exception e) {
            System.out.println("Get some problems during serving client: " + clients.peek().getName());
            e.printStackTrace();
        }
        clients.remove();
        serveClients();
    }

    private String takeOrder(Client client) {
        LinkedList<String> clientMenu = new LinkedList<>();
        for (String dish: menu) {
            clientMenu.add(dish);
        }
        return client.makeChoise(clientMenu);
    }

}
