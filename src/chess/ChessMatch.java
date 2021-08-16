package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
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
	
	private void initialSetup() {
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
		
	}
	
}
