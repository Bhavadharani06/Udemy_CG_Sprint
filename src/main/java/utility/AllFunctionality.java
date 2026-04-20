package utility;

import java.io.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllFunctionality {
	// WINDOW
	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void minimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void fullscreen(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	// NAVIGATION
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public void navigateTo(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	public void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	// DETAILS
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	// WAITS
	public void implicitWait(WebDriver driver, int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

	public static WebElement waitClickable(WebDriver driver, By locator, int sec) {
		return new WebDriverWait(driver, Duration.ofSeconds(sec))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement waitVisible(WebDriver driver, By locator, int sec) {
		return new WebDriverWait(driver, Duration.ofSeconds(sec)).until(ExpectedConditions.visibilityOf((WebElement) locator));
	}

	public WebElement waitPresence(WebDriver driver, By locator, int sec) {
		return new WebDriverWait(driver, Duration.ofSeconds(sec))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// ALERT
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendAlertText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	// WINDOW SWITCH
	public void switchByTitle(WebDriver driver, String title) {
		for (String win : driver.getWindowHandles()) {
			driver.switchTo().window(win);
			if (driver.getTitle().contains(title))
				break;
		}
	}

	public void switchByURL(WebDriver driver, String url) {
		for (String win : driver.getWindowHandles()) {
			driver.switchTo().window(win);
			if (driver.getCurrentUrl().contains(url))
				break;
		}
	}

	// FRAMES
	public void switchFrameByIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchFrameByName(WebDriver driver, String name) {
		driver.switchTo().frame(name);
	}

	public void switchFrameByElement(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// CLOSE
	public void close(WebDriver driver) {
		driver.close();
	}

	public void quit(WebDriver driver) {
		driver.quit();
	}

	// ACTIONS
	public void click(WebDriver driver, WebElement element) {
		new Actions(driver).click(element).perform();
	}

	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void hover(WebDriver driver, WebElement element) {
		new Actions(driver).moveToElement(element).perform();
	}

	public void dropdownDown(WebDriver driver, WebElement element, int count) {
		Actions act = new Actions(driver).click(element);
		for (int i = 0; i < count; i++) {
			act.sendKeys(Keys.ARROW_DOWN);
		}
		act.perform();
	}

	// SCREENSHOT
	// Capture Web page
	public String captureScreenshot(WebDriver driver, String testName) throws IOException {
		// Creating a method for time-stamp
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

		File dir = new File("./Reports/");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String path = "./Reports/" + testName + "_" + timestamp + ".png";

		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(temp, new File(path));
		} catch (Exception e) {
			throw new RuntimeException("Failed to capture element screenshot", e);
		}
		return path;
	}

	// FILE PROPERTIES
	// Read property
	public String getPropertyKeyValue(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/CommonData/CommonData.properties");

		Properties prop = new Properties();
		prop.load(fis);

		String value = prop.getProperty(key);

		fis.close();

		return value;
	}
	
	// JAVASCRIPT EXECUTOR

	public void scrollIntoView(WebDriver driver, WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickJS(WebDriver driver, WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// JAVA UTIL
	public int random(int range) {
		return new Random().nextInt(range);
	}

	public String currentDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	// ExcelUtility
	Workbook wb;
	Sheet sheet;

	// load Excel file
	public void loadExcelFile(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		wb = WorkbookFactory.create(fis);
		sheet = wb.getSheet(sheetName);
		fis.close();
	}

	// Read data
	public String getDataFromSingleCell(int rowNo, int cellNo) throws IOException {
		CellType data = sheet.getRow(rowNo).getCell(cellNo).getCellType();
		String value = null;
		if (data.equals(CellType.STRING)) {
			value = sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
		} else if (data.equals(CellType.NUMERIC)) {
			value = String.valueOf((int) sheet.getRow(rowNo).getCell(cellNo).getNumericCellValue());
		}

		return value;
	}

	public void closeExcel() throws IOException {
		wb.close();
	}

	// write data
	public void writeDataInTheCell(String filePath, String sheetName, int rowNo, int cellNo, String value)
			throws IOException {
		Row row = sheet.getRow(rowNo);
		if (row == null) {
			row = sheet.createRow(rowNo);
		}
		Cell cell = row.getCell(cellNo);
		if (cell == null) {
			cell = row.createCell(cellNo);
		}
		cell.setCellValue(value);
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		fos.close();
	}

	// Get entire data using Data Provider
	public Object[][] getExcelDataAsArray(String filePath, String sheetName) throws Exception {

		try (FileInputStream fis = new FileInputStream(filePath); Workbook wb = WorkbookFactory.create(fis)) {

			Sheet sheet = wb.getSheet(sheetName);

			if (sheet == null) {
				throw new Exception("Sheet not found: " + sheetName);
			}

			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[rows][cols];
			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= rows; i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				for (int j = 0; j < cols; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
				}
			}
			return data;
		}
	}
	
	public void waitForCaptchaIfPresent(WebDriver driver) {

	    try {
	        // Wait up to 60 seconds for page to stabilize
	        new WebDriverWait(driver, Duration.ofSeconds(60))
	            .until(d -> {
	                String url = d.getCurrentUrl();
	                return !url.contains("captcha") && !url.contains("verify");
	            });

	        System.out.println("✅ No CAPTCHA or handled");

	    } catch (Exception e) {
	        System.out.println("⚠ CAPTCHA detected → solve manually");
	        try {
	            Thread.sleep(40000); // manual solve time
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}
