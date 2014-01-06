package restaurant.UI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import restaurant.food.dish.Dish;
import restaurant.kitchen.command.SellDishCommand;
import restaurant.menu.Menu;
import restaurant.state.Restaurant;

public class Customer implements Runnable {

	private Uiswt		uiswt;
	private boolean		flag;
	private int			num;
	LinkedList<Integer>	seatsList;

	public Customer(int num) {
		this.num = num;
		uiswt = Uiswt.getInstance();

		MessageQueue.getInstance().addMessage("来了" + num + "位客人");
		System.out.println("来了" + num + "位客人");
		if (uiswt.seats < num) {
			System.out.println("座位不足，客人很扫兴的走了");
			MessageQueue.getInstance().addMessage("座位不足，客人很扫兴的走了");
			flag = false;
		}
		else {
			uiswt.seats -= num;
			uiswt.customers += num;
			flag = true;
			uiswt.update();
		}
	}

	@Override
	public void run() {
		if (!flag) {
			return;
		}
		try {
			seatsList = new LinkedList<Integer>();
			Boolean[] tmpList = uiswt.seatsList;
			for (int i = 0; i < tmpList.length; i++) {
				if (tmpList[i] && seatsList.size() < num) {
					seatsList.add(i);
					uiswt.takeSeat(i);
				}
			}

			/////////////////////////////////////////////////////////////////////////////////
			for (int i = 0; i < num; i++) {
				if (!sellDish()) {
					System.out.println("有客人无法买到菜了");
				}
			}
			//////////////////////////////////////////////////////////////////////////////////

			uiswt.update();
			Thread.sleep(10000);
			for (int i = 0; i < seatsList.size(); i++) {
				uiswt.releaseSeat(seatsList.get(i));
			}
			System.out.println("有" + num + "位客人离开了");
			MessageQueue.getInstance().addMessage("有" + num + "位客人离开了");
			uiswt.seats += num;		//客人接待完毕，座位复原
			uiswt.customers -= num;
			uiswt.update();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 需要调用命令模式中的
	 */
	public boolean sellDish() {

		ArrayList<Dish> availableDishsArrayList = Menu.getInstance().getAvailableDishs();
		if (availableDishsArrayList.size() == 0) {
			return false;
		}
		System.out.println("现在有" + availableDishsArrayList.size() + "道菜可以供应");

		Random random = new Random();
		int t1 = Math.abs(random.nextInt() % availableDishsArrayList.size());
		Dish dish = availableDishsArrayList.get(t1);
		MessageQueue.getInstance().addMessage("客人点了" + dish.getDescription());
		Restaurant restaurant = Restaurant.getInstance();
		restaurant.sell(new SellDishCommand(dish));
		return true;
	}

}
