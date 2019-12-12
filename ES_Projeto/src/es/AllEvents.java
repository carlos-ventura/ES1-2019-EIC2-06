package es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
/**
 * Classe responsavel pela regra criada pelo utilizador
 *
 */
public class AllEvents implements ActionListener{
	
	private LMRule lmRule;
	private FERule feRule;

	/**
	 * Construtor da classe AllEvents para a criação da regra definida pelo utilizador
	 * @param lmRule Parte de regra relativa ao Long Method
	 * @param feRule Parte da regra relativa ao Feature Envy
	 */
	public AllEvents(LMRule lmRule, FERule feRule) {
		this.lmRule = lmRule;
		this.feRule = feRule;
	}

	/**
	 * Método que permite adicionar propriedades à regra
	 * @param me JRadioButton que o utilizador clicou para a criação da regra
	 * @param s String que indica o que o utilizador escolheu
	 */
	public void addProperties(JRadioButton me,String s) {
		me.setActionCommand(s);
		me.addActionListener(this);
	}
	
	/**
	 * Método que é utilizado quando o utilizador clica num radio button
	 */
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