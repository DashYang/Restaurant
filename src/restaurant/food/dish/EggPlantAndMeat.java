package restaurant.food.dish;

import java.util.LinkedList;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Oil;
import restaurant.food.ingredient.Salt;
import restaurant.food.ingredient.Sauce;
import restaurant.food.material.Chicken;
import restaurant.food.material.Eggplant;
import restaurant.food.material.Material;
import restaurant.food.material.Meat;

public class EggPlantAndMeat extends Dish {

	public EggPlantAndMeat() {

		description = "EggPlantAndMeat";

		setIngredients(new LinkedList<Ingredient>());
		setMaterials(new LinkedList<Material>());

		getIngredients().add(new Sauce());
		getIngredients().add(new Salt());
		getIngredients().add(new Oil());

		getMaterials().add(new Meat(3));
		getMaterials().add(new Eggplant(4));
		getMaterials().add(new Chicken(1));
	}
	

	@Override
	public double getSellPrice() {
		return 16;
	}

}
