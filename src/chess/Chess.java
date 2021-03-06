package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pieces.*;

/** 
 * Contains overarching functionality, including that for gameplay
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Chess {

	/** 
	 * Main method
	 * @param args Command line argument
	 */
	public static void main(String[] args) {
		Board board = new Board();

		boolean white_move = true;
		boolean draw = false;

		Piece white_king = board.getPiece('e', 1);
		Piece black_king = board.getPiece('e', 8);
		Piece current_king = white_king;

		Piece current_piece = null;
		Piece previous_piece = null;

		Scanner scanner = new Scanner(System.in);

		while (true) {
			board.printBoard();

			if (white_move) {
				System.out.print("White's move: ");
				current_king = white_king;
			} else {
				System.out.print("Black's move: ");
				current_king = black_king;
			}

			String input = scanner.nextLine();

			while (!containsValidArguments(board, current_king, input, draw)) {
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

				break;
			}

			if (draw) {
				System.out.println("draw");
				break;
			}

			if (input.contains("draw?")) {
				draw = true;
			}

			current_piece = board.getPiece(input.split(" ")[0].charAt(0), (int) input.split(" ")[0].charAt(1) - '0');
			current_piece.move(board, input.split(" ")[1].charAt(0), (int) input.split(" ")[1].charAt(1) - '0');

			if (current_piece instanceof Pawn) {
				if ((white_move && current_piece.getRow() == 8) || (!white_move && current_piece.getRow() == 1)) {
					if (input.split(" ").length > 2 && input.split(" ")[2].equals("R"))
						board.setPiece(
								new Rook(current_piece.getColor(), current_piece.getCol(), current_piece.getRow()));
					else if (input.split(" ").length > 2 && input.split(" ")[2].equals("N"))
						board.setPiece(
								new Knight(current_piece.getColor(), current_piece.getCol(), current_piece.getRow()));
					else if (input.split(" ").length > 2 && input.split(" ")[2].equals("B"))
						board.setPiece(
								new Bishop(current_piece.getColor(), current_piece.getCol(), current_piece.getRow()));
					else
						board.setPiece(
								new Queen(current_piece.getColor(), current_piece.getCol(), current_piece.getRow()));
				}
			}

			Pair checkmatePair = causesCheck(board, white_move ? black_king.getColor() : white_king.getColor(),
					white_move ? black_king.getCol() : white_king.getCol(),
					white_move ? black_king.getRow() : white_king.getRow()); // Spicy Spicy

			if (checkmatePair.getBool()) {
				if (isCheckmate(board, checkmatePair.getPiece(), white_move ? black_king : white_king)) {
					System.out.println("Checkmate");
					System.out.println(white_move ? "White wins" : "Black wins");
					break;
				} else
					System.out.println("Check");
			}

			if (previous_piece instanceof Pawn) {
				((Pawn) previous_piece).setEnpassantable(false);
			}
			previous_piece = current_piece;

			white_move = !white_move;
			System.out.println();
		}

		scanner.close();
	}

	
	/** 
	 * Checks whether the user's desired move contains valid arguments and is valid
	 * @param board Current representation of the board
	 * @param current_king King of the current side
	 * @param input User inputted string that represents the move
	 * @param draw Whether opponent requested a draw
	 * @return boolean - true if desired move is valid, false otherwise
	 */
	private static boolean containsValidArguments(Board board, Piece current_king, String input, boolean draw) {
		char color = current_king.getColor();
		String[] parts = input.split(" ");

		if (draw && !input.equals("draw"))
			return false;

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

		char currentPieceCol = currentPiece.getCol();
		int currentPieceRow = currentPiece.getRow();

		currentPiece.move(board, parts[1].charAt(0), (int) parts[1].charAt(1) - '0');

		boolean inCheck = false;
		if (currentPiece instanceof King)
			inCheck = causesCheck(board, color, parts[1].charAt(0), (int) parts[1].charAt(1) - '0').getBool();
		else
			inCheck = causesCheck(board, color, current_king.getCol(), current_king.getRow()).getBool();

		currentPiece.move(board, currentPieceCol, currentPieceRow);
		if (newLocation != null)
			board.setPiece(newLocation);

		if (inCheck) {
			if (currentPiece instanceof Pawn)
				((Pawn) currentPiece).setEnpassant(false);
			return false;
		}

		return true;
	}

	
	/** 
	 * Checks whether a King at a specified location would be in check
	 * @param board Current representation of the board
	 * @param color Color of the King
	 * @param col Character representing the column of the King's location
	 * @param row Row number of the King's location
	 * @return Pair - Contains if the King is in check and if it is, an opposing piece causing it
	 */
	public static Pair causesCheck(Board board, char color, char col, int row) {
		// checks if a rook/queen can kill a piece at the given location
		for (int i = col + 1; i <= 'h'; i++) {
			if (board.getPiece((char) i, row) != null) {
				Piece obj = board.getPiece((char) i, row);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = col - 1; i >= 'a'; i--) {
			if (board.getPiece((char) i, row) != null) {
				Piece obj = board.getPiece((char) i, row);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = row + 1; i <= 8; i++) {
			if (board.getPiece(col, i) != null) {
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = row - 1; i >= 0; i--) {
			if (board.getPiece(col, i) != null) {
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != color && (obj instanceof Rook || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}

		// checks if a knight can kill a piece at the given location
		int colVals[] = { 1, -1, 1, -1, 2, 2, -2, -2 };
		int rowVals[] = { 2, 2, -2, -2, 1, -1, 1, -1 };

		for (int i = 0; i < rowVals.length; i++) {
			if (board.getPiece((char) (col + colVals[i]), row + rowVals[i]) != null) {
				Piece obj = board.getPiece((char) (col + colVals[i]), row + rowVals[i]);
				if (obj.getColor() != color && obj instanceof Knight)
					return new Pair(true, obj);
			}
		}

		// checks if a bishop/queen can kill a piece at the given location
		for (int i = col - 1, j = row + 1; i >= 'a' && j <= 8; i--, j++) { // upper left diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = col + 1, j = row + 1; i <= 'h' && j <= 8; i++, j++) { // upper right diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = col + 1, j = row - 1; i <= 'h' && j >= 1; i++, j--) { // lower right diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}
		for (int i = col - 1, j = row - 1; i >= 'a' && j >= 1; i--, j--) { // lower left diagonal
			if (board.getPiece((char) i, j) != null) {
				Piece obj = board.getPiece((char) i, j);
				if (obj.getColor() != color && (obj instanceof Bishop || obj instanceof Queen))
					return new Pair(true, obj);
				break;
			}
		}

		// checks if a pawn can kill a piece at the given location
		int pawnRow = row + 1;
		if (color == 'b')
			pawnRow = row - 1;

		if (board.getPiece((char) (col + 1), pawnRow) != null
				&& board.getPiece((char) (col + 1), pawnRow).getColor() != color
				&& board.getPiece((char) (col + 1), pawnRow) instanceof Pawn)
			return new Pair(true, board.getPiece((char) (col + 1), pawnRow));
		if (board.getPiece((char) (col - 1), pawnRow) != null
				&& board.getPiece((char) (col - 1), pawnRow).getColor() != color
				&& board.getPiece((char) (col - 1), pawnRow) instanceof Pawn)
			return new Pair(true, board.getPiece((char) (col - 1), pawnRow));

		// checks if the king can kill a piece at the given location
		for (int i = col - 1; i <= col + 1; i++) {
			// top row
			if (board.getPiece((char) i, row + 1) != null) {
				Piece obj = board.getPiece((char) i, row + 1);
				if (obj.getColor() != color && obj instanceof King)
					return new Pair(true, obj);
			}
			// left & right spaces
			if (i != col && board.getPiece((char) (i), row) != null) {
				Piece obj = board.getPiece((char) (i), row);
				if (obj.getColor() != color && obj instanceof King)
					return new Pair(true, obj);
			}
			// bottom row
			if (board.getPiece((char) i, row - 1) != null) {
				Piece obj = board.getPiece((char) i, row - 1);
				if (obj.getColor() != color && obj instanceof King)
					return new Pair(true, obj);
			}
		}

		return new Pair(false, null);
	}

	
	/** 
	 * Checks whether a given side is in checkmate
	 * @param board Current representation of the board
	 * @param attacker Piece that is causing the check
	 * @param oppKing King of the opponent
	 * @return boolean - true if the opponent is in checkmate, false otherwise
	 */
	private static boolean isCheckmate(Board board, Piece attacker, Piece oppKing) {

		char col = oppKing.getCol();
		int row = oppKing.getRow();

		// King can move out of the way to a safe location
		for (int i = Math.max(col - 1, 'a'); i <= Math.min(col + 1, 'h'); i++) {
			for (int j = Math.min(row + 1, 8); j >= Math.max(row - 1, 1); j--) {
				if ((i != col || j != row) && board.getPiece((char) i, j) == null
						&& !causesCheck(board, oppKing.getColor(), (char) i, j).getBool()) {
					return false;
				}
			}
		}
		List<Character> colList = new ArrayList<>();
		List<Integer> rowList = new ArrayList<>();

		if (attacker instanceof Bishop || (attacker instanceof Queen && attacker.getCol() != oppKing.getCol()
				&& attacker.getRow() != oppKing.getRow())) {
			if ((oppKing.getRow() - attacker.getRow()) == (oppKing.getCol() - attacker.getCol())) {
				if (oppKing.getRow() - attacker.getRow() < 0) {
					for (int i = Math.min(oppKing.getRow(), attacker.getRow()) + 1; i < Math.max(oppKing.getRow(),
							attacker.getRow()); i++) {
						rowList.add(i);
						colList.add((char) (oppKing.getCol() + (i - Math.min(oppKing.getRow(), attacker.getRow()))));
					}
				} else {
					for (int i = Math.min(oppKing.getRow(), attacker.getRow()) + 1; i < Math.max(oppKing.getRow(),
							attacker.getRow()); i++) {
						rowList.add(i);
						colList.add((char) (oppKing.getCol() - (Math.max(oppKing.getRow(), attacker.getRow())) + i));
					}
				}
			} else {
				if (oppKing.getRow() - attacker.getRow() < 0) {
					for (int i = Math.min(oppKing.getRow(), attacker.getRow()) + 1; i < Math.max(oppKing.getRow(),
							attacker.getRow()); i++) {
						rowList.add(i);
						colList.add((char) (oppKing.getCol() - (i - Math.min(oppKing.getRow(), attacker.getRow()))));
					}
				} else {
					for (int i = Math.min(oppKing.getRow(), attacker.getRow()) + 1; i < Math.max(oppKing.getRow(),
							attacker.getRow()); i++) {
						rowList.add(i);
						colList.add((char) (oppKing.getCol() + (Math.max(oppKing.getRow(), attacker.getRow())) - i));
					}
				}
			}
		} else if (attacker instanceof Rook || attacker instanceof Queen) {
			if (oppKing.getCol() == attacker.getCol()) {
				for (int i = Math.min(attacker.getRow(), oppKing.getRow()) + 1; i < Math.max(attacker.getRow(),
						oppKing.getRow()); i++) {
					colList.add(oppKing.getCol());
					rowList.add(i);
				}
			} else {
				for (int i = Math.min(attacker.getCol(), oppKing.getCol()) + 1; i < Math.max(attacker.getCol(),
						oppKing.getCol()); i++) {
					colList.add((char) i);
					rowList.add(attacker.getRow());
				}
			}
		}

		List<Piece> list = board.getPiecesByColor(oppKing.getColor());

		for (Piece piece : list) {
			if (piece instanceof King)
				continue;
			if (piece.canMove(board, attacker.getCol(), attacker.getRow())) { // kill the attacker
				char pieceCol = piece.getCol();
				int pieceRow = piece.getRow();

				piece.move(board, attacker.getCol(), attacker.getRow());
				boolean causesCheck = causesCheck(board, oppKing.getColor(), oppKing.getCol(), oppKing.getRow()).getBool();

				piece.move(board, pieceCol, pieceRow);
				board.setPiece(attacker);

				if (!causesCheck)
					return false;
			}
			for (int i = 0; i < colList.size(); i++) {
				if (piece.canMove(board, colList.get(i), rowList.get(i))) { // block the attacker
					char pieceCol = piece.getCol();
					int pieceRow = piece.getRow();

					piece.move(board, colList.get(i), rowList.get(i));
					boolean causesCheck = causesCheck(board, oppKing.getColor(), oppKing.getCol(), oppKing.getRow()).getBool();

					piece.move(board, pieceCol, pieceRow);

					if (!causesCheck)
						return false;
				}
			}
		}

		return true;

	}

}
