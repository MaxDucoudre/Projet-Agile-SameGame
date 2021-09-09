import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Cette classe Vue est la fenêtre qui va afficher l'écran de fin
* @author Paul LE CORRE & Brice PANIZZI
*/
public class Fin extends JFrame {

    public JButton[] button = new JButton[3];
    public Modele modal;

    /**
    * Création de la fenêtre qui va afficher l'écran de fin
    * avec le score de la partie et les boutons permettants
    * de relancer une partie, retourner au menu et quitter le jeu
    * @param modal Contient une partie des informations utiles au bon fonctionnement du jeu ainsi que la position où la fenetre devra être
    * @param nbpoints Nombre de points acquis lors de la partie
    */
	public Fin(Modele modal, int nbpoints){
        this.modal = modal;

        // Initialisation de la fenetre
        this.setTitle("Same Game");
        this.setLocation(this.modal.locX, this.modal.locY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creation du JPanelImage
        Image image = Toolkit.getDefaultToolkit().getImage("blocs/fin.gif");
        JPanelImage jpan = new JPanelImage(image);
        jpan.setPreferredSize(new Dimension(1160, 653));
        jpan.setOpaque(false);
        jpan.setLayout(null);

        // Lancement du gif
        Thread thread = new Thread( new AutoRepaint(this));
        thread.start();

        // Creation des JLabel affichant le score
        JLabel score = new JLabel(String.valueOf(nbpoints));
        score.setFont(new Font("Magneto", Font.PLAIN, 80));
        score.setForeground(new Color(123,27,91));
        score.setBounds(607,365, 300, 90);

        JLabel scoreshadow = new JLabel(String.valueOf(nbpoints));
        scoreshadow.setFont(new Font("Magneto", Font.PLAIN, 80));
        scoreshadow.setForeground(new Color(195+30, 75+30, 67+30, 200));
        scoreshadow.setBounds(608,368, 300, 90);

        // Creation des boutons
        button[0] = new JButton("Rejouer");
        button[0].setBounds(100,575, 150, 50);
        button[0].setBackground(new Color(123,27,91));
        
        button[1] = new JButton("Menu");
        button[1].setBounds(525,575,150,50);
        button[1].setBackground(new Color(123,27,91));

        button[2] = new JButton("Quitter");
        button[2].setBounds(920,575,150,50);
        button[2].setBackground(new Color(123,27,91));

        // Ajout des JButton et des JLabel au JPanelImage
        for (int i = 0; i<3; i++) {
            jpan.add(button[i]);
            button[i].addActionListener(new FinListener(this));
        }
        jpan.add(score);
        jpan.add(scoreshadow);

        this.add(jpan, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }
}