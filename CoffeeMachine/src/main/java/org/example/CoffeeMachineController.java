package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CoffeeMachineController {

    private Integer preparationTime;
    private HashMap<Integer, Beverage> beverageMap;

    public CoffeeMachineController(Integer preparationTime, List<Beverage> beverages){
        this.preparationTime = preparationTime;

        beverageMap = new HashMap<>();
        for (Beverage beverage: beverages) {
            beverageMap.put(beverage.getId(), beverage);
        }
    }

    public CoffeeMachineResponse orderBeverage(Integer beverageId) throws IllegalArgumentException{
        if (!beverageMap.containsKey(beverageId)){
            throw new IllegalArgumentException("Invalid beverage id");
        }
        return beverageMap.get(beverageId).prepareBeverage();
    }

}
