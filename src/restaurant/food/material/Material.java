package restaurant.food.material;

import restaurant.factory.GetName;

public abstract class Material implements GetName {

	private double	unitPrice;
	private int		amount;
	private String	name;

	public Material(double unitPrice, String name) {
		this.unitPrice = unitPrice;
		this.name = name;
	}

	public Material(double unitPrice, int amount, String name) {
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.name = name;
	}

	public double getPrice() {
		return unitPrice * amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
