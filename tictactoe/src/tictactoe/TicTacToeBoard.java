package tictactoe;

public class TicTacToeBoard {
	public static final String X = "X";
	public static final String O = "O";
	public static final String EMPTY = " ";

	private String[][] board; // "X", "O", "EMPTY"

	public TicTacToeBoard() {
		board = new String[3][3];
		
		for (String[] s : board) {
			for (int i = 0; i < s.length; i++) {
				s[i] = EMPTY;
			}
		}
	}
	
	public String toString() {
		String output = " ";
		for (String[] rows: board) {
			for (String space: rows) {
				output = output + space +  "|";
			}
			output = output + "\n";
		}
		return output.substring(0, output.length() - 1);
	}
	
	public static void main(String[] args) {
		TicTacToeBoard t = new TicTacToeBoard();
		System.out.print(t);
	}
	

}
