package restaurant.food.dish;

import java.util.LinkedList;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Oil;
import restaurant.food.ingredient.Salt;
import restaurant.food.ingredient.Sauce;
import restaurant.food.ingredient.Vinegar;
import restaurant.food.material.Eggplant;
import restaurant.food.material.Material;
import restaurant.food.material.Meat;

public class MeatAndEggPlant extends Dish {

	public MeatAndEggPlant() {

		description = "MeatAndEggPlant";

		setIngredients(new LinkedList<Ingredient>());
		setMaterials(new LinkedList<Material>());

		getIngredients().add(new Sauce());
		getIngredients().add(new Salt());
		getIngredients().add(new Oil());
		getIngredients().add(new Vinegar());

		getMaterials().add(new Meat(3));
		getMaterials().add(new Eggplant(4));
	}

	@Override
	public double getSellPrice() {
		return 14.0;
	}

}
