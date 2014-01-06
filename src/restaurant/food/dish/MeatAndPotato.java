package restaurant.food.dish;

import java.util.LinkedList;

import restaurant.food.ingredient.*;
import restaurant.food.material.*;

public class MeatAndPotato extends Dish {

	public MeatAndPotato() {

		description = "meatAndPotato";

		setIngredients(new LinkedList<Ingredient>());
		setMaterials(new LinkedList<Material>());

		getIngredients().add(new Pepper());
		getIngredients().add(new Salt());
		getIngredients().add(new Oil());
		getIngredients().add(new GourmetPowder());

		getMaterials().add(new Meat(3));
		getMaterials().add(new Potato(4));
	}

	@Override
	public double getSellPrice() {
		return 15.0;
	}

}
