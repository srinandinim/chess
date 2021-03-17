package chess;

import java.util.Scanner;

import pieces.*;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board();

		boolean white_move = true;
		char current_color = 'w';
		boolean done = false;
		boolean draw = false;

		Piece white_king = board.getPiece('e', 1);
		Piece black_king = board.getPiece('e', 8);

		Scanner scanner = new Scanner(System.in);

		while (!done) {
			board.printBoard();

			if (white_move) {
				System.out.print("White's move: ");
				current_color = 'w';
			} else {
				System.out.print("Black's move: ");
				current_color = 'b';
			}

			String input = scanner.nextLine();

			while (!containsValidArguments(board, current_color, input, draw)) {
				System.out.println("Illegal move, try again");
				if (white_move) {
					System.out.print("White's move: ");
				} else {
					System.out.print("Black's move: ");
				}
				input = scanner.nextLine();
			}

			if (input.equals("resign")) {
				if (white_move)
					System.out.println("Black wins");
				else
					System.out.println("White wins");

				done = true;
				break;
			}

			if (draw) {
				if (input.equals("draw")) {
					System.out.println("draw"); // TODO: check if we need to actually print draw or just call it day
					done = true;
					break;
				} else {
					draw = false;
				}
			}

			if (input.contains("draw?")) {
				draw = true;
			}

			Piece currentPiece = board.getPiece(input.split(" ")[0].charAt(0), (int) input.split(" ")[0].charAt(1) - '0');
			currentPiece.move(board, input.split(" ")[1].charAt(0), (int) input.split(" ")[1].charAt(1) - '0');

			white_move = !white_move;
			System.out.println();
		}

		scanner.close();
	}

	private static boolean containsValidArguments(Board board, char color, String input, boolean draw) {
		String[] parts = input.split(" ");
		if (parts.length == 1) {
			if (parts[0].equals("resign"))
				return true;
			if (parts[0].equals("draw") && draw)
				return true;
			return false;
		}

		if (parts.length < 2 || parts.length > 3)
			return false;

		if (parts[0].length() != 2 || parts[1].length() != 2)
			return false;

		if (!board.inBounds(parts[0].charAt(0), (int) parts[0].charAt(1) - '0')
				|| !board.inBounds(parts[1].charAt(0), (int) parts[1].charAt(1) - '0'))
			return false;

		Piece currentPiece = board.getPiece(parts[0].charAt(0), (int) parts[0].charAt(1) - '0');
		if (currentPiece == null || currentPiece.getColor() != color)
			return false;

		Piece newLocation = board.getPiece(parts[1].charAt(0), (int) parts[1].charAt(1) - '0');
		if (newLocation != null && newLocation.getColor() == color)
			return false;

		if (!currentPiece.canMove(board, parts[1].charAt(0), (int) parts[1].charAt(1) - '0'))
			return false;

		return true;
	}

	public static boolean causesCheck(Board board, char color, char col, int row) { 

		for (int i = col + 1; i <= 'h'; i++) {
			if (board.getPiece((char) i, row) != null) {
				Piece obj = board.getPiece((char) i, row);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = col - 1; i >= 'a'; i--) { 
			if (board.getPiece((char) i, row) != null) {
				Piece obj = board.getPiece((char) i, row);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = row + 1; i <= 8; i++) {
			if (board.getPiece(col, i) != null) {
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = row - 1; i >= 0; i--) {
			if (board.getPiece(col, i) != null) {
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}

		int colVals[] = {1,-1,1,-1,2,2,-2,-2}; // Knight moves
		int rowVals[] = {2,2,-2,-2,1,-1,1,-1};

		for (int i=0; i<rowVals.length; i++){ 
			if (board.getPiece((char) (col + colVals[i]), row + rowVals[i]) != null) {
				Piece obj = board.getPiece((char) (col + colVals[i]), row + rowVals[i]);
				if (obj.getColor() != color && obj instanceof Knight)
					return true;
			}
		}

		for (int i = col - 1, j = row + 1; i >= 'a' && j <= 8; i--, j++) { // upper left diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = col + 1, j = row + 1; i <= 'h' && j <= 8; i++, j++) { // upper right diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = col + 1, j = row - 1; i <= 'h' && j >= 1; i++, j--) { // lower right diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i = col - 1, j = row - 1; i >= 'a' && j >= 1; i--, j--) { // lower left diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}

		if (color == 'w') {
			if (board.getPiece((char) (col + 1), row + 1).getColor() == 'b'
					&& board.getPiece((char) (col + 1), row + 1) instanceof Pawn)
				return true;
			if (board.getPiece((char) (col - 1), row + 1).getColor() == 'b'
					&& board.getPiece((char) (col - 1), row + 1) instanceof Pawn)
				return true;
		}
		if (color == 'b') {
			if (board.getPiece((char) (col + 1), row - 1).getColor() == 'w'
					&& board.getPiece((char) (col + 1), row - 1) instanceof Pawn)
				return true;
			if (board.getPiece((char) (col - 1), row - 1).getColor() == 'w'
					&& board.getPiece((char) (col - 1), row - 1) instanceof Pawn)
				return true;
		}

		for (int i = col - 1; i <= col + 1; i++) {
			if (board.getPiece((char) i, row + 1) != null) { //top row
				Piece obj = board.getPiece((char) i, row + 1);
				if (obj.getColor() != color && obj instanceof King)
					return true;
			}
			if (board.getPiece((char) i, row - 1) != null) { // bottom row
				Piece obj = board.getPiece((char) i, row - 1);
				if (obj.getColor() != color && obj instanceof King)
					return true;
			}
		}
		for (int i = col - 1; i <= col + 1; i++){ //left and right space
			if (i != col && board.getPiece((char) (i), row) != null) {
				Piece obj = board.getPiece((char) (i), row);
				if (obj.getColor() != color && obj instanceof King)
					return true;
			}
		}

		return false;

	}

}
