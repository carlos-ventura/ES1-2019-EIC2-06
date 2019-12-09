package es;



public class LMRule extends Rule{
	private static final String  RULENAME="Is_Long_Method";
	
	
	
	public LMRule(Symbol symbol1,int var1,Condition condition,Symbol symbol2,int var2) {
		super(LMRule.RULENAME,symbol1,var1, condition, symbol2, var2);
	}
	
	public String toString() {
		return Rule.SPACE+RULENAME+": LOC"+ getSymbol1()+(int)getVar1() + " "+ getCondition()+ " CYCLO" + getSymbol2()+(int)getVar2();
	}
}
