package stepDefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pageObjects.SwiggyPageObjects;

public class Swiggy_Assignment2_Simplilearn {
	
	public static WebDriver driver;
	ArrayList<String> aryLst1 = new ArrayList<String>();
	public static ExtentSparkReporter spark;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;

	@Given("The user launches the application.")
	public void the_user_launches_the_application() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.swiggy.com/");
		spark = new ExtentSparkReporter("./TestReport/ExtentSparkReport.html");
		spark.config().setTheme(Theme.STANDARD);
		extent.attachReporter(spark);
		logger = extent.createTest("Registration Test");
		logger.info("Test Case Started");
		logger.pass("Browser Opened");
	}

	@When("Enter delivery location {string} and Click on search.")
	public void click_on_search(String enterDeliveryLocation) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {
			WebElement deliveryLocation = driver.findElement(By.xpath(SwiggyPageObjects.deliveryLocation_xpathLocator));
			deliveryLocation.sendKeys(enterDeliveryLocation);
			WebElement findFood = driver.findElement(By.xpath(SwiggyPageObjects.findFood_xpathLocator));
			findFood.click();
			logger.pass("Delivery location entered successfully");
		}
		catch (Exception e){
			System.out.println(e);
			logger.pass("Skip enetring delivery location due to unavailability");
		}
		finally {
			WebElement searchButton = driver.findElement(By.xpath(SwiggyPageObjects.searchButton_xpathLocator));
			searchButton.click();
			logger.pass("Item searching starts");
		}
	}

	@Then("Search for the product {string}.")
	public void Search_for_the_product(String enterProductName) throws AWTException {
		WebElement productNameSearch = driver.findElement(By.xpath(SwiggyPageObjects.productNameSearch_xpathLocator));
		productNameSearch.sendKeys(enterProductName);
		Robot rbt1 = new Robot();
		rbt1.keyPress(KeyEvent.VK_ENTER);
		WebElement selectedProductNameText = driver.findElement(By.xpath(SwiggyPageObjects.selectedProductNameText_xpathLocator));
		aryLst1.add(selectedProductNameText.getText());
		logger.pass("Item name entered successfully.");
	}

	@Then("Add the product into cart.")
	public void add_the_product_into_cart() throws InterruptedException {
		WebElement productAdd = driver.findElement(By.xpath(SwiggyPageObjects.productAdd_xpathLocator));
		productAdd.click();
		Thread.sleep(2000);

		try {
		WebElement continue1 = driver.findElement(By.xpath(SwiggyPageObjects.continue_xpathLocator));
		continue1.click();
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("catch block run");
		}
		
		try {
		WebElement continue2 = driver.findElement(By.xpath(SwiggyPageObjects.continue_xpathLocator));
		continue2.click();
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("catch block run");
		}
		
		try {
		WebElement addItem = driver.findElement(By.xpath(SwiggyPageObjects.addItem_xpathLocator));
		addItem.click();
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("catch block run");
		}

		logger.pass("Item added into cart successfully");
	}

	@Then("Goto cart.")
	public void goto_cart() {
		WebElement gotoCart = driver.findElement(By.xpath(SwiggyPageObjects.gotoCart_xpathLocator));
		gotoCart.click();
		logger.pass("Cart opens successfully");
	}

	@Then("Verify the prodect is added successfully.")
	public void verify_the_prodect_is_added_successfully() {
		WebElement addedProductNameText = driver.findElement(By.xpath(SwiggyPageObjects.addedProductNameText_xpathLocator));
		String addedProduct = addedProductNameText.getText();
		String selectedProduct = aryLst1.get(0);		
		boolean verification = addedProduct.contains(selectedProduct);
		Assert.assertTrue(verification);
		logger.pass("Verified that product is added successfully");
		extent.flush();
	}
}

	
	
	

	
