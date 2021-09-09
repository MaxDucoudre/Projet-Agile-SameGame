import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Controlleur permet de controller le jeu
* @author Paul LE CORRE & Brice PANIZZI
*/
public class GrilleListener implements MouseListener {
	Grille grille;
	Modele modal;
	JLabel[][] label;

	/**
	* @param grille Contient la grille du jeu en cours
	*/
	public GrilleListener(Grille grille) {
		this.grille = grille;
		this.modal = grille.modal;
		this.label = grille.label;
	}
	
	/**
	* Lorsque la souris rentre sur le component (dans ce cas un jlabel)
	* il est sélectionné dans la grille grâce à la méthode select()
	* de la classe Grille
	* @param e Informations sur l'évenement (non utilisé ici)
	*/
	public void mouseEntered(MouseEvent e) {
		Component component = e.getComponent();
		int c, l;
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<15; j++) {
				if(this.label[i][j] == component) {
					l = i;
					c = j;
					if (this.grille.tab_char[l][c] != ' ') {
						this.grille.select(l, c);
					}
				}
			}
		}
	}

	/**
	* Lorsque la souris sort d'un component (dans ce cas un jlabel)
	* toute la grille est déselectionnée
	* @param e Informations sur l'évenement (non utilisé ici)
	*/
	public void mouseExited(MouseEvent e) {
		this.grille.unselectAll();
	}

	/**
	* Lorsque la souris est relachée, on appelle les méthodes
	* addPoints(), removeSelected(), checkFall() et checkColumns()
	* de la classe grille ce qui fait que les blocs sélectionnés vont disparaitre
	* et les points vont être ajoutés
	* Nous vérifions si il reste des groupes avec la méthode 
	* remainingGroups() de la classe Grille, si ce n'est pas le cas,
	* la grille disparait et nous affichons la fin de la partie
	* @param e Informations sur l'évenement (non utilisé ici)
	*/
	public void mouseReleased(MouseEvent e) {

		Grille gr = this.grille;
		System.out.println();
		gr.printGridBool();
		gr.addPoints();
		gr.removeSelected();
		gr.checkFall();
		gr.checkColumns();
		gr.printGrid();
		System.out.println("--------------------------");

		if (gr.remainingGroups() == 0) {
			this.modal.locX = this.grille.fenetre.getLocation().x;
			this.modal.locY = this.grille.fenetre.getLocation().y;
			gr.fenetre.dispose();
			Fin fin = new Fin(this.modal, gr.getPoints());
		}
	}

	/**
	* @param e Informations sur l'évenement (non utilisé ici)
	*/
	public void mousePressed(MouseEvent e) {}

	/**
	* @param e Informations sur l'évenement (non utilisé ici)
	*/
	public void mouseClicked(MouseEvent e) {}
}