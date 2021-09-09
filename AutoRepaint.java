import javax.swing.*;
import java.awt.*;

/**
* Cette classe Controlleur permet de repaint la fenêtre en boucle jusqu'à 
* ce qu'elle soit dispose() grâce à la méthode run() pris
* en charge par le thread
* @author Paul LE CORRE & Brice PANIZZI
*/
public class AutoRepaint implements Runnable {
	
	private JFrame fenetre;
	
	/**
	* @param fenetre Contient la fenetre qui doit être actualisée en boucle de manière à faire fonctionner le gif
	*/
	public AutoRepaint(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	
	/**
	* La méthode qui va être executée par le thread :
	* tant que la fenêtre n'est pas dispose() ce qu'on vérifie
	* avec isDisplayable() la fenêtre va être repaint toutes
	* les 10 millisecondes
	*/
	@Override
	public void run() {
		while(this.fenetre.isDisplayable()) {
			this.fenetre.repaint();
			try {Thread.sleep(10);} catch (InterruptedException e) {}
		}
	}
}