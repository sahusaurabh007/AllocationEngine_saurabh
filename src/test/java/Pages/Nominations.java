package Pages;

import Testing.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Locale;

public class Nominations extends BaseClass {
    By MyNominationsTab = By.xpath("//span[contains(text(),'My Nominations')]");
    By first_Nomination= By.xpath("(//tr[contains(@class,'ant-table-row')])[1]");
    By Confirm_Nomination_btn= By.xpath("//span[contains(text(),'Confirm Nomination')]");
    By Opt_Out_btn= By.xpath("//span[contains(text(),'Opt Out')]");
    By yes_confirm_btn= By.xpath("//span[text()=\"Yes, Confirm\"]");
    By message= By.xpath("//div[@class=\"ant-message-notice\"]//span[2]");
    By error_message= By.xpath("//div[contains(@class,\"explain-error\")]");
    By optOutreason_btn= By.xpath("//input[@id=\"optOutReason\"]");
    By optOutOption= By.xpath("//div[contains(text(),'Tech Stack Mismatch') and contains(@class,\"option\")]");
    By optOutConfirm_btn= By.xpath("//span[text()='Confirm']");
    By status= By.xpath("//span[contains(@class,\"status\")]");
//    By = By.xpath("");

    public Nominations click_MyNominationsTab() throws InterruptedException {
        do_click(MyNominationsTab);
        return  this;
    }

    public Nominations click_1st_Nomination() throws InterruptedException {
        do_click(first_Nomination);
        return  this;
    }
    public Nominations click_Confirm_Nomination_btn() throws InterruptedException {
        do_click(Confirm_Nomination_btn);
        return  this;
    }
    public Nominations click_Opt_Out_btn() throws InterruptedException {
        do_click(Opt_Out_btn);
        return  this;
    }
    public Nominations click_optOutreason_btn() throws InterruptedException {
        do_click(optOutreason_btn);
        return  this;
    }
    public Nominations select_optOutOption(String option) throws InterruptedException {

        String option_xpath="//div[contains(text(),\""+option+"\") and contains(@class,\"option\")]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(option_xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath(option_xpath)));
        do_click(By.xpath(option_xpath));
        return  this;
    }
    public Nominations click_optOutConfirm_btn() throws InterruptedException {
        do_click(optOutConfirm_btn);
        return  this;
    }
    public Nominations click_yes_confirm_btn() throws InterruptedException {
        do_click(yes_confirm_btn);
        return  this;
    }
    public Nominations wait_for_message() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(message));
        return this;
    }
    public Nominations go_back() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().back();
        return this;
    }
    public Nominations validate_success_msg() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(message));
        String actual = driver.findElement(message).getText();
        Assert.assertTrue(actual.contains("Success"));
        return this;
    }
    public Nominations validate_error_message() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(error_message));
        String actual = driver.findElement(error_message).getText();
        Assert.assertTrue(actual.contains("reason for Opting Out"));
        return  this;
    }
    public Nominations validate_optOut_status() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(status));
        String actual = driver.findElement(status).getText();
        actual = actual.toLowerCase(Locale.ROOT);
        System.out.println("text = "+actual);
        Assert.assertTrue(actual.contains("opted out"));
        return  this;
    }
//    public Nominations click_() throws InterruptedException {
//
//        return  this;
//    }
}
