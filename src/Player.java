public class Player {


	static private String[] player = new String[] {"X","O"};
	static private int playerN = 0;



	public void switchPlayer()
	{

		if(playerN == 0)
		{
			playerN = 1;
		}
		else
		{
			playerN = 0;
		}

	}


	public String getPlayer()
	{
		return player[playerN];
	}

}
