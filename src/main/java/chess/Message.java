package chess;

import utils.Console;

enum Message {
	TITLE("----- CHESS -----"),
	BLACK_WINS("Black wins!!"),
	WHITE_WINS("White wins!!"),
	STALEMATE("Stalemate!! It's a tie"),
	CHOOSE_YOUR_MOVE("Its your turn. Choose your move."),
	WHITE("White"),
	BLACK("Black"),
	ILLEGAL_MOVE("Illegal move");


	private String message;
	
	static private Console console; 
	static {
		Message.console = new Console();
	}

	private Message(String message) {
		this.message = message;
	}

	void write() {
		Message.console.write(this.message);
	}

	void writeln() {
		Message.console.writeln(this.message);
	}

	void writeln(int attempts) {
		Message.console.writeln(this.message.replaceAll("#attempts", "" + attempts));
	}

	void writeln(int blacks, int whites) {
		Message.console.writeln(this.message.replaceFirst("#blacks", "" + blacks).replaceFirst("#whites", "" + whites));
	}

}
