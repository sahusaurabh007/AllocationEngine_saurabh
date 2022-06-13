package Testing;

import Pages.Allocate;
import Pages.BaseClass;
import Pages.MyPods;
import org.testng.annotations.Test;

public class AllocationOwnerTests extends BaseClass {
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();

    @Test(priority = 1)
    public void TC11_check_nomination_count() throws InterruptedException{
        AllocationOwner.click_login();

        String pod_id="468";

        // AO nominates a hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();
        AllocationOwner.add_hasher();
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.go_back();

        // validation
        AllocationOwner.validate_count(1);
    }
    //@Test(priority = 2)
    @Test( enabled=false )
    public void TC12_check_count_change() throws InterruptedException{
        String pod_id="461";
        String hasher1 = "sallyrutherford";
        String hasher2 = "katherinepullman";
        String hasher3 = "evanjames";

        // AO nominates 3 hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();

        AllocationOwner.search_hasher(hasher1);
        AllocationOwner.nominate_hasher(hasher1);
        AllocationOwner.nominate_hasher("sallyrutherford");
        AllocationOwner.close_search();

        AllocationOwner.search_hasher(hasher2);
        AllocationOwner.nominate_hasher(hasher2);
        AllocationOwner.nominate_hasher("katherinepullman");
        AllocationOwner.close_search();

        AllocationOwner.search_hasher(hasher3);
        AllocationOwner.nominate_hasher(hasher3);
        AllocationOwner.nominate_hasher("evanjames");

        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.wait_for_consideration();
        AllocationOwner.go_back();

        // validate
        AllocationOwner.validate_count(2);
    }
    @Test( priority = 3)
    //@Test( enabled=false )
    public void TC14_check_hasher_allocated() throws InterruptedException {
        String pod_id="461";
        String hasher = "rebeccakelly";

        // AO nominates a hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();
        AllocationOwner.search_hasher(hasher);
        AllocationOwner.nominate_hasher(hasher);
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.wait_for_consideration();
        AllocationOwner.go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_allocation_tab();
        AllocationOwner.click_view_response();
        AllocationOwner.wait_for_message();
        AllocationOwner.select_hasher();
        AllocationOwner.click_close();

        // validation
        AllocationOwner.validate_selected_hasher();
    }
    @Test( priority = 4)
    //@Test( enabled=false )
    public void TC15_check_hasher_alrady_allocated() throws InterruptedException {
        String pod_id="465";
        String hasher = "rebeccakelly";

        // AO nominates a hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();
        AllocationOwner.search_hasher(hasher);
        AllocationOwner.nominate_hasher(hasher);
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.wait_for_consideration();
        AllocationOwner.go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_allocation_tab();
        AllocationOwner.click_view_response();
        AllocationOwner.select_hasher();
        AllocationOwner.wait_for_message();
        AllocationOwner.click_close();
        AllocationOwner.accept_pod();
        AllocationOwner.click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.accept_pod();
        ProductOwner.click_confirm_pod();

        // AO nominates a hasher
        String pod_id_2="462";
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id_2);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();
        AllocationOwner.search_hasher(hasher);
        AllocationOwner.nominate_hasher(hasher);
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.wait_for_consideration();
        AllocationOwner.go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id_2);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id_2);
        AllocationOwner.click_allocation_tab();
        AllocationOwner.click_view_response();
        AllocationOwner.select_hasher();
        AllocationOwner.wait_for_message();

        // validate hasher already allocated
        AllocationOwner.validate_already_allocated();
    }
    // @Test(priority = 6)
    @Test( enabled=false )
    public void TC20_check_closed_status() throws InterruptedException {

        String pod_id = "468";
        String hasher = "melanierutherford";

        // AO nominates a hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        AllocationOwner.reset_filter();
        AllocationOwner.search_hasher(hasher);
        AllocationOwner.nominate_hasher(hasher);
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_success();
        AllocationOwner.wait_for_consideration();
        AllocationOwner.go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_allocation_tab();
        AllocationOwner.click_view_response();
        AllocationOwner.select_hasher();
        AllocationOwner.wait_for_message();
        AllocationOwner.click_close();
        AllocationOwner.accept_pod();
        AllocationOwner.click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.accept_pod();
        ProductOwner.click_confirm_pod();

        // check closed status
        AllocationOwner.click_allocations();
        AllocationOwner.click_closed_tab();
        AllocationOwner.validate_closed_status(pod_id);
    }
}
