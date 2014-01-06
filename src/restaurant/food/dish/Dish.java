package restaurant.food.dish;

import java.util.LinkedList;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.material.Material;

public abstract class Dish {

	protected String				description	= "unknow dish";

	private LinkedList<Ingredient>	ingredients;
	private LinkedList<Material>	materials;

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		double price = 0;

		for (Ingredient tmp : getIngredients()) {
			price += tmp.getPrice();
		}
		for (Material tmp : getMaterials()) {
			price += tmp.getPrice();
		}

		return price;
	}

	public abstract double getSellPrice();

	public LinkedList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(LinkedList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public LinkedList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(LinkedList<Material> materials) {
		this.materials = materials;
	}
}
