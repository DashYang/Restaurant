package restaurant.food.material;

public class Potato extends Material {

	public Potato() {
		super(1.0, "potato");
	}

	public Potato(int amount) {
		super(1.0, amount, "potato");
	}

	@Override
	public String getChineseName() {
		return "土豆";
	}
}
