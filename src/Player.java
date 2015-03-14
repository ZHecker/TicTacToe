public class Player {

	private String[] player = new String[] {"X","O"};
	private int playerN = 0;

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


	public int getPlayerN() {
		return playerN;
	}

	public String getPlayer()
	{
		return player[playerN];
	}

	public Player()
	{

	}

	public Player(Player p)
	{

		if(p.getPlayerN() == 0)
		{
			this.playerN = 1;
		}
		else
		{
			this.playerN = 0;
		}


	}

}
