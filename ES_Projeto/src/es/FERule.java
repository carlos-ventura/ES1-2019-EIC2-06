package es;

public class FERule extends Rule {
	private static final String  RULENAME="Is_Feature_Envy";

	public FERule(Symbol symbol1, double var1, Condition condition, Symbol symbol2, double d) {
		super(symbol1, var1, condition, symbol2, d);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return Rule.SPACE+Rule.SPACE+RULENAME+": ATFD"+ getSymbol1()+getVar1() + " "+ getCondition()+ " LAA" + getSymbol2()+getVar2() ;
	}
}