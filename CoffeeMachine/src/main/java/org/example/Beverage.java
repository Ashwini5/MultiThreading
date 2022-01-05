package org.example;

import java.util.ArrayList;
import java.util.List;

public class Beverage {

    private static Integer idCounter = 0;
    private Integer id;
    private String name;
    private ArrayList<BeverageIngredient> ingredients;

    public Beverage(String name, List<BeverageIngredient> ingredients){
        this.id = idCounter++;
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
        this.name = name;

    }

    public Integer getId(){
        return this.id;
    }

    public List<BeverageIngredient> getBeverageIngredients(){
        return this.ingredients;
    }

    public CoffeeMachineResponse prepareBeverage(){
        System.out.println(this.ingredients);
        return ControllerFactory.getInstance().getIngredientController().consumeIngredients(this.ingredients);
    }

    @Override
    public String toString(){
        return ingredients.toString();
    }
}
