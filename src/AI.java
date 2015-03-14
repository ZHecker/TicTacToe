import java.util.Arrays;

public class AI {

	private int[][] board = new int[9][9];

	public void generateTree(TicTacToeBoard tbord,String currentPlayer)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(tbord.felder[x][y].getText().equals(""))
				{
					board[x][y] = -1;
					continue;
				}

				switch (tbord.felder[x][y].getText().charAt(0))
				{
					case 'X' : board[x][y] = 0; break;
					case 'O' : board[x][y] = 1; break;
					default : board[x][y] = -1; break;
				}
			}
		}


		GameState s1 = new GameState(board,currentPlayer.equals("X") ? 0:1,tbord.activeGroup);
		Level l1 = new Level();


		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(x>= s1.activeGroup[0] && x<= s1.activeGroup[2] && y>= s1.activeGroup[1] && y<= s1.activeGroup[3])
				{

					if(s1.board[x][y] != 1 || s1.board[x][y] != 0)
					{
						// int[][] newBoard = s1.board.clone(); -> NOT A DEEP COPY!
						int[][] newBoard = copy(board);
						newBoard[x][y] = 1;
						l1.addLevel(new GameState(newBoard,0,getActiveGroup(x,y)));
					}
				}
			}
		}
	}



	public int[] getActiveGroup(int x,int y)
	{

		if(y % 3 == 0 && x % 3 == 0)
		{
			return TicTacToeBoard.G1;

		}
		else if(y % 3 == 0 && (x-1) % 3 == 0)
		{
			return TicTacToeBoard.G2;
		}
		else if(y % 3 == 0 && (x-2) % 3 == 0)
		{
			return TicTacToeBoard.G3;
		}
		else if((y-1) % 3 == 0 && (x) % 3 == 0)
		{
			return TicTacToeBoard.G4;
		}
		else if((y-1) % 3 == 0 && (x-1) % 3 == 0)
		{
			return TicTacToeBoard.G5;
		}
		else if((y-1) % 3 == 0 && (x-2) % 3 == 0)
		{
			return TicTacToeBoard.G6;
		}
		else if((y-2) % 3 == 0 && (x) % 3 == 0)
		{
			return TicTacToeBoard.G7;
		}
		else if((y-2) % 3 == 0 && (x-1) % 3 == 0)
		{
			return TicTacToeBoard.G8;
		}
		else if((y-2) % 3 == 0 && (x-2) % 3 == 0)
		{
			return TicTacToeBoard.G9;
		}


		return null;
	}


	public int[][] copy(int[][] input) {
		int[][] target = new int[input.length][];
		for (int i=0; i <input.length; i++) {
			target[i] = Arrays.copyOf(input[i], input[i].length);
		}
		return target;
	}


}
