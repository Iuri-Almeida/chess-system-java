package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
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
		
		nextTurn();
		
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
	
	private void placeNewPiece (char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		
		// white players
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
		
		// black players
		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		
	}
	
}
