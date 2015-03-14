import java.util.ArrayList;

public class Level {


	public ArrayList<GameState> gameStates = new ArrayList<GameState>();


	public void addLevel(GameState s)
	{
		gameStates.add(s);
	}


	public void printLevel()
	{

		for(GameState gs : gameStates)
		{
			gs.printGstate();
			System.out.println();

		}

	}

}
