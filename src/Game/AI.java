package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class AI {

	private TicTacToeBoard toeBoard;
	int searchDepth = 6;


	public AI(TicTacToeBoard toeBoard) {
		this.toeBoard = toeBoard;
	}

	public void generateTree()
	{
		byte[][] board = new byte[9][9];

		Player aiPlayer = new Player();
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
		addSubNode(1, miniMaxTree.root);
		evaluateTree(miniMaxTree);

	}

	void addSubNode(int currentDepth, Node node)
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

	void generatePossibleMoves(Node node)
	{

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				if(x>= node.value.activeGroup[0] && x<= node.value.activeGroup[2] &&
						y>= node.value.activeGroup[1] && y<= node.value.activeGroup[3])
				{

					if(node.value.board[x][y] == -1) // D'oh
					{
						byte[][] newBoard = copy(node.value.board);
						newBoard[x][y] = (byte)node.value.player.getPlayerN();
						//GameState ng = new GameState(new Player(node.value.player),newBoard,getActiveGroup(x,y));
						GameState newState = new GameState(new Player(node.value.player),newBoard,getActiveGroup(x,y),x,y);
						Node node1 = new Node(newState);
						node.addChildren(node1);
					}
				}
			}
		}
	}

	void evaluateTree(Tree tree)
	{
		minimax(tree.root,true);
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

		Random r = new Random();

		Node click;

		if(bestNodes.size() > 1)
		{
			click = bestNodes.get(r.nextInt(bestNodes.size()));
		}
		else
		{
			click = bestNodes.get(0);
		}

		toeBoard.click(click.value.getX(), click.value.getY());

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

	int test = 1;


	int[] getActiveGroup(int x, int y)
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

	byte[][] copy(byte[][] input) {
		byte[][] target = new byte[input.length][];
		for (int i=0; i <input.length; i++) {
			target[i] = Arrays.copyOf(input[i], input[i].length);
		}
		return target;
	}

}
