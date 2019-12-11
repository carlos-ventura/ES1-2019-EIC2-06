package es;

public enum Condition{
	AND ('A') ,OR('O');
	
	private final char c;
	
	Condition(char c) {
		this.c = c;
	}
	public String toString() {
		if(c=='A') {
			return "and";
		}else if(c=='O'){
			return "or";
		}else {
			return " "; 
		}
	}
}