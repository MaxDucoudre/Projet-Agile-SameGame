import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Arrays;

/**
* Cette classe est la plus importante car c'est elle qui gere
* tout le jeu
* @author Paul LE CORRE & Brice PANIZZI
*/
public class Grille {
    private int ligne = 10;
    private int colonne = 15;
    private boolean[][] selected = new boolean[ligne][colonne];
    public char[][] tab_char = new char[ligne][colonne];
    public JLabel[][] label = new JLabel[ligne][colonne];

    public JLabel printpoints = new JLabel("0");
    private int nbpoints = 0;
    public JPanelImage jpan;

    public Jeu fenetre;
    public Modele modal;
    
    /**
    * Creation de la grille
    * @param fenetre la fenetre dans laquelle le JLabelImage qui contient la grille devra apparaitre
    */
    public Grille(Jeu fenetre){
        this.modal = fenetre.modal;
        this.fenetre = fenetre;

        // Initialisation des variables pour l'aléatoire
        int max = 2;
        int min = 0;
        int range = max - min + 1;
        Random random = new Random();

        // Creation du JPanelImage
        Image image = Toolkit.getDefaultToolkit().getImage("blocs/en_jeu.gif");
        this.jpan = new JPanelImage(image);
        this.jpan.setPreferredSize(new Dimension(1160, 653));
        this.jpan.setOpaque(false);
        GridLayout grille = new GridLayout(ligne,colonne);
        this.jpan.setLayout(grille);
        
        // Lancement du gif
        Thread thread = new Thread( new AutoRepaint(fenetre));
        thread.start();

        // Création de la grille, avec des couleurs allouées aléatoirement 
        int choix;
        for(int i=0; i<ligne; i++){
            for(int j = 0; j<colonne; j++) {

                choix = (int)(Math.random() * range) + min;

                this.label[i][j] = new JLabel();
                this.label[i][j].setBackground(new Color(255, 0, 189)); 
                this.label[i][j].setOpaque(false);
                this.label[i][j].addMouseListener(new GrilleListener(this));
                this.jpan.add(this.label[i][j]);

                // Condition couleur rouge
                if (choix == 0) {
                    this.tab_char[i][j] = 'R';
                }
                // Condition couleur vert
                if (choix == 1) {
                    this.tab_char[i][j] = 'V';
                }

                // Condition couleur bleu
                if (choix == 2) {
                    this.tab_char[i][j] = 'B';
                }
            }
        }

        // Si une grille doit être importée, remplacer tab_char par cette dernière
        if (this.modal.importGrid) {
            this.tab_char = this.modal.getGrid();
        }

        // Remplissage du tableau selected
        for (int i = 0; i<10;i++) {
            Arrays.fill(this.selected[i], false);
        }

        this.refresh();
    }

    
    public void printGridBool() {
    	for(int i = 0; i <ligne;i++) {
    		for(int j = 0; j<colonne;j++) {
    			if(selected[i][j] == false) {
    		System.out.print("F");

    			} else {
    		System.out.print("T");

    			}

    		}
			System.out.println("");

    	} 
			System.out.println("");

    }

   public void printGrid() {
        for(int i = 0; i <ligne;i++) {
            for(int j = 0; j<colonne;j++) {
            System.out.print(tab_char[i][j]);

            }
            System.out.println("");

        } 
            System.out.println("");

    }

    /**
    * Permet de mettre les images sur chaque case
    * en fonction du tableau de caractères tab_char
    */
    public void refresh() {
        ImageIcon rouge = new ImageIcon("blocs/rouge.png");
        ImageIcon vert = new ImageIcon("blocs/vert.png");
        ImageIcon bleu = new ImageIcon("blocs/bleu.png");
        for(int i=0; i<ligne; i++){
            for(int j = 0; j<colonne; j++) {
                //Condition couleur rouge
                if (this.tab_char[i][j] == 'R') {
                    this.label[i][j].setIcon(rouge);
                }
                //Condition couleur verte
                else if (this.tab_char[i][j] == 'V') {
                    this.label[i][j].setIcon(vert);
                }

                //Condition couleur bleu
                else if (this.tab_char[i][j] == 'B') {
                    this.label[i][j].setIcon(bleu);
                }
                else if (this.tab_char[i][j] == ' ') {
                    this.label[i][j].setIcon(null);
                }
            }
        }
    }

    /**
    * Permet de selectionner le bloc en question si il n'est pas vide et que son groupe fait plus de 1, puis selectionne ensuite les blocs adjacents
    * @return true si la case en question n'est pas vide et fait partie d'un groupe sinon return false
    */
    public boolean select(int l, int c) {
        int counter = 0;
        if(this.tab_char[l][c] == ' ') {
            return false; // Dans le cas ou la case est vide
        }
        // HAUT
        if (l>0) {
            if (!this.selected[l-1][c] && this.tab_char[l][c] == this.tab_char[l-1][c]) {
                selected[l-1][c] = true;
                counter++;
                selectAdjacents(l-1, c);
            }
        }

        // BAS
        if (l<9) {
            if (!this.selected[l+1][c] && this.tab_char[l][c] == this.tab_char[l+1][c]) {
                selected[l+1][c] = true;
                counter++;
                selectAdjacents(l+1, c);
            }
        }

        // GAUCHE
        if (c>0) {
            if (!this.selected[l][c-1] && this.tab_char[l][c] == this.tab_char[l][c-1]) {
                selected[l][c-1] = true;
                counter++;
                selectAdjacents(l, c-1);
            }
        }

        // DROITE
        if (c<14) {
            if (!this.selected[l][c+1] && this.tab_char[l][c] == this.tab_char[l][c+1]) {
                selected[l][c+1] = true;
                counter++;
                selectAdjacents(l, c+1);
            }
        }
        if (counter>0) {
            this.label[l][c].setOpaque(true);
            this.fenetre.repaint();
            return true; // Dans le cas ou le groupe fait plus de 1 bloc
        } else {
            return false; // Dans le cas ou le groupe fait 1 bloc
        }
    }


    /**
    * Méthode récursive permetant de selectionner tous les blocs du même groupe que 
    * celui en coordonnées (l,c)
    */
    public void selectAdjacents(int l, int c) {
        this.label[l][c].setOpaque(true);
        this.fenetre.repaint();
        // HAUT
        if (l>0) {
            if (!this.selected[l-1][c] && this.tab_char[l][c] == this.tab_char[l-1][c]) {
                selected[l-1][c] = true;
                selectAdjacents(l-1, c);
            }
        }
        // BAS
        if (l<9) {
            if (!this.selected[l+1][c] && this.tab_char[l][c] == this.tab_char[l+1][c]) {
                selected[l+1][c] = true;
                selectAdjacents(l+1, c);
            }
        }
        // GAUCHE
        if (c>0) {
            if (!this.selected[l][c-1] && this.tab_char[l][c] == this.tab_char[l][c-1]) {
                selected[l][c-1] = true;
                selectAdjacents(l, c-1);
            }
        }
        // DROITE
        if (c<14) {
            if (!this.selected[l][c+1] && this.tab_char[l][c] == this.tab_char[l][c+1]) {
                selected[l][c+1] = true;
                selectAdjacents(l, c+1);
            }
        }
    }

    /**
    * Permet de déselectionner tous les blocs
    */
    public void unselectAll() {
        for (int l = 0; l<10; l++) {
            for (int c = 0; c<15; c++) {
                this.selected[l][c] = false;
                this.label[l][c].setOpaque(false);
                this.fenetre.repaint();
            }
        }
    }

    /**
    * Compte le nombre de points que vont rapporter le groupe sélectionné
    * l'ajoute au score du joueur et actualise le JLabel affichant le score
    */
    public void addPoints() {
        int bsel = 0;
        for (int l = 0; l<10; l++) {
            for (int c = 0; c<15; c++) {
                if (this.selected[l][c]) {
                    bsel++;
                }
            }
        }
        if (bsel>1) {
            this.nbpoints += (bsel-2)*(bsel-2);
            this.printpoints.setText(Integer.toString(this.nbpoints));
        }
    }

    /**
    * @return le score du joueur
    */
    public int getPoints() {
        return nbpoints;
    }

    /**
    * Permet de supprimer les blocs sélectionnés
    */
    public void removeSelected() {
        for (int l = 0; l<10; l++) {
            for (int c = 0; c<15; c++) {
                if (this.selected[l][c]) {
                    this.tab_char[l][c] = ' ';
                }
            }
        }
        this.unselectAll();
        this.refresh();
    }

    /**
    * Cette méthode déplace tous les blocs d'une même colonne
    * à la place de tous les blocs d'une autre colonne
    * @param from colonne qui va être déplacée
    * @param to colonne qui va être remplacée par from
    */
    public void moveColumn(int from, int to) {
        for (int l = 0; l<10; l++) {
            tab_char[l][to] = tab_char[l][from];
            tab_char[l][from] = ' ';
        }
    }

    /**
    * Cette méthode vérifie si des colonnes sont vides
    * et les remplace grâce à moveColumn() par des colonnes
    * non vides à leur droite
    * 
    * Plus précisément l'algorithme va chercher la première colonne vide
    * puis si il en trouve une il va chercher à la suite la prochaine colonne
    * non vide et si il y en a une remplacer les deux et incrémenter la variable counter,
    * tant qu'au moins une modification de colonne sera à faire la méthode va s'appeller
    * elle même récursivement
    */
    public void checkColumns() {
        int blocs;
        int counter = 0;
        int emptyColumn = 0;
        int p = 1;
        for (int c=0; c<15; c++) {
            blocs = 0;
            for (int l = 0; l<10; l++) {
                if (this.tab_char[l][c] != ' ') {
                    blocs++;
                }
            }
            if (blocs == 0 && p==1) {
                p = 0;
                emptyColumn = c;
            } else if (blocs != 0 && p==0) {
                moveColumn(c, emptyColumn);
                counter++;
                p = 1;
            }
        }
        if (counter>0) {
            this.checkColumns();
        }
        this.refresh();
    }

    /**
    * Cette méthode va rechercher récursivement si il existe
    * blocs vide en dessous de blocs non vides puis les remplacer,
    * et faire ca en boucle jusqu'a e qu'il n'y ai plus de modification à faire
    */
    public void checkFall() {
        int counter = 0;
        for (int l = 0; l<9; l++) {
            for (int c = 0; c<15; c++) {
                if (this.tab_char[l][c] != ' ' && this.tab_char[l+1][c] == ' ') {
                    this.tab_char[l+1][c] = this.tab_char[l][c];
                    this.tab_char[l][c] = ' ';
                    counter++;
                }
            }
        }
        if (counter>0) {
            this.checkFall();
        }
        this.refresh();
    }

    /**
    * Cette méthode renvoie le nombre de groupe non vides
    * dans la grille qui font plus de 1 bloc, elle permet
    * de savoir si la partie est terminée ou non
    * @return nombre de groupe restants
    */
    public int remainingGroups() {
        int nbgroups = 0;
        for (int l = 0; l<10; l++) {
            for (int c = 0; c<15; c++) {
                if (!this.selected[l][c]) {
                    if (this.select(l, c)) {
                        nbgroups++;
                    }
                }
            }
        }
        this.unselectAll();
        return nbgroups;
    }
}