import javax.swing.*;
import java.awt.*;

public class ColorJButton extends JButton {


	private Color defaultColor;

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}


	public void revertColor()
	{

		super.setBackground(defaultColor);

	}


	public ColorJButton(String text) {
		super(text, null);
	}


}
