package es;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * Classe que trata da tabela que contem os dados do ficheiro excel e 2 colunas para os valores de Long Method e Feature Envy
 * definidos na regra criada pelo utilizador
 *
 */
public class DetailTable extends MethodTable {

	/**
	 * Array de Strings com os nomes das colunas da tabela
	 */
	final String[] columnNames = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "is_long_method(new)", "iPlasma", "PMD", 
			"is_feature_envy", "is_feature_envy(new)"};

	/**
	 * Array de Classes que define o tipo de dados de cada coluna da tabela
	 */
	final Class[] columnClasses = {Integer.class, String.class, String.class, String.class , Integer.class, Integer.class, Integer.class,
			Double.class,Boolean.class,Boolean.class, Boolean.class, Boolean.class};  
	
	/**
	 * Vetor com toda a informação lida do ficheiro excel, cada elemento corresponde a uma linha da tabela
	 */
	final Vector data = new Vector();

	/**
	 * Objeto do tipo LMRule que vai guardar os valores e a logica da regra criada pelo utilizador,
	 * no inicio do programa estes valores são os valores por defeito no enunciado do projeto
	 */
	private LMRule lmRule=new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
	
	/**
	 * Objeto do tipo FERule que vai guardar os valores e a logica da regra criada pelo utilizador,
	 * no inicio do programa estes valores são os valores por defeito no enunciado do projeto
	 */
	private FERule feRule=new FERule(Symbol.MAIOR,4,Condition.AND,Symbol.MENOR,0.42);

	/**
	 * Método que altera o valor do atributo lmRule
	 * @param lmRule Valor que o atributo lmRule irá tomar
	 */
	public void setRMRule(LMRule lmRule) {
		this.lmRule=lmRule;
	}
	
	/**
	 * Método que altera o valor do atributo feRule
	 * @param feRule Valor que o atributo feRule irá tomar
	 */
	public void setFERule(FERule feRule) {
		this.feRule=feRule;
	}
	
	/**
	 * Método que devolve um array de Strings em que cada elemento contem o nome de uma coluna da tabela
	 * @return Array de Strings com o nome das colunas
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * Método que devolve um array de Classes em que cada elemento contem o tipo de conteúdo de uma coluna da tabela
	 * @return Array de Classes com o tipo de dados das colunas
	 */
	public Class[] getColumnClasses() {
		return columnClasses;
	}
	
	/**
	 * Método que devolve o vetor que contém toda a informação da tabela
	 * @return Vetor com os dados da tabela
	 */
	public Vector getData() {
		return data;
	}

	
	public void clear() {
		data.clear();
	}
	
	
	public void addMethod(Method m) {
		data.addElement(m);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	
	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class getColumnClass(int c) {
		return columnClasses[1];
	}

	public Object getValueAtRow(int row) {
		Method m = (Method) data.elementAt(row);
		return m;
	}
	@Override
	public Object getValueAt(int row, int col) {
		Method m = (Method) data.elementAt(row);
		boolean aux=m.is_long_method((int)lmRule.getVar1(), (int)lmRule.getVar2(), lmRule.getSymbol1(), lmRule.getSymbol2(), lmRule.getCondition());
		boolean aux2=m.isF_e_rule((int)feRule.getVar1(), (double)feRule.getVar2(), feRule.getSymbol1(), feRule.getSymbol2(), feRule.getCondition());
		if (col == 0)      return m.getId();
		else if (col == 1) return m.getPpackage();
		else if (col == 2) return m.getCclass();
		else if (col == 3) return m.getName();
		else if (col == 4) return m.getLoc();
		else if (col == 5) return m.getCyclo();
		else if (col == 6) return m.getAtfd();
		else if (col == 7) return m.getLaa();
		else if (col == 8) return m.isL_m();
		else if (col == 9) return aux; 
		else if (col == 10) return m.isiPlasma();
		else if (col == 11) return m.isPmd();
		else if (col == 12) return m.isF_e();
		else if (col == 13) return aux2; 
		else return null;
	}
	
	
	/**
	 * Método que devolve a regra criada pelo utilizador relativa ao Long Method
	 * @return Devolve o atributo lmRule, que contém um objeto do tipo LMRule
	 */
	public Rule getLMRule() {
		
		return lmRule;
	}
	
	/** 
 	 * Método que devolve a regra criada pelo utilizador relativa ao Feature Envy
	 * @return Devolve o atributo feRule, que contém um objeto do tipo FERule
	 */
	public Rule getFERule() {
	
		return feRule;
	}
}

class DetailMethodCellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		MethodTable wtm = (MethodTable) table.getModel();
		Method m = (Method) wtm.getValueAtRow(row);  
		if ((column == 9 && (boolean)wtm.getValueAt(row,column)!=m.isL_m()) || (column==13  && (boolean)wtm.getValueAt(row,column)!=m.isF_e())) {
			setBackground(Color.red);
	    }else {
	    	setBackground(Color.white);
	    }
		return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
	}
}