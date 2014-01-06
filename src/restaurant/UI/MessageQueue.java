package restaurant.UI;

import java.util.LinkedList;


public class MessageQueue {

	static MessageQueue	uniqueInstance;

	LinkedList<String>	message;

	private MessageQueue() {
		message = new LinkedList<String>();
	}


	public static MessageQueue getInstance() {
		if (uniqueInstance == null) {
			synchronized (MessageQueue.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new MessageQueue();
				}
			}
		}
		return uniqueInstance;
	}

	public String getMessage(int i) {
		if (i < message.size())
			return message.get(i);
		return "";
	}

	public void addMessage(String tmp) {
		message.add(tmp);
		if (message.size() > 4) {
			message.remove(0);
		}
		Uiswt.getInstance().updateRollingLabel();
	}
}
