package es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * Classe que trata da leitura do ficheiro excel
 *
 */
public class ExcelReader implements ActionListener {

	private static String keep="";
	private MethodTable methodTable;
	private DetailTable detailTable;
	private ResultPanel resultp;

	private LMRule lmRule;
	
	/**
	 * Construtor da classe ExcelReader
	 * @param methodTable Tabela com os dados do ficheiro
	 * @param detailTable Tabela com os dados do ficheiro e com colunas para o Long Method e Feature Envy para a regra que o utilizador define
	 * @param resultp Tabela com os valores de DCI, DII, ADCI e ADII
	 * @param lmRule Regra definida pelo utilizador para o campo Long Method
	 */
	public ExcelReader(MethodTable methodTable,DetailTable detailTable,ResultPanel resultp,LMRule lmRule) {
		this.methodTable=methodTable;
		this.detailTable=detailTable;
		this.resultp=resultp;
		this.lmRule = lmRule;
	}

	/**
	 * Método que é chamado quando se clica no botao "Choose File"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls", "xlsx");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			setKeep(selectedFile.getAbsolutePath());
			System.out.println(keep);
			if(getKeep().contains("xlx") || getKeep().contains("xlsx"))
				readExcel();

			resultp.checkErrors(methodTable, lmRule);

		} else if(returnVal == JFileChooser.CANCEL_OPTION ) {
			return;
		}

	}
	
	/**
	 * Metodo que adicona o ficheiro Excel graficamente à GUI. 
	 * Cria objetos Method para cada linha da tabela com todos os atributos
	 */
	public void readExcel() {
		try {
			methodTable.clear();
			detailTable.clear();
			FileInputStream file = new FileInputStream(new File(getKeep()));
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row rot=sheet.getRow(0);
			int rows = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() != CellType.BLANK) {
						if (cell.getCellType() != CellType.STRING ||cell.getStringCellValue().trim().length() > 0) {
							rows++;
							break;
						}
					}
				}
			}

			// -1 Header Row
			rows--;
			//I've Header and I'm ignoring header for that I've +1 in loop
			for(int i=sheet.getFirstRowNum()+1;i<=rows;i++){
				Method m= new Method();
				Row ro=sheet.getRow(i);
				if(ro != null) { // tentativa de tratar quando existem linhas brancas tanto ao inicio como no fim ou no meio
					for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
						Cell ce = ro.getCell(j);
						if(j==0){  
							int a = (int)ce.getNumericCellValue();
							//If you have Header in text It'll throw exception because it won't get NumericValue
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
				methodTable.addMethod(m);
				detailTable.addMethod(m);
				workbook.close();
			} 
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter do atributo keep
	 * @return Valor do atributo keep
	 */
	public static String getKeep() {
		return keep;
	}
	
	/**
	 * Setter do atributo keep
	 * @param keep Valor que o atributo keep irá tomar
	 */
	public static void setKeep(String keep) {
		ExcelReader.keep = keep;
	}
	
	/**
	 * Getter do atributo lmRule
	 * @return Valor do atributo lmRule
	 */
	public LMRule getLmRule() {
		return lmRule;
	}

}