
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class test1 {
    WebDriver driver;
    WebDriverWait wait;
    public Logger logger = Logger.getLogger(this.getClass().getName());

    By login = By.xpath("//button");
    By deloitte = By.xpath("//input[@value='Deloitte']");
    By email = By.xpath("//input[@type='email']");
    By next = By.xpath("//input[@value='Next']");
    By password = By.xpath("//input[@type='password']");
    By signin = By.xpath("//span[@id='submitButton']");

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
    By close= By.xpath("//button[@aria-label=\"Close\"]");

    By allocate= By.xpath("//span[text()=\"Allocations\"]");
    By selected_hasher= By.xpath("//tr[1]//h2[@class=\"name\"]");
    By pod_id = By.xpath("//span[text()=\"POD-159\"]");
    By allocation_tab = By.xpath("//div[text()=\"Allocation\"]");
    By view_response= By.xpath("//button[text()=\"View Responses\"]");
    By select= By.xpath("//button[text()=\"Select\"]");
    By ao_accept_pod= By.xpath("//span[text()=\"Confirm Pod\"]");
    By closed_tab= By.xpath("//div[contains(text(),\"Closed Pods\")]");
    By allocated_check= By.xpath("//li[text()=\"Allocated in \"]");

    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");

    By confidence= By.xpath("//input[@class=\"ant-select-selection-search-input\"]");
    By high_btn= By.xpath("//div[text()=\"High\"]");
    By po_select= By.xpath("//button[contains(@class, \"submit-btn\")]");
    By mypods= By.xpath("//span[text()=\"My Pods\"]");
    By po_accept_pod= By.xpath("//span[text()=\"Accept\"]");
    By confirm_pod= By.xpath("//span[text()=\"Yes, Confirm\"]");

    By search= By.xpath("//input[@class=\"ant-input\"]");
    By searchBtn= By.xpath("//button[contains(@class,\"input-search-button\")]");
    By closeBtn= By.xpath("//span[contains(@class,\"close-circle\")]");
    //    By = By.xpath("");

    public void js_click(By element) throws InterruptedException {
        System.out.println(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(element));
        Thread.sleep(1000);
    }

    public void stale_click(By xpath){
        System.out.println(xpath);
        // get the "xpath" element
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));

        // wait the element "xpath" to become stale
        wait.until(ExpectedConditions.stalenessOf(element));

        // click on "xpath" once the page is reloaded
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).click();

    }

   @BeforeTest
//@Test( enabled=false )
    public void initial_setup() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\IdeaProjects\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://dna-staging.hashedin.com/allocation/allocate");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         wait = new WebDriverWait(driver, 30);

    }
    @Test( priority = 1)
    public void click_login() throws InterruptedException {
        driver.findElement(login).click();
        js_click(deloitte);
        driver.findElement(email).sendKeys("saurasahu@deloitte.com");
        js_click(next);
        Thread.sleep(2000);

        driver.navigate().refresh();
//        driver.findElement(email).sendKeys("saurasahu@deloitte.com");
//        js_click(next);

//        Thread.sleep(6000);
//
//        driver.findElement(password).sendKeys("Sks8+s^kl@hi2");
//
//        Thread.sleep(2000);
//        driver.findElement(signin).click();
//        Thread.sleep(12000);
    }
    @Test( priority = 2)
    //@Test( enabled=false )
    public void check_nomination_count() throws InterruptedException {

        // AO nominates a hasher
        String pod="468";
        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));

        int countbefore=0;
        js_click(zero_nominations);
//        try {
//            countbefore  = Integer.parseInt(driver.findElement(nominatation).getText().replaceAll("[\\D]", ""));
//            driver.findElement(nominatation).click();
//        }
//        catch(NoSuchElementException e){
//            countbefore=0;
//            js_click(zero_nominations);
//        }

        js_click(filter);
        js_click(reset);
        js_click(apply);

        js_click(add);      // adds 1st hasher
        js_click(confirm);

       // Thread.sleep(2000);
        WebDriverWait success_wait = new WebDriverWait(driver, 5);
       success_wait.until(ExpectedConditions.presenceOfElementLocated(success));

//        success_wait.until(ExpectedConditions.visibilityOfElementLocated(success));

        driver.navigate().back();

        int countAfter = Integer.parseInt(driver.findElement(nominatation).getText().replaceAll("[\\D]", ""));
        Assert.assertEquals(countAfter - countbefore, 1);
    }
  //  @Test( priority = 3)
   @Test( enabled=false )
    public void check_count_change_TC_12() throws InterruptedException {

        // AO nominates hashers
        String pod="461";
        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));

        int countBefore=0;
        js_click(zero_nominations);

        js_click(filter);
        js_click(reset);
        js_click(apply);

        String hasher = "sallyrutherford";
        driver.findElement(search).sendKeys(hasher);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        hasher = "katherinepullman";
        js_click(closeBtn);
        driver.findElement(search).sendKeys(hasher);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        hasher = "evanjames";
        js_click(closeBtn);
        driver.findElement(search).sendKeys(hasher);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        js_click(confirm);

        wait.until(ExpectedConditions.presenceOfElementLocated(success));
        Thread.sleep(2000);

        // hashers are considered after 3 mins
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);

        driver.navigate().back();

        int countAfter = Integer.parseInt(driver.findElement(nominatation).getText().replaceAll("[\\D]", ""));
        Assert.assertEquals(countAfter - countBefore, 2);
    }
    //@Test( priority = 4)
    @Test( enabled=false )
    public void check_hasher_allocated() throws InterruptedException {
        logger.info("check_hasher_allocated");

        String pod="461";

        // AO nominates a hasher
        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));
        js_click(zero_nominations);
        js_click(filter);
        js_click(reset);
        js_click(apply);

        String hasher = "rebeccakelly";

        driver.findElement(search).sendKeys(hasher);
        //js_click(searchBtn);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        js_click(confirm);
        wait.until(ExpectedConditions.presenceOfElementLocated(success));
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().back();

        // PO gives confidence level to hasher
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));
        js_click(nominatation);
        Thread.sleep(2000);

        WebElement yourOption = driver.findElement(confidence);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);

        Thread.sleep(2000);
        js_click(po_select);
        Thread.sleep(2000);
        js_click(close);

        // Ao allocates hasher
        Thread.sleep(2000);
        js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-"+pod+"\"]"));
        js_click(allocation_tab);
        js_click(view_response);
        js_click(select);
        Thread.sleep(2000);
        js_click(close);

        String name = driver.findElement(selected_hasher).getText();
        Assert.assertNotEquals(name,"");

    }

    //@Test(priority = 5)
    @Test( enabled=false )
    public void check_hasher_already_allocated() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(3000);
        String pod1="465";

        // AO nominates a hasher
         js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-"+pod1+"\"]"));
        js_click(zero_nominations);
        js_click(filter);
        js_click(reset);
        js_click(apply);

        String hasher = "annelangdon";

        driver.findElement(search).sendKeys(hasher);
        //js_click(searchBtn);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        js_click(confirm);
        wait.until(ExpectedConditions.presenceOfElementLocated(success));
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().back();

        // PO gives confidence level to hasher
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-"+pod1+"\"]"));
        js_click(nominatation);
        Thread.sleep(2000);

        WebElement yourOption = driver.findElement(confidence);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);

        Thread.sleep(2000);
        js_click(po_select);
        Thread.sleep(2000);
        js_click(close);

        // Ao allocates hasher
        Thread.sleep(2000);
        js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-"+pod1+"\"]"));
        js_click(allocation_tab);
        js_click(view_response);
        js_click(select);

        WebDriverWait success_wait = new WebDriverWait(driver, 5);
        success_wait.until(ExpectedConditions.presenceOfElementLocated(message));
        js_click(close);
        js_click(ao_accept_pod);
        js_click(confirm_pod);

        // po Accepts pod
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-"+pod1+"\"]"));
        js_click(po_accept_pod);
        js_click(confirm_pod);

        // second pod
        String pod2="462";

        // AO nominates a hasher
        js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-"+pod2+"\"]"));
        js_click(zero_nominations);
        js_click(filter);
        js_click(reset);
        js_click(apply);

        // hasher = "annelangdon";

        driver.findElement(search).sendKeys(hasher);
        //js_click(searchBtn);
        js_click(By.xpath("//h4[text()=\""+hasher+"_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));

        js_click(confirm);
        wait.until(ExpectedConditions.presenceOfElementLocated(success));
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(3*60*1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().back();

        // PO gives confidence level to hasher
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-"+pod2+"\"]"));
        js_click(nominatation);
        Thread.sleep(2000);

        yourOption = driver.findElement(confidence);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);

        Thread.sleep(2000);
        js_click(po_select);
        Thread.sleep(2000);
        js_click(close);

        // Ao allocates hasher
        Thread.sleep(2000);
        js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-"+pod2+"\"]"));
        js_click(allocation_tab);
        js_click(view_response);
        js_click(select);

        WebDriverWait success_wait2 = new WebDriverWait(driver, 5);
        success_wait2.until(ExpectedConditions.presenceOfElementLocated(message));

       String msg=  driver.findElement(message).getText();
        Assert.assertTrue(msg.contains("is already allocated to another POD"));

    }

   // @Test(priority = 6)
    @Test( enabled=false )
    public void check_closed_status() throws InterruptedException {

        String pod1 = "468";

        // AO nominates a hasher
        js_click(allocate);
        driver.navigate().refresh();

        js_click(By.xpath("//span[text()=\"POD-" + pod1 + "\"]"));
        js_click(zero_nominations);
        js_click(filter);
        js_click(reset);
        js_click(apply);

        String hasher = "melanierutherford";
        driver.findElement(search).sendKeys(hasher);
        js_click(By.xpath("//h4[text()=\"" + hasher + "_test@deloitte.com\"]//ancestor::tr//span[contains(@class,\"anticon-user-add\")]"));
        js_click(confirm);
        wait.until(ExpectedConditions.presenceOfElementLocated(success));
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(3 * 60 * 1000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        driver.navigate().back();

//        // PO gives confidence level to hasher
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-" + pod1 + "\"]"));
        js_click(nominatation);
        Thread.sleep(2000);
        WebElement yourOption = driver.findElement(confidence);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
        js_click(po_select);
        Thread.sleep(2000);
        js_click(close);

        // Ao allocates hasher
        Thread.sleep(2000);
        js_click(allocate);
        js_click(By.xpath("//span[text()=\"POD-" + pod1 + "\"]"));
        js_click(allocation_tab);
        js_click(view_response);
        js_click(select);
        WebDriverWait success_wait = new WebDriverWait(driver, 5);
        success_wait.until(ExpectedConditions.presenceOfElementLocated(message));
        js_click(close);
        js_click(ao_accept_pod);
        js_click(confirm_pod);

        // po Accepts pod
        js_click(mypods);
        js_click(By.xpath("//span[text()=\"POD-" + pod1 + "\"]"));
        js_click(po_accept_pod);
        js_click(confirm_pod);

        // check closed
        js_click(allocate);
        driver.navigate().refresh();
        js_click(closed_tab);

        String closed_pod_path ="(//span[text()=\"POD-"+pod1+"\"]/ancestor::td)/following-sibling::td//span[text()=\"CLOSED\"]";
        String actual = driver.findElement(By.xpath(closed_pod_path)).getText();

        Assert.assertEquals(actual,"CLOSED");
    }
}