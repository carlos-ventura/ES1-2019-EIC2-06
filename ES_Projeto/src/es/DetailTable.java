package es;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class DetailTable extends MethodTable {

	final String[] columnNames = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "is_long_method(new)", "iPlasma", "PMD", 
			"is_feature_envy", "is_feature_envy(new)"};

	final Class[] columnClasses = {Integer.class, String.class, String.class, String.class , Integer.class, Integer.class, Integer.class,
			Double.class,Boolean.class,Boolean.class, Boolean.class, Boolean.class};  
	
	final Vector data = new Vector();

	
	private LMRule lmRule=new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
	
	private FERule feRule=new FERule(Symbol.MAIOR,4,Condition.AND,Symbol.MENOR,0.42);


	public void setRMRule(LMRule lmRule) {
		this.lmRule=lmRule;
	}
	
	public void setFERule(FERule feRule) {
		this.feRule=feRule;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}

	
	public Class[] getColumnClasses() {
		return columnClasses;
	}
	
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
	
	

	public Rule getLMRule() {
		
		return lmRule;
	}
	
	
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