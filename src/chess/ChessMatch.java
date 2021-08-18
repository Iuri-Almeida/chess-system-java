package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard;
	private List<Piece> capturedPieces;

	public ChessMatch() {
		board = new Board(ChessConstants.ROWS, ChessConstants.COLUMNS);
		turn = 1;
		currentPlayer = Color.WHITE;
		piecesOnTheBoard = new ArrayList<>();
		capturedPieces = new ArrayList<>();
		
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece[][] getPieces() {
		
		int rows = board.getRows();
		int columns = board.getColumns();
		
		ChessPiece[][] pieces = new ChessPiece[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			
			for (int j = 0; j < columns; j++) {
				
				pieces [i][j] = (ChessPiece) board.piece(i, j);
				
			}
			
		}
		
		return pieces;
		
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		
		Position position = sourcePosition.toPosition();
		
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
		
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target);
		
		if (testCheck(currentPlayer)) {
			
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check.");
			
		}
		
		check = testCheck(opponent(currentPlayer));
		
		checkMate = testCheckMate(opponent(currentPlayer));
		
		if (!checkMate) nextTurn();
		
		return (ChessPiece) capturedPiece;
		
	}
	
	private Piece makeMove(Position source, Position target) {
		
		Piece piece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		board.placePiece(piece, target);
		
		if (capturedPiece != null) {
			
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
			
		}
		
		return capturedPiece;
		
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		
		Piece piece = board.removePiece(target);
		board.placePiece(piece, source);
		
		if (capturedPiece != null) {
			
			board.placePiece(capturedPiece, target);
			
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
		
	}
	
	private void validateSourcePosition(Position position) {
		
		if (!board.thereIsAPiece(position)) throw new ChessException("There is no piece on source position.");
		
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) throw new ChessException("The chosen piece is not yours.");
		
		if (!board.piece(position).isThereAnyPossibleMove()) throw new ChessException("There is no possible moves for the chosen piece.");
		
	}
	
	private void validateTargetPosition(Position source, Position target) {
		
		if (!board.piece(source).possibleMove(target)) throw new ChessException("The chosen piece can't move to target position.");
		
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		for (Piece p : list) if (p instanceof King) return (ChessPiece) p;
		
		throw new IllegalStateException("There is no " + color + " king on the board.");
		
	}
	
	private boolean testCheck(Color color) {
		
		Position kingPosition = king(color).getChessPosition().toPosition();
		
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for (Piece p : opponentPieces) {
			
			boolean[][] mat = p.possibleMoves();
			
			// king position
			int row = kingPosition.getRow();
			int column = kingPosition.getColumn();
			
			if (mat[row][column]) return true;
			
		}
		
		return false;
		
	}
	
	private boolean testCheckMate(Color color) {
		
		if (!testCheck(color)) return false;
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
		
		for (Piece p : list) {
			
			boolean[][] mat = p.possibleMoves();
			
			int rows = mat.length;
			int columns = mat[0].length;
			
			for (int i = 0; i < rows; i++) {
				
				for (int j = 0; j < columns; j++) {
					
					if (mat[i][j]) {
						
						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);;
						
						if (!testCheck) return false;
						
					}
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	private void placeNewPiece (char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		
		// white players
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		
		// black players
		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
		
	}
	
}
