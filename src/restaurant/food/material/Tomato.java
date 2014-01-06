package restaurant.food.material;

public class Tomato extends Material {

	public Tomato()
	{
		super(1.0, "Tomato");
	}

	public Tomato(int amount)
	{
		super(1.0, amount, "Tomato");
	}

	@Override
	public String getChineseName() {
		return "番茄";
	}
}
