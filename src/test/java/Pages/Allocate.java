package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Allocate extends BaseClass{

    By cell = By.xpath("//tbody//tr[1]//td");
    By POD= By.xpath("//span[text()=\"POD-158\"]");
    By nominate= By.xpath("//tr[contains(@class,\"row-level-0 pointer\")][1]");
    By zero_nominations= By.xpath("//td[6]//button");
    By nomination_with_count = By.xpath("(//article[@class='ant-typography'])[1]");
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

    By view_btn= By.xpath("//img[contains(@src,\"view\")]");
    By hasher_name= By.xpath("//h2[@class='name']");
    By nominate_hasher= By.xpath("//span[contains(@class,\"anticon-user-add\")]");
    By hasher_status= By.xpath("//li[contains(@class,'list-item')]");
    By no_nomination_status = By.xpath("//div[text()=\"No other Nominations\"]");
    By bands= By.xpath("(//p[text()=\"Bands\"])//following::input[@type=\"search\"][1]");
    By page_size= By.xpath("(//input[@aria-label=\"Page Size\"])[1]");

    public String name="";

    public Allocate click_allocations() throws InterruptedException {
        js_click(allocate);
        return  this;
    }
    public Allocate click_pod(String pod) throws InterruptedException {
        new WebDriverWait(driver, 5).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        driver.navigate().refresh();

        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));

        return  this;
    }
    public Allocate click_nominate() throws InterruptedException {
        js_click(nominate);
        return this;
    }
    public Allocate click_nominate_2() throws InterruptedException {

        js_click(zero_nominations);
        return this;
    }
    public Allocate click_filter() throws InterruptedException {
        js_click(filter);
        return this;
    }
    public Allocate click_reset() throws InterruptedException {
        js_click(reset);
        return this;
    }
    public Allocate click_apply() throws InterruptedException {
        js_click(apply);
        return this;
    }
    public Allocate reset_filter() throws InterruptedException {
        this.click_filter();
        this.click_reset();
        this.click_apply();

        return this;
    }

    public Allocate filter_b8_band() throws InterruptedException {
        js_click(filter);
        js_click(reset);

        wait.until(ExpectedConditions.presenceOfElementLocated(bands));
        WebElement yourOption = driver.findElement(bands);
        yourOption.sendKeys("B8");
        yourOption.sendKeys(Keys.RETURN);

        //Thread.sleep(2000);
        js_click(apply);
        return this;
    }
    public Allocate change_page_size() throws InterruptedException {
        // change page size to 100 hashers
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(page_size));
        WebElement yourOption = driver.findElement(page_size);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 20).
                until(webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));

        return this;
    }
    public Allocate add_hasher() throws InterruptedException {
        js_click(add);      // adds 1st hasher
        return this;
    }
    public Allocate nominate_unallocated_hasher() throws InterruptedException {
        int i = 0;
        boolean flag = true;
        String status="";

        while(flag) {
            i++;

            // scrollIntoView() to scroll to the element before it is clicked
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//img[contains(@src,\"view\")])[" + i + "]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("(//img[contains(@src,\"view\")])[" + i + "]")));

            // get name of hasher
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//img[contains(@src,\"view\")])["+i+"]//ancestor::tr//div[@class=\"profile\"]//h2[@class=\"name\"]")));
            name = driver.findElement(By.xpath("(//img[contains(@src,\"view\")])["+i+"]//ancestor::tr//div[@class=\"profile\"]//h2[@class=\"name\"]")).getText();

            // click eye icon
            js_click(By.xpath("(//img[contains(@src,\"view\")])[" + i + "]"));

            //Thread.sleep(1000);
            try {
                WebDriverWait wait = new WebDriverWait(driver, 3);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[contains(@class,'list-item')])")));
                int status_size = driver.findElements(By.xpath("(//li[contains(@class,'list-item')])")).size();

                flag=false;
                for (int j = 1; j <= status_size; j++) {

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[contains(@class,'list-item')])[" + j + "]")));
                    status = driver.findElement(By.xpath("(//li[contains(@class,'list-item')])[" + j + "]")).getText();

                    if (status.contains("Allocated")) {
                        System.out.println("already Allocated");
                        flag=true;
                    }
                }
            }
            catch(Exception e){
                System.out.println("no nominations");
                wait.until(ExpectedConditions.presenceOfElementLocated(no_nomination_status));
                flag = false;
            }

        }

        // nominate the hasher
        js_click(By.xpath("(//span[contains(@class,\"anticon-user-add\")])["+i+"]"));

//        i++;  // due to add nominations a hasher is displayed in nominated hasher & has index 1
//              // so index for all hashers in search results are increased by 1


        return this;
    }
    public Allocate confirm_nominations() throws InterruptedException {
        js_click(confirm);
        return this;
    }
//    public Allocate wait_for_success() throws InterruptedException {
//        wait.until(ExpectedConditions.presenceOfElementLocated(success));
//        wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(success));
//
//        return this;
//    }
    public Allocate wait_for_message() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(message));

        return this;
    }
    public Allocate go_back() throws InterruptedException {
        driver.navigate().back();
        return this;
    }

    public Allocate search_hasher(String hasher) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(search));
        driver.findElement(search).sendKeys(hasher);
        return this;
    }
    public Allocate nominate_hasher(String hasher) throws InterruptedException {
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));
        return this;
    }
    public Allocate close_search() throws InterruptedException {
        js_click(closeBtn);
        return this;
    }
    public Allocate wait_for_consideration() throws InterruptedException {
        // status of hashers changes to considered after 3 mins
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        return this;
    }
    public Allocate click_allocation_tab() throws InterruptedException {
        js_click(allocation_tab);
        return this;
    }
    public Allocate click_view_response() throws InterruptedException {
        js_click(view_response);
        return this;
    }
    public Allocate select_hasher() throws InterruptedException {
        js_click(select);
       // Thread.sleep(2000);
        return this;
    }
    public Allocate click_close() throws InterruptedException {
        js_click(close);
        return this;
    }
    public Allocate accept_pod() throws InterruptedException {
        js_click(ao_accept_pod);
        return this;
    }
    public Allocate click_confirm_pod() throws InterruptedException {
        js_click(confirm_pod);
        return this;
    }
    public Allocate click_closed_tab() throws InterruptedException {
        driver.navigate().refresh();
        js_click(closed_tab);
        return this;
    }
    public Allocate validate_count(int expected) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(nomination_with_count));
        int countAfter = Integer.parseInt(driver.findElement(nomination_with_count).getText().replaceAll("[\\D]", ""));
        Assert.assertEquals(countAfter , expected);
        return this;
    }
    public Allocate validate_selected_hasher() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(selected_hasher));
        String name = driver.findElement(selected_hasher).getText();
        Assert.assertNotEquals(name,"");
        return this;
    }
    public Allocate validate_already_allocated() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        String msg=  driver.findElement(message).getText();
        Assert.assertTrue(msg.contains("is already allocated to another POD"));
        return this;
    }
    public Allocate validate_closed_status(String pod) throws InterruptedException {
        String closed_pod_path ="(//span[text()=\"POD-"+pod+"\"]/ancestor::td)/following-sibling::td//span[text()=\"CLOSED\"]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(closed_pod_path)));
        String actual = driver.findElement(By.xpath(closed_pod_path)).getText();

        Assert.assertEquals(actual,"CLOSED");
        return this;
    }
//    public Allocate click_() throws InterruptedException {
//
//    }
}
