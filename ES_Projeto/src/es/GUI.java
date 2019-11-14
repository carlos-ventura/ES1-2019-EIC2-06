package es;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GUI {

	private JFrame frame;
	private MethodList methodlist;
	private JTable table;
	public static String keep = "";
	
	private JTextField loc = new JTextField("80");
	private JTextField cyclo =new JTextField("10");
	private JTextField atfd = new JTextField("4");
	private JTextField laa = new JTextField("0.42");
	
	private void create_rule() {
		JPanel main = new JPanel();
		JPanel rule_panel = new JPanel();
		JPanel sum = new JPanel();
		sum.setLayout(new GridLayout(1,0));
		rule_panel.setLayout(new GridLayout(10, 0, 10, 10));
		JLabel rule_name = new JLabel("Rule's Name:");
		JTextField rule_new_name = new JTextField();
		JLabel c_m = new JLabel("Choose Metrics:");
		JLabel m = new JLabel("");
		JCheckBox cb_loc = new JCheckBox("LOC");
		JCheckBox cb_cyclo = new JCheckBox("CYCLO");
		JCheckBox cb_atfd = new JCheckBox("ATFD");
		JCheckBox cb_laa = new JCheckBox("LAA");
		JLabel c_t = new JLabel("Choose Thresholds:");
		JLabel t = new JLabel("");
		JLabel l_loc = new JLabel("LOC:");
		JTextField t_loc = new JTextField("0");
		JLabel l_cyclo = new JLabel("CYCLO:");
		JTextField t_cyclo = new JTextField("0");
		JLabel l_atfd = new JLabel("ATFD:");
		JTextField t_atfd = new JTextField("0");
		JLabel l_laa = new JLabel("LAA:");
		JTextField t_laa = new JTextField("0");
		JLabel final_expression = new JLabel("Final expression, complete the spaces:");

		String [] ops = {">", ">=", "<", "<=", "="};
		JList<String> operators = new JList<String>(ops);
		String [] logic = {"||", "&&"};
		JList<String> log = new JList<String>(logic);
		rule_panel.add(rule_name);
		rule_panel.add(rule_new_name);
		rule_panel.add(c_m);
		rule_panel.add(m);
		rule_panel.add(cb_loc);
		rule_panel.add(cb_cyclo);
		rule_panel.add(cb_atfd);
		rule_panel.add(cb_laa);

		rule_panel.add(c_t);
		rule_panel.add(t);

		rule_panel.add(l_loc);
		rule_panel.add(t_loc);
		rule_panel.add(l_cyclo);
		rule_panel.add(t_cyclo);
		rule_panel.add(l_atfd);
		rule_panel.add(t_atfd);
		rule_panel.add(l_laa);
		rule_panel.add(t_laa);
		rule_panel.add(final_expression);


		main.add(rule_panel);
		main.add(sum);
		JOptionPane.showConfirmDialog(
				null, main, "Make new Rule", JOptionPane.OK_CANCEL_OPTION);
	}
	private void create_objects() {
		try {
			
			//Clean list
			methodlist.clear();
			FileInputStream file = new FileInputStream(new File(keep));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Row rot=sheet.getRow(0);
			int rows = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() != CellType.BLANK) {
						if (cell.getCellType() != CellType.STRING ||
								cell.getStringCellValue().trim().length() > 0) {
							rows++;
							break;
						}
					}
				}
			}
			System.out.println(rows);
			System.out.println(sheet.getFirstRowNum());
			// -1 Header Row
			rows--;
			//Ignorar Header
			for(int i=sheet.getFirstRowNum()+1;i<=rows;i++){
				Method m= new Method();
				Row ro=sheet.getRow(i);
				if(ro != null) { // tentativa de tratar quando existem linhas brancas tanto ao inicio como no fim ou no meio
					for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
						Cell ce = ro.getCell(j);
						if(j==0){  
							int a = (int)ce.getNumericCellValue();
							m.setId(a);
						}
						else if(j==1){
							m.setPpackage(ce.getStringCellValue());
						}
						else if(j==2){
							m.setCclass(ce.getStringCellValue());
						}   
						else if(j==3){
							m.setName(ce.getStringCellValue());
						}    
						else if(j==4){
							//passa-se alguma coisa com esta coluna quando no excel eliminamos a linha completa
							//por alguma razao a linha passa como nao estando nula por isso este else gigante
							if (ce != null) {
								m.setLoc((int)ce.getNumericCellValue());
							} else {
								rows++;
							}
						}    
						else if(j==5){
							m.setCyclo((int)ce.getNumericCellValue());
						}    
						else if(j==6){
							m.setAtfd((int)ce.getNumericCellValue());
						}    
						else if(j==7){ 
							if(ce.getCellType()==CellType.STRING) {
								m.setLaa(ce.getStringCellValue()); 
							}
							else if(ce.getCellType()==CellType.NUMERIC) { 
								m.setLaa(ce.getNumericCellValue());
							}
						}    
						else if(j==8){
							m.setL_m(ce.getBooleanCellValue());
						}    
						else if(j==9){
							m.setiPlasma(ce.getBooleanCellValue());
						} 
						else if(j==10){
							m.setPmd(ce.getBooleanCellValue());
						}
						if(j==11){
							m.setF_e(ce.getBooleanCellValue());
						}
					} 
				}else {
					//dar add uma linha a matrix
					rows++;
				}
				methodlist.addMethod(m);
				workbook.close();

			} 

			file.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void gui_thresholds() {

		double [] initial = new double [4];
		initial[0]= Double.parseDouble(loc.getText());
		initial[1]= Double.parseDouble(cyclo.getText());
		initial[2]= Double.parseDouble(atfd.getText());
		initial[3]= Double.parseDouble(laa.getText());

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2, 0, 20, 10));
		myPanel.add(new JLabel("Long method"));
		myPanel.add(new JLabel("LOC:"));
		myPanel.add(loc);
		myPanel.add(new JLabel("CYCLO:"));
		myPanel.add(cyclo);
		myPanel.add(new JLabel("Feature envy"));
		myPanel.add(new JLabel("ATFD:"));
		myPanel.add(atfd);
		myPanel.add(new JLabel("LAA:"));
		myPanel.add(laa);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			loc.setText(String.valueOf(initial[0]));
			cyclo.setText(String.valueOf(initial[1]));
			atfd.setText(String.valueOf(initial[2]));
			laa.setText(String.valueOf(initial[3]));
		}
		if(result==JOptionPane.OK_OPTION) {
			for(int i=0;i<methodlist.getColumnCount();i++) {
				table.setDefaultRenderer(table.getColumnClass(i), new MethodCellRenderer(Integer.parseInt(loc.getText()),Integer.parseInt(cyclo.getText())));
			}
			methodlist.fireTableDataChanged();
		}


	}
	
	
	public void create_GUI() {
		JScrollPane scrollPane=new JScrollPane(table);
		frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setLayout(new GridLayout(2,0));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		JButton clear_file = new JButton("Clear File");
		JButton file = new JButton("Choose File");
		
		JButton thresholds = new JButton("Change Thresholds");
		JButton new_rule = new JButton("New Rule");

		clear_file.setPreferredSize(new Dimension(300,50));
		file.setPreferredSize(new Dimension(300,50));

		thresholds.setPreferredSize(new Dimension(300,50));
		new_rule.setPreferredSize(new Dimension(300,50));


		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls", "xlsx");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();

					keep = selectedFile.getAbsolutePath();
					if(keep.contains("xlx") || keep.contains("xlsx"))
						create_objects();

				} else if(returnVal == JFileChooser.CANCEL_OPTION ) {
					return;
				}
			}
		});
		
		clear_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				methodlist.clear();
				methodlist.fireTableDataChanged();
			}
		});
		
		thresholds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui_thresholds();
			}
		});
		new_rule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create_rule();
				
			}
			
		});
		panel1.add(file);
		panel1.add(clear_file);
		panel1.add(scrollPane);
		panel2.add(thresholds);
		panel2.add(new_rule);
		frame.add(panel1);
		frame.add(panel2);


	}
	
	public GUI() {
		frame = new JFrame("Avaliacao da qualidade de detecao de defeitos de desenho em projetos de software");
		methodlist= new MethodList();
		table=new JTable(methodlist);
		for(int i=0;i<methodlist.getColumnCount();i++) {
			table.setDefaultRenderer(table.getColumnClass(i), new MethodCellRenderer(Integer.parseInt(loc.getText()),Integer.parseInt(cyclo.getText())));
		}
		table.setPreferredScrollableViewportSize(new Dimension(1100,295));
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		create_GUI();
	}
	
	public void open() {
		frame.setSize(1500, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.open();

	}

}
