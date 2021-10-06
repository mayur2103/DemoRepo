package Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Get_Data_from_Excel {

	public static WebDriver driver;
	public static Workbook book;
	public static Sheet sheet;
	
	public static String FilePath = (System.getProperty("user.dir") + "\\src\\main\\java\\Files\\TestData.xlsx");
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InvalidFormatException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://demoqa.com/login");
		
		//Get Data from excel
		
		FileInputStream file = null;
		
		file = new FileInputStream(FilePath);
		book = WorkbookFactory.create(file);
		
		sheet = book.getSheet("Login");
		
		int k = 0;
		for(int i = 0; i < sheet.getLastRowNum(); i++) {	
			String userName = sheet.getRow(i+1).getCell(k+0).toString().trim();
			String password = sheet.getRow(i+1).getCell(k+1).toString().trim();
			
			driver.findElement(By.id("userName")).sendKeys(userName);
			driver.findElement(By.id("password")).sendKeys(password);
			System.out.println("UserName: " + userName);
			System.out.println("Password: " + password);
		}

	}

}
