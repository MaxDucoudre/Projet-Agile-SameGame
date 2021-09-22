    /**
    * Interface : BotInterface
    * Interface a implémenter dans vos bot, le bot sera initialisé dans le moteur de jeu et ses méthodes seront lancés dans le moteur de jeu
    * @version 1.0
    * @author Le Conseil des 4
    */
    interface BotInterface {

    	/**
    	* Méthode permettant d'appliquer une API de moteur de jeu au bot 
    	* A faire juste après la création.
    	*/
    	public abstract void setAPI(API interface_moteur_jeu);

        /**
        *Permet de ne jouer qu'un seul coup.
        */
        public void jouerCoup();
      
    	/**
    	* Méthode permettant de lancer la partie d'un bot
    	*/
    	public abstract void startBot();

    }
