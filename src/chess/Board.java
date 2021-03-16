package chess;

import pieces.*;

public class Board {

	private Piece[][] board;
	private final int dimension = 8;

	public Board() {
		board = new Piece[dimension][dimension];
	}

	public void initBoard() {
		initTopRow('b', 8);
		initTopRow('w', 1);

		for (int i = 0; i < dimension; i++){
			setPiece(new Pawn('w', (char) (i + 97), 2));
			setPiece(new Pawn('b', (char) (i + 97), 7));
		}
	}

	private void initTopRow(char color, int row){
		setPiece(new Rook(color, 'a', row));
		setPiece(new Rook(color, 'h', row));
		setPiece(new Knight(color, 'b', row));
		setPiece(new Knight(color, 'g', row));
		setPiece(new Bishop(color, 'c', row));
		setPiece(new Bishop(color, 'f', row));
		setPiece(new Queen(color, 'd', row));
		setPiece(new King(color, 'e', row));
	}

	public void printBoard() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j] + " ");
				} else if (i % 2 != j % 2) {
					System.out.print("##" + " ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println((dimension - i));
		}

		for (int i = 0; i < dimension; i++) {
			System.out.print(" " + (char) (i + 97) + " ");
		}

		System.out.println();
		System.out.println();
	}

	public int getDimension() {
		return dimension;
	}

	public Piece getPiece(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		return board[rowCoordinate][colCoordinate];
	}

	public void setPiece(Piece piece) {
		int rowCoordinate = dimension - piece.getRow();
		int colCoordinate = piece.getCol() - 'a';

		board[rowCoordinate][colCoordinate] = piece;
	}

	public void nullLocation(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		board[rowCoordinate][colCoordinate] = null;
	}

}
