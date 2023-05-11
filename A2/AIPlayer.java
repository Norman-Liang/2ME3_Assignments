import java.util.Random;

public class AIPlayer extends Player{
	
	Random rng = new Random();
	
	public AIPlayer (char symbol, Board board, String name) {
		super(symbol,board,name);
	}
	
	public void makeMove(Board board) {
		int pos = board.winOrDefPos(symbol, rng.nextInt(7));
		board.addToken(symbol, pos+1);
	}
}
