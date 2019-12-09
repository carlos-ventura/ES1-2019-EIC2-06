package es;

enum Symbol{
	MAIOR ('>'),MENOR('<'),VAZIO(' ');
	
	private final char c;

	Symbol(char c) {
		this.c = c;
	}

	public String toString() {
		return ""+c;
	}
}