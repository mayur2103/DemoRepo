package Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Get_Data_From_TestNG {
	
	public static WebDriver driver;
	public static Workbook book;
	public static Sheet sheet;
	
	public static String FilePath = (System.getProperty("user.dir") + "\\src\\main\\java\\Files\\TestData.xlsx");

		
	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		System.out.println("Launching Chrome Browser"); 
		
		driver.get("https://demoqa.com/login");
	}

	@Test(dataProvider="getTestData")
	public void loginTest(String uname, String pswd) throws Exception {
		
		driver.findElement(By.id("userName")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pswd);
		System.out.println("UserName: " + uname);
		System.out.println("Password: " + pswd);
		
	}
	
	@DataProvider(name="getTestData")
	public static Object[][] getTestData() throws InvalidFormatException, IOException {
		
		FileInputStream file = null;
		
		file = new FileInputStream(FilePath);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet("Login");
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i = 0; i < sheet.getLastRowNum(); i++) {
			for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i+1).getCell(k).getStringCellValue();
			}
		}
		return data;
	}

}
