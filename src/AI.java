public class AI {

	private int[][] board = new int[9][9];

	public void generateTree(TicTacToeBoard tbord,String currentPlayer)
	{

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				switch (tbord.felder[x][y].getText().charAt(0))
				{
					case 'X' : board[x][y] = 0; break;
					case 'O' : board[x][y] = 1; break;
					default : board[x][y] = -1; break;
				}
			}
		}

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				System.out.print(board[x][y] + " ");
			}
			System.out.println();
		}


		GameState s1 = new GameState(board,currentPlayer.equals("X") ? 0:1,tbord.activeGroup);

	}
}
