package Testing;

import Pages.Allocate;
import Pages.BaseClass;
import org.testng.annotations.Test;

public class AllocationOwnerTests extends BaseClass {
    Allocate allocate=new Allocate();

    @Test(priority = 1)
    public void TC_11_check_nomination_count() throws InterruptedException{
        allocate.click_login();

        allocate.click_pod("468");
        allocate.click_nominate();
        allocate.reset_filter();
        allocate.add_hasher();
        allocate.confirm_nominations();
        allocate.wait_for_success();
        allocate.go_back();
        allocate.validate_count(1);
    }
    @Test(priority = 2)
    public void TC_12_check_count_change() throws InterruptedException{
        allocate.click_pod("461");
        allocate.click_nominate();
        allocate.reset_filter();
        allocate.nominate_hasher("sallyrutherford");
        allocate.close_search();
        allocate.nominate_hasher("katherinepullman");
        allocate.close_search();
        allocate.nominate_hasher("evanjames");
        allocate.confirm_nominations();
        allocate.wait_for_success();
        allocate.wait_for_consideration();
        allocate.go_back();
        allocate.validate_count(2);
    }
}
