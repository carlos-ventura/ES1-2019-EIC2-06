package es;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class MethodTable extends AbstractTableModel {
	
	final String[] columnNames = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma", "PMD", 
	"is_feature_envy"};
	
	final Class[] columnClasses = {Integer.class, String.class, String.class, String.class , Integer.class, Integer.class, Integer.class,
			Double.class,Boolean.class,Boolean.class, Boolean.class, Boolean.class};  
	
	final Vector data = new Vector();
	
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
		if (col == 0)      return m.getId();
		else if (col == 1) return m.getPpackage();
		else if (col == 2) return m.getCclass();
		else if (col == 3) return m.getName();
		else if (col == 4) return m.getLoc();
		else if (col == 5) return m.getCyclo();
		else if (col == 6) return m.getAtfd();
		else if (col == 7) return m.getLaa();
		else if (col == 8) return m.isL_m();
		else if (col == 9) return m.isiPlasma();
		else if (col == 10) return m.isPmd();
		else if (col == 11) return m.isF_e();
		else return null;
	}
}

class MethodCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		MethodTable wtm = (MethodTable) table.getModel();
		Method m = (Method) wtm.getValueAtRow(row);  
		return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
	}
}