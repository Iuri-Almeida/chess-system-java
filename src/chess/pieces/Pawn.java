package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "P";
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
		
		if (getColor() == Color.WHITE) {
			
			// up one
			aux.setValues(row - 1, column);
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
			// up two
			aux.setValues(row - 2, column);
			Position aux2 = new Position(row - 2, column);
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) &&
				getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2) &&
				getMoveCount() == 0) mat[aux.getRow()][aux.getColumn()] = true;
			
			// up right
			aux.setValues(row - 1, column + 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
			// up left
			aux.setValues(row - 1, column - 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
		} else {
			
			// down one
			aux.setValues(row + 1, column);
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
			// down two
			aux.setValues(row + 2, column);
			Position aux2 = new Position(row + 2, column);
			if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) &&
				getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2) &&
				getMoveCount() == 0) mat[aux.getRow()][aux.getColumn()] = true;
			
			// down right
			aux.setValues(row + 1, column + 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
			// down left
			aux.setValues(row + 1, column - 1);
			if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
			
		}
		
		return mat;
	}

}
