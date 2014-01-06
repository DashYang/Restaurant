package restaurant.food.addition;

import restaurant.food.dish.Dish;

public class Drumstick extends AdditionDecorator {
	
	Dish dish;
	
	public Drumstick(Dish dish) {
		this.dish = dish;
	}

	@Override
	public String getDescription() {
		return dish.getDescription() + ",add drumstick";
	}

	@Override
	public double getPrice() {
		return dish.getPrice() + 4.0;
	}

	@Override
	public double getSellPrice() {
		return dish.getSellPrice() + 5.0;
	}

}
