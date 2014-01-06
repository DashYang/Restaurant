package restaurant.food.dish;

import java.util.LinkedList;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Oil;
import restaurant.food.ingredient.Pepper;
import restaurant.food.ingredient.Salt;
import restaurant.food.material.Chicken;
import restaurant.food.material.Material;
import restaurant.food.material.Potato;

public class ChickenAndPotato extends Dish {

	public ChickenAndPotato() {

		description = "ChickenAndPotato";

		setIngredients(new LinkedList<Ingredient>());
		setMaterials(new LinkedList<Material>());

		getIngredients().add(new Pepper());
		getIngredients().add(new Salt());
		getIngredients().add(new Oil());

		getMaterials().add(new Chicken(3));
		getMaterials().add(new Potato(4));
	}

	@Override
	public double getSellPrice() {
		return 15.0;
	}

}
