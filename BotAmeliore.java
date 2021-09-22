import java.util.Random;

public class BotAmeliore extends Bot {

	private int coordX, coordY;
	private InterfaceIA interfaceIA;
	private char[][] char_tab;
	private boolean[][] selected;

	public BotAmeliore(String bot_name) {
		super(bot_name);


	}

	public char mostPresentChar(char[][] char_tab) {
		int i,j;
		int red = 0;
		int green = 0;
		int blue = 0;
		int max;
		
		for(i = 0; i < this.getLine(); i++) {
			for(j = 0; j< this.getCol(); j++) {
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
		this.char_tab = this.interfaceMJ.getGrille(); 
		this.selected = new boolean[this.getLine()][this.getCol()];

		do {
			int maxX = this.getLine()-1;
			int minX = 0;
			Random randomX = new Random();
			super.coordX = (int)Math.floor(Math.random()*(maxX-minX+1)+minX);


			int maxY = this.interfaceIA.getColonne()-1;
			int minY = 0;
			Random randomY = new Random();
			super.coordY = (int)Math.floor(Math.random()*(maxY-minY+1)+minY);
			
		} while(this.validateCoord(super.coordX, super.coordY) == false);

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
@Override
	public void genererCoordX() {
		this.genererCoord();
		
	}
@Override

	public void genererCoordY() {

	}





    /**
    * Permet de selectionner le bloc en question si il n'est pas vide et que son groupe fait plus de 1, puis selectionne ensuite les blocs adjacents
    * @return true si la case en question n'est pas vide et fait partie d'un groupe sinon return false
    */
    public boolean select(int l, int c) {
        int counter = 0;
        if(this.char_tab[l][c] == ' ') {
            return false; // Dans le cas ou la case est vide
        }
        // HAUT
        if (l>0) {
            if (!this.selected[l-1][c] && this.char_tab[l][c] == this.char_tab[l-1][c]) {
                selected[l-1][c] = true;
                counter++;
                selectAdjacents(l-1, c);
            }
        }

        // BAS
        if (l<9) {
            if (!this.selected[l+1][c] && this.char_tab[l][c] == this.char_tab[l+1][c]) {
                selected[l+1][c] = true;
                counter++;
                selectAdjacents(l+1, c);
            }
        }

        // GAUCHE
        if (c>0) {
            if (!this.selected[l][c-1] && this.char_tab[l][c] == this.char_tab[l][c-1]) {
                selected[l][c-1] = true;
                counter++;
                selectAdjacents(l, c-1);
            }
        }

        // DROITE
        if (c<14) {
            if (!this.selected[l][c+1] && this.char_tab[l][c] == this.char_tab[l][c+1]) {
                selected[l][c+1] = true;
                counter++;
                selectAdjacents(l, c+1);
            }
        }
        if (counter>0) {
            return true; // Dans le cas ou le groupe fait plus de 1 bloc
        } else {
            return false; // Dans le cas ou le groupe fait 1 bloc
        }
    }


    /**
    * Méthode récursive permetant de selectionner tous les blocs du même groupe que 
    * celui en coordonnées (l,c)
    */
    public void selectAdjacents(int l, int c) {
        // HAUT
        if (l>0) {
            if (!this.selected[l-1][c] && this.char_tab[l][c] == this.char_tab[l-1][c]) {
                selected[l-1][c] = true;
                selectAdjacents(l-1, c);
            }
        }
        // BAS
        if (l<9) {
            if (!this.selected[l+1][c] && this.char_tab[l][c] == this.char_tab[l+1][c]) {
                selected[l+1][c] = true;
                selectAdjacents(l+1, c);
            }
        }
        // GAUCHE
        if (c>0) {
            if (!this.selected[l][c-1] && this.char_tab[l][c] == this.char_tab[l][c-1]) {
                selected[l][c-1] = true;
                selectAdjacents(l, c-1);
            }
        }
        // DROITE
        if (c<14) {
            if (!this.selected[l][c+1] && this.char_tab[l][c] == this.char_tab[l][c+1]) {
                selected[l][c+1] = true;
                selectAdjacents(l, c+1);
            }
        }
    }

}