package application;

public class ProgramConstants {
	
	// Board
	public static int ROWS = 8;
	public static int COLUMNS = 8;
	public static char FIRST_COLUMN = 'a';
	public static char LAST_COLUMN = 'h';
	
	// Game Status
	public static String CHECK = "CHECK";
	public static String CHECKMATE = "CHECKMATE";
	public static String NO_CHECK = "NO CHECK";
	
	// Game Colors
	public static String BLACK_PIECE_COLOR = ANSIColorConstants.ANSI_YELLOW;
	public static String WHITE_PIECE_COLOR = ANSIColorConstants.ANSI_WHITE;
	public static String BACKGROUND_COLOR = ANSIColorConstants.ANSI_BLUE_BACKGROUND;
	public static String INDICATORS_COLOR = ANSIColorConstants.ANSI_GREEN;
	public static String GAME_STATUS_COLOR = ANSIColorConstants.ANSI_RED;
	public static String RESET_COLOR = ANSIColorConstants.ANSI_RESET;

}
