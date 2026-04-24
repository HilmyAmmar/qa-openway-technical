import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PeriplusCartTest {

    // --- PERIPLUS CREDENTIALS ---
    private static final String EMAIL = "your_email_here@gmail.com";
    private static final String PASSWORD = "your_password_here";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.periplus.com/account/Login");
    }

    @Test
    public void testAddToCartMortalEngines() throws InterruptedException {
        // Step 1: Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
            .sendKeys(EMAIL); 
        driver.findElement(By.id("ps")).sendKeys(PASSWORD);
        driver.findElement(By.id("button-login")).click();

        // Step 2: Search Product
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter_name_desktop")));
        searchBar.sendKeys("Mortal Engines");
        searchBar.submit();

        // Step 3: Select Product
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[@class='product-content product-contents']//h3//a)[1]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        Thread.sleep(1500); 
        firstProduct.click();

        // Step 4: Add to Cart
        WebElement addToCartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//button[contains(@class, 'btn-add-to-cart')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

        // Step 5: Wait for cart update, then navigate directly
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cart_total"), "1"));
        driver.get("https://www.periplus.com/checkout/cart");

        // Step 6: Verify product existence
        wait.until(ExpectedConditions.urlContains("/cart"));
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//p[contains(@class, 'product-name')]//a[contains(text(), 'Mortal Engines')]")
        ));
        Assert.assertTrue(cartItem.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}