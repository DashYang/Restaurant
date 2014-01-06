package restaurant.UI;

import java.util.Random;

import org.eclipse.swt.widgets.Shell;

import restaurant.menu.Menu;

public class DoBusiness implements Runnable {

	private Shell	shell;
	private boolean	flag;

	public DoBusiness() {
		shell = Uiswt.getInstance().shell;
		flag = true;
	}

	@Override
	public void run() {
		while (!shell.isDisposed() && Uiswt.getInstance().isBusiness) {
			try {
				Thread.sleep(1000);
				Random random = new Random();
				int t1 = Math.abs(random.nextInt() % 20);
				if (t1 < 5 && t1 > 0) {

					if (Menu.getInstance().getAvailableDishs().size() > 0) {
						Customer customer = new Customer(t1);
						new Thread(customer).start();
						flag = true;
					}
					else if (flag) {
						flag = false;
						Uiswt.getInstance().openMessageBox("现在没有菜了");
					}
					else {
						MessageQueue.getInstance().addMessage("没有菜了，客人不会来了");
						System.out.println("没有菜了，客人不会来了");
					}
				}

			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
