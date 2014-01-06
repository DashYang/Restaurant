package restaurant.food.material;

public class Chicken extends Material {

	public Chicken()
	{
		super(3.0 ,"Chicken");
	}

	public Chicken(int amount) 
	{
		super(3.0, amount, "Chicken");
	}

	@Override
	public String getChineseName() {
		return "鸡肉";
	}
}
