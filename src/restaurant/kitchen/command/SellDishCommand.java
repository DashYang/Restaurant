package restaurant.kitchen.command;

import restaurant.factory.Storage;
import restaurant.food.dish.Dish;

/**
 * 卖菜的命令模式类
 */
public class SellDishCommand implements Command
{
	Dish	dish;

	public SellDishCommand(Dish dish)
	{
		this.dish = dish;
	}

	/**
	 * 扣多少食材，增加多少钱
	 */
	@Override
	public void execute()
	{
		Storage storage = Storage.getInstance();
		if (storage.avaiable(dish)) {
			storage.prepare(dish);
			storage.setRevenue(storage.getRevenue() + dish.getSellPrice());
		}
		else {
			System.out.println("糟糕，食材不够了");
		}

	}
}
