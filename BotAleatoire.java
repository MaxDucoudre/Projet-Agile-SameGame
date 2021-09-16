import java.util.Random;

public class BotAleatoire extends Bot {

	private InterfaceIA interfaceIA;

	public BotAleatoire(String bot_name, InterfaceIA interfaceIA) {
		super(bot_name, interfaceIA);
		this.interfaceIA = interfaceIA;
		interfaceIA.startBot(this);
	}

	public int genererCoordX() {

		int max = this.interfaceIA.getLigne()-1;
		int min = 0;

    	Random random = new Random();
    	int coordX = (int)Math.floor(Math.random()*(max-min+1)+min);

    	return coordX;
	}

	public int genererCoordY() {

		int max = this.interfaceIA.getColonne()-1;
		int min = 0;

    	Random random = new Random();
    	int coordY = (int)Math.floor(Math.random()*(max-min+1)+min);


    	return coordY;
	}

}