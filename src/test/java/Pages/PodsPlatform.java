package Pages;

import Testing.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class PodsPlatform extends BaseClass {
    public String pod_id="";

    By test_btn = By.xpath("(//a)[1]");
    By edit_btn = By.xpath("//span[text()=\"Edit Details\"]");
    By delivery_leader = By.xpath("//input[@id=\"Project Form_DeliveryLeader\"]");
    By project_manager = By.xpath("//input[@id=\"Project Form_ProjectManager\"]");
    By update_btn = By.xpath("//span[text()=\"Update\"]");
    By message = By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");
    By new_config_btn = By.xpath("//span[text()=\" New Configuration\"]");
    By config_name = By.xpath("//input[contains(@id,\"podConfigName\")]");
    By pod_size = By.xpath("//input[contains(@id,\"podSize\")]");
    By config_start_date = By.xpath("//input[contains(@id,\"startDate\")]");
    By date_today = By.xpath("(//td[contains(@class,\"ant-picker-cell-today\")])[1]");
    By capability = By.xpath("(//input[@class=\"ant-select-selection-search-input\"])[1]");
    // SDET
    By track= By.xpath("(//input[@class=\"ant-select-selection-search-input\"])[2]");
    By skill_set = By.xpath("(//input[@class=\"ant-select-selection-search-input\"])[3]");
    // Java
    By save_config_btn = By.xpath("//span[contains(text(),\"Save Configuration\")]");
    By new_pod_btn = By.xpath("//span[contains(text(),\" New Pod\")]");
    By pod_name = By.xpath("//input[contains(@placeholder,\"Pod Name\")]");
    By onboard_date = By.xpath("//input[contains(@id,\"On-boarding\")]");
    By onboard_date_today = By.xpath("(//td[contains(@class,\"ant-picker-cell-today\")])[1]");
    By billing_date = By.xpath("//input[contains(@id,\"Billing start\")]");
    By billing_date_today = By.xpath("(//td[contains(@class,\"ant-picker-cell-today\")])[2]");
    By pod_SKU = By.xpath("//input[contains(@id,\"pod-template-1\")]");
    By pod_SKU_value = By.xpath("//div[contains(text(),\"Size: \") ]");
    By confirm_pod_btn = By.xpath("//button[@id=\"submitRoundBtn\" ]");
    By select_config = By.xpath("//input[@id=\"podconfiguration-1\" ]");
    By continue_btn = By.xpath("//span[text()=\"Continue\" ]");
    By confirm_btn = By.xpath("//span[text()=\"Confirm\" ]");
    By all_pods = By.xpath("(//div[@class=\"ant-space-item\"]//a)");
    By move_to_allocation_btn = By.xpath("//span[text()=\"Move to Allocation\"]");
//    By = By.xpath("");

    public void create_newpod() throws InterruptedException {
        Thread.sleep(7000);

            click_test_btn();
            click_edit_btn();
            set_delivery_leader();
            set_product_manager();
            click_update_btn();
            wait_for_message();
            go_back();
            click_new_config();
            set_config_name();
            click_config_start_date();
            set_todays_date();
            set_capability("SDET");
            set_track("SDET");
            set_skill("Java");
            click_save_config();
            wait_for_message();
            click_new_pod();
            set_pod_name();
            click_onboard_date();
            set_onboard_start_date();
            click_billing_date();
            set_billing_start_date();
            set_SKU();
            click_confirm_pod_btn();
            select_config();
            click_continue_btn();
            click_confirm_btn();
            wait_for_message();
            click_latest_created_pod();
            click_move_to_allocation();
            click_confirm_btn();
        }

    public PodsPlatform click_test_btn() throws InterruptedException {
        //Thread.sleep(10000);
        System.out.println(test_btn);
        wait.until(ExpectedConditions.presenceOfElementLocated(test_btn));
        wait.until(ExpectedConditions.visibilityOfElementLocated(test_btn));

        driver.findElement(test_btn).click();
        //Thread.sleep(1000);
        return this;
    }

    public PodsPlatform click_edit_btn() throws InterruptedException {
        do_click(edit_btn);
        return this;
    }

    public PodsPlatform set_delivery_leader() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(delivery_leader));
        WebElement yourOption = driver.findElement(delivery_leader);
        yourOption.sendKeys(prop.getProperty("Email"));

        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return this;
    }

    public PodsPlatform set_product_manager() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(project_manager));
        WebElement yourOption = driver.findElement(project_manager);
        yourOption.sendKeys(prop.getProperty("Email"));

        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return this;
    }

    public PodsPlatform click_update_btn() throws InterruptedException {
        do_click(update_btn);
        return this;
    }

    public PodsPlatform wait_for_message() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(message));
        return this;
    }

    public PodsPlatform go_back() throws InterruptedException {
        driver.navigate().back();
        return this;
    }
    public PodsPlatform click_new_config() throws InterruptedException {
        do_click(new_config_btn);
        return  this;
    }

    public PodsPlatform set_config_name() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(config_name));
        driver.findElement(config_name).sendKeys("new_pod");
        return  this;
    }
    public PodsPlatform click_config_start_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(config_start_date));
        driver.findElement(config_start_date).click();
        return  this;
    }
    public PodsPlatform set_todays_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(date_today));
        js_click(date_today);
        return  this;
    }
    public PodsPlatform set_capability(String str) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(capability));

        WebElement yourOption = driver.findElement(capability);
        yourOption.sendKeys(str);
        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return  this;
    }
    public PodsPlatform set_track(String str) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(track));

        WebElement yourOption = driver.findElement(track);
        yourOption.sendKeys(str);
        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return  this;
    }
    public PodsPlatform set_skill(String str) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(skill_set));

        WebElement yourOption = driver.findElement(skill_set);
        yourOption.sendKeys(str);
        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return  this;
    }
    public PodsPlatform click_save_config() throws InterruptedException {
        do_click(save_config_btn);
        return  this;
    }
    public PodsPlatform click_new_pod() throws InterruptedException {
        do_click(new_pod_btn);
        return  this;
    }
    public PodsPlatform set_pod_name() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(pod_name));
        driver.findElement(pod_name).sendKeys("new_pod");
        return  this;
    }
    public PodsPlatform click_onboard_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(onboard_date));
        driver.findElement(onboard_date).click();
        return  this;
    }
    public PodsPlatform set_onboard_start_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(onboard_date_today));
        driver.findElement(onboard_date_today).click();
        return  this;
    }
    public PodsPlatform click_billing_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(billing_date));
        driver.findElement(billing_date).click();
        return  this;
    }
    public PodsPlatform set_billing_start_date() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(billing_date_today));
        driver.findElement(billing_date_today).click();
        return  this;
    }
    public PodsPlatform set_SKU() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(pod_SKU));

        WebElement yourOption = driver.findElement(pod_SKU);
        yourOption.sendKeys("1");
        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return  this;
    }
    public PodsPlatform click_confirm_pod_btn() throws InterruptedException {
        do_click(confirm_pod_btn);
        return  this;
    }
    public PodsPlatform select_config() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(select_config));

        WebElement yourOption = driver.findElement(select_config);
        yourOption.click();
        Thread.sleep(2000);
        yourOption.sendKeys(Keys.DOWN);
        yourOption.sendKeys(Keys.RETURN);
        return  this;
    }
    public PodsPlatform click_continue_btn() throws InterruptedException {
        do_click(continue_btn);
        return  this;
    }
    public PodsPlatform click_confirm_btn() throws InterruptedException {
        do_click(confirm_btn);
        return  this;
    }
    public PodsPlatform click_latest_created_pod() throws InterruptedException {
        Thread.sleep(4000);         // as after 2 success messages it takes some time to display new pod
        //driver.navigate().refresh();

        wait.until(ExpectedConditions.presenceOfElementLocated(all_pods));
        int size = driver.findElements(all_pods).size();
        By latest_pod = By.xpath("(//div[@class=\"ant-space-item\"]//a)["+size+"]");
        pod_id = driver.findElement(latest_pod).getText();
        do_click(latest_pod);
        return this;
        }
    public PodsPlatform click_move_to_allocation() throws InterruptedException {
        Thread.sleep(4000);
        //wait.until(ExpectedConditions.elementToBeClickable(move_to_allocation_btn));
        do_click(move_to_allocation_btn);
        return this;
    }
    public PodsPlatform validate_move_to_allocation() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(message));
        String actual = driver.findElement(message).getText();

        Assert.assertTrue(actual.contains("success"));
        return this;
        }
//    public PodsPlatform click_() throws InterruptedException {
//        wait_and_click();
//        return this;
//        }


}
