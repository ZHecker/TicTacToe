import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	private TicTacToeBoard tboard;
	public int x,y;
	private JButton button;

	public ButtonListener(TicTacToeBoard tboard, int x, int y, JButton button) {
		this.tboard = tboard;
		this.x = x;
		this.y = y;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(!button.getText().equals("X") && !button.getText().equals("O"))
		{
			button.setText(Player_Controler.player);
			Player_Controler.buttonPressed();
			tboard.setActiveGroup(this);
		}
		else
		{
			System.out.println("Not a valid move!");
		}

	}
}


