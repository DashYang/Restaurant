package restaurant.factory;

import java.util.ArrayList;
import java.util.LinkedList;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.*;
import restaurant.food.material.*;
import restaurant.menu.Observer;
import restaurant.menu.Subject;

// 食材的贮备，唯一的对象
public class Storage implements Subject {

	private double						revenue;
	private ArrayList<Observer>			observers;
	private ArrayList<StorageAdapter>	storageList;
	private volatile static Storage		uniqueInstance;

	private Storage() {
		setRevenue(1000);
		observers = new ArrayList<Observer>();
		storageList = new ArrayList<StorageAdapter>();
		storageList.add(new StorageAdapter(new Chicken()));
		storageList.add(new StorageAdapter(new Eggplant()));
		storageList.add(new StorageAdapter(new Tomato()));
		storageList.add(new StorageAdapter(new Meat()));
		storageList.add(new StorageAdapter(new Potato()));
		storageList.add(new StorageAdapter(new GourmetPowder()));
		storageList.add(new StorageAdapter(new Oil()));
		storageList.add(new StorageAdapter(new Sauce()));
		storageList.add(new StorageAdapter(new Vinegar()));
		storageList.add(new StorageAdapter(new Pepper()));
		storageList.add(new StorageAdapter(new Salt()));
		
	}

	public static Storage getInstance() {

		if (uniqueInstance == null) {
			synchronized (Storage.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Storage();
				}
			}
		}
		return uniqueInstance;
	}

	public int getAmount(String name) {
		
		for (StorageAdapter storage : storageList) {
			if (storage.getName().equals(name)) {
				return storage.getAmount();
			}
		}
		return 0;
	}

	public int getAmount(Ingredient tmp) {

		for (StorageAdapter storage : storageList) {
			if (storage.getName().equals(tmp.getName())) {
				return storage.getAmount();
			}
		}
		return 0;
	}

	public int getAmount(Material tmp) {

		for (StorageAdapter storage : storageList) {
			if (storage.getName().equals(tmp.getName())) {
				return storage.getAmount();
			}
		}
		return 0;
	}

	public void addAmount(Ingredient tmp, int num) {
		for (StorageAdapter ingredient : storageList) {
			if (ingredient.getName().equals(tmp.getName())) {
				ingredient.setAmount(num + ingredient.getAmount());
			}
		}
		nodifyObserver();
	}

	public void addAmount(Material tmp, int num) {
		for (StorageAdapter material : storageList) {
			if (material.getName().equals(tmp.getName())) {
				material.setAmount(num + material.getAmount());
			}
		}
		nodifyObserver();
	}



	public boolean avaiable(Dish dish) {

		LinkedList<Material> materials = dish.getMaterials();
		for (Material tmp : materials) {
			for (StorageAdapter storage : storageList) {
				if (tmp.getName().equals(storage.getName()) && tmp.getAmount() > storage.getAmount()) {
					return false;
				}
			}
		}

		LinkedList<Ingredient> ingredients = dish.getIngredients();
		for (Ingredient tmp : ingredients) {
			for (StorageAdapter storage : storageList) {
				if (tmp.getName().equals(storage.getName()) && storage.getAmount() <= 0) {
					return false;
				}
			}
		}

		return true;
	}

	public void prepare(Dish dish) {

		LinkedList<Material> materials = dish.getMaterials();
		for (Material tmp : materials) {
			for (StorageAdapter storage : storageList) {
				if (tmp.getName().equals(storage.getName())) {
					storage.setAmount(storage.getAmount() - tmp.getAmount());
				}
			}
		}

		LinkedList<Ingredient> ingredients = dish.getIngredients();
		for (Ingredient tmp : ingredients) {
			for (StorageAdapter storage : storageList) {
				if (tmp.getName().equals(storage.getName()) && storage.getAmount() > 0) {
					storage.setAmount(storage.getAmount() - 1);
				}
			}
		}
		
		nodifyObserver();
	}


	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		if (observers.contains(o))
			observers.remove(o);
	}

	@Override
	public void nodifyObserver() {
		for (Observer tmp : observers) {
			tmp.update();
		}
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

}
