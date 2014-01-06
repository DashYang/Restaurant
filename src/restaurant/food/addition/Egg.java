package restaurant.food.addition;

import restaurant.food.dish.Dish;

public class Egg extends AdditionDecorator {

	Dish	dish;

	public Egg(Dish dish) {
		this.dish = dish;
	}

	@Override
	public double getPrice() {
		return dish.getPrice() + 0.8;
	}

	@Override
	public String getDescription() {
		return dish.getDescription() + ",add egg";
	}

	@Override
	public double getSellPrice() {
		return dish.getPrice() + 1.0;
	}
}
