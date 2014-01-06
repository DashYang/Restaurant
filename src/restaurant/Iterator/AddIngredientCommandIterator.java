package restaurant.Iterator;

import java.util.*;

import restaurant.kitchen.command.AddIngredientCommand;

public class AddIngredientCommandIterator implements Iterator 
{
		LinkedList<AddIngredientCommand> addIngredientCommands;
		int position = 0;
	 
		public AddIngredientCommandIterator(LinkedList<AddIngredientCommand> addIngredientCommands) 
		{
			this.addIngredientCommands = addIngredientCommands;
		}
		
		@Override
		public Object next() 
		{
			AddIngredientCommand tmp = addIngredientCommands.get(position) ;
			position = position + 1;
			return tmp;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (position >= addIngredientCommands.size() || addIngredientCommands == null) {
				return false;
			} else {
				return true;
			}
		}
}
