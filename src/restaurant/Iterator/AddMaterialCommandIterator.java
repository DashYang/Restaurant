package restaurant.Iterator;

import java.util.*;
import restaurant.kitchen.command.AddMaterialCommand;

public class AddMaterialCommandIterator implements Iterator 
{
		LinkedList<AddMaterialCommand> addMaterialCommands;
		int position = 0;
	 
		public AddMaterialCommandIterator(LinkedList<AddMaterialCommand> addMaterialCommands) 
		{
			this.addMaterialCommands = addMaterialCommands;
		}
		
		@Override
		public Object next() 
		{
			AddMaterialCommand tmp = addMaterialCommands.get(position) ;
			position = position + 1;
			return tmp;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (position >= addMaterialCommands.size() || addMaterialCommands == null) {
				return false;
			} else {
				return true;
			}
		}
}
