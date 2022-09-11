package net.shrill.farmhand.item;

import net.minecraft.world.food.FoodProperties;

public class Foods { // Foods class for registering food items, seperate to keep it easy to edit/add
    public static final FoodProperties Corn = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.6f)
            .alwaysEat()
            .build();


}