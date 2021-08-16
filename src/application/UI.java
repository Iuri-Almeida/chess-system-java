package application;

import chess.ChessPiece;
import chess.Color;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print((rows - i) + " ");
			
			for (int j = 0; j < columns; j++) {
				
				printPiece(pieces[i][j]);;
				
			}
			
			System.out.println();
			
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPiece(ChessPiece piece) {
		
		if (piece == null) System.out.print("-");
		else {
			
			if (piece.getColor() == Color.WHITE) System.out.print(ANSIColorConstants.ANSI_WHITE + piece + ANSIColorConstants.ANSI_RESET);
			else System.out.print(ANSIColorConstants.ANSI_YELLOW + piece + ANSIColorConstants.ANSI_RESET);
			
		}
		
		System.out.print(" ");
		
	}
	
}
