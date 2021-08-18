package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
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
		
		// up right
		aux.setValues(row - 1, column + 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() - 1, aux.getColumn() + 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// up left
		aux.setValues(row - 1, column - 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() - 1, aux.getColumn() - 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down right
		aux.setValues(row + 1, column + 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() + 1, aux.getColumn() + 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down left
		aux.setValues(row + 1, column - 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() + 1, aux.getColumn() - 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		return mat;
	}

}
