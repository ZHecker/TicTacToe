public class GameState {

	public byte[][] board;
	public int[] activeGroup;
	public Player player;
	public int score = Integer.MIN_VALUE;
	private final int CONSEQUENTIAL2 = 5; //5
	private final int CONSEQUENTIAL3 = 100000; //10000
	private int x;
	private int y;

	public GameState(Player player, byte[][] board, int[] activeGroup) {
		this.player = player;
		this.board = board;
		this.activeGroup = activeGroup;
	}

	public GameState(Player player, byte[][] board, int[] activeGroup,int x,int y) {
		this.player = player;
		this.board = board;
		this.activeGroup = activeGroup;
		calculateScore();
		this.x = x;
		this.y = y;
	}


	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}


	public void printRaw()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
					System.out.print(board[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println("Score: " + score);
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

		System.out.println("Score: " + score);
	}

	public int calculateScore()
	{
		return (checkVertical() + checkHorizontal() + checkDiagonal() + checkAntiDiagonal());
	}

	private int checkVertical()
	{
		int consO = 0;
		int consX = 0;
		int score = 0;

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {

				if(board[x][y] == 1)
				{
					consO++;
				}
				else
				{
					consO = 0;
				}

				if(board[x][y] == 0)
				{
					consX++;
				}
				else
				{
					consX = 0;
				}

				if(consO == 2)
				{
					score += CONSEQUENTIAL2;
				}
				if(consO == 3)
				{
					score += CONSEQUENTIAL3;
				}

				if(consX == 2)
				{
					score -= CONSEQUENTIAL2;
				}
				if(consX == 3)
				{
					score -= CONSEQUENTIAL3;
				}


			}
		}

		return score;

	}

	private int checkHorizontal()
	{
		int score = 0;

		int consO = 0;
		int consX = 0;

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(board[x][y] == 1)
				{
					consO++;
				}
				else
				{
					consO = 0;
				}

				if(board[x][y] == 0)
				{
					consX++;
				}
				else
				{
					consX = 0;
				}

				if(consO == 2)
				{
					score += CONSEQUENTIAL2;
				}
				if(consO == 3)
				{
					score += CONSEQUENTIAL3;
				}

				if(consX == 2)
				{
					score -= CONSEQUENTIAL2;
				}
				if(consX == 3)
				{
					score -= CONSEQUENTIAL3;
				}
			}
		}
		return score;
	}

	private int checkDiagonal()
	{

		int score = 0;

		for (int i = 0; i < 8; i++) {


			int diagonalenX = 8-i;
			int diagonalenY = i;
			int consO = 0;
			int consX = 0;

			while(diagonalenX !=0 && diagonalenY != 0)
			{
				diagonalenX--;
				diagonalenY--;
			}

			while (diagonalenX <= 8 && diagonalenY <= 8)
			{

				if(board[diagonalenX][diagonalenY] == 1)
				{
					consO++;
				}
				else
				{
					consO = 0;
				}

				if(board[diagonalenX][diagonalenY] == 0)
				{
					consX++;
				}
				else
				{
					consX = 0;
				}

				if(consO == 2)
				{
					score += CONSEQUENTIAL2;
				}
				if(consO == 3)
				{
					score += CONSEQUENTIAL3;
				}

				if(consX == 2)
				{
					score -= CONSEQUENTIAL2;
				}
				if(consX == 3)
				{
					score -= CONSEQUENTIAL3;
				}

				diagonalenX++;
				diagonalenY++;
			}
		}
		return score;
	}

	private int checkAntiDiagonal()
	{

		int score = 0;

		for (int i = 1; i < 8; i++) {

			int antiDiagonalenX = i;
			int antiDiagonalenY = i;
			int consO = 0;
			int consX = 0;

			while(antiDiagonalenX !=8 && antiDiagonalenY != 0)
			{
				antiDiagonalenX++;
				antiDiagonalenY--;
			}

			while (antiDiagonalenX >= 0 && antiDiagonalenY <= 8)
			{

				if(board[antiDiagonalenX][antiDiagonalenY] == 1)
				{
					consO++;
				}
				else
				{
					consO = 0;
				}

				if(board[antiDiagonalenX][antiDiagonalenY] == 0)
				{
					consX++;
				}
				else
				{
					consX = 0;
				}

				if(consO == 2)
				{
					score += CONSEQUENTIAL2;
				}
				if(consO == 3)
				{
					score += CONSEQUENTIAL3;
				}

				if(consX == 2)
				{
					score -= CONSEQUENTIAL2;
				}
				if(consX == 3)
				{
					score -= CONSEQUENTIAL3;
				}

				antiDiagonalenX--;
				antiDiagonalenY++;
			}
		}

		return score;
	}
}
