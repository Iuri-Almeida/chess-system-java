package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print((rows - i) + " ");
			
			for (int j = 0; j < columns; j++) {
				
				ChessPiece piece = pieces[i][j];
				
				System.out.print(((piece == null) ? "-" : piece) + " ");
				
			}
			
			System.out.println();
			
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
}
