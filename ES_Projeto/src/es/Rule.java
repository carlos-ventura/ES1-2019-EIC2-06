package es;

/**
 * 
 * Classe abstrata que é utilizada para a criação de regras para o Long Method e para o Feature Envy
 *
 */
public abstract class Rule {
	public static final String  SPACE="                                                       ";
	
	private Symbol symbol1;
	private double var1;
	private Condition condition;
	private Symbol symbol2;
	private double var2;
	
	/**
	 * 
	 * @param symbol1 Symbolo de logica, maior, menor, ou nenhum deles
	 * @param var1 Métrica a ser utilizada
	 * @param condition Condição de logica, e, ou, ou nenhum deles
	 * @param symbol2 Symbolo de logica, maior, menor, ou nenhum deles
	 * @param var2 Métrica a ser utilizada
	 */
	public Rule(Symbol symbol1,double var1,Condition condition,Symbol symbol2,double var2) {
		this.symbol1 = symbol1;
		this.var1 = var1;
		this.condition = condition;
		this.symbol2 = symbol2;
		this.var2 = var2;
		
	}

	/**
	 * Getter do atributo Var1
	 * @return Valor do atributo Var1
	 */
	public double getVar1() {
		return var1;
	}

	/**
	 * Setter do atributo var1
	 * @param var1 Valor que o atributo var1 irá tomar
	 */
	public void setVar1(double var1) {
		this.var1 = var1;
	}

	/**
	 * Getter do atributo Symbol1
	 * @return Valor do atributo Symbol1
	 */
	public Symbol getSymbol1() {
		return symbol1;
	}

	/**
	 * Setter do atributo symbol1
	 * @param symbol1 Valor que o atributo symbol1 irá tomar
	 */
	public void setSymbol1(Symbol symbol1) {
		this.symbol1 = symbol1;
	}

	/**
	 * Getter do atributo Var2
	 * @return Valor do atributo Var2
	 */
	public double getVar2() {
		return var2;
	}

	/**
	 * Setter do atributo var2
	 * @param var2 Valor que o atributo var2 irá tomar
	 */
	public void setVar2(double var2) {
		this.var2 = var2;
	}

	/**
	 * Getter do atributo Symbol2
	 * @return Valor do atributo Symbol2
	 */
	public Symbol getSymbol2() {
		return symbol2;
	}

	/**
	 * Setter do atributo symbol2
	 * @param symbol2 Valor que o atributo symbol2 irá tomar
	 */
	public void setSymbol2(Symbol symbol2) {
		this.symbol2 = symbol2;
	}

	/**
	 * Getter do atributo condition
	 * @return Valor do atributo condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Setter do atributo condition
	 * @param condition Valor que o atributo condition irá tomar
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	


}