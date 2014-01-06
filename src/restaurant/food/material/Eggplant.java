package restaurant.food.material;

// 茄子
public class Eggplant extends Material {



	public Eggplant()
	{
		super(3.0, "eggplant");
	}

	public Eggplant(int amount)
	{
		super(3.0, amount, "eggplant");
	}

	@Override
	public String getChineseName() {
		return "茄子";
	}
}
