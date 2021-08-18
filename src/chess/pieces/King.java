package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		
		int rows = getBoard().getRows();
		int columns = getBoard().getRows();
		
		boolean[][] mat = new boolean[rows][columns];
		
		// auxiliary position
		Position aux = new Position(0, 0);
		
		// piece position
		int row = position.getRow();
		int column = position.getColumn();
		
		// up
		aux.setValues(row - 1, column);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// up right
		aux.setValues(row - 1, column + 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// right
		aux.setValues(row, column + 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down right
		aux.setValues(row + 1, column + 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down
		aux.setValues(row + 1, column);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down left
		aux.setValues(row + 1, column - 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// left
		aux.setValues(row, column - 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// up left
		aux.setValues(row - 1, column - 1);
		if (getBoard().positionExists(aux) && canMove(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// SPECIAL MOVE - CASTLING
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			// Castling - King side
			Position posRook01 = new Position(row, column + 3);
			if (testRookCastling(posRook01)) {
				
				Position p01 = new Position(row, column + 1);
				Position p02 = new Position(row, column + 2);
				
				if (getBoard().piece(p01) == null && getBoard().piece(p02) == null) mat[row][column + 2] = true;
				
			}
			
			// Castling - Queen side
			Position posRook02 = new Position(row, column - 4);
			if (testRookCastling(posRook02)) {
				
				Position p01 = new Position(row, column - 1);
				Position p02 = new Position(row, column - 2);
				Position p03 = new Position(row, column - 3);
				
				if (getBoard().piece(p01) == null && getBoard().piece(p02) == null && getBoard().piece(p03) == null) mat[row][column - 2] = true;
				
			}
			
		}
		
		return mat;
	}
	
	private boolean canMove(Position position) {
		
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		
		return piece == null || piece.getColor() != getColor();
		
	}
	
	private boolean testRookCastling(Position position) {
		
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		
		return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
		
	}

}
