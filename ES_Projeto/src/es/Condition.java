package es;

/**
 * 
 * Este enumerado � utilizado para a cria��o de regras atrav�s da GUI
 *
 */
public enum Condition{
	AND ('A') ,OR('O');
	
	private final char c;
	
	/**
	 * Construtor do enumerado que define o valor do atributo c
	 * @param c Caracter que � fornecido
	 * 
	 */
	Condition(char c) {
		this.c = c;
	}
	
	/**
	 * M�todo toString que devolve o valor da condi��o
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