import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Controlleur permet d'écouter si un des boutons
* du menu est cliqué
* @author Paul LE CORRE & Brice PANIZZI
*/
public class MenuListener implements ActionListener {
	Menu menu;
	Modele modal;
	JButton[] button;

	/**
	* @param menu Contient la fenetre du menu qui devra être dispose()
	*/
	public MenuListener(Menu menu, JButton[] button) {
		this.menu = menu;
		this.modal = menu.modal;
		this.button = button;
	}

	/**
	* @param e Informations sur l'événement qui permet de savoir quel bouton a été cliqué
	*/
	public void actionPerformed(ActionEvent e) {
		//Ouvre le jeu
		if (e.getSource() == button[0]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.menu.getLocation().x;
			this.modal.locY = this.menu.getLocation().y;
			// Fermeture de la fenêtre de menu et ouverture de la fenêtre de jeu
			this.menu.dispose();
	        Jeu game = new Jeu(this.modal);
		}
		//Ouvre les paramètres
		else if (e.getSource() == button[1]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.menu.getLocation().x;
			this.modal.locY = this.menu.getLocation().y;
			// Fermeture de la fenêtre de menu et ouverture de la fenêtre parametres
			this.menu.dispose();
			Parametres param = new Parametres(this.modal);
		}
		//Quitte le programme
		else if (e.getSource() == button[2]){
			// Fermeture de la fenêtre du menu
			this.menu.dispose();
		}
	}
}