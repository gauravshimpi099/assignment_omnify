package org.example.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class BaseUtility {

    protected static WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    protected void startBrowser() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.agoda.com/en-in");

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public synchronized void scrollToElement(WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        act.scrollToElement(element).build().perform();

    }

    public void switchToWindowByTitle(String expectedTitle) {
        // Get all open window handles
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            // Switch to each window one by one
            driver.switchTo().window(handle);

            // Check the title
            if (Objects.requireNonNull(driver.getTitle()).contains(expectedTitle)) {
                System.out.println("Switched to window with title: " + expectedTitle);
                return;
            }
        }

        throw new RuntimeException("No window found with title: " + expectedTitle);
    }

    public synchronized void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementToBeDisplay(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void closeWindow() {
        driver.close();
    }
}
