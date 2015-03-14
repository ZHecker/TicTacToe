public class GameState {



	public int[][] board;
	public int[] activeGroup;
	public Player player;


	public GameState(Player player, int[][] board, int[] activeGroup) {
		this.player = player;
		this.board = board;
		this.activeGroup = activeGroup;
	}

	public void printGstate()
	{

		for (int y = 0; y < 9; y++) {
			System.out.print("|");
			for (int x = 0; x < 9; x++) {

				if(board[x][y] == -1)
				{
					System.out.print("  " + " | ");
				}
				else
				{
					System.out.print(" " + board[x][y] + " | ");
				}
			}
			System.out.println();
		}

		System.out.println();
	}

	// -> Generate all possible moves -> if player = 1 -> Maximize
	// if player = 0 -> minimize


	// X = 0
	// O = 1
	// NONE = -1

}
