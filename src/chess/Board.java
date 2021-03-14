package chess;

import pieces.Piece;

public class Board {
	
	private Piece[][] board;
	private final int dimension = 8;
	
	public Board() {
		board = new Piece[dimension][dimension];
	}
	
	public void printBoard() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				
			}
			System.out.println(" " + (dimension-i));
		}
		
		for (int i = 0; i < dimension; i++) {
			System.out.print (" " + (char) (i+97));
		}
		
	}

}
