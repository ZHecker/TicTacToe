import javax.swing.*;
import java.awt.*;

public class TicTacToeBoard {


	static JFrame jFrame = new JFrame();
	static JButton felder[][];


	public int G1[] = new int[] {0,0,2,2};
	public int G2[] = new int[] {3,0,5,2};
	public int G3[] = new int[] {6,0,8,2};
	public int G4[] = new int[] {0,3,2,5};
	public int G5[] = new int[] {3,3,5,5};
	public int G6[] = new int[] {6,3,8,5};
	public int G7[] = new int[] {0,6,2,8};
	public int G8[] = new int[] {3,6,5,8};
	public int G9[] = new int[] {6,6,8,8};


	public TicTacToeBoard(){

		jFrame.setLayout(new GridLayout(9,9));
		felder = new JButton[9][9];

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				felder[x][y] = new JButton("(" + x + "," + y + ")");
				felder[x][y].addActionListener(new ButtonListener(this,x,y,felder[x][y]));
				jFrame.add(felder[x][y]);
			}
		}

		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);


	}


	public void setActiveGroup(ButtonListener grid)
	{

		if(grid.y % 3 == 0 && grid.x % 3 == 0)
		{
			activateGroup(G1);
		}

		else if(grid.y % 3 == 0 && (grid.x-1) % 3 == 0)
		{
			activateGroup(G2);
		}

		else if(grid.y % 3 == 0 && (grid.x-2) % 3 == 0)
		{
			activateGroup(G3);
		}
		else if((grid.y-1) % 3 == 0 && (grid.x) % 3 == 0)
		{
			activateGroup(G4);
		}
		else if((grid.y-1) % 3 == 0 && (grid.x-1) % 3 == 0)
		{
			activateGroup(G5);
		}
		else if((grid.y-1) % 3 == 0 && (grid.x-2) % 3 == 0)
		{
			activateGroup(G6);
		}
		else if((grid.y-2) % 3 == 0 && (grid.x) % 3 == 0)
		{
			activateGroup(G7);
		}
		else if((grid.y-2) % 3 == 0 && (grid.x-1) % 3 == 0)
		{
			activateGroup(G8);
		}
		else if((grid.y-2) % 3 == 0 && (grid.x-2) % 3 == 0)
		{
			activateGroup(G9);
		}

	}

	public void activateGroup(int[] gruppe)
	{

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(x>= gruppe[0] && x<= gruppe[2] && y>= gruppe[1] && y<= gruppe[3])
				{
					felder[x][y].setEnabled(true);
				}
				else
				{
					felder[x][y].setEnabled(false);
				}

			}
		}
		
	}


	public static void main(String[] args)
	{
		new TicTacToeBoard();
	}

}
