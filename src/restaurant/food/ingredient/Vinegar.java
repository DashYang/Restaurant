package restaurant.food.ingredient;

public class Vinegar extends Ingredient {
	public Vinegar() {
		super(0.1, "Vinegar");
	}

	@Override
	public String getChineseName() {
		return "醋";
	}
}
