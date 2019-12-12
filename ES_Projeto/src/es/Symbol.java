package es;

/**
 * 
 * Este enumerado � utilizado para a cria��o de regras atrav�s da GUI
 *
 */
public enum Symbol{
	MAIOR ('>'),MENOR('<');
	
	private final char c;
	
	/**
	 * Construtor do enumerado que define o valor do atributo c
	 * @param c Caracter que � fornecido
	 * 
	 */
	Symbol(char c) {
		this.c = c;
	}
	
	/**
	 * M�todo toString que devolve o valor do s�mbolo
	 */
	public String toString() {
		return ""+c;
	}
}