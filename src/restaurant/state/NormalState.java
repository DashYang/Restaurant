package restaurant.state;

import restaurant.UI.Uiswt;
import restaurant.factory.Storage;
import restaurant.kitchen.command.Command;

public class NormalState implements State
{
	Restaurant restaurant;
	public NormalState(Restaurant restaurant)
	{
		this.restaurant = restaurant;
	}
	
	@Override
	public void buy(Command cmd)
	{
		cmd.execute();
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
	public void sell(Command cmd) 
	{
		cmd.execute();
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
	public String toString()
	{
		return "正常经营";
	}

	@Override
	public void open() 
	{
		Uiswt uiswt = Uiswt.getInstance();
		uiswt.openMessageBox("已经开张！");
	}

	@Override
	public void close() 
	{
		Uiswt uiswt = Uiswt.getInstance();
		restaurant.setState(restaurant.getCloseState());
		uiswt.openMessageBox("关闭");
	}
}
