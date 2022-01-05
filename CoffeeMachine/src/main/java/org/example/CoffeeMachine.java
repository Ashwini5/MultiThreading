package org.example;

/**
 * Hello world!
 *
 */
public class CoffeeMachine
{
    public static void main( String[] args ) {
        CoffeeMachineController coffeeMachineController = ControllerFactory.getInstance().getCoffeeMachineController();
        IngredientController ingredientController = ControllerFactory.getInstance().getIngredientController();

        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(0));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(2));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(1));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(1));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(0));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(2));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(2));
        System.out.println(ingredientController);
        System.out.println(coffeeMachineController.orderBeverage(1));
        System.out.println(ingredientController);
//        System.out.println(coffeeMachineController.orderBeverage(3).toString());
    }
}
