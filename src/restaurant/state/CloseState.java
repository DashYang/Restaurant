package restaurant.state;

import restaurant.UI.MessageQueue;
import restaurant.UI.Uiswt;
import restaurant.factory.Storage;
import restaurant.kitchen.command.Command;

public class CloseState implements State
{
	Restaurant restaurant;
	
	public CloseState(Restaurant restaurant)
	{
		this.restaurant = restaurant;
	}
	
	@Override
	public void buy(Command cmd)
	{
		//cmd.execute();
	}

	@Override
	public void sell(Command cmd) 
	{
		//cmd.execute();
	}
	
	@Override
	public String toString()
	{
		return "今日休息!";
	}

	@Override
	public void open() 
	{
		Uiswt uiswt = Uiswt.getInstance();
		uiswt.openMessageBox("开始营业");
		MessageQueue.getInstance().addMessage("开始营业了");
		if(Storage.getInstance().getRevenue() < 10000.0)
		{
			restaurant.setState(restaurant.getBadstate());
		}else if(Storage.getInstance().getRevenue() >= 10000.0 && Storage.getInstance().getRevenue() < 20000.0)
		{
			restaurant.setState(restaurant.getNormalstate());
		}
		else
		{
			restaurant.setState(restaurant.getGoodstate());
		}
	}

	@Override
	public void close() 
	{
		Uiswt uiswt = Uiswt.getInstance();
		uiswt.openMessageBox("已经关闭！");
	}
}