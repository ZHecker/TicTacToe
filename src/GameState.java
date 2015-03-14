public class GameState {



	public int[][] board;
	public int currentMove;
	public int[] activeGroup;


	public GameState(int[][] board, int currentMove,int[] activeGroup) {
		this.board = board;
		this.currentMove = currentMove;
		this.activeGroup = activeGroup;

	}


	// -> Generate all possible moves -> if player = 1 -> Maximize
	// if player = 0 -> minimize


	// X = 0
	// O = 1
	// NONE = -1

}
