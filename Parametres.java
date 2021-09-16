/**
* @author Paul LE CORRE & Brice PANIZZI
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Parametres extends JFrame{

    JButton[] button = new JButton[8];
    Modele modal;
    JPanelImage jpan;

    public Parametres(Modele modal) {
        this.modal = modal;
        this.setTitle("Same Game");
        this.setLocation(this.modal.locX, this.modal.locY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = Toolkit.getDefaultToolkit().getImage("blocs/parametres.gif");

        this.jpan = new JPanelImage(image);
        this.jpan.setPreferredSize(new Dimension(1160, 653));
        this.jpan.setOpaque(false);
        this.jpan.setLayout(null);
        
        Thread thread = new Thread( new AutoRepaint(this));

        thread.start();

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



        button[4] = new JButton("Bot Aléatoire");
        button[4].setBounds(375,450,150,50);
        button[4].setBackground(new Color(123,27,91));

        button[5] = new JButton("Bot Glouton");
        button[5].setBounds(600,450,150,50);
        button[5].setBackground(new Color(123,27,91));

        button[6] = new JButton("Bot Ameliorer");
        button[6].setBounds(800,450,150,50);
        button[6].setBackground(new Color(123,27,91));

        button[7] = new JButton("Sans Bot");
        button[7].setBounds(175,450,150,50);
        button[7].setBackground(Color.YELLOW);



        for (int i = 0; i<8; i++) {
            jpan.add(button[i]);
            button[i].addActionListener(new ParamListener(this, this.button));
        }
        
        this.add(jpan);

        this.pack();
        this.setVisible(true );
    }

}