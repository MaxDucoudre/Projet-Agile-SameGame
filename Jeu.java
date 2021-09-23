import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Vue est la fenêtre qui va afficher l'écran de jeu
* @author Paul LE CORRE & Brice PANIZZI
*/
public class Jeu extends JFrame {
    Modele modal;

    /**
    * Affiche la fenêtre de jeu qui contient la grille ainsi que le score
    * @param modal Contient une partie des informations utiles au bon fonctionnement du jeu ainsi que la position où la fenetre devra être
    */
    public Jeu(Modele modal){
        this.modal = modal;

        
        // Initialisation de la fenetre
        this.setTitle("Same Game");
        this.setLocation(this.modal.locX, this.modal.locY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grille grille = new Grille(this);
        this.add(grille.printpoints, BorderLayout.NORTH);
        this.add(grille.jpan, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}