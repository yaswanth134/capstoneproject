package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtility {
	public String fetchDataFromPropertyFile(String key) throws IOException {
			
			FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\capstoneproject\\src\\test\\resources\\TestData\\saif.property");
			Properties prop = new Properties();
			prop.load(fis);
			return prop.getProperty(key);
			
	}
	
	public String fetchStringDataFromExcelFile(String sheetName, int rowNo, int cellNo) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\capstoneproject\\src\\test\\resources\\TestData\\login.xlsx");
		
		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		
		
	}

}
