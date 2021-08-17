package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
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
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setRow(aux.getRow() - 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// right
		aux.setValues(row, column + 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setColumn(aux.getColumn() + 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down
		aux.setValues(row + 1, column);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setRow(aux.getRow() + 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		// left
		aux.setValues(row, column - 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setColumn(aux.getColumn() - 1);
			
		}
		
		if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) mat[aux.getRow()][aux.getColumn()] = true;
		
		return mat;
	}

}
