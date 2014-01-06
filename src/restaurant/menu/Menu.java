package restaurant.menu;

import java.util.ArrayList;

import restaurant.factory.Orders;
import restaurant.factory.Storage;
import restaurant.food.dish.*;

public class Menu implements Observer {

	Storage			storage;

	ArrayList<Dish>	dishs;			//所有菜的菜单
	ArrayList<Dish>	availableDishs;	//现在有那些菜可以出售

	static Menu		uniqueInstance;

	public static Menu getInstance() {
		if (uniqueInstance == null) {
			synchronized (Orders.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Menu(Storage.getInstance());
				}
			}
		}
		return uniqueInstance;
	}


	private Menu(Storage storage) {
		this.storage = storage;
		storage.registerObserver(this);

		dishs = new ArrayList<Dish>();
		dishs.add(new MeatAndPotato());
		dishs.add(new ChickenAndPotato());
		dishs.add(new EggPlantAndMeat());
		dishs.add(new PotatoAndEggplant());
		dishs.add(new MeatAndPotato());
		dishs.add(new MeatAndTomato());
		availableDishs = new ArrayList<Dish>();
		update();
	}

	@Override
	public void update() {
		for (Dish tmp : dishs) {
			if (storage.avaiable(tmp)) {
				if (!availableDishs.contains(tmp))
					availableDishs.add(tmp);
			}
			else {
				if (availableDishs.contains(tmp))
					availableDishs.remove(tmp);
			}
		}
	}


	public ArrayList<Dish> getAvailableDishs() {
		return availableDishs;
	}


	public void setAvailableDishs(ArrayList<Dish> availableDishs) {
		this.availableDishs = availableDishs;
	}

}
