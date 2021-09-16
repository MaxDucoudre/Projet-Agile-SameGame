/**
* @version 1.0
* @author Paul Le Corre
*/
public class InterfaceIA {
	public Grille grille;

	public InterfaceIA(Grille grille) {
		this.grille = grille;

	}



	
	public void selectPoint(int l, int c) {

		this.grille.select(l, c);


		try{Thread.sleep(300);} catch(InterruptedException e){}

		this.grille.addPoints();
		this.grille.removeSelected();
		this.grille.checkFall();
		this.grille.checkColumns();
		try{Thread.sleep(100);} catch(InterruptedException e){}

	}

	public void startBot(Bot bot) {
		
		int coordX,coordY;

		while (this.grille.remainingGroups() != 0) {
			coordX = bot.genererCoordX();
			coordY = bot.genererCoordY();

			if(bot.getBotName() != "ame") {
				
				if(bot.verifCase(coordX, coordY) == true) {

					this.selectPoint(coordX, coordY);
				}

			} else {
				if(bot.verifCase(coordX, coordY) == true) {

					if(bot.validateCoord(coordX, coordY) == true) {
						this.selectPoint(coordX, coordY);
					}

				}


			}
		}


		this.grille.modal.locX = this.grille.fenetre.getLocation().x;
		this.grille.modal.locY = this.grille.fenetre.getLocation().y;
		this.grille.fenetre.dispose();
		Fin fin = new Fin(this.grille.modal, this.grille.getPoints());

	}




	public int getRemainingGroups() {
		return this.grille.remainingGroups();
	}

	public  char[][] getTabChar() {
		return this.grille.getTabChar();
	}

	public int getLigne() {
		return this.grille.getLigne();
	}

	public int getColonne() {
		return this.grille.getColonne();
	}
}