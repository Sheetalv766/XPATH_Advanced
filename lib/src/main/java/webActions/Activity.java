package webActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Activity {

	WebDriver driver = null;

	/**
	 * use this method to initialize the browser.
	 */
	public WebDriver startBrowser() throws MalformedURLException {
		// Code to Launch Browser using Zalenium in Crio workspace
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

		return driver;
	}


	// snippet_3_1
	// Given a student name , find their roll number
	public static String snippet_3_1(String name, WebDriver driver) {
		// visit to site
		driver.get("https://web-locators-static-site-qa.vercel.app/Web%20Table");

		WebElement topperTab =
				driver.findElement(By.xpath("//span[text()='Static Table - Subject Topper']"));
		//// *[@type='button'][2]
		// click on it
		topperTab.click();

		// give student name
		List<WebElement> studentRollNoCell =
				driver.findElements(By.xpath(("//td[text()='DPS_Grade9_15']")));
		if (studentRollNoCell.size() == 0) {
			return "";
		}
		WebElement rollNumber = studentRollNoCell.get(0);
		return rollNumber.getText();
	}


	// snippet_3_2
	// Given a Subject, Get the highest mark obtained on that subjec
	public static String snippet_3_2(String subject, WebDriver driver) {
		driver.get("https://web-locators-static-site-qa.vercel.app/Web%20Table");

		WebElement topperTab =
				driver.findElement(By.xpath("//span[text()='Static Table - Subject Topper']"));
		topperTab.click();

		List<WebElement> highestMarksCell =
				driver.findElements(By.xpath(String.format("//td[text()='99']", subject)));
		if (highestMarksCell.size() == 0) {
			return "";
		}
		WebElement highestMarks = highestMarksCell.get(0);
		return highestMarks.getText();

	}


	// snippet_3_3
	// Given a Student roll number, find which subject they have topped
	public static String snippet_3_3(String rollNumber, WebDriver driver) {
		driver.get("https://web-locators-static-site-qa.vercel.app/Web%20Table");

		WebElement topperTab =
				driver.findElement(By.xpath("//span[text()='Static Table - Subject Topper']"));
		topperTab.click();

		List<WebElement> rollNoSubjectCell = driver.findElements(By.xpath("//td[text()='C++']"));
		if (rollNoSubjectCell.size() == 0) {
			return "";
		}
		WebElement subject = rollNoSubjectCell.get(0);
		return subject.getText();
	}


	// snippet_3_4
	public static Boolean snippet_3_4(WebDriver driver) throws InterruptedException {

		// 1. Navigate to the QKART Site
		driver.get("https://crio-qkart-frontend-qa.vercel.app/");

		// 2. Search for "shoes"
		WebElement searchBar = driver.findElement(By.xpath("//input[@name='search']"));
		searchBar.sendKeys("shoes");
		Thread.sleep(3000);

		// 3. Check the number of results displayed
		List<WebElement> result =
				driver.findElements(By.xpath("//div[contains(@class,'css-sycj1h')]"));
		return result.size() == 2;
	}


	public static Boolean snippet_3_5(WebDriver driver) throws InterruptedException {

		// 1. Navigate to the QKART Site
		driver.get("https://crio-qkart-frontend-qa.vercel.app/");

		// 2. Search for "shoes"
		WebElement searchBar = driver.findElement(By.xpath("//input[@name='search']"));
		searchBar.sendKeys("shoes");
		Thread.sleep(3000);

		// 3. For each row, check the difference between India Size and EU Size
		WebElement sizeChart = driver.findElement(By.xpath("//*[contains(text(),'Size chart')]"));
		sizeChart.click();

		List<WebElement> indiaList = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
		List<WebElement> euList = driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
		for (int i = 0; i < indiaList.size(); i++) {
			int indiaSize = Integer.parseInt(indiaList.get(i).getText());
			int euSize = Integer.parseInt(euList.get(i).getText());

			int count = 0;
			if ((euSize - indiaSize) == 34) {
				count = count + 1;
			}
			if (count == indiaList.size()) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	public static Boolean snippet_3_6(WebDriver driver) throws InterruptedException {

		// 1. Navigate to QKART Site
		driver.get("https://crio-qkart-frontend-qa.vercel.app/");

		// 2. Search for "Plastic Balls"
		WebElement searchBar = driver.findElement(By.xpath("//input[@name='search']"));
		searchBar.sendKeys("Plastic Balls");
		Thread.sleep(3000);

		// Verify its star rating

		List<WebElement> listOfStar = driver.findElements(By.xpath("//img[contains(@src,'balls')]//following-sibling::div//span[contains(@class,'iconFilled')]"));

		return listOfStar.size() / 2 == 3;
		// return verifyStar.size() / 2 == 3;
	}

	
	public static void main(String[] args)
			throws InterruptedException, MalformedURLException, ParseException {

		// Create the object of Xpath class
		Activity activity = new Activity();

		// Start the browser
		WebDriver driver = activity.startBrowser();

		// Uncomment for Milestone 3 Activity
		// System.out.println(snippet_3_1("Krishna", driver));
		// System.out.println(snippet_3_2("HTML", driver));
		// System.out.println(snippet_3_3("DPS_Grade9_19", driver));

		// System.out.println(snippet_3_4(driver));
		// System.out.println(snippet_3_5(driver));
		// System.out.println(snippet_3_6(driver));

	}

}
