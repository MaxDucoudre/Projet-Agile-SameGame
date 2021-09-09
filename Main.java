/**
* @author Paul LE CORRE & Brice PANIZZI
*/

import javax.swing.*;
import java.awt.*;

public class Main{
	/**
	* Lance le programme et initialise la classe modele
	* @param args la liste des arguments de la ligne de commande (inutilisée ici)
	*/
	public static void main(String[] args) {
		Modele modal = new Modele();
		Menu menu = new Menu(modal);
	}
}
