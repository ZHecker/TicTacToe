public class Player_Controler {


	static public String player = "X";


	public static void buttonPressed()
	{
		if(player.equals("X"))
			player = "O";
		else
			player = "X";
	}



}
