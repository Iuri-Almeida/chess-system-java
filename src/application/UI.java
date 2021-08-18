package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessConstants;
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
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		
		String gameStatus = ChessConstants.NO_CHECK;
		
		printBoard(chessMatch.getPieces());
		
		System.out.println();
		printCapturedPieces(captured);
		
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		
		if (!chessMatch.getCheckMate()) {
			
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) gameStatus = ChessConstants.CHECK;
			
		} else {
			
			gameStatus = ChessConstants.CHECKMATE;
			
			Color player = chessMatch.getCurrentPlayer();
			
			System.out.println("Winner: " + ((player == Color.BLACK) ? ANSIColorConstants.ANSI_YELLOW : ANSIColorConstants.ANSI_WHITE)
					+ player + ANSIColorConstants.ANSI_RESET);
			
		}
		
		System.out.println();
		System.out.println("Game status: " + ANSIColorConstants.ANSI_RED + gameStatus + ANSIColorConstants.ANSI_RESET);
		
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print(ANSIColorConstants.ANSI_GREEN + (rows - i) + " " + ANSIColorConstants.ANSI_RESET);
			
			for (int j = 0; j < columns; j++) {
				
				printPiece(pieces[i][j], false);;
				
			}
			
			System.out.println();
			
		}
		
		System.out.println(ANSIColorConstants.ANSI_GREEN +  "  a b c d e f g h" + ANSIColorConstants.ANSI_RESET);
		
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		
		int rows = pieces.length;
		int columns = pieces[0].length;
		
		for (int i = 0; i < rows; i++) {
			
			System.out.print(ANSIColorConstants.ANSI_GREEN + (rows - i) + " " + ANSIColorConstants.ANSI_RESET);
			
			for (int j = 0; j < columns; j++) {
				
				printPiece(pieces[i][j], possibleMoves[i][j]);;
				
			}
			
			System.out.println();
			
		}
		
		System.out.println(ANSIColorConstants.ANSI_GREEN +  "  a b c d e f g h" + ANSIColorConstants.ANSI_RESET);
		
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
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured pieces:");
		System.out.println(ANSIColorConstants.ANSI_WHITE + "White: " + Arrays.toString(white.toArray()) + ANSIColorConstants.ANSI_RESET);
		System.out.println(ANSIColorConstants.ANSI_YELLOW + "Black: " + Arrays.toString(black.toArray()) + ANSIColorConstants.ANSI_RESET);
		
	}
	
}
