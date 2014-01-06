package restaurant.UI;


import restaurant.factory.Orders;
import restaurant.factory.Storage;
import restaurant.menu.Menu;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Storage storage = Storage.getInstance();
		Orders orders = Orders.getInstance();
		Menu menu = Menu.getInstance();


		try {
			Uiswt ui = Uiswt.getInstance();
			ui.swt();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
