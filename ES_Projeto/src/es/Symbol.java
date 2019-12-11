package es;

public enum Symbol{
	MAIOR ('>'),MENOR('<');
	
	private final char c;
	
	Symbol(char c) {
		this.c = c;
	}
	
	public String toString() {
		return ""+c;
	}
}