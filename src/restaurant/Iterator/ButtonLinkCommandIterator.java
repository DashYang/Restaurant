package restaurant.Iterator;

import java.util.LinkedList;
import restaurant.kitchen.command.*;;

public class ButtonLinkCommandIterator implements Iterator
{
	private LinkedList<ButtonLinkCommand> buttonLinkCommands;
	int position = 0;
	
	public ButtonLinkCommandIterator(LinkedList<ButtonLinkCommand> buttonLinkCommands)
	{
		this.buttonLinkCommands = buttonLinkCommands;
	}
	
	@Override
	public Object next()
	{
		ButtonLinkCommand tmp = buttonLinkCommands.get(position) ;
		position = position + 1;
		return tmp;
	}
	
	@Override
	public boolean hasNext() 
	{
		if (position >= buttonLinkCommands.size() || buttonLinkCommands == null) {
			return false;
		} else {
			return true;
		}
	}
}
