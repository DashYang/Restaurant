package restaurant.food.ingredient;

public class Pepper extends Ingredient {
	public Pepper() {
		super(0.3, "pepper");
	}

	@Override
	public String getChineseName() {
		return "辣椒";
	}


}
