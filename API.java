    /**
    * Interface : API
    * Interface a implémenter dans le moteur du jeu permettant aux bots de fonctionner
    * @version 1.0
    * @author Le Conseil des 5
    * 
    */
    public interface API {

    	/**
    	* Méthode permettant de récupérer la grille de caractère représentant l'état de la partie.
    	* Grille composée de 'R', 'V', 'B' et ' ' si vide.
    	* grille[x][y] avec x la colonne et y la ligne.
    	* @return tableau bidimentionnel de caractère représentant l'état de la grille 
    	*/
    	char[][] getGrille();

    	/**
    	* Méthode permettant de séléctionner un groupe de pion en fonction des coordonées X et Y données.
    	* Avec x la colonne et y la ligne.
    	* @param x entier représentant la position X du pion séléctionné dans la grille 
    	* @param y entier représentant la position Y du pion séléctionné dans la grille 
    	*/
    	void selectGroup(int x, int y);

    	/**
    	* Méthode permettant de détruire le groupe de pions séléctionné.
    	* Nécéssite d'avoir utilisé la méthode selectGroup() avant.
    	* Et d'ajouter les points aux score.
    	*/
    	void destroyGroup();

    	/**
    	* Permet d'obtenir le score généré par le groupe séléctionné
    	* @return le score obtenu par un groupe séléctionné.
    	*/
    	int getScore();

    	/**
    	* @return le score actuel de la partie en cours
    	*/
    	int getTotalScore();
      
     
        /**
        * Permet de savoir si la partie est finie.
        * @return true si partie finie, false si partie jouable.
        */
        public boolean getFini();

        /**
        * Déselectionner tous les blocs.
        */
        public void unselectAll();
    }
