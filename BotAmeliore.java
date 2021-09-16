import java.util.Random;

public class BotAmeliore extends Bot {

	private int coordX, coordY;
	private InterfaceIA interfaceIA;
	private char[][] char_tab;

	public BotAmeliore(String bot_name, InterfaceIA interfaceIA) {
		super(bot_name, interfaceIA);
		this.interfaceIA = interfaceIA;
		this.char_tab = this.interfaceIA.getTabChar();
		this.interfaceIA.startBot(this);

	}

	public char mostPresentChar(char[][] char_tab) {
		int i,j;
		int red = 0;
		int green = 0;
		int blue = 0;
		int max;
		
		for(i = 0; i < this.interfaceIA.getLigne(); i++) {
			for(j = 0; j< this.interfaceIA.getColonne(); j++) {
				if(char_tab[i][j] == 'R') {
					red++;
				} else if(char_tab[i][j] == 'B') {
					blue++;
				} else if(char_tab[i][j] == 'V') {
					green++;
				} 
			}
		}

		int[] arrayPresentChar = new int[3];
		arrayPresentChar[0] = red;
		arrayPresentChar[1] = blue;
		arrayPresentChar[2] = green;

		max = arrayPresentChar[0];
		for(i=1;i < arrayPresentChar.length;i++){ 
			if(arrayPresentChar[i] > max){ 
				max = arrayPresentChar[i]; 
			} 
		} 

		if(max == red) {
			return 'R';
		} else if(max == blue) {
			return 'B';
		} else if(max == green) {
			return 'V';
		}
		return ' ';
	}

	public boolean charIsSave(int coordX, int coordY) {
		if(this.char_tab[coordX][coordY] == this.mostPresentChar(this.char_tab)) {
			return true;
		} 
		return false;
	}

	public int numberOfGroupOfChar(char car) {
		int nbgroups = 0;
		for (int l = 0; l<10; l++) {
			for (int c = 0; c<15; c++) {
				if (!this.interfaceIA.grille.selected[l][c]) {
					if (this.interfaceIA.grille.select(l, c)) {
						if(char_tab[l][c] == car) {
							nbgroups++;
						}
					}
				}
			}
		}
		this.interfaceIA.grille.unselectAll();
		return nbgroups;
	}


	public void genererCoord() {

		do {
			int maxX = this.interfaceIA.getLigne()-1;
			int minX = 0;
			Random randomX = new Random();
			this.coordX = (int)Math.floor(Math.random()*(maxX-minX+1)+minX);


			int maxY = this.interfaceIA.getColonne()-1;
			int minY = 0;
			Random randomY = new Random();
			this.coordY = (int)Math.floor(Math.random()*(maxY-minY+1)+minY);
			
		} while(this.validateCoord(this.coordX, this.coordY) == false);

	}

	public char[] charNotSave(char mostPresentChar) {
		char[] charNotSave = new char[2];
		if(mostPresentChar == 'R') {
			charNotSave[0] = 'V';
			charNotSave[1] = 'B';
		} else if(mostPresentChar == 'V') {
			charNotSave[0] = 'R';
			charNotSave[1] = 'B';

		} else if(mostPresentChar == 'B') {
			charNotSave[0] = 'V';
			charNotSave[1] = 'R';
		}
		return charNotSave;
	}


	public boolean validateCoord(int coordX, int coordY) {
		char mostPresentChar = this.mostPresentChar(this.char_tab);
		char[] charNotSave = new char[2];

	    charNotSave = this.charNotSave(mostPresentChar);

		if(numberOfGroupOfChar(charNotSave[0]) == 0) {
			if(numberOfGroupOfChar(charNotSave[1]) == 0) {
				return true;

			}
		}

		if(this.charIsSave(coordX, coordY) == true) {
			return false;
		} 

		return true;

	}

	public int genererCoordX() {
		this.genererCoord();

		return coordX;
	}

	public int genererCoordY() {

		return coordY;
	}

}