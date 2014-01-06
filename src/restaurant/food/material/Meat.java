package restaurant.food.material;

public class Meat extends Material {

	public Meat() {
		super(3.0 ,"meat");
	}

	public Meat(int amount) {
		super(3.0, amount, "meat");
	}

	@Override
	public String getChineseName() {
		return "猪肉";
	}

}
