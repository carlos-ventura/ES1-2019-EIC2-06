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

public class ExcelReader implements ActionListener {
	private static String keep="";
	private MethodTable methodTable;


	public ExcelReader(MethodTable methodTable) {
		this.methodTable = methodTable;

	}
	@Override
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
				readExcel();

		} else if(returnVal == JFileChooser.CANCEL_OPTION ) {
			return;
		}

	}
	public void readExcel() {
		try {
			methodTable.clear();
			FileInputStream file = new FileInputStream(new File(keep));
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
				workbook.close();
			} 
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}