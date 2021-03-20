package chess;

import java.util.ArrayList;
import java.util.List;

import pieces.*;

/** 
 * This class encapsulates the 8*8 chess board that holds the game piece. 
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Board {
	
	/** 
	 * two-dimensional array that holds all pieces in play
	 */
	private Piece[][] board;

	/** 
	 * dimension of the board
	 */
	private final int dimension = 8;

	
	public Board() {
		board = new Piece[dimension][dimension];

		initTopRow('b', 8);
		initTopRow('w', 1);

		for (int i = 0; i < dimension; i++) {
			setPiece(new Pawn('w', (char) (i + 'a'), 2));
			setPiece(new Pawn('b', (char) (i + 'a'), 7));
		}
	}

	
	/** 
	 * @param color
	 * @param row
	 */
	private void initTopRow(char color, int row) {
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

	
	/** 
	 * @return int
	 */
	public int getDimension() {
		return dimension;
	}

	
	/** 
	 * 
	 * @param col
	 * @param row
	 * @return boolean
	 */
	public boolean inBounds(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		if (rowCoordinate >= dimension || colCoordinate >= dimension || rowCoordinate < 0 || colCoordinate < 0)
			return false;

		return true;
	}

	
	/** 
	 * @param col
	 * @param row
	 * @return Piece
	 */
	public Piece getPiece(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		if (!inBounds(col, row))
			return null;

		return board[rowCoordinate][colCoordinate];
	}

	
	/** 
	 * @param piece
	 */
	public void setPiece(Piece piece) {
		int rowCoordinate = dimension - piece.getRow();
		int colCoordinate = piece.getCol() - 'a';

		board[rowCoordinate][colCoordinate] = piece;
	}

	
	/** 
	 * @param col
	 * @param row
	 */
	public void nullLocation(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		board[rowCoordinate][colCoordinate] = null;
	}

	
	/** 
	 * @param color
	 * @return List<Piece>
	 */
	public List<Piece> getPiecesByColor(char color) {

		List<Piece> list = new ArrayList<>();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (board[i][j] != null && (board[i][j].getColor() == color))
					list.add(board[i][j]);
			}
		}

		return list;
	}

}
