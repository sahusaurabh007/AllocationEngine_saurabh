package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Allocate extends BaseClass{
    By login = By.xpath("//button");
    By deloitte = By.xpath("//input[@value='Deloitte']");
    By email = By.xpath("//input[@type='email']");
    By next = By.xpath("//input[@value='Next']");

    By cell = By.xpath("//tbody//tr[1]//td");
    By POD= By.xpath("//span[text()=\"POD-158\"]");
    By zero_nominations= By.xpath("//td[6]//button");
    By nominatation = By.xpath("(//article[@class='ant-typography'])[1]");
    By add = By.xpath("(//span[@class='anticon anticon-user-add'])[1]");
    By confirm = By.xpath("//span[contains(text(),\"Confirm Nominations\")]");
    // By back = By.xpath("(//button[contains(@class,\"back-btn\")])[1]");
    By filter = By.xpath("//span[@class=\"ant-badge\"]");
    By reset = By.xpath("//button[contains(@class,\"resetBtn\")]");
    By apply = By.xpath("//span[contains(text(),\"Apply\")]");
    By success = By.xpath("//span[text()=\"Successful\"]");


    By allocate= By.xpath("//span[text()=\"Allocations\"]");
    By selected_hasher= By.xpath("//tr[1]//h2[@class=\"name\"]");
    By pod_id = By.xpath("//span[text()=\"POD-159\"]");
    By allocation_tab = By.xpath("//div[text()=\"Allocation\"]");
    By view_response= By.xpath("//button[text()=\"View Responses\"]");
    By select= By.xpath("//button[text()=\"Select\"]");
    By close= By.xpath("//button[@aria-label=\"Close\"]");
    By ao_accept_pod= By.xpath("//span[text()=\"Confirm Pod\"]");
    By closed_tab= By.xpath("//div[contains(text(),\"Closed Pods\")]");
    By allocated_check= By.xpath("//li[text()=\"Allocated in \"]");
    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");
    By confirm_pod= By.xpath("//span[text()=\"Yes, Confirm\"]");
    By search= By.xpath("//input[@class=\"ant-input\"]");
    By searchBtn= By.xpath("//button[contains(@class,\"input-search-button\")]");
    By closeBtn= By.xpath("//span[contains(@class,\"close-circle\")]");


    public void click_login() throws InterruptedException {
        driver.findElement(login).click();
        js_click(deloitte);
        driver.findElement(email).sendKeys("saurasahu@deloitte.com");
        js_click(next);

//        Thread.sleep(3000);
//        driver.navigate().refresh();
    }
    public void click_allocations() throws InterruptedException {
        js_click(allocate);
    }
    public void click_pod(String pod) throws InterruptedException {
        new WebDriverWait(driver, 5).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        driver.navigate().refresh();

        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));
    }
    public void click_nominate() throws InterruptedException {

        js_click(zero_nominations);
    }
    public void reset_filter() throws InterruptedException {
        js_click(filter);
        js_click(reset);
        js_click(apply);
    }
    public void add_hasher() throws InterruptedException {
        js_click(add);      // adds 1st hasher
    }
    public void confirm_nominations() throws InterruptedException {
        js_click(confirm);
    }
    public void wait_for_success() throws InterruptedException {
        WebDriverWait success_wait = new WebDriverWait(driver, 5);
        success_wait.until(ExpectedConditions.presenceOfElementLocated(success));
    }
    public void go_back() throws InterruptedException {
        driver.navigate().back();
    }
    public void validate_count(int expected) throws InterruptedException {
        int countAfter = Integer.parseInt(driver.findElement(nominatation).getText().replaceAll("[\\D]", ""));
        Assert.assertEquals(countAfter , expected);
    }

    public void search_hasher(String hasher) throws InterruptedException {
        driver.findElement(search).sendKeys(hasher);
    }
    public void nominate_hasher(String hasher) throws InterruptedException {
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));
    }
    public void close_search() throws InterruptedException {
        js_click(closeBtn);
    }
    public void wait_for_consideration() throws InterruptedException {
        // status of hashers changes to considered after 3 mins
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
    }
    public void click_allocation_tab() throws InterruptedException {
        js_click(allocation_tab);
    }
    public void click_view_response() throws InterruptedException {
        js_click(view_response);
    }
    public void select_hasher() throws InterruptedException {
        js_click(select);
        Thread.sleep(2000);
    }
    public void click_close() throws InterruptedException {
        js_click(close);
    }
    public void _() throws InterruptedException {
        String name = driver.findElement(selected_hasher).getText();
        Assert.assertNotEquals(name,"");
    }
//    public void click_() throws InterruptedException {
//
//    }
}
