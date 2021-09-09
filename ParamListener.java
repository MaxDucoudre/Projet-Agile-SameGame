import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Controlleur permet d'écouter si un des boutons
* de l'écran des parametres est cliqué
* @author Paul LE CORRE & Brice PANIZZI
*/
public class ParamListener implements ActionListener {
	private Parametres parametres;
	public Modele modal;
	private JButton[] button;

	/**
	* @param parametres Contient la fenetre des parametres qui devra être dispose()
	*/
	public ParamListener(Parametres parametres, JButton[] button) {
		this.parametres = parametres;
		this.modal = parametres.modal;
		this.button = button;
	}

	/**
	* @param e Informations sur l'événement qui permet de savoir quel bouton a été cliqué
	*/
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.button[0]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			// Fermeture de la fenêtre de parametres et ouverture de la fenêtre du menu
			this.parametres.dispose();
			Menu menu = new Menu(this.modal);	
		}

		else if (e.getSource() == this.button[1]){
			this.modal.importGrid = false;
			this.button[1].setBackground(Color.YELLOW);
			this.button[2].setBackground(new Color(123,27,91));
		}

		else if (e.getSource() == this.button[2]){
			// Importation de la grille
			GridFile filechooser = new GridFile(this.parametres);
            if (filechooser.isOk()) {
                this.modal.setGrid(filechooser.getGrid());
                this.modal.importGrid = true;
            } else {
            	System.err.println("Fichier introuvable ou incorrect");
            }
            if (this.modal.importGrid) {
				this.button[1].setBackground(new Color(123,27,91));
				this.button[2].setBackground(Color.YELLOW);
            }
		}

		else if (e.getSource() == this.button[3]){
			// Enregistrement des coordonnées de la fenêtre pour la création de la prochaine
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			// Fermeture de la fenêtre de parametres et ouverture de la fenêtre de jeu
			this.parametres.dispose();
			Jeu game = new Jeu(this.modal);
		}
	}
}