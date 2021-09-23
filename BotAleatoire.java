import java.util.Random;

public class BotAleatoire extends Bot {

	char[][] tab_char;
	int counter = 0;
	private boolean[][] selected;


	public BotAleatoire(String bot_name) {
		super(bot_name);

	}

	public void genererCoord() {
		this.selected = new boolean[this.getLine()][this.getCol()];
		this.tab_char = this.interfaceMJ.getGrille();
		int min = 0;
		int max;
		Random random = new Random();
		int coordx_temp,coordy_temp;


		do{
			max = this.getLine()-1;
			coordx_temp = (int)Math.floor(Math.random()*(max-min+1)+min);


			max = this.getCol()-1;
			coordy_temp = (int)Math.floor(Math.random()*(max-min+1)+min);
			this.findGroupSize(coordx_temp,coordy_temp);

		} while(tab_char[coordx_temp][coordy_temp] == ' ' || this.counter <= 1);
			
			super.coordX = coordx_temp;
			super.coordY = coordy_temp;
	}



	public boolean findGroupSize(int l, int c) {


		if(this.tab_char[l][c] == ' ') {
            return false; // Dans le cas ou la case est vide
        }
        // HAUT
        if (l>0) {
        	if (!this.selected[l-1][c] && tab_char[l][c] == tab_char[l-1][c]) {
        		selected[l-1][c] = true;
        		findGroupSize(l-1, c);
        	}
        }

        // BAS
        if (l<9) {
        	if (!this.selected[l+1][c] && tab_char[l][c] == tab_char[l+1][c]) {
        		selected[l+1][c] = true;
        		findGroupSize(l+1, c);
        	}
        }

        // GAUCHE
        if (c>0) {
        	if (!this.selected[l][c-1] && tab_char[l][c] == tab_char[l][c-1]) {
        		selected[l][c-1] = true;
        		findGroupSize(l, c-1);
        	}
        }

        // DROITE
        if (c<14) {
        	if (!this.selected[l][c+1] && tab_char[l][c] == tab_char[l][c+1]) {
        		selected[l][c+1] = true;
        		findGroupSize(l, c+1);
        	}
        }
        this.counter++;
            return true; // Dans le cas ou le groupe fait plus de 1 bloc

        }


    }