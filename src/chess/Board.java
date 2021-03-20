package chess;

import java.util.ArrayList;
import java.util.List;

import pieces.*;

/** 
 * Encapsulates the 8*8 chess board that holds the game piece 
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Board {
	
	/** 
	 * Two-dimensional array that holds all pieces in play
	 */
	private Piece[][] board;

	/** 
	 * Dimension of the board
	 */
	private final int dimension = 8;

	/** 
	 * Initializes the board and populates it with initial pieces
	 */
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
	 * Initializes the non-pawn pieces for specified color
	 * @param color Color of the pieces ('w'/'b')
	 * @param row Row of the non-pawn pieces of the specificed color
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


	/** 
	 * Prints the complete board with the '##' and current pieces in their respective locations
	 */
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
	 * Gets the board dimension
	 * @return int - Dimension
	 */
	public int getDimension() {
		return dimension;
	}

	
	/** 
	 * Checks if a position is within the boundaries of the board
	 * @param col Character representing the column
	 * @param row Row number
	 * @return boolean - true if location is contained in the board, false otherwise
	 */
	public boolean inBounds(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		if (rowCoordinate >= dimension || colCoordinate >= dimension || rowCoordinate < 0 || colCoordinate < 0)
			return false;

		return true;
	}

	
	/** 
	 * Gets the piece at specified location
	 * @param col Character representing the desired column
	 * @param row Row number
	 * @return Piece - Piece at desired location
	 */
	public Piece getPiece(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		if (!inBounds(col, row))
			return null;

		return board[rowCoordinate][colCoordinate];
	}

	
	/** 
	 * Adds a piece to the board based on the piece's location
	 * @param piece Piece that needs to be added to the board
	 */
	public void setPiece(Piece piece) {
		int rowCoordinate = dimension - piece.getRow();
		int colCoordinate = piece.getCol() - 'a';

		board[rowCoordinate][colCoordinate] = piece;
	}

	
	/** 
	 * Makes the desired location null on the board
	 * @param col Character representing the column
	 * @param row Row number
	 */
	public void nullLocation(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		board[rowCoordinate][colCoordinate] = null;
	}

	
	/** 
	 * Obtains the pieces on the board of a specified color
	 * @param color  Color of the pieces ('w'/'b')
	 * @return List<Piece> - List of all the pieces of the desired color
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
