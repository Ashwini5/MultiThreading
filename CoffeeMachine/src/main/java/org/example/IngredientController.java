package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IngredientController {
    private Integer lowVolumeThreshold;
    private HashMap<Integer, Integer> maxIngredientVolumes;
    private HashMap<Integer, Integer> currentIngredientVolumes;

    public IngredientController(Map<Integer, Integer> maxIngredientVolumes, Integer lowVolumeThreshold){
        this.maxIngredientVolumes = new HashMap<>();
        this.maxIngredientVolumes.putAll(maxIngredientVolumes);
        this.currentIngredientVolumes = new HashMap<>();
        this.currentIngredientVolumes.putAll(maxIngredientVolumes);
        this.lowVolumeThreshold = lowVolumeThreshold;
    }

    public Boolean isIngredientLevelLow(Integer ingredientId){return false;}

    private IngredientStatus checkIngredientAvailability(Integer ingredientId, Integer requiredVolume){

        IngredientStatus ingredientStatus = IngredientStatus.OK;
        if(!currentIngredientVolumes.containsKey(ingredientId)){
            throw new IllegalArgumentException("Invalid ingredient id");
        }
        Integer currentIngredientVolume = currentIngredientVolumes.get(ingredientId);
        Integer volAfterConsumption = currentIngredientVolume - requiredVolume;
        if (volAfterConsumption <= lowVolumeThreshold){
            ingredientStatus = IngredientStatus.LOW;
        }
        if (volAfterConsumption < 0) {
            ingredientStatus = IngredientStatus.INSUFFICIENT;
        }
        return ingredientStatus;
    }

    public CoffeeMachineResponse consumeIngredients(List<BeverageIngredient> ingredients) {

        List<ImmutablePair<BeverageIngredient, IngredientStatus>> ingredientStatusList =
            IntStream.range(0, ingredients.size())
                .mapToObj(i -> new ImmutablePair<>(ingredients.get(i), checkIngredientAvailability(ingredients.get(i).getIngredientId(), ingredients.get(i).getQuantityInMl())))
                .collect(Collectors.toList());

        Boolean ingredientsAvailable = ingredientStatusList.stream().allMatch(ingredient -> ingredient.getValue() == IngredientStatus.OK);

        List<BeverageIngredient> lowIngredients =
            ingredientStatusList
                .stream()
                .filter(ingredient -> ingredient.getValue() == IngredientStatus.LOW)
                .map(ingredient -> ingredient.getKey())
                .collect(Collectors.toList());

        List<BeverageIngredient> insufficientIngredients =
                ingredientStatusList
                        .stream()
                        .filter(ingredient -> ingredient.getValue() == IngredientStatus.INSUFFICIENT)
                        .map(ingredient -> ingredient.getKey())
                        .collect(Collectors.toList());

        if(!ingredientsAvailable) {
            return new CoffeeMachineResponse(false, lowIngredients, insufficientIngredients);
        }
        ingredients.stream().forEach(ingredient -> {decreaseIngredient(ingredient.getIngredientId(), ingredient.getQuantityInMl());});
        return new CoffeeMachineResponse(true, lowIngredients, insufficientIngredients);
    }

    private void decreaseIngredient(Integer ingredientId, Integer ingredientVolume) throws IllegalArgumentException{
        if(!currentIngredientVolumes.containsKey(ingredientId)){
            throw new IllegalArgumentException("Invalid ingredient id");
        }
        currentIngredientVolumes.put(ingredientId, currentIngredientVolumes.get(ingredientId) - ingredientVolume);
    }

    public IngredientStatus refillIngredient(Integer ingredientId) throws IllegalArgumentException{
        if(!currentIngredientVolumes.containsKey(ingredientId) || !maxIngredientVolumes.containsKey(ingredientId)){
            throw new IllegalArgumentException("Invalid ingredient id");
        }
        Integer maxVolume = maxIngredientVolumes.get(ingredientId);
        currentIngredientVolumes.put(ingredientId, maxVolume);

        return IngredientStatus.OK;
    }

    @Override
    public String toString(){
        return currentIngredientVolumes.toString();
    }
}
