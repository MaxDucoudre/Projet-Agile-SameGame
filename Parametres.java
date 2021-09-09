/**
* @author Paul LE CORRE & Brice PANIZZI
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Parametres extends JFrame{

    public JButton[] button = new JButton[4];
    public Modele modal;

    /**
    * Création de la fenêtre qui va afficher l'écran des parametres
    * avec des boutons permettants d'importer une grille ou
    * d'en créer une aléatoire, de retourner au menu ou de lancer le jeu
    * @param modal Contient une partie des informations utiles au bon fonctionnement du jeu ainsi que la position de la fenetre
    */
    public Parametres(Modele modal) {
        this.modal = modal;

        // Initialisation de la fenetre
        this.setTitle("Same Game");
        this.setLocation(this.modal.locX, this.modal.locY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = Toolkit.getDefaultToolkit().getImage("blocs/parametres.gif");

        // Creation du JPanelImage
        JPanelImage jpan = new JPanelImage(image);
        jpan.setPreferredSize(new Dimension(1160, 653));
        jpan.setOpaque(false);
        jpan.setLayout(null);
        
        // Lancement du gif
        Thread thread = new Thread( new AutoRepaint(this));
        thread.start();

        // Creation des boutons
        button[0] = new JButton("Retour");
        button[0].setBounds(100,575,150,50);
        button[0].setBackground(new Color(123,27,91));
        
        button[1] = new JButton("Grille aléatoire");
        button[1].setBounds(250,325,200,50);
        
        button[2] = new JButton("Importer grille");
        button[2].setBounds(720,325,200,50);
        if (this.modal.importGrid) {
            button[2].setBackground(Color.YELLOW);
            button[1].setBackground(new Color(123,27,91));
        } else {
            button[1].setBackground(Color.YELLOW);
            button[2].setBackground(new Color(123,27,91));
        }
        
        button[3] = new JButton("Jouer");
        button[3].setBounds(900,575,150,50);
        button[3].setBackground(new Color(123,27,91));

        // Ajout des JButton au JPanelImage
        for (int i = 0; i<4; i++) {
            jpan.add(button[i]);
          button[i].addActionListener(new ParamListener(this, this.button));
        }

        this.add(jpan);

        this.pack();
        this.setVisible(true);
    }
}