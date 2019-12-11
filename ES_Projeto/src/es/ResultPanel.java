package es;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ResultPanel extends JPanel{
	private int dci=0;
	private int dii=0;
	private int adci=0;
	private int adii=0;
	private ResultTable result;
	
	public ResultPanel() {
		result=new ResultTable();
		setLayout(new GridLayout(2,0));
		result.addLine(new Line("iPlasma",0,0,0,0));
		result.addLine(new Line("PMD",0,0,0,0));
		result.addLine(new Line("New Rule",0,0,0,0));
		JTable resultTable = new JTable(result);
		JScrollPane sp=new JScrollPane(resultTable);
		JPanel aux=new JPanel();
		
		JLabel lb=new JLabel("GLOBAL RESULTS");
		aux.add(lb);
		add(aux,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		
	}
	public void checkErrors(MethodTable methodTable, LMRule lmRule) {
		result.clear();
		for(int i = 0; i<methodTable.getRowCount(); i++ ) {
			Method m = (Method)methodTable.getValueAtRow(i);			
			if (m.isL_m() && m.isiPlasma())
				dci++;
			if (m.isL_m() && !m.isiPlasma())
				dii++;
			
			if (!m.isL_m() && !m.isiPlasma())
				adci++;
			
			if (!m.isL_m() && m.isiPlasma())
				adii++;
			
		}	
		result.addLine(new Line("iPlasma",dci,dii,adci,adii));
		dci=dii=adci=adii=0;
		for(int i = 0; i<methodTable.getRowCount(); i++ ) {
			Method m = (Method)methodTable.getValueAtRow(i);			
			if (m.isL_m() && m.isPmd())
				dci++;	
				
			if (m.isL_m()==true && m.isPmd()==false)
				dii++;	
					
			if (!m.isL_m() && !m.isPmd())
				adci++;
			
			if (!m.isL_m() && m.isPmd())
				adii++;
		}
		result.addLine(new Line("PMD",dci,dii,adci,adii));
		dci=dii=adci=adii=0;
		boolean aux;
		for(int i = 0; i<methodTable.getRowCount(); i++ ) {
			Method m = (Method)methodTable.getValueAtRow(i);	
			aux=m.is_long_method((int)lmRule.getVar1(), (int)lmRule.getVar2());
			if (aux && m.isL_m())
				dci++;
			if (aux && !m.isL_m())
				dii++;

			if (!aux && !m.isL_m())
				adci++;

			if (!aux && m.isL_m())
				adii++;

		}	
		result.addLine(new Line("New Is_Long_Method",dci,dii,adci,adii));

		dci=dii=adci=adii=0;
	}
	
	public void clear() {
		result.clear();
		result.fireTableDataChanged();
	}
	
class Line{
	private String  tool;
	private int lineDCI ;
	private int lineDII;
	private int lineADCI;
	private int lineADII ;
	public Line(String tool,int lineDCI,int lineDII,int lineADCI,int lineADII) {
		this.tool=tool;
		this.lineDCI=lineDCI;
		this.lineDII=lineDII;
		this.lineADCI=lineADCI;
		this.lineADII=lineADII;
	}
	public String getTool() {
		return tool;
	}
	public int getLineDCI() {
		return lineDCI;
	}
	public int getLineDII() {
		return lineDII;
	}
	public int getLineADCI() {
		return lineADCI;
	}
	public int getLineADII() {
		return lineADII;
	}
	public String toString() {
	      return "[" + tool + ", " + lineDCI + ", " + lineDII + ", " + lineADCI + ", " +lineADII+ "]"; }
}

class ResultTable extends AbstractTableModel{
	final String[] columnNames = {"TOOL","DCI", "DII", "ADCI", "ADII"};
	  
	   // holds the data types for all our columns
	   final Class[] columnClasses = {String.class, Integer.class,Integer.class, Integer.class, Integer.class};
	  
	   // holds our data
	   final Vector data = new Vector();
	// adds a row
	   public void addLine(Line w) {
	      data.addElement(w);
	      fireTableRowsInserted(data.size()-1, data.size()-1);
	   }
	   public void clear() {
			data.clear();
		}
	@Override
	public int getRowCount() {
	      return data.size();
	   }
	public Object getValueAtRow(int row) {
	      Line m = (Line) data.elementAt(row);
	      return m;
	}
	public String getColumnName(int col) {
	      return columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
	      return columnNames.length;
	   }
	@Override
	public Object getValueAt(int row, int col) {
	      Line line = (Line) data.elementAt(row);
	      if (col == 0)      return line.getTool();
	      else if (col == 1) return line.getLineDCI();
	      else if (col == 2) return line.getLineDII();
	      else if (col == 3) return line.getLineADCI();
	      else if (col == 4) return line.getLineADII();
	      else return null;
	   }
}
}