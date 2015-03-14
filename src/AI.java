import java.util.Arrays;

public class AI {

	int searchDepth = 3;
	private Player aiPlayer;


	public void generateTree(TicTacToeBoard tbord)
	{

		int[][] board = new int[9][9];

		aiPlayer = new Player();
		aiPlayer.switchPlayer();

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

		GameState s1 = new GameState(aiPlayer,board,tbord.activeGroup);
		Node rootNode = new Node(s1);
		Tree miniMaxTree = new Tree(rootNode);
		minimaxRecursive(1,rootNode);
		evaluateTree(miniMaxTree);




		//Level l1 = new Level();

		/*

		GameState s1 = new GameState(board,aiPlayer.getPlayerN(),tbord.activeGroup);
		Node rootNode = new Node(s1);
		Tree miniMaxTree = new Tree(rootNode);


		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(x>= s1.activeGroup[0] && x<= s1.activeGroup[2] && y>= s1.activeGroup[1] && y<= s1.activeGroup[3])
				{

					if(s1.board[x][y] != 1 || s1.board[x][y] != 0)
					{
						// int[][] newBoard = s1.board.clone(); -> NOT A DEEP COPY!
						int[][] newBoard = copy(board);
						newBoard[x][y] = 1;
						GameState ng = new GameState(newBoard,0,getActiveGroup(x,y));
						Node n1 = new Node(ng);
						miniMaxTree.root.addChildren(n1);
						//l1.addLevel(new GameState(newBoard,0,getActiveGroup(x,y)));
					}
				}
			}
		}
		*/
	}



	public void minimaxRecursive(int currentDepth,Node node)
	{
		if(currentDepth < searchDepth)
		{
			generatePossibleMoves(node);

			for(Node subNode : node.children)
			{
				minimaxRecursive((currentDepth+1),subNode);
			}
		}
	}


	public void generatePossibleMoves(Node node)
	{

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(x>= node.value.activeGroup[0] && x<= node.value.activeGroup[2] &&
						y>= node.value.activeGroup[1] && y<= node.value.activeGroup[3])
				{

					if(node.value.board[x][y] != 1 || node.value.board[x][y] != 0)
					{
						int[][] newBoard = copy(node.value.board);
						newBoard[x][y] = node.value.player.getPlayerN();
						GameState ng = new GameState(new Player(node.value.player),newBoard,getActiveGroup(x,y));
						Node node1 = new Node(ng);
						node.addChildren(node1);
					}
				}
			}
		}
	}

	public void evaluateTree(Tree tree)
	{

		System.out.println("Root Node:");
		tree.root.value.printGstate();


		System.out.println("Child Nodes:");
		for(Node n : tree.root.children)
		{
			n.value.printGstate();
		}

		System.out.println("Child-Child Nodes:");
		for (Node n : tree.root.children)
		{
			for (Node n1 : n.children)
			{
				n1.value.printGstate();
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
