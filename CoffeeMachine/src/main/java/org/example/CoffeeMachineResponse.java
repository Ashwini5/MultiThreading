package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoffeeMachineResponse {

    Boolean status;
    ArrayList<BeverageIngredient> lowIngredients;
    ArrayList<BeverageIngredient> insufficientIngredients;

    public CoffeeMachineResponse(){}

    public CoffeeMachineResponse(Boolean status, List<BeverageIngredient> lowIngredients, List<BeverageIngredient> insufficientIngredients){
        this.status = status;
        this.lowIngredients = new ArrayList<>();
        this.lowIngredients.addAll(lowIngredients);
        this.insufficientIngredients = new ArrayList<>();
        this.insufficientIngredients.addAll(insufficientIngredients);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<BeverageIngredient> getLowIngredients() {
        return lowIngredients;
    }

    public void setLowIngredients(ArrayList<BeverageIngredient> lowIngredients) {
        this.lowIngredients = lowIngredients;
    }

    public ArrayList<BeverageIngredient> getInsufficientIngredients() {
        return insufficientIngredients;
    }

    public void setInsufficientIngredients(ArrayList<BeverageIngredient> insufficientIngredients) {
        this.insufficientIngredients = insufficientIngredients;
    }

    @Override
    public String toString(){
        String response = "------------------------\n" +
        "Status - " + this.status +
        "| Low Ingredients - " + lowIngredients +
        "| Insufficient Ingredients - " + insufficientIngredients;

        return response;
    }
}
