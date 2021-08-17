package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	} 
	
	public static ChessPosition readChessPosition(Scanner sc) {
		
		try {
			
			String s = sc.nextLine();
			
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			
			return new ChessPosition(column, row);
			
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
		
	}
	
	public static void printMatch(ChessMatch chessMatch) {
		
		printBoard(chessMatch.getPieces());
		
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
		
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print((rows - i) + " ");
			
			for (int j = 0; j < columns; j++) {
				
				printPiece(pieces[i][j], false);;
				
			}
			
			System.out.println();
			
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print((rows - i) + " ");
			
			for (int j = 0; j < columns; j++) {
				
				printPiece(pieces[i][j], possibleMoves[i][j]);;
				
			}
			
			System.out.println();
			
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		
		if (background) System.out.print(ANSIColorConstants.ANSI_BLUE_BACKGROUND);
		
		if (piece == null) System.out.print("-" + ANSIColorConstants.ANSI_RESET);
		else {
			
			if (piece.getColor() == Color.WHITE) System.out.print(ANSIColorConstants.ANSI_WHITE + piece + ANSIColorConstants.ANSI_RESET);
			else System.out.print(ANSIColorConstants.ANSI_YELLOW + piece + ANSIColorConstants.ANSI_RESET);
			
		}
		
		System.out.print(" ");
		
	}
	
}
