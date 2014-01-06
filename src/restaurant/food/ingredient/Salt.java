package restaurant.food.ingredient;

public class Salt extends Ingredient{
	public Salt(){
		super(0.1, "Salt");
	}

	@Override
	public String getChineseName() {
		return "盐";
	}
}
