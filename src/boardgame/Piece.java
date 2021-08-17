package boardgame;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		
		int row = position.getRow();
		int column = position.getColumn();
		
		return possibleMoves()[row][column];
		
	}
	
	public boolean isThereAnyPossibleMove() {
		
		boolean[][] mat = possibleMoves();
		
		int row = mat.length;
		int column = mat[0].length;
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < column; j++) {
				
				if (mat[i][j]) return true;
				
			}
			
		}
		
		return false;
		
	}

}
