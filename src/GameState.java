public class GameState {



	public int[][] board;
	public int currentMove;
	public int[] activeGroup;


	public GameState(int[][] board, int currentMove,int[] activeGroup) {
		this.board = board;
		this.currentMove = currentMove;
		this.activeGroup = activeGroup;

	}

	public void printGstate()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				System.out.print(board[x][y] + " ");
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
