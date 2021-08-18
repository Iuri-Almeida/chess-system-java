package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "N";
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
		aux.setValues(row - 2, column + 1);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// up left
		aux.setValues(row - 2, column - 1);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// right up
		aux.setValues(row - 1, column + 2);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// right down
		aux.setValues(row + 1, column + 2);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down right
		aux.setValues(row + 2, column + 1);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// down left
		aux.setValues(row + 2, column - 1);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// left up
		aux.setValues(row - 1, column - 2);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		// left down
		aux.setValues(row + 1, column - 2);
		if ((getBoard().positionExists(aux) && canMove(aux))) mat[aux.getRow()][aux.getColumn()] = true;
		
		return mat;
	}
	
	private boolean canMove(Position position) {
		
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		
		return piece == null || piece.getColor() != getColor();
		
	}

}
