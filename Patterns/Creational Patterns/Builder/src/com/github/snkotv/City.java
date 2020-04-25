package com.github.snkotv;

import com.github.snkotv.restaurant.Restaurant;

import java.util.LinkedList;

public class City {

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Pear");
        restaurant.meetNewClient(new Client("Alex"));
        LinkedList<Client> students = new LinkedList<>() {
            {
                add(new Client("Jone"));
                add(new Client("Jim"));
                add(new Client("Shone"));
            }
        };
        restaurant.meetNewClient(students);
    }
}
