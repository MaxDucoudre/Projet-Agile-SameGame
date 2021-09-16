
abstract class Bot {

	protected String bot_name;
	protected InterfaceIA interfaceIA;

	public Bot(String bot_name, InterfaceIA interfaceIA) {
		this.bot_name = bot_name;
		this.interfaceIA = interfaceIA;

	}

	public boolean verifCase(int coordX, int coordY) {

		char[][] charTab = this.interfaceIA.getTabChar();
		if (charTab[coordX][coordY] == ' ') {
			return false;
		} else {
			// if groupe est plus grand que 1
			return true;
			// enfid
		}
	}

	public boolean validateCoord(int coordX, int coordY) {
		return true;
	}

	public String getBotName() {
		return bot_name;
	}


	public int genererCoordX() {
		return 0;
	}

	public int genererCoordY() {
		return 0;
	}




}



