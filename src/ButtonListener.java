import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {


	public int x,y;
	private int move;
	private TicTacToeBoard tboard;
	private JButton button;

	static private String[] player = new String[] {"X","O"};
	static private int playerN = 0;



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
			button.setText(player[playerN]);
			move++;

			if (!tboard.check4win(player[playerN],move,x,y))
			{
				tboard.setActiveGroup(this);

				if(playerN == 0)
					playerN = 1;
				else
					playerN = 0;
			}


		}
		else
		{
			System.out.println("Not a valid move!");
		}

	}
}


