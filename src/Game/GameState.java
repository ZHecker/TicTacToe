package Game;

public class GameState {

	public byte[][] board;
	public int[] activeGroup;
	public Player player;
	public int score;
	private final int CONSEQUENTIAL2 = 10;
	private final int CONSEQUENTIAL3 = 1000;
	private final int BLOCKED_3_MOVE = 10;
	private int x;
	private int y;


	// O -> 1
	// X -> 0

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


				switch (board[x][y])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
				}

				switch (consO)
				{
					case 2 : score += CONSEQUENTIAL2; break;
					case 3 : score += CONSEQUENTIAL3; break;
				}

				switch (consX)
				{
					case 2 : score -= CONSEQUENTIAL2; break;
					case 3 : score -= CONSEQUENTIAL3; break;
				}
			}
		}

		return score + checkVerticalReverse();

	}

	private int checkVerticalReverse()
	{
		int consO = 0;
		int consX = 0;
		int score = 0;

		for (int x = 8; x >= 0; x--) {
			for (int y = 8; y >= 0; y--) {

				switch (board[x][y])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
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

				switch (board[x][y])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
				}

				switch (consO)
				{
					case 2 : score += CONSEQUENTIAL2; break;
					case 3 : score += CONSEQUENTIAL3; break;
				}

				switch (consX)
				{
					case 2 : score -= CONSEQUENTIAL2; break;
					case 3 : score -= CONSEQUENTIAL3; break;
				}
			}

		}
		return score + checkHorizontalReverse();
	}

	private int checkHorizontalReverse()
	{
		int score = 0;
		int consO = 0;
		int consX = 0;

		for (int y = 8; y >= 0; y--) {
			for (int x = 8; x >= 0; x--) {


				switch (board[x][y])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
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


				switch (board[diagonalenX][diagonalenY])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
				}

				switch (consO)
				{
					case 2 : score += CONSEQUENTIAL2; break;
					case 3 : score += CONSEQUENTIAL3; break;
				}

				switch (consX)
				{
					case 2 : score -= CONSEQUENTIAL2; break;
					case 3 : score -= CONSEQUENTIAL3; break;

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

				switch (board[antiDiagonalenX][antiDiagonalenY])
				{
					case 1 : consO++;

						if(consX == 2)
						{
							score += BLOCKED_3_MOVE;
						}
						consX = 0;
						break;

					case 0 : consX++;

						if(consO == 2)
						{
							score -= BLOCKED_3_MOVE;
						}
						consO = 0;
						break;
					case -1 : consO = 0; consX = 0; break;
				}

				switch (consO)
				{
					case 2 : score += CONSEQUENTIAL2; break;
					case 3 : score += CONSEQUENTIAL3; break;
				}

				switch (consX)
				{
					case 2 : score -= CONSEQUENTIAL2; break;
					case 3 : score -= CONSEQUENTIAL3; break;

				}


				antiDiagonalenX--;
				antiDiagonalenY++;
			}
		}

		return score;
	}

}
