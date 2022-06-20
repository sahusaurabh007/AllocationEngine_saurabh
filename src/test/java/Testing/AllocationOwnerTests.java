package Testing;

import Pages.Allocate;
import Pages.BaseClass;
import Pages.Login;
import Pages.MyPods;
import org.testng.annotations.Test;

public class AllocationOwnerTests extends BaseClass {
    Login login = new Login();
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();

    @Test
    public void Select_unalocated_hasher() throws InterruptedException{

        String pod_id="468";

        login.login_to_website();

        // AO nominates a hasher
        AllocationOwner.click_allocations();
        AllocationOwner.click_pod(pod_id);
        AllocationOwner.click_nominate();
        //Thread.sleep(6000);
        AllocationOwner.filter_b8_band();
        Thread.sleep(3000);
        AllocationOwner.change_page_size();
        //Thread.sleep(4000);
         AllocationOwner.nominate_unallocated_hasher();
        System.out.println(AllocationOwner.name);
       // Thread.sleep(2000);
        AllocationOwner.confirm_nominations();
        AllocationOwner.wait_for_message();

    }

    @Test
    public void TC11_check_nomination_count() throws InterruptedException{

        String pod_id="468";

        login.login_to_website();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()
                .add_hasher()
                .confirm_nominations()
                .wait_for_message()
                .go_back()
                .validate_count(1);
    }

    @Test
    public void TC12_check_count_change() throws InterruptedException{

        String pod_id="461";
        String hasher1 = "sallyrutherford";
        String hasher2 = "katherinepullman";
        String hasher3 = "evanjames";

        login.login_to_website();

        // AO nominates 3 hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()

                .search_hasher(hasher1)
                .nominate_hasher(hasher1)
                .close_search()

                .search_hasher(hasher2)
                .nominate_hasher(hasher2)
                .close_search()

                .search_hasher(hasher3)
                .nominate_hasher(hasher3)

                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back()

        // validate
                .validate_count(2);
    }

    @Test
    public void TC14_check_hasher_allocated() throws InterruptedException {

        String pod_id="461";
        String hasher = "rebeccakelly";

        login.login_to_website();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()
                .search_hasher(hasher)
                .nominate_hasher(hasher)
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_allocation_tab()
                .click_view_response()
                .select_hasher()
                .wait_for_message()
                .click_close()

        // validation
                .validate_selected_hasher();
    }

    @Test
    public void TC15_check_hasher_alrady_allocated() throws InterruptedException {

        String pod_id="465";
        String hasher = "rebeccakelly";

        login.login_to_website();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()
                .search_hasher(hasher)
                .nominate_hasher(hasher)
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
         AllocationOwner.click_allocations()
                        .click_pod(pod_id)
                        .click_allocation_tab()
                        .click_view_response()
                        .select_hasher()
                        .wait_for_message()
                        .click_close()
                        .accept_pod()
                        .click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.accept_pod();
        ProductOwner.click_confirm_pod();

        // AO nominates a hasher
        String pod_id_2="462";
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()
                .search_hasher(hasher)
                .nominate_hasher(hasher)
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id_2);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_allocation_tab()
                .click_view_response()
                .select_hasher()
                .wait_for_message()
                .validate_already_allocated();
    }

    @Test
    public void TC20_check_closed_status() throws InterruptedException {

        String pod_id = "468";
        String hasher = "melanierutherford";

        login.login_to_website();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                .reset_filter()
                .search_hasher(hasher)
                .nominate_hasher(hasher)
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.click_nominate();
        ProductOwner.give_confidence();
        ProductOwner.confirm_feedback();
        ProductOwner.close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_allocation_tab()
                .click_view_response()
                .select_hasher()
                .wait_for_message()
                .click_close()
                .accept_pod()
                .click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods();
        ProductOwner.click_pod(pod_id);
        ProductOwner.accept_pod();
        ProductOwner.click_confirm_pod();

        // check closed status
         AllocationOwner.click_allocations()
                        .click_closed_tab()
                        .validate_closed_status(pod_id);
    }
}
