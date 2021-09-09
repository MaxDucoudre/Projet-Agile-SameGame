import javax.swing.*;
import java.awt.*;

/**
* Cette classe crée un JPanel avec un fond
* @author Paul LE CORRE & Brice PANIZZI
*/
public class JPanelImage extends JPanel {
	private Image image;

	/**
	* @param image Image qui va servir de fond au JPanel
	*/
	public JPanelImage(Image image){
		this.image = image;
	}
	 
	/**
	* Affiche l'image
	* {@inheritdoc}
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	 	g.drawImage(this.image, 0, 0, this);
	}
}