package Pages;

import Testing.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyPods extends BaseClass {

    By MyPodsTab = By.xpath("//span[contains(text(),'My Pods')]");
    By po_accept_pod= By.xpath("//span[text()=\"Accept\"]");
    By confirm_pod= By.xpath("//span[text()=\"Yes, Confirm\"]");
    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");
    //By success = By.xpath("//span[text()=\"Successful\"]");

    By nomination_with_count = By.xpath("(//article[@class='ant-typography'])[1]");
    By zero_nominations= By.xpath("//td[6]//button");
    By close= By.xpath("//button[@aria-label=\"Close\"]");
    By closed_status = By.xpath("//span[text()=\"CLOSED\"]");
    By closed_tab= By.xpath("//div[contains(text(),\"Closed Pods\")]");

//    By confidence= By.xpath("//input[contains(@id,\"confidence\")]");
//    By high_btn= By.xpath("//div[text()=\"High\"]");
    By confidenceLevel = By.xpath("//div[@title='High']");
    By po_select= By.xpath("//span[@class='anticon anticon-check-circle']");

    public int count=0;

    public MyPods click_Mypods() throws InterruptedException {
        do_click(MyPodsTab);

        return this;
    }
    public MyPods click_pod(String pod) throws InterruptedException {
        new WebDriverWait(driver, 5).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        driver.navigate().refresh();

        do_click(By.xpath("//span[text()=\""+pod+"\"]"));
        return this;
    }
    public MyPods click_nominate() throws InterruptedException {
        do_click(zero_nominations);
        return this;
    }
    public MyPods click_nominations() throws InterruptedException {
        do_click(nomination_with_count);
        return this;
    }
    public MyPods provide_ConfidenceLevel() throws InterruptedException {
        do_click(confidenceLevel);
        return this;
    }
//    public MyPods give_confidence() throws InterruptedException {
//        wait.until(ExpectedConditions.presenceOfElementLocated(confidence));
//        WebElement yourOption = driver.findElement(confidence);
//        yourOption.sendKeys(Keys.DOWN);
//        yourOption.sendKeys(Keys.RETURN);
//
//        new WebDriverWait(driver, 20).
//                until(webDriver -> ((JavascriptExecutor) webDriver).
//                        executeScript("return document.readyState").equals("complete"));
//        Thread.sleep(1000);
//        return this;
//    }

    public MyPods select_hasher() throws InterruptedException {
        do_click(po_select);
        return this;
    }
    public MyPods close_feedback() throws InterruptedException {
        do_click(close);
        Thread.sleep(2000);
        return this;
    }
    public MyPods accept_pod() throws InterruptedException {
        do_click(po_accept_pod);
        return this;
    }
    public MyPods click_confirm_pod() throws InterruptedException {
        do_click(confirm_pod);
        return this;
    }

    public MyPods go_back() throws InterruptedException {
        driver.navigate().back();
        return this;
    }
    public MyPods wait_for_consideration() throws InterruptedException {
        // status of hashers changes to considered after 3 mins
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        return this;
    }
    public MyPods count_nominations() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(nomination_with_count));
        count = Integer.parseInt(driver.findElement(nomination_with_count).getText().replaceAll("[\\D]", ""));
       return this;
    }
    public MyPods wait_for_message() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(message));

        return this;
    }
    public MyPods validate_count(int expected) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(nomination_with_count));
        int countAfter = Integer.parseInt(driver.findElement(nomination_with_count).getText().replaceAll("[\\D]", ""));
        Assert.assertEquals(countAfter , expected);
        return this;
    }

    public MyPods validate_confirmed_status(String pod) throws InterruptedException {
        String status ="(//span[text()=\""+pod+"\"]/ancestor::td)/following-sibling::td//span[text()=\"CONFIRMED\"]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(status)));
        String actual = driver.findElement(By.xpath(status)).getText();

        Assert.assertEquals(actual,"CONFIRMED");
        return this;
    }
    public MyPods validate_closed_status() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(closed_status));
        String actual=  driver.findElement(closed_status).getText();
        Assert.assertEquals(actual,"CLOSED");
        return this;
    }

    public MyPods validate_closed_status_in_closed_tab(String pod) throws InterruptedException {
        String status ="(//span[text()=\""+pod+"\"]/ancestor::td)/following-sibling::td//span[text()=\"CLOSED\"]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(status)));
        String actual = driver.findElement(By.xpath(status)).getText();

        Assert.assertEquals(actual,"CLOSED");
        return this;
    }
    public MyPods click_closed_tab() throws InterruptedException {
        driver.navigate().refresh();
        do_click(closed_tab);
        return this;
    }

    public MyPods validate_success_msg() throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        String actual = driver.findElement(message).getText();
        Assert.assertEquals(actual,"Successful");
        return this;
    }

    public MyPods click_() throws InterruptedException {
        return this;
    }

}
