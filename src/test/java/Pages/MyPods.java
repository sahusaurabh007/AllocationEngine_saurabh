package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPods extends BaseClass{
    By confidence= By.xpath("//input[@class=\"ant-select-selection-search-input\"]");
    By high_btn= By.xpath("//div[text()=\"High\"]");
    By po_select= By.xpath("//button[contains(@class, \"submit-btn\")]");
    By close= By.xpath("//button[@aria-label=\"Close\"]");
    By mypods= By.xpath("//span[text()=\"My Pods\"]");
    By po_accept_pod= By.xpath("//span[text()=\"Accept\"]");
    By confirm_pod= By.xpath("//span[text()=\"Yes, Confirm\"]");
    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");

    public void click_Mypods() throws InterruptedException {
        js_click(mypods);
    }
    public void click_pod(String pod) throws InterruptedException {
        new WebDriverWait(driver, 5).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        driver.navigate().refresh();

        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));
    }
    public void give_confidence() throws InterruptedException {
        WebElement yourOption = driver.findElement(confidence);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);

        Thread.sleep(2000);
    }
    public void confirm_feedback() throws InterruptedException {
        js_click(po_select);
        Thread.sleep(2000);
    }
    public void close_feedback() throws InterruptedException {
        js_click(close);
    }
    public void click_() throws InterruptedException {

    }
    public void click_() throws InterruptedException {

    }
    public void click_() throws InterruptedException {

    }

    public void click_() throws InterruptedException {

    }
}
