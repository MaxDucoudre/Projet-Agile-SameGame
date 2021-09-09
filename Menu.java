import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Vue est la fenêtre qui va afficher le menu principal
* @author Paul LE CORRE & Brice PANIZZI
*/
public class Menu extends JFrame {

    JButton[] button = new JButton[3];
    Modele modal;
    JPanelImage jpan;

    /**
    * Création de la fenêtre qui va afficher le menu
    * avec des boutons permettants d'aller dans les parametres, 
    * lancer le jeu ou quitter
    * @param modal Contient une partie des informations utiles au bon fonctionnement du jeu ainsi que la position de la fenetre
    */
    public Menu(Modele modal) {
        this.modal = modal;

        // Initialisation du théme Nimbus 
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(ClassNotFoundException e) {
        } catch(InstantiationException e) {
        } catch(IllegalAccessException e) {
        } catch(UnsupportedLookAndFeelException e) {}

        // Initialisation de la fenêtre
        this.setTitle("Same Game");
        this.setLocation(this.modal.locX, this.modal.locY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = Toolkit.getDefaultToolkit().getImage("blocs/menu.gif");

        // Creation du JPanelImage
        this.jpan = new JPanelImage(image);
        this.jpan.setPreferredSize(new Dimension(1160, 653));
        this.jpan.setOpaque(false);
        this.jpan.setLayout(null);
        
        // Lancement du gif
        Thread thread = new Thread( new AutoRepaint(this ));
        thread.start();

        // Creation des boutons
        button[0] = new JButton("Jouer");
        button[0].setSelected(false);
        button[0].setBounds(100, 575, 150, 50);
        button[0].setBackground(new Color(123,27,91));

        button[1] = new JButton("Paramètres");
        button[1].setBounds(510, 575, 150, 50);
        button[1].setBackground(new Color(123,27,91));

        button[2] = new JButton("Quitter");
        button[2].setBounds(900, 575, 150, 50);
        button[2].setBackground(new Color(123,27,91));

        // Ajout des JButton au JPanelImage
        for (int i = 0; i<3; i++) {
            jpan.add(button[i]);
          	button[i].addActionListener(new MenuListener(this, this.button));
        }

        this.add(jpan);

        this.pack();
        this.setVisible(true );

    }
}