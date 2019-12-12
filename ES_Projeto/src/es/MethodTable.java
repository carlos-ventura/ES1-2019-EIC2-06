package es;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * Classe responsavel pela tabela com a informa��o do ficheiro excel
 *
 */
public class MethodTable extends AbstractTableModel {
	
	/**
	 * Array de Strings com os nomes das colunas da tabela
	 */
	final String[] columnNames = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma", "PMD", 
	"is_feature_envy"};
	
	/**
	 * Array de Classes que define o tipo de dados de cada coluna da tabela
	 */
	final Class[] columnClasses = {Integer.class, String.class, String.class, String.class , Integer.class, Integer.class, Integer.class,
			Double.class,Boolean.class,Boolean.class, Boolean.class, Boolean.class};  
	
	/**
	 * Vetor com toda a informa��o lida do ficheiro excel, cada elemento corresponde a uma linha da tabela
	 */
	final Vector data = new Vector();
	
	/**
	 * M�todo que faz a limpeza do conte�do da tabela
	 */
	public void clear() {
		data.clear();
	}
	
	/**
	 * M�todo que adiciona o argumento m ao vetor que contem os dados da tabela
	 * @param m Objeto do tipo Method que cont�m conte�do de uma linha da tabela
	 * 
	 * 
	 */
	public void addMethod(Method m) {
		data.addElement(m);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}

	/**
	 * Getter do n�mero de linhas da tabela
	 * @return Tamanho do vetor data
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/**
	 * Getter do n�mero de colunas da tabela
	 * @return Comprimento do atributo columnNames
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Getter do nome da coluna no �ndice fornecido
	 * @param col �ndice da coluna
	 * @return Elemento de columnNames no �ndice col
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * Getter do tipo de dados da coluna
	 * @param c �ndice da coluna
	 * @return Elemento de columnClasses
	 */
	public Class getColumnClass(int c) {
		return columnClasses[1];
	}
	
	/**
	 * Getter do conte�do da linha no �ndice fornecido
	 * @param row �ndice da linha
	 * @return Objeto do tipo Method
	 */
	public Object getValueAtRow(int row) {
		Method m = (Method) data.elementAt(row);
		return m;
	}

	/**
	 * Getter do conte�do de uma c�lula nos �ndices da coluna e linhas fornecidos
	 * @param row �ndice da linha
	 * @param col �ndice da coluna
	 * @return Conte�do da c�lula
	 */
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

/**
 * 
 * Classe para poder editar o conte�do de uma linha da tabela (mudar a cor, tipo de letra, etc)
 *
 */
class MethodCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		MethodTable wtm = (MethodTable) table.getModel();
		Method m = (Method) wtm.getValueAtRow(row);  
		return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
	}
}