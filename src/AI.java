import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AI extends Thread {

	int searchDepth = 7;
	private Player aiPlayer;
	private TicTacToeBoard toeBoard;


	public void run()
	{
		try {
			Thread.sleep(1500);

		}catch (Exception e)
		{

		}

		generateTree();
	}

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
		s1.printRaw();


		Node rootNode = new Node(s1);
		Tree miniMaxTree = new Tree(rootNode);
		addSubNode(1, miniMaxTree.root);
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

	public void addSubNode(int currentDepth,Node node)
	{
		if(currentDepth < searchDepth)
		{
			generatePossibleMoves(node);

			for(Node subNode : node.children)
			{
				addSubNode((currentDepth + 1), subNode);
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

					if(node.value.board[x][y] == -1) // D'oh
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
		System.out.println(minimax(tree.root, true));
		int currentBest = Integer.MIN_VALUE;
		ArrayList<Node> bestNodes = new ArrayList<Node>();

		for (Node n : tree.root.children)
		{

			if(n.value.score > currentBest)
			{
				bestNodes.clear();
				currentBest = n.value.score;
				bestNodes.add(n);
			}
			else if(n.value.score == currentBest)
			{
				bestNodes.add(n);
			}

		}


		/*

		for (Node n : bestNodes)
		{
			n.value.printGstate();
		}

		*/

		Random r = new Random();

		Node klick;

		if(bestNodes.size() > 1)
		{
			klick = bestNodes.get(r.nextInt(bestNodes.size()));
		}
		else
		{
			klick = bestNodes.get(0);
		}

		toeBoard.click(klick.value.getX(),klick.value.getY());

	}

	private int minimax(Node node,boolean maximizing)
	{

		if(node.children.size() == 0)
		{
			return node.value.calculateScore();
		}

		int bestValue;


		if(maximizing)
		{
			bestValue = Integer.MIN_VALUE;

			for (Node n1 : node.children)
			{
				int nodeValue = minimax(n1,false);

				n1.value.score = nodeValue;

				if(nodeValue > bestValue)
				{
					bestValue = nodeValue;

				}
			}
			return bestValue;
		}
		else
		{
			bestValue = Integer.MAX_VALUE;

			for(Node n1 : node.children)
			{
				int nodeValue = minimax(n1,true);

				n1.value.score = nodeValue;

				if(nodeValue < bestValue)
				{
					bestValue = nodeValue;
				}

			}
			return bestValue;
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
