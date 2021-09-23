import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Arrays;


public class Variantes extends JFrame{
	public Grille grille;
	private Modele modal;
	private JFrame gameframe;
	private char[][][] listchartab = new char[150][10][15];
	private int[] listpoints = new int[150];

	public Variantes(Grille grille, Modele modal, JFrame componentparent, Bot[] bots) {


		this.grille = grille;
		this.modal = modal;
		this.gameframe = componentparent;

		System.out.println("Ouverture fenêtre gérer la partie");
		this.setLayout(new BorderLayout());

		this.setSize(200, 600);
		this.setTitle("Gérer la partie");
		this.setLocation(this.modal.locX+1200, this.modal.locY+50);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



	    Image image = Toolkit.getDefaultToolkit().getImage("blocs/en_jeu.gif");
       
	    JPanel jpan = new JPanelImage(image);
	    jpan.setPreferredSize(new Dimension(200,600));
	    jpan.setOpaque(false);
		jpan.setLayout(new GridLayout(7,1));

	    Thread thread = new Thread( new AutoRepaint(this));
	    thread.start();
		JButton[] buttons = new JButton[5];



		JButton undoButton = new JButton("Undo");
		buttons[0] = undoButton;
		JButton redoButton = new JButton("Redo");
		buttons[1] = redoButton;
		JButton bot1button = new JButton("Bot Aléatoire");
		buttons[2] = bot1button;
		JButton bot2button = new JButton("Bot Glouton");
		buttons[3] = bot2button;
		JButton bot3button = new JButton("Bot Amélioré");
		buttons[4] = bot3button;


		undoButton.addActionListener(new VariantesListener(buttons, this.grille, this, bots));
		redoButton.addActionListener(new VariantesListener(buttons, this.grille, this, bots));
		bot1button.addActionListener(new VariantesListener(buttons, this.grille, this, bots));
		bot2button.addActionListener(new VariantesListener(buttons, this.grille, this, bots));
		bot3button.addActionListener(new VariantesListener(buttons, this.grille, this, bots));

		for(int i = 0; i<5;i++) {
			buttons[i].setBackground(new Color(123,27,91));

		}

		JLabel labelbot1 = new JLabel("");





		JPanel panelBot1 = new JPanel();
		panelBot1.setOpaque(false);

		panelBot1.setLayout(new GridLayout(2,1));
		panelBot1.add(new JLabel(""));
		panelBot1.add(bot1button);

		JPanel panelBot2 = new JPanel();
		panelBot2.setOpaque(false);

		panelBot2.setLayout(new GridLayout(2,1));
		panelBot2.add(new JLabel(""));
		panelBot2.add(bot2button);

		JPanel panelBot3 = new JPanel();
		panelBot3.setOpaque(false);

		panelBot3.setLayout(new GridLayout(2,1));
		panelBot3.add(new JLabel(""));
		panelBot3.add(bot3button);




		JPanel panel = new JPanel();
		panel.setOpaque(false);

		panel.setLayout(new GridLayout(2,1));
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);

		panel2.setLayout(new GridLayout(1,2));



		panel2.add(undoButton);
		panel2.add(redoButton);
		panel.add(new JLabel("Gerer les coups"));
		panel.add(panel2);


		JPanel voidpanel = new JPanel();
		voidpanel.setOpaque(false);

		jpan.add(panel);
		jpan.add(panelBot1);
		jpan.add(panelBot2);
		jpan.add(panelBot3);


        this.add(jpan, BorderLayout.CENTER);
        this.pack();
		this.setVisible(true);



	}



	public void setPointTurn(int turn, int points) {
		this.listpoints[turn] = points;
	}

	public int getPointTurn(int turn) {
		return this.listpoints[turn];
	}


	public char[][] getGrilleWithTurn(int turn) {

		int ligne = this.grille.getLigne(); 
		int colonne = this.grille.getColonne();

		char[][] charTab = new char[ligne][colonne];

		for(int i = 0; i < ligne; i++) {
			for(int j =0; j < colonne; j++) {
				charTab[i][j] = this.listchartab[turn][i][j];
			}
		}
		return charTab;
	}


	public void setGrilleWithTurn(int turn, char[][] charTab) {
		int ligne = this.grille.getLigne(); 
		int colonne = this.grille.getColonne();

		for(int i = 0; i < ligne; i++) {
			for(int j =0; j < colonne; j++) {
				this.listchartab[turn][i][j] = charTab[i][j];
			}
		}


	}

}
