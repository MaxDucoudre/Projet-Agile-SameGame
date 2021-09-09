import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
* Cette classe permet de 
* @author Paul LE CORRE & Brice PANIZZI
*/
public class GridFile {
	private String[] gridString = new String[10];
	private char[][] gridChar = new char[10][15];

	/**
	* Affiche le sélecteur de fichier et met le contenu de ses 10 premières lignes dans gridString
	* @param fenetre Contient la fenêtre ou doit être ouvert le sélecteur de fichier
	*/
	public GridFile(JFrame fenetre) {
		JFileChooser file = new JFileChooser("./");
	    file.showOpenDialog(fenetre);
	    try {
	    	BufferedReader reader = new BufferedReader(new FileReader(file.getSelectedFile()));

	    	for (int i = 0; i<10; i++) {
	    		gridString[i] = reader.readLine();
	    	}

	    	reader.close();
	    }catch(FileNotFoundException err) {
	    }catch(IOException err) {
	    }catch(NullPointerException err){
	    }
	    
	    if (this.isOk()) {
	    	this.stringToChar();
	    }
	}

	/**
	* Cette méthode transforme le tableau de String
	* en tableau de char
	*/
	private void stringToChar() {
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<15; j++) {
				gridChar[i][j] = gridString[i].charAt(j);
			}
		}
	}

	/**
	* Cette méthode vérifie si le fichier est exploitable ou si il est incorrect
	* @return true si les informations contenue dans gridString sont correctes
	*/
	public boolean isOk() {
		String[] gS = this.gridString;
		for (int i =0; i<10; i++) {
			if (gS[i] == null) {
				return false;
			}
			if (gS[i].length() != 15) {
				return false;
			} else {
				for (int j = 0; j<15; j++) {
					if (gS[i].charAt(j) != 'R' && gS[i].charAt(j) != 'V' && gS[i].charAt(j) != 'B') {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	* Cette méthode permet d'accéder à gridChar
	* @return le tableau de caractères qui va représenté la grille
	*/
	public char[][] getGrid() {
		return this.gridChar;
	}
}