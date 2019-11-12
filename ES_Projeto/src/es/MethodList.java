package es;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MethodList extends AbstractTableModel {
	
	private final String[] columnNames = {"MethodID","package","class","method","LOC","CYCLO","ATFD","LAA","is_long_method","iPlasma","is_feature_envy"};
	
	//holds the data type for all columns
	private final Class[] columnClasses = {Integer.class,String.class,String.class,String.class,Integer.class,Integer.class,Integer.class,
			Double.class,Boolean.class,Boolean.class,Boolean.class,Boolean.class};
	
	//holds our data
	private final Vector data = new Vector();
	
	public void clear() {
		data.clear();
	}
	
	public void addMethod(Method m) {
		data.addElement(m);
		fireTableRowsInserted(data.size()-1,data.size()-1);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int col) {
		return columnClasses[col];
	}
	
	public Object getValueAtRow(int row) {
		Method m = (Method) data.elementAt(row);
		return m;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Method m = (Method) data.elementAt(rowIndex);
		if(columnIndex==0) return m.getId();
		else if (columnIndex==1) return m.getPpackage();
		else if (columnIndex==2) return m.getCclass(); 
		else if (columnIndex==3) return m.getName();
		else if (columnIndex==4) return m.getLoc();
		else if (columnIndex==5) return m.getCyclo();
		else if (columnIndex==6) return m.getAtfd();
		else if (columnIndex==7) return m.getLaa();
		else if (columnIndex==8) return m.isL_m();
		else if (columnIndex==9) return m.isiPlasma();
		else if (columnIndex==10) return m.isPmd();
		else if (columnIndex==11) return m.isF_e();
		return null;
	}
}

class MethodCellRenderer extends DefaultTableCellRenderer{
	private int loc;
	private int cyclo;
	
	public MethodCellRenderer(int loc, int cyclo) {
		this.loc=loc;
		this.cyclo=cyclo;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int column) {
		MethodList ml=(MethodList) table.getModel();
		Method m=(Method) ml.getValueAtRow(row);
		if(column==8 && m.is_long_method(loc, cyclo)) {
			setBackground(Color.RED);
		}else {
			setBackground(Color.WHITE);
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
	}
}
