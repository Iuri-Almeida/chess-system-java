package chess;

import boardgame.Position;

public class ChessPosition {
	
	private int row;
	private char column;
	
	public ChessPosition(char column, int row) {
		
		if (column < ChessConstants.FIRST_COLUMN || column > ChessConstants.LAST_COLUMN || row < 1 || row > ChessConstants.ROWS)
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
	
	public Position toPosition() {
		
		int row = ChessConstants.ROWS - this.row;
		int column = this.column - ChessConstants.FIRST_COLUMN;
		
		return new Position(row, column);
	}
	
	public static ChessPosition fromPosition(Position position) {
		
		int row = ChessConstants.ROWS - position.getRow();
		char column = (char) (ChessConstants.FIRST_COLUMN + position.getColumn());
		
		return new ChessPosition(column, row);
		
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}

}
