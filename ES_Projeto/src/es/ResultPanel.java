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

/**
 * Classe responsavel pela criação da tabela com a informação acerca das diferentes ferramentas e os erros detetados
 *
 */
public class ResultPanel extends JPanel{
	private int dci=0;
	private int dii=0;
	private int adci=0;
	private int adii=0;
	private ResultTable result;
	
	/**
	 * Construtor da tabela com a informação acerca das diferentes ferramentas e os erros detetados
	 */
	public ResultPanel() {
		result=new ResultTable();
		setLayout(new BorderLayout());
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
	
	/**
	 * Método que calcula o número de erros relativos segundo os valores de DCI, DII, ADCI e ADII
	 * @param methodTable Tabela com os dados do ficheiro excel
	 * @param lmRule Regra definida pelo utilizador
	 */
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
			aux=m.is_long_method((int)lmRule.getVar1(), (int)lmRule.getVar2(), lmRule.getSymbol1(), lmRule.getSymbol2(), lmRule.getCondition());
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

	/**
	 * Método que faz a limpeza do conteúdo da tabela
	 */
	public void clear() {
		result.clear();
		result.fireTableDataChanged();
	}

	/**
	 * Getter do atributo resultTable
	 * @return Objeto do tipo ResultTable
	 */
	public ResultTable getResult() {
		return result;
	}


	/**
	 * 
	 * classe que representa uma linha da tabela com a informação do ficheiro excel e com duas colunas adicionais para a visualização 
	 * do Long Method e do Feature Envy segundo a regra definida pelo utilizador
	 *
	 */
	public	class Line{
		private String  tool;
		private int lineDCI ;
		private int lineDII;
		private int lineADCI;
		private int lineADII ;
		
		/**
		 * Construtor da classe Line 
		 * @param tool Ferramenta utilizada(iPlasma, PMD, ou regra definida pelo utilizador)
		 * @param lineDCI Número de DCI
		 * @param lineDII Número de DII
		 * @param lineADCI Número de ADCI
		 * @param lineADII Número de ADII
		 */
		public Line(String tool,int lineDCI,int lineDII,int lineADCI,int lineADII) {
			this.tool=tool;
			this.lineDCI=lineDCI;
			this.lineDII=lineDII;
			this.lineADCI=lineADCI;
			this.lineADII=lineADII;
		}

		/**
		 * Getter do atributo Tool
		 * @return Valor do atributo Tool
		 */
		public String getTool() {
			return tool;
		}
		
		/**
		 * Getter to atributo DCI
		 * @return Valor do atributo DCI
		 */
		public int getLineDCI() {
			return lineDCI;
		}
		
		/**
		 * Getter do atributo DII
		 * @return Valor do atributo DII
		 */
		public int getLineDII() {
			return lineDII;
		}
	
		/**
		 * Getter do atributo ADCI
		 * @return Valor do atributo ADCI
		 */
		public int getLineADCI() {
			return lineADCI;
		}
		
		/**
		 * Getter do atributo ADII
		 * @return Valor do atributo ADII
		 */
		public int getLineADII() {
			return lineADII;
		}
		
		/**
		 * Método toString 
		 */
		public String toString() {
			return "[" + tool + ", " + lineDCI + ", " + lineDII + ", " + lineADCI + ", " +lineADII+ "]"; }
	}

	/**
	 *
	 * Classe responsavel pela tabela com a informação do ficheiro excel e com duas colunas adicionais para a visualização 
	 * do Long Method e do Feature Envy segundo a regra definida pelo utilizador
	 */
	public class ResultTable extends AbstractTableModel{
		
		/**
		 * Array de Strings com os nomes das colunas da tabela
		 */
		final String[] columnNames = {"TOOL","DCI", "DII", "ADCI", "ADII"};
		
		/**
		 * Array de Classes que define o tipo de dados de cada coluna da tabela
		 */
		final Class[] columnClasses = {String.class, Integer.class,Integer.class, Integer.class, Integer.class};
		
		/**
		 * Vetor com toda a informação lida do ficheiro excel, cada elemento corresponde a uma linha da tabela
		 */
		final Vector data = new Vector();
		
		/**
		 * Método que adiciona o argumento w ao vetor que contem os dados da tabela
		 * @param w Objeto do tipo Line que contém conteúdo de uma linha da tabela
		 * 
		 * 
		 */
		public void addLine(Line w) {
			data.addElement(w);
			fireTableRowsInserted(data.size()-1, data.size()-1);
		}
		
		/**
		 * Método que faz a limpeza do conteúdo da tabela
		 */
		public void clear() {
			data.clear();
		}
		
		/**
		 * Getter do número de linhas da tabela
		 * @return Tamanho do vetor data
		 */
		@Override
		public int getRowCount() {
			return data.size();
		}
		
		/**
		 * Getter do conteúdo da linha no índice fornecido
		 * @param row Índice da linha
		 * @return Objeto do tipo Line
		 */
		public Object getValueAtRow(int row) {
			Line m = (Line) data.elementAt(row);
			return m;
		}
		
		/**
		 * Getter do nome da coluna no índice fornecido
		 * @param col Índice da coluna
		 * @return Elemento de columnNames no índice col
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		/**
		 * Getter do número de colunas da tabela
		 * @return Comprimento do atributo columnNames
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
		/**
		 * Getter do conteúdo de uma célula nos índices da coluna e linhas fornecidos
		 * @param row Índice da linha
		 * @param col Índice da coluna
		 * @return Conteúdo da célula
		 */
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