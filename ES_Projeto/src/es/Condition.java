package es;

/**
 * 
 * Este enumerado é utilizado para a criação de regras através da GUI
 *
 */
public enum Condition{
	AND ('A') ,OR('O');
	
	private final char c;
	
	/**
	 * Construtor do enumerado que define o valor do atributo c
	 * @param c Caracter que é fornecido
	 * 
	 */
	Condition(char c) {
		this.c = c;
	}
	
	/**
	 * Método toString que devolve o valor da condição
	 */
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