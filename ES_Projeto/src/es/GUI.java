package es;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.DetailTable;
import es.ResultPanel;

/**
 * Classe responsavel pela criação da GUI principal e da
 * GUI respetiva à criação de uma nova regra
 */
public class GUI extends JFrame {
	private MethodTable methodTable;
	private DetailTable detailTable;
	private JTable table;
	private JTextField is_L;
	private JTextField is_F;
	private JTextField loc = new JTextField("80");
	private JTextField cyclo =new JTextField("10");
	private JTextField atfd = new JTextField("4");
	private JTextField laa = new JTextField("0.42");
	private JTable dtable;
	
	private LMRule lmRule=new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
	private FERule feRule=new FERule(Symbol.MAIOR,4,Condition.AND,Symbol.MENOR,0.42);
	
	private ResultPanel resultp = new ResultPanel();
	
	/**
	 * Metodo que trata da criacao dos aspetos primerio da GUI
	 * Trata das acoes de pressionar em botoes
	 */
	public void create_GUI() {
		methodTable = new MethodTable();
		detailTable = new DetailTable();
	    table = new JTable(getMethodTable());
	    dtable = new JTable(getDetailTable());
	    for (int i =0; i<getMethodTable().getColumnCount();i++) {
	         getTable().setDefaultRenderer(getTable().getColumnClass(i), new MethodCellRenderer());
	    }
		getTable().setFillsViewportHeight(true);
		getTable().setDefaultEditor(Object.class, null);
		
		for (int i =0; i<getDtable().getColumnCount();i++) {
			getDtable().setDefaultRenderer(getDtable().getColumnClass(i), new DetailMethodCellRenderer());
		 }
	    
		getDtable().setFillsViewportHeight(true);
		getDtable().setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(getTable());
		JScrollPane scrollPane2 = new JScrollPane(getDtable());
		
		
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new GridLayout(2,0));
		
		JButton file = new JButton("Choose File");
		JButton clear = new JButton("Clear File");

		JButton thresholds = new JButton("Change Thresholds");

		clear.setPreferredSize(new Dimension(300, 50));
		file.setPreferredSize(new Dimension(300, 50));
		thresholds.setPreferredSize(new Dimension(300, 50));
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		resultp = new ResultPanel();
		file.addActionListener(new ExcelReader(getMethodTable(), getDetailTable(), getResultp(),getLmRule()));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMethodTable().clear();
				getMethodTable().fireTableDataChanged();	
				getDetailTable().clear();
				getDetailTable().fireTableDataChanged();
				getResultp().clear();	
			}
			
		});
		thresholds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui_thresholds();
			}
		});
		JPanel rulesp=new JPanel();
		rulesp.setLayout(new GridLayout(0,2));
		is_L=new JTextField(getLmRule().toString());
		is_L.setEditable(false);
		is_L.setBackground(Color.LIGHT_GRAY);
		is_F=new JTextField(getFeRule().toString());
		is_F.setEditable(false);
		is_F.setBackground(Color.LIGHT_GRAY);
		rulesp.add(is_L);
		rulesp.add(is_F);
		JPanel bp=new JPanel();
		bp.add(thresholds);
		rulesPanel.add(rulesp);
		rulesPanel.add(bp);
		JPanel bp2=new JPanel();
		bp2.add(file);
		bp2.add(clear);
		panel1.add(bp2,BorderLayout.NORTH);
		panel1.add(scrollPane);
		JLabel lb=new JLabel("RESULTS");
		JPanel auxpn=new JPanel();
		auxpn.add(lb);
		panel3.add(auxpn,BorderLayout.NORTH);
		panel3.add(scrollPane2,BorderLayout.CENTER);
		add(panel1);
		add(rulesPanel);
		add(panel3);
		add(getResultp());
	}
	
	/**
	 * Metodo que permite alterar os thresholds das metricas em relacao a detencao de defeitos
	 * long method e feature envy
	 */
	private void gui_thresholds() {
		double [] initial = new double [4];
		initial[0]= Double.parseDouble(loc.getText());
		initial[1]= Double.parseDouble(cyclo.getText());
		initial[2]= Double.parseDouble(atfd.getText());
		initial[3]= Double.parseDouble(laa.getText());
		AllEvents m=new AllEvents(lmRule,feRule);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(3,0));
		
		JPanel panellm=new JPanel();
		panellm.setLayout(new FlowLayout());
		JLabel lmname= new JLabel("Long Method -> ");
		panellm.add(lmname);
		//grupo de LOC > ou <
		ButtonGroup groupLoc = new ButtonGroup();
		JLabel loclb=new JLabel("LOC: ");
		JRadioButton maiorLoc = new JRadioButton(">");		
		m.addProperties(maiorLoc,"loc >");
		JRadioButton menorLoc = new JRadioButton("<");
		m.addProperties(menorLoc,"loc <");
		groupLoc.add(maiorLoc);
		groupLoc.add(menorLoc);
		panellm.add(loclb);
		panellm.add(maiorLoc);
		panellm.add(menorLoc);
		panellm.add(loc);
		
		//grupo de AND and OR
		ButtonGroup groupConditional = new ButtonGroup();
		JRadioButton andC = new JRadioButton("AND");
		m.addProperties(andC,"andLM");
		JRadioButton orC = new JRadioButton("OR");
		m.addProperties(orC,"orLM");
		groupConditional.add(andC);
		groupConditional.add(orC);
		panellm.add(andC);
		panellm.add(orC);
		
		//grupo de CYCLO > ou <
		ButtonGroup groupCyclo = new ButtonGroup();
		JLabel cyclolb=new JLabel("CYCLO:");
		JRadioButton maiorCyclo = new JRadioButton(">");
		m.addProperties(maiorCyclo,"cyclo >");
		JRadioButton menorCyclo = new JRadioButton("<");
		m.addProperties(menorCyclo,"cyclo <");
		
		groupCyclo.add(maiorCyclo);
		groupCyclo.add(menorCyclo);
		panellm.add(cyclolb);
		panellm.add(maiorCyclo);
		panellm.add(menorCyclo);
		panellm.add(cyclo);
		
		//Panel de Feature Envy
		JPanel panelfe=new JPanel();
		panelfe.setLayout(new FlowLayout());
		JLabel fename= new JLabel("Feature Envy ->");
		panelfe.add(fename);
		
		//grupo de ATFD > ou <
		
		ButtonGroup groupAtfd = new ButtonGroup();
		JLabel atfdlb=new JLabel("ATFD:");
		JRadioButton maiorAtfd = new JRadioButton(">");
		m.addProperties(maiorAtfd,"atfd >");
		JRadioButton menorAtfd = new JRadioButton("<");
		m.addProperties(menorAtfd,"atfd <");
		
		groupAtfd.add(maiorAtfd);
		groupAtfd.add(menorAtfd);
		panelfe.add(atfdlb);
		panelfe.add(maiorAtfd);
		panelfe.add(menorAtfd);
		panelfe.add(atfd);
		
		//grupo de AND and OR
		ButtonGroup groupConditionalfe = new ButtonGroup();
		JRadioButton andCfe = new JRadioButton("AND");
		m.addProperties(andCfe,"andFE");
		JRadioButton orCfe = new JRadioButton("OR");
		m.addProperties(orCfe,"orFE");
		groupConditionalfe.add(andCfe);
		groupConditionalfe.add(orCfe);
		panelfe.add(andCfe);
		panelfe.add(orCfe);
		
		//grupo de LAA > ou <
		ButtonGroup groupLaa = new ButtonGroup();
		JLabel laalb=new JLabel(" LAA:  ");
		JRadioButton maiorLaa = new JRadioButton(">");
		m.addProperties(maiorLaa,"laa >");
		JRadioButton menorLaa = new JRadioButton("<");
		m.addProperties(menorLaa,"laa <");
		groupLaa.add(maiorLaa);
		groupLaa.add(menorLaa);
		panelfe.add(laalb);
		panelfe.add(maiorLaa);
		panelfe.add(menorLaa);
		panelfe.add(laa);
		
		panel.add(panellm);
		panel.add(panelfe);
		int result = JOptionPane.showConfirmDialog(null, panel,"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			loc.setText(String.valueOf(initial[0]));
			cyclo.setText(String.valueOf(initial[1]));
			atfd.setText(String.valueOf(initial[2]));
			laa.setText(String.valueOf(initial[3]));
		}
		
		if(result ==JOptionPane.OK_OPTION) {
			for (int i =0; i<getMethodTable().getColumnCount();i++) {
		         getTable().setDefaultRenderer(getTable().getColumnClass(i), new MethodCellRenderer());
		    }
			methodTable.fireTableDataChanged();	
			try {
				lmRule.setVar1(Double.valueOf(loc.getText()));
				lmRule.setVar2(Double.valueOf(cyclo.getText()));
				feRule.setVar1(Double.valueOf(atfd.getText()));
				feRule.setVar2(Double.valueOf(laa.getText()));
			} catch ( java.lang.NumberFormatException e) {
				
			}
			is_L.setText(getLmRule().toString());
			is_F.setText(getFeRule().toString());
			resultp.checkErrors(getMethodTable(),getLmRule());
			detailTable.setRMRule(getLmRule());
			detailTable.setFERule(getFeRule());
			detailTable.fireTableDataChanged();
		}
	}

	/** 
	 * Construtor da interface visual
	 */
	public GUI() {
		setTitle("Avaliação da qualidade de deteção de defeitos de desenho em projetos de software");
		setLayout(new GridLayout(4,0));
	    create_GUI();
	}
	

	/**
	 * Metodo para abrir a GUI  permitir a sua visualizacao com tamanho predefinido
	 */
	public void open() {
		setSize(1200, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	/**
	 * Método main para iniciar o programa
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		GUI f=new GUI();
		f.open();
	}

	/**
	 * Método que devolve a tabela que contém os dados do ficheiro excel e as duas colunas que dizem se o método é Long Method ou Feature Envy segundo a regra definida pelo utilizador
	 * @return Devolve o atributo dtable que contém um objeto do tipo JTable
	 */
	public JTable getDtable() {
		return dtable;
	}

	/**
	 * Método que devolve a tabela que contém os dados do ficheiro excel
	 * @return Devolve o atributo table, que contém um objeto do tipo JTable 
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Método que devolve a tabela que contém os dados do ficheiro excel e as duas colunas que dizem se o método é Long Method ou Feature Envy segundo a regra definida pelo utilizador
	 * @return Devolve o atributo dtable que contém um objeto do tipo MethodTable
	 */
	public MethodTable getMethodTable() {
		return methodTable;
	}

	/**
	 * Método que devolve a tabela que contém os dados do ficheiro excel
	 * @return Devolve o atributo detailTable, que contém um objeto do tipo MethodTable
	 */
	public DetailTable getDetailTable() {
		return detailTable;
	}

	/**
	 * Método que devolve a tabela com a informação acerca das diferentes ferramentas e os erros detetados
	 * @return Devolve o atributo resultp, que contém um objeto do tipo ResultTable
	 */
	public ResultPanel getResultp() {
		return resultp;
	}

	/**
	 * Método que devolve a regra criada pelo utilizador relativa ao Long Method
	 * @return Devolve o atributo lmRule, que contém um objeto do tipo LMRule
	 */
	public LMRule getLmRule() {
		return lmRule;
	}
	
	/** 
 	 * Método que devolve a regra criada pelo utilizador relativa ao Feature Envy
	 * @return Devolve o atributo feRule, que contém um objeto do tipo FERule
	 */
	public FERule getFeRule() {
		return feRule;
	}
}