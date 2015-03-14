import javax.swing.*;
import java.awt.*;

public class TicTacToeBoard {


	private JFrame jFrame = new JFrame();
	public JButton felder[][];

	public static final int G1[] = new int[] {0,0,2,2};
	public static final int G2[] = new int[] {3,0,5,2};
	public static final int G3[] = new int[] {6,0,8,2};
	public static final int G4[] = new int[] {0,3,2,5};
	public static final int G5[] = new int[] {3,3,5,5};
	public static final int G6[] = new int[] {6,3,8,5};
	public static final int G7[] = new int[] {0,6,2,8};
	public static final int G8[] = new int[] {3,6,5,8};
	public static final int G9[] = new int[] {6,6,8,8};

	private Player player;
	private int moves = 0;
	private AI minimax;


	public int[] activeGroup;


	public TicTacToeBoard(){

		jFrame.setLayout(new GridLayout(9,9));
		felder = new JButton[9][9];
		player = new Player();

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				felder[x][y] = new JButton("");
				felder[x][y].addActionListener(new ButtonListener(this,x,y));
				jFrame.add(felder[x][y]);
			}
		}

		jFrame.setTitle("X is Playing");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	private void finishGame(String winner)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				felder[x][y].setEnabled(false);
				jFrame.setTitle(winner + " has Won!");

			}
		}
	}

	public boolean check4win(String player,int x,int y)
	{

		if(player.equals("X"))
		{
			jFrame.setTitle("O is Playing");
		}
		else
		{
			jFrame.setTitle("X is Playing");
		}

		int cons = 0;

		for (int i = 0; i < 9; i++) {

			if(felder[x][i].getText().equals(player))
			{
				cons++;
			}
			else
			{
				cons = 0;
			}
			if(cons == 3)
			{
				finishGame(player);
				return true;
			}
		}


		cons = 0;

		for (int i = 0; i < 9; i++) {

			if(felder[i][y].getText().equals(player))
			{
				cons++;
			}
			else
			{
				cons = 0;
			}
			if(cons == 3)
			{
				finishGame(player);
				return true;
			}
		}

		cons = 0;
		int diagonalenX = x;
		int diagonalenY = y;

		while(diagonalenX !=0 && diagonalenY != 0)
		{
			diagonalenX--;
			diagonalenY--;
		}

		while (diagonalenX < 8 && diagonalenY < 8)
		{

			if(felder[diagonalenX][diagonalenY].getText().equals(player))
			{
				cons++;
			}
			else
			{
				cons = 0;
			}
			if(cons == 3)
			{
				finishGame(player);
				return true;
			}

			diagonalenX++;
			diagonalenY++;
		}

		cons = 0;

		int antiDiagonalenX = x;
		int antiDiagonalenY = y;

		while(antiDiagonalenX !=8 && antiDiagonalenY != 0)
		{
			antiDiagonalenX++;
			antiDiagonalenY--;
		}


		while (antiDiagonalenX >= 0 && antiDiagonalenY < 8)
		{

			if(felder[antiDiagonalenX][antiDiagonalenY].getText().equals(player))
			{
				cons++;
			}
			else
			{
				cons = 0;
			}
			if(cons == 3)
			{
				finishGame(player);
				return true;
			}

			antiDiagonalenX--;
			antiDiagonalenY++;

		}

		if(moves == (9^2 - 1)){
			System.out.println("Draw!");
		}


		return false;
	}

	public void setActiveGroup(int x,int y)
	{

		if(y % 3 == 0 && x % 3 == 0)
		{
			activateGroup(G1);

		}
		else if(y % 3 == 0 && (x-1) % 3 == 0)
		{
			activateGroup(G2);
		}
		else if(y % 3 == 0 && (x-2) % 3 == 0)
		{
			activateGroup(G3);
		}
		else if((y-1) % 3 == 0 && (x) % 3 == 0)
		{
			activateGroup(G4);
		}
		else if((y-1) % 3 == 0 && (x-1) % 3 == 0)
		{
			activateGroup(G5);
		}
		else if((y-1) % 3 == 0 && (x-2) % 3 == 0)
		{
			activateGroup(G6);
		}
		else if((y-2) % 3 == 0 && (x) % 3 == 0)
		{
			activateGroup(G7);
		}
		else if((y-2) % 3 == 0 && (x-1) % 3 == 0)
		{
			activateGroup(G8);
		}
		else if((y-2) % 3 == 0 && (x-2) % 3 == 0)
		{
			activateGroup(G9);
		}
	}

	public void activateGroup(int[] gruppe)
	{

		activeGroup = gruppe;

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

	public void click(int x,int y)
	{

		JButton button = felder[x][y];

		if(!button.getText().equals("X") && !button.getText().equals("O"))
		{
			button.setText(player.getPlayer());
			moves++;

			if (!check4win(player.getPlayer(),x,y))
			{
				setActiveGroup(x, y);
				player.switchPlayer();

				if(player.getPlayer().equals("O"))
				{
					minimax = new AI();
					minimax.generateTree(this);
				}


			}
		}
		else
		{
			System.out.println("Not a valid move!");
		}
	}

	public static void main(String[] args)
	{
		new TicTacToeBoard();
	}

}
