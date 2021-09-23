import java.util.Random;

public class BotGlouton extends Bot {

	private int counter;

	private boolean[][] selected;

	


	public BotGlouton(String bot_name) {
		super(bot_name);

	}


	public void setBiggestGroupCoord() {
		this.selected = new boolean[this.getLine()][this.getCol()];

		int[][] tab_group = this.allGroupSize();

		for(int i = 0; i < this.getLine(); i++) {
			for(int j = 0; j < this.getCol(); j++) {
				if (tab_group[i][j] == this.getMaxValue(tab_group)) {
					super.coordX = i;
					super.coordY = j;
					this.unselectAll();
					return;
				}
			}	
		}



	}

	public int getMaxValue(int[][] array) {
		int maxValue = array[0][0];
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array[j].length; i++) {
				if (array[j][i] > maxValue) {
					maxValue = array[j][i];
				}
			}
		}
		return maxValue;
	}

	public int[][] allGroupSize() {
		int[][] groupSize = new int[this.getLine()][this.getCol()];
		for(int i = 0; i < this.getLine(); i++) {
			for(int j = 0; j < this.getCol(); j++) {

				if(this.verifCase(i,j) == false ) {
					groupSize[i][j] = 0;
				} else {

					this.findGroupSize(i,j);
					this.counter--;

					groupSize[i][j] = this.counter;
					this.counter = 0;
					this.unselectAll();
				}

			}
		}
		return groupSize;
	}

	public void printAllGroupSize() {
		int[][] groupSize = this.allGroupSize();
		for(int i = 0; i < this.getLine(); i++) {
			for(int j = 0; j < this.getCol(); j++) {
				System.out.print(groupSize[i][j] + "  ");
			}
			System.out.println();
		}

	}


	public boolean findGroupSize(int l, int c) {
			char[][] tab_char = super.interfaceMJ.getGrille();


		if(tab_char[l][c] == ' ') {
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

        public void unselectAll() {
        	for (int l = 0; l<10; l++) {
        		for (int c = 0; c<15; c++) {
        			this.selected[l][c] = false;

        		}
        	}
        }

        public void genererCoord() {
        	this.setBiggestGroupCoord();
        }

 

    }