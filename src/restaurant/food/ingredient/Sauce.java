package restaurant.food.ingredient;

public class Sauce extends Ingredient {
	public Sauce() {
		super(0.1, "Sauce");
	}

	@Override
	public String getChineseName() {
		return "酱油";
	}


}
