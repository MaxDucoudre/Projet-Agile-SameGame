/**
* @author Paul LE CORRE & Brice PANIZZI
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParamListener implements ActionListener {
	Parametres parametres;
	Modele modal;
	JButton[] button;
	

	public ParamListener(Parametres parametres, JButton[] button) {
		this.parametres = parametres;
		this.modal = parametres.modal;
		this.button = button;
	}



	public void actionPerformed(ActionEvent e){


		if(e.getSource() == this.button[0]){
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.parametres.dispose();
			Menu menu = new Menu(this.modal);
			
		}
		else if (e.getSource() == this.button[1]){
			this.modal.importGrid = false;
			this.button[1].setBackground(Color.YELLOW);
			this.button[2].setBackground(new Color(123,27,91));
		}

		else if (e.getSource() == this.button[2]){
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
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.parametres.dispose();
			System.out.println("Type validé : " + this.modal.gametype);


			Jeu game = new Jeu(this.modal); // lance la partie
		}

		//Action souris sur Bot

		else if (e.getSource() == this.button[4]){
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.button[4].setBackground(Color.YELLOW);
			this.button[5].setBackground(new Color(123,27,91));
			this.button[6].setBackground(new Color(123,27,91));
			this.button[7].setBackground(new Color(123,27,91));
			this.modal.gametype = 1; // bot aléatoire


		}
		
		else if (e.getSource() == this.button[5]){
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.button[5].setBackground(Color.YELLOW);
			this.button[6].setBackground(new Color(123,27,91));
			this.button[4].setBackground(new Color(123,27,91));
			this.button[7].setBackground(new Color(123,27,91));
			this.modal.gametype = 2; // Bot Glouton

		}
		
		else if (e.getSource() == this.button[6]){
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.button[6].setBackground(Color.YELLOW);
			this.button[4].setBackground(new Color(123,27,91));
			this.button[5].setBackground(new Color(123,27,91));
			this.button[7].setBackground(new Color(123,27,91));
			this.modal.gametype = 3; // bot amélioré


		}

		else if (e.getSource() == this.button[7]){
			this.modal.locX = this.parametres.getLocation().x;
			this.modal.locY = this.parametres.getLocation().y;
			this.button[7].setBackground(Color.YELLOW);
			this.button[5].setBackground(new Color(123,27,91));
			this.button[6].setBackground(new Color(123,27,91));
			this.button[4].setBackground(new Color(123,27,91));
			this.modal.gametype = 0; // sans bot

		}
	}
}