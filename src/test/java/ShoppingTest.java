import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://shop.pragmatic.bg/index.php?route=product/product&product_id=43");
    }

    @Test
    public void testShopping() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement addToChartElement = driver.findElement(By.id("button-cart"));
        addToChartElement.click();
        WebElement shoppingBasketElement = driver.findElement(By.id("cart-total"));
        shoppingBasketElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]")));
        WebElement checkOutElement = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]"));
        checkOutElement.click();

        WebElement assertContentPage = driver.findElement(By.xpath("//*[@id=\"checkout-checkout\"]/ul/li[3]/a"));
        String text = assertContentPage.getText();
        // това ми е за проверка на думата Checkout, закоментирал съм я :)
        //System.out.println(text);

        Assert.assertEquals(text, "Checkout");


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
