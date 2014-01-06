package restaurant.food.ingredient;

public class GourmetPowder extends Ingredient {
	public GourmetPowder()
	{
		super(0.3, "Gaourme");
	}

	@Override
	public String getChineseName() {
		return "味精";
	}
}
