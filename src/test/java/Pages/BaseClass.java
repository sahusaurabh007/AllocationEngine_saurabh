package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseClass {
      public static WebDriver driver;
      public static WebDriverWait wait;

    @BeforeMethod
    public static WebDriver initialSetup(){         // start chrome with given url

        System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\IdeaProjects\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://dna-staging.hashedin.com/allocation/allocate");

        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        return driver;
    }

    public void js_click(By element) throws InterruptedException {
        System.out.println(element);
        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));

        wait.until(ExpectedConditions.presenceOfElementLocated(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(element));

        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));

        Thread.sleep(1000);
    }

    // @AfterMethod
    public void closeBrowser() {  // close browser
        driver.close();
    }
}
