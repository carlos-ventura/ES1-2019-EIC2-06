package es;


public class Rule {
	protected static final String  SPACE="                                    ";
	private String rulename;
	private Symbol symbol1;
	private double var1;
	private Condition condition;
	private Symbol symbol2;
	private double var2;
	
	
	public Rule(String rulename,Symbol symbol1,double var1,Condition condition,Symbol symbol2,double var2) {
		this.rulename = rulename;
		this.symbol1 = symbol1;
		this.var1 = var1;
		this.condition = condition;
		this.symbol2 = symbol2;
		this.var2 = var2;
		
	}
	
	public double getVar1() {
		return var1;
	}
	
	public void setVar1(double var1) {
		this.var1 = var1;
	}
	
	public Symbol getSymbol1() {
		return symbol1;
	}
	
	public void setSymbol1(Symbol symbol1) {
		this.symbol1 = symbol1;
	}

	public double getVar2() {
		return var2;
	}
	
	public void setVar2(double var2) {
		this.var2 = var2;
	}
	
	public Symbol getSymbol2() {
		return symbol2;
	}
	
	public void setSymbol2(Symbol symbol2) {
		this.symbol2 = symbol2;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	


}