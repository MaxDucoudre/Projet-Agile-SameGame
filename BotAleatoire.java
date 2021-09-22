import java.util.Random;

public class BotAleatoire extends Bot {


	public BotAleatoire(String bot_name) {
		super(bot_name);
	}

	public void genererCoordX() {

		int max = this.getLine()-1;
		int min = 0;

    	Random random = new Random();
    	super.coordX = (int)Math.floor(Math.random()*(max-min+1)+min);

	}

	public void genererCoordY() {

		int max = this.getCol()-1;
		int min = 0;

    	Random random = new Random();
    	super.coordY = (int)Math.floor(Math.random()*(max-min+1)+min);

	}


}