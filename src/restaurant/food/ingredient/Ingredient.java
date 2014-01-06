package restaurant.food.ingredient;

import restaurant.factory.GetName;

public abstract class Ingredient implements GetName {
	private double	price;
	private String	name;

	public Ingredient(double price, String name) {
		this.price = price;
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
