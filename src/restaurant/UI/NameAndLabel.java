package restaurant.UI;

import org.eclipse.swt.widgets.Label;

public class NameAndLabel {

	private Label	label;
	private String	chineseName, name;

	public NameAndLabel(Label label, String chineseName, String name) {
		this.label = label;
		this.chineseName = chineseName;
		this.name = name;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
