package restaurant.factory;

import java.util.LinkedList;


import restaurant.food.dish.Dish;

//各种食材的储备,只能创建唯一的对象
public class Orders {		

	Storage				storage;
	LinkedList<Dish>	queue;
	
	private volatile static Orders	uniqueInstance;
	
	public static Orders getInstance(){
		if(uniqueInstance == null){
			synchronized (Orders.class) {
				if(uniqueInstance == null){
					uniqueInstance = new Orders();
				}
			}
		}
		return uniqueInstance;
	}

	public Orders() {
		this.storage = Storage.getInstance();
		queue = new LinkedList<Dish>();
	}

	public void addDish(Dish dish) {
		if (storage.avaiable(dish)) {
			queue.add(dish);
			storage.prepare(dish);
		}
		
		if(queue.size()>10){
			getFirstDish();
		}
	}

	public Dish getFirstDish() {
		if (queue.size() > 0) {
			Dish tmp = queue.get(0);
			queue.remove(0);
			return tmp;
		}
		return null;
	}

}
