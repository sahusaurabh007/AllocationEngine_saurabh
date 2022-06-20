package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends BaseClass{
    By login = By.xpath("//button");
    By deloitte = By.xpath("//input[@value='Deloitte']");
    By email = By.xpath("//input[@type='email']");
    By next = By.xpath("//input[@value='Next']");

    public Login login_to_website() throws InterruptedException {
        driver.findElement(login).click();
        js_click(deloitte);

        driver.findElement(email).sendKeys("saurasahu@deloitte.com");
        js_click(next);

        return this;
    }
}
