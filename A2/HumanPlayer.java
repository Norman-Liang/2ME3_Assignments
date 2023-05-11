import java.util.Scanner;

public class HumanPlayer extends Player{
	
	Scanner input = new Scanner(System.in);
	
	public HumanPlayer (char symbol, Board board, String name) {
		super(symbol,board,name);
	}
	
	public void makeMove(Board board) {
		System.out.print(super.name + ", please input your move: ");
		int pos;
		do {
			pos = input.nextInt();
			if (board.fullCol(pos)) {
				System.out.print("Please choose a non-full column: ");
			}
		} while (board.fullCol(pos));
		
		board.addToken(symbol, pos);
	}
	
}
