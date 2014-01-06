package restaurant.state;

import restaurant.kitchen.command.Command;

public interface State 
{
	public void buy(Command cmd);   //购买食材
	public void sell(Command cmd);    //出售食品
	public void open();                       //开张
	public void close();                      //关门
	public String toString();                 //状态情况
}
