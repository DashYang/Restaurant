package restaurant.kitchen.command;

import org.eclipse.swt.widgets.Button;

public class ButtonLinkCommand 
{
	private Button  btn;
	private Command cmd;
	
	public ButtonLinkCommand()
	{
		btn = null;
		cmd = null;
	}
	public ButtonLinkCommand(Button btn, Command cmd) 
	{
		this.btn = btn;
		this.cmd = cmd;
	}
	public Button getBtn() {
		return btn;
	}
	public void setBtn(Button btn) {
		this.btn = btn;
	}
	public Command getCmd() {
		return cmd;
	}
	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}
}
