public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	char [][] board;
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve
	 * what the assignment is asking, you'll have to
	 * 
	 */
	
	public Board() {
		this.board = new char [NUM_OF_ROW][NUM_OF_COLUMNS]; 
	}
	
	public void printBoard() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			System.out.print("|");
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.board[i][j] == '\u0000' && i == NUM_OF_ROW-1)
					System.out.print("_|");
				else 
					System.out.print(this.board[i][j] + "|");
			}
			System.out.print("\n");
		}
	}
	
	public boolean rowCheck() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS-3; j++) {
				if (this.board[i][j] == this.board[i][j+1] && this.board[i][j] != '\u0000') {
					if (this.board[i][j] == this.board[i][j+2]) {
						if (this.board[i][j] == this.board[i][j+3]) 
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean columnCheck() {
		for (int i = 0; i < NUM_OF_ROW-3; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.board[i][j] == this.board[i+1][j] && this.board[i][j] != '\u0000') {
					if (this.board[i][j] == this.board[i+2][j]) {
						if (this.board[i][j] == this.board[i+3][j]) 
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean diagCheck() {
		for (int i = NUM_OF_ROW-1; i > 2; i--) {
			for (int j = 0; j < NUM_OF_COLUMNS-3; j++) {
				if (this.board[i][j] == this.board[i-1][j+1] && this.board[i][j] != '\u0000') {
					if (this.board[i][j] == this.board[i-2][j+2]) {
						if (this.board[i][j] == this.board[i-3][j+3]) 
							return true;
					}
				}
			}
		}
		
		for (int i = 0; i < NUM_OF_ROW-3; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS-3; j++) {
				if (this.board[i][j] == this.board[i+1][j+1] && this.board[i][j] != '\u0000') {
					if (this.board[i][j] == this.board[i+2][j+2]) {
						if (this.board[i][j] == this.board[i+3][j+3]) 
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean containsWin() {
		
		if (this.rowCheck()) return true;
		if (this.columnCheck()) return true;
		if (this.diagCheck()) return true;		
		return false;
	}
	
	public boolean isTie() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.board[i][j] == '\u0000') 
					return false;				
			}
		}
		if (this.containsWin()) return true;
		else return false;
	}
	
	public void reset() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				this.board[i][j] = '\u0000';
			}
		}
	}
	
	public void addToken(char symbol, int num) {
		for (int i = NUM_OF_ROW-1; i >= 0; i--) {
			if (this.board[i][num-1] == '\u0000') {
				this.board[i][num-1] = symbol;
				break;
			}
		}		
	}

	public boolean fullCol (int num) {
		if (this.board[0][num-1] != '\u0000')
			return true;
		return false;
	}
	
	public boolean placeable(int drop, int row) {
		
		if (row == NUM_OF_ROW-1) {
			if (this.board[row][drop] == '\u0000') return true;
		}
		if (this.board[row][drop] == '\u0000' && this.board[row+1][drop] != '\u0000')
				return true;
		return false;
	}
	
	public int winPos(char symbol) {
		int firstSection;
		int secondSection;
		int gap;
		boolean gapExist;
		//ROW 
		for (int i = 0; i < NUM_OF_ROW; i++) {
			firstSection = 0;
			secondSection = 0;
			gap = 0;
			gapExist = false;
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (board[i][j] == symbol) {
					if (gapExist == false) firstSection += 1; 
					else secondSection += 1;
				} else if (board[i][j] == '\u0000') {
					if (gapExist == false) {
						gapExist = true;
						gap = j;
					} else {
						gapExist = true;
						firstSection = secondSection;
						secondSection = 0;
						gap = j;
					}
				} else {
					gapExist = false;
					firstSection = 0;
					secondSection = 0;
				}
				if (firstSection + secondSection == 3 && gapExist) {
					if (placeable(gap, i)) {
						return gap;
					}
				}
			}
		}

		//COLUMN
		for (int i = 1; i < NUM_OF_ROW-2; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.board[i][j] == symbol) {
					if (this.board[i][j] == this.board[i+1][j]) {
						if (this.board[i][j] == this.board[i+2][j]) {
							if (this.placeable(j, i-1)) return j;
						}
					}
				}
			}
		}
		
		//UP DIAG
		for (int i = 3; i < NUM_OF_ROW; i++) {
			firstSection = 0;
			secondSection = 0;
			gap = 0;
			gapExist = false;
			for (int j = 0; j <= i; j++) {
				if (board[i-j][j] == symbol) {
					if (gapExist == false) firstSection += 1; 
					else secondSection += 1;
				} else if (board[i-j][j] == '\u0000') {
					if (gapExist == false) {
						gapExist = true;
						gap = j;
					} else {
						gapExist = true;
						firstSection = secondSection;
						secondSection = 0;
						gap = j;
					}
				} else {
					gapExist = false;
					firstSection = 0;
					secondSection = 0;
				}
				if (firstSection + secondSection == 3) {
					if (placeable(gap, i-gap)) {
						return gap;
					}
				}
				
			}
		}
		
		for (int i = 1; i < NUM_OF_COLUMNS; i++) {
			firstSection = 0;
			secondSection = 0;
			int gapX = 0;
			int gapY = 0;
			gapExist = false;
			int count = 0;
			for (int j = NUM_OF_ROW-1; j >= i-1; j--) {
				if (board[j][i+count] == symbol) {
					if (gapExist == false) firstSection += 1; 
					else secondSection += 1;
				} else if (board[j][i+count] == '\u0000') {
					if (gapExist == false) {
						gapExist = true;
						gapX = i+count;
						gapY = j;
					} else {
						gapExist = true;
						firstSection = secondSection;
						secondSection = 0;
						gapX = i+count;
						gapY = j;
					}
				} else {
					gapExist = false;
					firstSection = 0;
					secondSection = 0;
				}
				if (firstSection + secondSection == 3 && gapExist) {
					if (placeable(gapX, gapY)) {
						return gapX;
					}
				}
				count++;
			}
		}
		
		//DOWN DIAG
		for (int i = 0; i < NUM_OF_ROW; i++) {
			firstSection = 0;
			secondSection = 0;
			int gapX = 0;
			int gapY = 0;
			gapExist = false;
			int count = 0;
			for (int j = 0; j < NUM_OF_ROW-i; j++) {
				if (board[i+count][j] == symbol) {
					if (gapExist == false) firstSection += 1; 
					else secondSection += 1;
				} else if (board[i+count][j] == '\u0000') {
					if (gapExist == false) {
						gapExist = true;
						gapX = j;
						gapY = i+count;
					} else {
						gapExist = true;
						firstSection = secondSection;
						secondSection = 0;
						gapX = j;
						gapY = i+count;
					}
				} else {
					gapExist = false;
					firstSection = 0;
					secondSection = 0;
				}
				if (firstSection + secondSection == 3) {
					if (placeable(gapX, gapY)) {
						return gapX;
					}
				}
				count++;
			}
		}
		
		for (int i = 1; i < NUM_OF_COLUMNS; i++) {
			firstSection = 0;
			secondSection = 0;
			int gapX = 0;
			int gapY = 0;
			gapExist = false;
			int count = 0;
			for (int j = 0; j <= NUM_OF_ROW-i; j++) {
				if (board[j][i+count] == symbol) {
					if (gapExist == false) firstSection += 1; 
					else secondSection += 1;
				} else if (board[j][i+count] == '\u0000') {
					if (gapExist == false) {
						gapExist = true;
						gapX = i+count;
						gapY = j;
					} else {
						gapExist = true;
						firstSection = secondSection;
						secondSection = 0;
						gapX = i+count;
						gapY = j;
					}
				} else {
					gapExist = false;
					firstSection = 0;
					secondSection = 0;
				}
				if (firstSection + secondSection == 3 && gapExist) {
					if (placeable(gapX, gapY)) {
						return gapX;
					}
				}
				count++;
			}
		}
		
		return NUM_OF_COLUMNS+1;
	}

	public char oppToken(char symbol) {

		char opp = '\u0000';
		
		back:
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.board[i][j] != symbol && this.board[i][j] != opp) {
					opp = this.board[i][j];
					break back;
				}
			}
		}

		return opp;	
	}
	
	public int winOrDefPos(char symbol, int rng) {
		
		char opp = this.oppToken(symbol);
		
		if (this.winPos(symbol) != NUM_OF_COLUMNS+1) {
			return this.winPos(symbol);
		}
		
		if (opp == '\u0000') {
			return rng;
		}
		if (this.winPos(opp) != NUM_OF_COLUMNS+1) {
			return this.winPos(opp);
		}

		return rng;
	}
	
}
