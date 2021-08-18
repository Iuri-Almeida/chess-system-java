package chess;

import application.ProgramConstants;
import boardgame.Position;

public class ChessPosition {
	
	private int row;
	private char column;
	
	public ChessPosition(char column, int row) {
		
		if (column < ProgramConstants.FIRST_COLUMN || column > ProgramConstants.LAST_COLUMN || row < 1 || row > ProgramConstants.ROWS)
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
		
		int row = ProgramConstants.ROWS - this.row;
		int column = this.column - ProgramConstants.FIRST_COLUMN;
		
		return new Position(row, column);
	}
	
	public static ChessPosition fromPosition(Position position) {
		
		int row = ProgramConstants.ROWS - position.getRow();
		char column = (char) (ProgramConstants.FIRST_COLUMN + position.getColumn());
		
		return new ChessPosition(column, row);
		
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}

}
