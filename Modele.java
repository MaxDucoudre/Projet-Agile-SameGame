/**
* Cette classe Modele permet de garder en mémoire des données tout au long du programme
* @author Paul LE CORRE & Brice PANIZZI
*/
public class Modele {
	int locX = 100;
	int locY = 100;
	boolean importGrid = false;
	char[][] gridChar = new char[10][15];

	/**
	* @param gridChar grille qui va remplacer l'actuelle
	*/
	public void setGrid(char[][] gridChar) {
		this.gridChar = gridChar;
	}

	/**
	* @return une copie de gridChar
	*/
	public char[][] getGrid() {
		// Creation d'une copie de gridChar
		char[][] gChar = new char[10][15];
		for (int i = 0; i<10;i++) {
			for (int j = 0; j<15;j++) {
				gChar[i][j] = this.gridChar[i][j];
			}
		}
		// Return de la copie
		return gChar;
	}
}