/**
* @author Paul LE CORRE & Brice PANIZZI
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VariantesListener implements ActionListener {
	JButton[] buttons;
	Grille grille;
	Variantes variantes;
	Bot[] bots;

	public VariantesListener(JButton[] buttons, Grille grille, Variantes variantes, Bot[] bots) {
		this.buttons = buttons;
		this.grille = grille;
		this.variantes = variantes;
		this.bots = bots;
	}



	public void actionPerformed(ActionEvent e){


		if(e.getSource() == this.buttons[0]){ // Bouton Undo
			System.out.println("Undo");
			this.grille.turn--;
			if(this.grille.turn < 0) {
				this.grille.turn = 0;
			} else {


				char[][] newTabChar = this.variantes.getGrilleWithTurn(this.grille.turn);
				this.grille.setTabChar(newTabChar);

				int newScore = this.variantes.getPointTurn(this.grille.turn);
				this.variantes.setPointTurn(this.grille.turn, newScore);
				this.grille.setScore(newScore);
			}

			
		 } else if (e.getSource() == this.buttons[1]) { // Bouton Redo
		 	System.out.println("Redo");

		 	this.grille.turn++;
		 	if(this.grille.turn == this.grille.maxturn+1) {
		 		this.grille.turn--;
		 	} else {

		 		char[][] newTabChar = this.variantes.getGrilleWithTurn(this.grille.turn);
		 		this.grille.setTabChar(newTabChar);

		 		int newScore = this.variantes.getPointTurn(this.grille.turn);
		 		this.variantes.setPointTurn(this.grille.turn, newScore);
		 		this.grille.setScore(newScore);
		 	}
		 } else if (e.getSource() == this.buttons[2]) { // bot aléatoire
		 	this.bots[0].jouerCoup();
		 } else if (e.getSource() == this.buttons[3]) { // bot glouton
		 	this.bots[1].jouerCoup();

		 } else if (e.getSource() == this.buttons[4]) { // bot ameliore
		 	this.bots[2].jouerCoup();

		 }

		}

	}