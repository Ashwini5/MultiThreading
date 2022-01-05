package org.example;

public class BeverageIngredient {
    private Integer id;
    private Integer ingredientId;
    private Integer quantityInMl;
    private static Integer idCounter = 0;

    public BeverageIngredient(Integer ingredientId, Integer quantityInMl){
        this.id = idCounter++;
        this.ingredientId = ingredientId;
        this.quantityInMl = quantityInMl;
    }

    public Integer getId(){
        return this.id;
    }

    public Integer getIngredientId(){
        return this.ingredientId;
    }

    public Integer getQuantityInMl(){
        return this.quantityInMl;
    }

    @Override
    public String toString(){
        return String.valueOf(this.ingredientId) + ":" + String.valueOf(quantityInMl);
    }
}
