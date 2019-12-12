package es;

/**
 * 
 * Este enumerado é utilizado para a criação de regras através da GUI
 *
 */
public enum Symbol{
	MAIOR ('>'),MENOR('<');
	
	private final char c;
	
	/**
	 * Construtor do enumerado que define o valor do atributo c
	 * @param c Caracter que é fornecido
	 * 
	 */
	Symbol(char c) {
		this.c = c;
	}
	
	/**
	 * Método toString que devolve o valor do símbolo
	 */
	public String toString() {
		return ""+c;
	}
}