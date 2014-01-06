package restaurant.food.ingredient;

public class Oil extends Ingredient {
	public Oil() {
		super(0.3, "Oil");
	}

	@Override
	public String getChineseName() {
		return "油";
	}
}
