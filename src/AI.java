import java.util.Arrays;
import java.util.Random;

public class AI {

	int searchDepth = 6;
	private Player aiPlayer;
	private Random r;
	private TicTacToeBoard toeBoard;


	public AI(TicTacToeBoard toeBoard) {
		this.toeBoard = toeBoard;
	}

	public void generateTree()
	{

		int[][] board = new int[9][9];

		aiPlayer = new Player();
		aiPlayer.switchPlayer();

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(toeBoard.felder[x][y].getText().equals(""))
				{
					board[x][y] = -1;
					continue;
				}

				switch (toeBoard.felder[x][y].getText().charAt(0))
				{
					case 'X' : board[x][y] = 0; break;
					case 'O' : board[x][y] = 1; break;
					default : board[x][y] = -1; break;
				}
			}
		}

		GameState s1 = new GameState(aiPlayer,board,toeBoard.activeGroup);
		Node rootNode = new Node(s1);
		Tree miniMaxTree = new Tree(rootNode);
		minimaxRecursive(1,miniMaxTree.root);
		evaluateTree(miniMaxTree);


		//Level l1 = new Level();

		/*

		GameState s1 = new GameState(board,aiPlayer.getPlayerN(),tbord.activeGroup);
		Node rootNode = new Node(s1);
		Tree miniMaxTree = new Tree(rootNode);


		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
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
						//GameState ng = new GameState(new Player(node.value.player),newBoard,getActiveGroup(x,y));
						GameState newState = new GameState(new Player(node.value.player),newBoard,getActiveGroup(x,y),x,y);
						Node node1 = new Node(newState);
						node.addChildren(node1);
					}
				}
			}
		}
	}

	public void evaluateTree(Tree tree)
	{

		/*
		Node bestNode;

		negamax(tree.root, -1);
		bestNode = tree.root.children.get(0);

		for (Node n : tree.root.children)
		{
			n.value.printGstate();
		}

		bestNode.value.printGstate();
		toeBoard.click(bestNode.value.getX(),bestNode.value.getY());

		*/



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


	private int negamax(Node n,int negamax_player)
	{

		int bestValue = Integer.MIN_VALUE;

		if(n.children.size() == 0)
		{
			return negamax_player * (n.value.calculateScore());
		}

		for(Node n1 : n.children)
		{
			n1.value.score = -negamax(n1,-negamax_player);

			if(n1.value.score > bestValue)
			{
				bestValue = n1.value.score;
			}

		}

		return bestValue;

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
