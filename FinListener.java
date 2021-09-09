import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Controlleur permet d'écouter si un des boutons
* de l'écran de fin est cliqué
* @author Paul LE CORRE & Brice PANIZZI
*/
public class FinListener implements ActionListener {
	Fin fin;
	Modele modal;
	JButton[] button;

	/**
	* @param fin Contient l'écran de fin qui devra être dispose()
	*/
	public FinListener(Fin fin) {
		this.fin = fin;
		this.modal = fin.modal;
		this.button = fin.button;
	}

	/**
	* @param e Informations sur l'événement qui permet de savoir quel bouton a été cliqué
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.fin.getLocation().x;
			this.modal.locY = this.fin.getLocation().y;

			// Fermeture de la fenêtre de fin et réouverture de la fenêtre de jeu
			this.fin.dispose();
			Jeu game = new Jeu(this.modal);
		}

		else if (e.getSource() == button[1]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.fin.getLocation().x;
			this.modal.locY = this.fin.getLocation().y;
			// Fermeture de la fenêtre de fin et réouverture de la fenêtre du menu
			this.fin.dispose();
			Menu menu = new Menu(this.modal);
		}

		else if (e.getSource() == button[2]){
			// Fermeture de la fenêtre de fin
			this.fin.dispose();

		}
	}
}	