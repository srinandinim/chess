package chess;

import pieces.*;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board();
		board.initBoard();

		boolean move = true; // white: true
		boolean done = false;

		while (!done){
			board.printBoard();
			done = true;
		}

	}

	public void testcode(){
		/* PAWN
		// Make sure pawn does not go backwards
		Piece pawn = board.getPiece('b', 7);
		for (int i = 0; i < 8; i++){
			System.out.print (pawn.canMove(board, (char) ('a' + i), 8) + " ");
		}
		System.out.println(); // EO: false false false false false false false false

		// Make sure pawn can only travel 1 move or at beg 2 moves
		System.out.println (pawn.canMove(board, 'b', 6)); // EO: true
		System.out.println (pawn.canMove(board, 'b', 5)); // EO: true
		System.out.println (pawn.canMove(board, 'b', 3)); // EO: false
		System.out.println (board.getPiece('c', 2).canMove(board, 'c', 4)); // EO: true
		System.out.println (board.getPiece('d', 2).canMove(board, 'd', 5)); // EO: false
		System.out.println (board.getPiece('a', 2).canMove(board, 'a', 4)); // EO: true

		// Kill diagonally
		System.out.println (board.getPiece('a', 4).canMove(board, 'b', 5)); // EO: true
		// System.out.println (board.getPiece('b', 5).canMove(board, 'b', 6)); // EO: true
		// System.out.println (board.getPiece('c', 7).canMove(board, 'b', 6)); // EO: true


		// Make sure pawn does not overlap exisitng pawn
		System.out.println (board.getPiece('c', 7).canMove(board, 'c', 5)); // EO: true
		System.out.println (board.getPiece('c', 5).canMove(board, 'c', 3)); // EO: false
		*/

		/*
		Piece rook1 = new Rook('w', 'a', 1);
		board.setPiece(rook1);
		Piece rook2 = new Rook('b', 'd', 1);
		board.setPiece(rook2);
		System.out.println (rook1.canMove(board, 'c', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'b', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'd', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'h', 1));
		board.printBoard();
		*/
		/*
		Piece bishop1 = new Bishop('w', 'e', 5);
		board.setPiece(bishop1);
		Piece bishop2 = new Bishop('b', 'b', 8);
		board.setPiece(bishop2);
		System.out.println (bishop2.canMove(board, 'e', 5));
		board.printBoard();
		
		Piece knight1 = new Knight('w', 'b', 1);
		board.setPiece(knight1);
		Piece knight2 = new Knight('w', 'a', 2);
		board.setPiece(knight2);
		System.out.println (knight1.canMove(board, 'c', 3));
		System.out.println (knight1.canMove(board, 'a', 2));
		board.printBoard();
		*/
	}

	public boolean causesCheck(Piece piece, Board board, char col, int row){

		for (int i=col+1; i<='h'; i++){ //To the right
			if (board.getPiece((char)i, row) != null){
				Piece obj = board.getPiece((char)i, row);
				if (obj.getColor() != piece.getColor() && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=col-1; i>='a'; i--){ // To the left
			if (board.getPiece((char)i, row) != null){
				Piece obj = board.getPiece((char)i, row);
				if (obj.getColor() != piece.getColor() && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=row+1; i<=8; i++){ //Going up
			if (board.getPiece(col, i) != null){
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != piece.getColor() && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=row-1; i>=0; i--){ // Going down
			if (board.getPiece(col, i) != null){
				Piece obj = board.getPiece(col, i);
				if (obj.getColor() != piece.getColor() && (obj instanceof Rook || obj instanceof Queen))
					return true;
				break;
			}
		}


		if (board.getPiece((char)(col+1), row+2) != null){
			Piece obj = board.getPiece((char)(col+1), row+2);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col-1), row+2) != null){
			Piece obj = board.getPiece((char)(col-1), row+2);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col+1), row-2) != null){
			Piece obj = board.getPiece((char)(col+1), row+2);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col-1), row-2) != null){
			Piece obj = board.getPiece((char)(col+1), row+2);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}

		if (board.getPiece((char)(col+2), row+1) != null){
			Piece obj = board.getPiece((char)(col+2), row+1);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col+2), row-1) != null){
			Piece obj = board.getPiece((char)(col+2), row-1);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col-2), row+1) != null){
			Piece obj = board.getPiece((char)(col-2), row+1);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}
		if (board.getPiece((char)(col-2), row-1) != null){
			Piece obj = board.getPiece((char)(col-2), row-1);
			if (obj.getColor() != piece.getColor() && obj instanceof Knight)
				return true;
		}


		for (int i=col-1, j=row+1; i>='a' && j<=8; i--, j++){
			if (board.getPiece((char)i, j) != null){
				Piece obj = board.getPiece((char)i, j);
				if (obj.getColor() != piece.getColor() && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=col+1, j=row+1; i<='h' && j<=8; i++, j++){
			if (board.getPiece((char)i, j) != null){
				Piece obj = board.getPiece((char)i, j);
				if (obj.getColor() != piece.getColor() && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=col+1, j=row-1; i<='h' && j>=1; i++, j--){
			if (board.getPiece((char)i, j) != null){
				Piece obj = board.getPiece((char)i, j);
				if (obj.getColor() != piece.getColor() && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}
		for (int i=col-1, j=row-1; i>='a' && j>=1; i--, j--){
			if (board.getPiece((char)i, j) != null){
				Piece obj = board.getPiece((char)i, j);
				if (obj.getColor() != piece.getColor() && (obj instanceof Bishop || obj instanceof Queen))
					return true;
				break;
			}
		}

		if (piece.getColor() == 'w'){
			if (board.getPiece((char)(col+1), row+1).getColor() == 'b' && board.getPiece((char)(col+1), row+1) instanceof Pawn)
				return true;
			if (board.getPiece((char)(col-1), row+1).getColor() == 'b' && board.getPiece((char)(col-1), row+1) instanceof Pawn)
				return true;
		}
		if (piece.getColor() == 'b'){
			if (board.getPiece((char)(col+1), row-1).getColor() == 'w' && board.getPiece((char)(col+1), row-1) instanceof Pawn)
				return true;
			if (board.getPiece((char)(col-1), row-1).getColor() == 'w' && board.getPiece((char)(col-1), row-1) instanceof Pawn)
				return true;
		}

		for (int i = col-1; i<=col+1; i++){
			if (board.getPiece((char)i, row+1) != null){
				Piece obj = board.getPiece((char)i, row+1);
				if (obj.getColor() != piece.getColor() && obj instanceof King)
					return true;
			}
			if (board.getPiece((char)i, row-1) != null){
				Piece obj = board.getPiece((char)i, row-1);
				if (obj.getColor() != piece.getColor() && obj instanceof King)
					return true;
			}
		}
		if (board.getPiece((char)(col-1), row) != null){
			Piece obj = board.getPiece((char)(col-1), row);
			if (obj.getColor() != piece.getColor() && obj instanceof King)
				return true;
		}
		if (board.getPiece((char)(col+1), row) != null){
			Piece obj = board.getPiece((char)(col+1), row);
			if (obj.getColor() != piece.getColor() && obj instanceof King)
				return true;
		}

		return false;

	}

}
