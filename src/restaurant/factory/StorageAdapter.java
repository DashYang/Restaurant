package restaurant.factory;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.material.Material;

public class StorageAdapter {
	private int		amount;
	private String	name;

	public StorageAdapter(Ingredient tmp) {
		setName(tmp.getName());
		setAmount(10);
	}

	public StorageAdapter(Material tmp) {
		setName(tmp.getName());
		setAmount(100);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
