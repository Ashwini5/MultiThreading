package org.example;

public class Ingredient {

    private Integer id;
    private String name;
    private static Integer idCounter = 0;

    public Ingredient(String name){
        this.id = idCounter++;
        this.name = name;
    }
}
