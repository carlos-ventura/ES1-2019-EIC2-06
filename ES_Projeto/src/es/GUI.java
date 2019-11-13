package es;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GUI {

	private JFrame frame;
	private JButton file;
	private JPanel panel1;
	private JPanel panel2;
	private JButton thresholds;
	private JButton new_rule;
	private JButton clear_file;

	private JTable myTable;
	private ArrayList<Method> methodslist;
	private ArrayList<Method> deletelist;
	private JScrollPane scrollPane;
	private String [][] data;
	private DefaultTableModel model;
	private String [][] data_test;

	private String[] c_names = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "iPlasma", "PMD", "is_feature_envy"};

	public static String keep = "";

	public GUI() {
		methodslist = new ArrayList<>();
		deletelist = new ArrayList<>();
		frame = new JFrame("Avaliacao da qualidade de detecao de defeitos de desenho em projetos de software");
		create_GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private void create_objects() {
		try {
			
			//Clean list
			methodslist.clear();
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
			data_test = new String[rows][rot.getLastCellNum()];

			//Ignorar Header
			for(int i=sheet.getFirstRowNum()+1;i<=rows;i++){
				Method m= new Method();
				Row ro=sheet.getRow(i);
				if(ro != null) { // tentativa de tratar quando existem linhas brancas tanto ao inicio como no fim ou no meio
					for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
						Cell ce = ro.getCell(j);
						if(j==0){  
							int a = (int)ce.getNumericCellValue();
							data_test[i-1][j] = String.valueOf(a);
							m.setId(a);
						}
						else if(j==1){
							data_test[i-1][j] = ce.getStringCellValue();
							m.setPpackage(ce.getStringCellValue());
						}
						else if(j==2){
							data_test[i-1][j] = ce.getStringCellValue();
							m.setCclass(ce.getStringCellValue());
						}   
						else if(j==3){
							data_test[i-1][j] = ce.getStringCellValue();
							m.setName(ce.getStringCellValue());
						}    
						else if(j==4){
							//passa-se alguma coisa com esta coluna quando no excel eliminamos a linha completa
							//por alguma razao a linha passa como nao estando nula por isso este else gigante
							if (ce != null) {
								data_test[i-1][j] = String.valueOf(ce.getNumericCellValue());

								m.setLoc((int)ce.getNumericCellValue());
							} else {
								rows++;
								copy_Matrix();
							}
						}    
						else if(j==5){
							data_test[i-1][j] = String.valueOf(ce.getNumericCellValue());
							m.setCyclo((int)ce.getNumericCellValue());
						}    
						else if(j==6){
							data_test[i-1][j] = String.valueOf(ce.getNumericCellValue());
							m.setAtfd((int)ce.getNumericCellValue());
						}    
						else if(j==7){ 
							if(ce.getCellType()==CellType.STRING) {
								data_test[i-1][j] = ce.getStringCellValue();
								m.setLaa(ce.getStringCellValue()); 
							}
							else if(ce.getCellType()==CellType.NUMERIC) { 
								m.setLaa(ce.getNumericCellValue());
								data_test[i-1][j] = String.valueOf(ce.getNumericCellValue());
							}
						}    
						else if(j==8){
							data_test[i-1][j] = String.valueOf(ce.getBooleanCellValue());
							m.setL_m(ce.getBooleanCellValue());
						}    
						else if(j==9){
							data_test[i-1][j] = String.valueOf(ce.getBooleanCellValue());
							m.setiPlasma(ce.getBooleanCellValue());
						} 
						else if(j==10){
							data_test[i-1][j] = String.valueOf(ce.getBooleanCellValue());
							m.setPmd(ce.getBooleanCellValue());
						}
						if(j==11){
							data_test[i-1][j] = String.valueOf(ce.getBooleanCellValue());
							m.setF_e(ce.getBooleanCellValue());
						}
					} 
				}else {
					//dar add uma linha a matrix
					rows++;
					copy_Matrix();

				}
				methodslist.add(m);
				workbook.close();

			} 

			//remover "metodos" nulos criados
			for(Method methods: methodslist){
				if(methods.getId()==0)
					deletelist.add(methods);
				//System.out.println("ID:"+methods.getId()+" firstName:" +methods.getName() + " LOC " + methods.getLoc());
			}
			methodslist.removeAll(deletelist);
			deletelist.clear();

			for(Method methods: methodslist){
				System.out.println("ID:"+methods.getId()+" firstName:" +methods.getName() + " LOC " + methods.getLoc());
			}


			file.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void copy_Matrix() {
		String [] [] data_testt = new String[data_test.length + 1][12];
		for (int t = 0; t < data_test.length; t++) {
			for(int j = 0; j < 12; j++)
				data_testt[t][j] = data_test[t][j];
		}
		data_test = new String[data_testt.length][12];

		for (int k = 0; k < data_testt.length; k++) {
			for(int j = 0; j < 12; j++)
				data_test[k][j] = data_testt[k][j];
		}

	}
	
	private void create_table() {
		data = new String [][] {{null,null,null,null,null,null,null,null,null,null,null,null}};
		myTable = new JTable();
		model = (DefaultTableModel) myTable.getModel();
		model = new DefaultTableModel(data,c_names);
		myTable.setModel(model);
		myTable.setPreferredScrollableViewportSize(new Dimension(1100, 295));
		myTable.setFillsViewportHeight(true);
		myTable.setDefaultEditor(Object.class, null);

		scrollPane = new JScrollPane(myTable);

	}




	public void create_GUI() {

		frame.setLayout(new GridLayout(2,0));

		panel1 = new JPanel();
		panel2 = new JPanel();

		clear_file = new JButton("Clear File");
		file = new JButton("Choose File");
		thresholds = new JButton("Change Thresholds");
		new_rule = new JButton("New Rule");
		
		create_table();

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
						System.out.println(keep);

					create_objects();
					System.out.println(data);
					model = new DefaultTableModel(data_test,c_names); // for example
					myTable.setModel(model);
					model.fireTableDataChanged();

				} else if(returnVal == JFileChooser.CANCEL_OPTION ) {
					return;
				}
				System.out.println(data);

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

	public void open() {
		frame.setSize(1500, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}


	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.open();

	}

}
