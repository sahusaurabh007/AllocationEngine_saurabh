package Pages;

import Testing.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyPods extends BaseClass {

    // common xpaths
    By confidenceLevel = By.xpath("//div[@title='High']");
    By po_select= By.xpath("//span[@class='anticon anticon-check-circle']");
    By MyPodsTab = By.xpath("//span[contains(text(),'My Pods')]");
    By confirm_pod= By.xpath("//span[text()=\"Yes, Confirm\"]");
    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");

    // saurabh xpaths
    By po_accept_pod= By.xpath("//span[text()=\"Accept\"]");
    By nomination_with_count = By.xpath("(//article[@class='ant-typography'])[1]");
    By zero_nominations= By.xpath("//td[6]//button");
    By close= By.xpath("//button[@aria-label=\"Close\"]");
    By closed_status = By.xpath("//span[text()=\"CLOSED\"]");
    By closed_tab= By.xpath("//div[contains(text(),\"Closed Pods\")]");

    //Chandana X-paths
    By consideredStatus = By.xpath("//span[contains(text(),'Considered')]");
    By RespondedStatus = By.xpath("//span[contains(text(),'Responded')]");
    By StatusOfPod = By.xpath("//span[contains(text(),'ALLOCATION READY')]");
    By statusOfPodAfterNominate = By.xpath("//span[contains(text(),'IN PROGRESS')]");
    By CommentsField = By.xpath("//textarea[@class='ant-input']");
    By choose_confidence =  By.xpath("(//input[@type='search'])[2]");
    By myPodsNomination = By.xpath("//Span[@class='nomination-with-count']");
    By MessageBeforeNominate = By.xpath("//div[@class='message-for-allocation-ready']");

    public int count=0;

    // common action methods

    public MyPods click_MyPods(){
        wait.until(ExpectedConditions.presenceOfElementLocated(MyPodsTab));
        Actions act =  new Actions(driver);
        act.moveToElement(driver.findElement(MyPodsTab)).click().perform();
        return this;
    }
//    public MyPods click_MyPods() throws InterruptedException {
//        do_click(MyPodsTab);
//        return this;
//    }
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
    public MyPods click_choose_confidence() throws InterruptedException {
        do_click(choose_confidence);
        return this;
    }
    public MyPods provide_ConfidenceLevel() throws InterruptedException {
        do_click(confidenceLevel);
        return this;
    }

    public MyPods select_hasher() throws InterruptedException {
        do_click(po_select);
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

    // saurabh Action Methods
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

    // chandana Action Methods
    public MyPods verify_NewPods(){
        String Status = driver.findElement(StatusOfPod).getText();
        System.out.println("Status of New Pod Under open pods tab in PO dashboard:"+" "+Status);
        Assert.assertEquals(Status,"ALLOCATION READY");
        return this;
    }
    public MyPods Verify_Message(){
        String Message = driver.findElement(MessageBeforeNominate).getText();
        System.out.println("message before Starting the Nomination:"+" "+Message);
        Assert.assertEquals(Message,"Waiting for Allocation Owner to start nominating Hashers");
        return this;
    }
    public MyPods Verify_StatusAfterNominating(){
        String Status = driver.findElement(statusOfPodAfterNominate).getText();
        System.out.println("Status of pod After Nominating under OpenPods tab on PO dashBoard :"+" "+Status);
        Assert.assertEquals(Status,"IN PROGRESS");
        return this;
    }
    public MyPods click_MyPodsNomination() throws InterruptedException {
        do_click(myPodsNomination);
        return this;
    }

    public MyPods Comments() throws InterruptedException {
        WebElement ele = driver.findElement(CommentsField);
        ele.sendKeys(prop.getProperty("Comments"));
        return this;
    }
    public MyPods Verifying_TheConsideredStatus(){
        String Status = driver.findElement(consideredStatus).getText();
        System.out.println("Status After Complete the Nomination is:"+" "+Status);
        Assert.assertEquals(Status,"CONSIDERED");
        return this;
    }
    public MyPods Verify_StatusResponded(){
        String Status = driver.findElement(RespondedStatus).getText();
        System.out.println("Status After giving Confidence Level:"+" "+Status);
        Assert.assertEquals(Status,"RESPONDED");
        return this;
    }
//    public MyPods click_() throws InterruptedException {
//        return this;
//    }

}
