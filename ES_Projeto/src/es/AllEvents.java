package es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class AllEvents implements ActionListener{
	
	private LMRule lmRule;
	private FERule feRule;

	public AllEvents(LMRule lmRule, FERule feRule) {
		this.lmRule = lmRule;
		this.feRule = feRule;
	}

	public void addProperties(JRadioButton me,String s) {
		me.setActionCommand(s);
		me.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String aux=e.getActionCommand();
		if(aux.equals("loc >")) lmRule.setSymbol1(Symbol.MAIOR);
		else if (aux.equals("loc <")) lmRule.setSymbol1(Symbol.MENOR);
		else if (aux.equals("andLM")) lmRule.setCondition(Condition.AND);
		else if (aux.equals("orLM")) lmRule.setCondition(Condition.OR);
		else if (aux.equals("cyclo >")) lmRule.setSymbol2(Symbol.MAIOR);
		else if (aux.equals("cyclo <")) lmRule.setSymbol2(Symbol.MENOR);
		else if (aux.equals("atfd >")) feRule.setSymbol1(Symbol.MAIOR);
		else if (aux.equals("atfd <")) feRule.setSymbol1(Symbol.MENOR);
		else if (aux.equals("andFE")) feRule.setCondition(Condition.AND);
		else if (aux.equals("orFE")) feRule.setCondition(Condition.OR);
		else if (aux.equals("laa >")) feRule.setSymbol2(Symbol.MAIOR);
		else if (aux.equals("laa <")) feRule.setSymbol2(Symbol.MENOR);
	}
	
}