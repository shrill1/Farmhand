package net.shrill.farmhand.item;

import net.minecraft.world.food.FoodProperties;

public class FarmhandFoods { // FarmhandFoods class for registering food items, seperate to keep it easy to edit/add
    public static final FoodProperties CORN_COB = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.6f)
            .alwaysEat()
            .build();
}