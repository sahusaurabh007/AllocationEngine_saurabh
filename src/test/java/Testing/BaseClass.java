package Testing;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
      public static WebDriver driver;
      public static WebDriverWait wait;
      public static Properties prop;

    @BeforeMethod
    public static WebDriver initialSetup(){         // start chrome with given url

        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();

        load_properties_file();
        driver.get(prop.getProperty("Url"));

        wait = new WebDriverWait(driver, 40);
        return driver;
    }
    public void do_click(By element) throws InterruptedException {
        System.out.println(element);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        driver.findElement(element).click();
        Thread.sleep(1000);
    }
    public void js_click(By element) throws InterruptedException {
        System.out.println(element);

        wait.until(ExpectedConditions.presenceOfElementLocated(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(element));

        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));

        Thread.sleep(1000);
    }
    public static void load_properties_file(){
        File file = new File(System.getProperty("user.dir")+"/src/test/Config.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop = new Properties();

        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void go_to_allocation_engine() throws InterruptedException {
        driver.get("https://dna-staging.hashedin.com/allocation/allocate");
    }
    public void go_to_pods_platform() throws InterruptedException {
        driver.get("https://dna-staging.hashedin.com/pods/requests/PR-87");
    }
    // @AfterMethod
    public void closeBrowser() {  // close browser
        driver.close();
    }
}
