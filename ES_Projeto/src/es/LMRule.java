package es;


/**
 * 
 * Classe responsavel pela apresentação da descrição da regra definida pelo utilizador relativa ao Long Method
 *
 */
public class LMRule extends Rule{
	public static final String  RULENAME="New_Is_Long_Method";
	
	/**
	 * 
	 * Construtor da classe LMRule que chama o construtor da superclasse Rule
	 * @param symbol1 Simbolo de entrada, toma valores do enumerado Symbol
	 * @param var1 Valor do loc a que é fornecido 
	 * @param condition Condição de entrada, toma valores do enumerado Condition
	 * @param symbol2 Simbolo de entrada, toma valores do enumerado Symbol
	 * @param var2 Valor do cylclo que é fornecido
	 * 
	 * 
	 */
	public LMRule(Symbol symbol1,int var1,Condition condition,Symbol symbol2,int var2) {
		super(symbol1,var1, condition, symbol2, var2);
	}

	/**
	 * Método que apresenta na GUI os parametros da regra definidos pelo utilizador
	 */
	public String toString() {
		return Rule.SPACE+RULENAME+": LOC"+ getSymbol1()+(int)getVar1() + " "+ getCondition()+ " CYCLO" + getSymbol2()+(int)getVar2();
	}
}
