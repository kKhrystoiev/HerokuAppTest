import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class HerokuAppTest {
    private static WebDriver driver;
    private static String LINKS_LOCATOR = "//*[@id='content']//a";
    private static String LINKS_LOCATOR_BY_TEXT = "//*[@id='content']//a[.='$1']";

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
    }

    @Test
    public void test_herokuApp() {
        String linkName = System.getProperty("linkName");
        List<WebElement> listOfElements = driver.findElements(By.xpath(LINKS_LOCATOR));
        Assert.assertTrue("Link's name not found", listOfElements.stream().map(WebElement::getText).anyMatch(e -> e.equals(linkName)));
        driver.findElement(By.xpath(LINKS_LOCATOR_BY_TEXT.replace("$1", linkName))).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
