package org.example;

import java.util.*;

public class ControllerFactory {
    private IngredientController ingredientController;
    private CoffeeMachineController coffeeMachineController;
    private static ControllerFactory controllerFactory;

    public static ControllerFactory getInstance(){
        if (controllerFactory == null){
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }

    public IngredientController getIngredientController(){
        return this.ingredientController;
    }

    public CoffeeMachineController getCoffeeMachineController(){
        return this.coffeeMachineController;
    }

    private ControllerFactory(){
        Integer lowVolumeThreshold;
        HashMap<Integer, Integer> maxIngredientVolumes;
        Integer preparationTime;
        List<Beverage> beverages;

        lowVolumeThreshold = 20;
        preparationTime = 10;

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water"));
        ingredients.add(new Ingredient("Coffee"));
        ingredients.add(new Ingredient("Milk"));
        ingredients.add(new Ingredient("Chocolate"));
        ingredients.add(new Ingredient("Sugar"));

        maxIngredientVolumes = new HashMap<>();
        maxIngredientVolumes.put(0, 200);
        maxIngredientVolumes.put(1, 150);
        maxIngredientVolumes.put(2, 200);
        maxIngredientVolumes.put(3, 100);
        maxIngredientVolumes.put(4, 100);

        List<BeverageIngredient> hotChocIngredients = new ArrayList<>();
        hotChocIngredients.add(new BeverageIngredient(0, 30));
        hotChocIngredients.add(new BeverageIngredient(2, 20));
        hotChocIngredients.add(new BeverageIngredient(3, 40));
        hotChocIngredients.add(new BeverageIngredient(4, 10));

        List<BeverageIngredient> coffeeIngredients = new ArrayList<>();
        coffeeIngredients.add(new BeverageIngredient(1, 50));
        coffeeIngredients.add(new BeverageIngredient(2, 40));
        coffeeIngredients.add(new BeverageIngredient(4, 10));

        List<BeverageIngredient> latteIngredients = new ArrayList<>();
        latteIngredients.add(new BeverageIngredient(1, 30));
        latteIngredients.add(new BeverageIngredient(2, 50));
        latteIngredients.add(new BeverageIngredient(4, 20));

        Beverage hotChocolate = new Beverage("Hot Chocolate", hotChocIngredients);
        Beverage latte = new Beverage("Latte", latteIngredients);
        Beverage coffee = new Beverage("Coffee", coffeeIngredients);

        beverages = Arrays.asList(hotChocolate, latte, coffee);

        this.ingredientController = new IngredientController(maxIngredientVolumes, lowVolumeThreshold);
        this.coffeeMachineController = new CoffeeMachineController(preparationTime, beverages);
    }
}
