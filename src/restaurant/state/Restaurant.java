package restaurant.state;

import restaurant.kitchen.command.Command;

/**********
 * 
 * singleton 模式，状态模式
 *
 **********/

public class Restaurant 
{
	State normalstate;
	State badstate;
	State goodstate;
	State closeState;
	
	static Restaurant	uniqueInstance;

	State state = closeState;
	public Restaurant()
	{
		normalstate = new NormalState(this);
		badstate = new BadState(this);
		goodstate = new GoodState(this);
		closeState = new CloseState(this);
		state = closeState;
	}
	
	public static Restaurant getInstance() 
	{
		if (uniqueInstance == null) {
			synchronized (Restaurant.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Restaurant();
				}
			}
		}
		return uniqueInstance;
	}
	
	public void buy(Command cmd)
	{
		state.buy(cmd);
	}

	public void sell(Command cmd) 
	{
		state.sell(cmd);
	}
	
	public String toString()
	{
		return state.toString();
	}
	
	
	public State getNormalstate() {
		return normalstate;
	}

	public void setNormalstate(State normalstate) {
		this.normalstate = normalstate;
	}

	public State getBadstate() {
		return badstate;
	}

	public void setBadstate(State badstate) {
		this.badstate = badstate;
	}

	public State getGoodstate() {
		return goodstate;
	}

	public void setGoodstate(State goodstate) {
		this.goodstate = goodstate;
	}

	public State getCloseState() {
		return closeState;
	}

	public void setCloseState(State closeState) {
		this.closeState = closeState;
	}

	public static Restaurant getUniqueInstance() {
		return uniqueInstance;
	}

	public static void setUniqueInstance(Restaurant uniqueInstance) {
		Restaurant.uniqueInstance = uniqueInstance;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void open() 
	{		
		state.open();
	}

	public void close() 
	{
		state.close();	
	}
}
