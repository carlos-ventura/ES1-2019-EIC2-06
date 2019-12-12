package es;

/**
 * 
 * Classe responsavel pela apresentação da descrição da regra definida pelo utilizador relativa ao Feature Envy
 *
 */
public class FERule extends Rule {
	private static final String  RULENAME="Is_Feature_Envy";

	/**
	 * 
	 * Construtor da classe LMRule que chama o construtor da superclasse Rule
	 * @param symbol1 Simbolo de entrada, toma valores do enumerado Symbol
	 * @param var1 Valor do atfd a que é fornecido 
	 * @param condition Condição de entrada, toma valores do enumerado Condition
	 * @param symbol2 Simbolo de entrada, toma valores do enumerado Symbol
	 * @param d Valor do laa que é fornecido
	 * 
	 * 
	 */
	public FERule(Symbol symbol1, double var1, Condition condition, Symbol symbol2, double d) {
		super(symbol1, var1, condition, symbol2, d);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Método que apresenta na GUI os parametros da regra definidos pelo utilizador
	 */
	public String toString() {
		return Rule.SPACE+Rule.SPACE+RULENAME+": ATFD"+ getSymbol1()+getVar1() + " "+ getCondition()+ " LAA" + getSymbol2()+getVar2() ;
	}
}