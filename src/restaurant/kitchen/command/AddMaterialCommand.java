package restaurant.kitchen.command;

import restaurant.factory.Storage;
import restaurant.food.material.Material;

public class AddMaterialCommand implements Command
{
	private Material	material;

	public AddMaterialCommand(Material material)
	{
		this.material = material;
	}


	/**
	 * 购买原料
	 */
	public void execute()
	{
		Storage storage = Storage.getInstance();

		System.out.println("你将会购买" + material.getName() + "你有" + storage.getRevenue() + "元");
		System.out.print("它原本有：" + storage.getAmount(material));
		storage.addAmount(material, 20);
		System.out.print("它现在有：" + storage.getAmount(material));
		storage.setRevenue(storage.getRevenue() - 20 * material.getUnitPrice());
		System.out.println("您购买了" + material.getName() + "你有" + storage.getRevenue() + "元");
	}
}
