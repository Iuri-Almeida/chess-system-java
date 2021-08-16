package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces() {
		
		int rows = board.getRows();
		int columns = board.getColumns();
		
		ChessPiece[][] pieces = new ChessPiece[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			
			for (int j = 0; j < columns; j++) {
				
				pieces [i][j] = (ChessPiece) board.piece(i, j);
				
			}
			
		}
		
		return pieces;
		
	}
	
}
