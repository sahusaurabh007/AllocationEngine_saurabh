package Pages;

import Testing.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Login extends BaseClass {
    By login = By.xpath("//button");
    By deloitte = By.xpath("//input[@value='Deloitte']");
    By email = By.xpath("//input[@type='email']");
    By next = By.xpath("//input[@value='Next']");
    By error_loading = By.xpath("//div[contains(text(),\"Error 500\")]");

    public Login login_to_website() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(login));
        driver.findElement(login).click();

        js_click(deloitte);

        wait.until(ExpectedConditions.presenceOfElementLocated(email));
        driver.findElement(email).sendKeys(prop.getProperty("Email"));

        js_click(next);

        driver.manage().timeouts().pageLoadTimeout(100, SECONDS);

        Thread.sleep(20000);
        loading_error();
        return this;
    }
    public Login loading_error() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(error_loading));
            System.out.println("error_loading");
            driver.navigate().refresh();
        }
        catch(Exception ex){
            System.out.println("Page loaded successfully !!");
        }
        return this;
    }
}
