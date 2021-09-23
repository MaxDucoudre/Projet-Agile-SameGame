import java.util.Arrays;


abstract class Bot implements BotInterface {

	protected String bot_name;
	protected API interfaceMJ;
	protected int coordX = 0;
	protected int coordY = 0;

	public Bot(String bot_name) {
		this.bot_name = bot_name;

	}

	public String getBotName() {
		return bot_name;
	}


	public int getCol() {
		char[][] grille = this.interfaceMJ.getGrille(); 

  		return grille[0].length;
	}


	public int getLine() {
		char[][] grille = this.interfaceMJ.getGrille(); 

		return grille.length;

	}


	public boolean verifCase(int coordX, int coordY) {

		char[][] charTab = this.interfaceMJ.getGrille(); 
		if (charTab[coordX][coordY] == ' ') {
			return false;
		} else {
			// if groupe est plus grand que 1
			return true;
			// enfid
		}
	}

	public void genererCoord() {
		this.coordX = 0;
		this.coordX = 0;
	}

	

	public void setAPI(API interface_moteur_jeu) {
		this.interfaceMJ = interface_moteur_jeu;
	}

	public void jouerCoup() {
			this.genererCoord();
			this.interfaceMJ.selectGroup(this.coordX, this.coordY);

			this.interfaceMJ.destroyGroup();
	}

	public void startBot() {
		while(this.interfaceMJ.getFini() == false) {

			this.jouerCoup();

		}
	}


}



