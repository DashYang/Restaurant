package restaurant.kitchen.command;

import restaurant.factory.Storage;
import restaurant.food.ingredient.Ingredient;

public class AddIngredientCommand implements Command
{
	private Ingredient	ingredient;

	public AddIngredientCommand(Ingredient ingredient)
	{
		this.ingredient = ingredient;
	}

	/**
	 * 购买配料
	 */
	public void execute()
	{
		Storage storage = Storage.getInstance();
		System.out.println("你将会购买" + ingredient.getName() + "你有" + storage.getRevenue() + "元");
		System.out.print("它原本有：" + storage.getAmount(ingredient));
		storage.addAmount(ingredient, 5);
		storage.setRevenue(storage.getRevenue() - 5 * ingredient.getPrice());
		System.out.print("它现在有：" + storage.getAmount(ingredient));
		System.out.println("你购买了" + ingredient.getName() + "你有" + storage.getRevenue() + "元");
	}
}
