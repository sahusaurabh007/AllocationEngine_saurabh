package Testing;


import Pages.Allocate;
import Pages.BaseClass;
import Pages.Login;
import Pages.MyPods;
import org.testng.annotations.Test;

public class ProductOwnerTests extends BaseClass{
    Login login = new Login();
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();

    @Test
    public void TC15_check_nomination_count_update() throws InterruptedException{

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
                .go_back();

        // po
        ProductOwner.click_Mypods()
                    .click_pod(pod_id)
                    .validate_count(1);

    }
    @Test
    public void TC16_check_nominations_count_donot_change() throws InterruptedException{
        String pod_id="490";
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
                .go_back();

        // po
        ProductOwner.click_Mypods()
                .click_pod(pod_id)
                .count_nominations()
                .wait_for_consideration()
                .validate_count(ProductOwner.count);
    }
    @Test
    public void TC18_check_confirmed_status() throws InterruptedException {

        String pod_id = "468";

        login.login_to_website();

        //AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
        //Thread.sleep(6000);
                .filter_b8_band()
                .change_page_size()
        //Thread.sleep(4000);
                .nominate_unallocated_hasher()
        //Thread.sleep(2000);
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                    .click_pod(pod_id)
                    .click_nominations()
                    .give_confidence()
                    .confirm_feedback()
                    .close_feedback();

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

        // validation in mypods
        ProductOwner.click_Mypods()
                .validate_confirmed_status(pod_id);

    }
    @Test
    public void TC19_check_PO_accept_pod() throws InterruptedException {

        String pod_id = "454";
        login.login_to_website();

        //AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(pod_id)
                .click_nominate()
                //Thread.sleep(6000);
                .filter_b8_band()
                .change_page_size()
                //Thread.sleep(4000);
                .nominate_unallocated_hasher()
                //Thread.sleep(2000);
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(pod_id)
                .click_nominations()
                .give_confidence()
                .confirm_feedback()
                .close_feedback();

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
        ProductOwner.click_Mypods()
                .click_pod(pod_id)
                .accept_pod()
                .click_confirm_pod()
                .validate_success_msg();
    }
     @Test
    public void TC20_check_closed_status() throws InterruptedException {

        String pod_id = "462";

        login.login_to_website();

         //AO nominates a hasher
         AllocationOwner.click_allocations()
                 .click_pod(pod_id)
                 .click_nominate()
                 //Thread.sleep(6000);
                 .filter_b8_band()
                 .change_page_size()
                 //Thread.sleep(4000);
                 .nominate_unallocated_hasher()
                 //Thread.sleep(2000);
                 .confirm_nominations()
                 .wait_for_message()
                 .wait_for_consideration()
                 .go_back();

        // PO gives confidence level to hasher
         ProductOwner.click_Mypods()
                 .click_pod(pod_id)
                 .click_nominations()
                 .give_confidence()
                 .confirm_feedback()
                 .close_feedback();

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
         ProductOwner.click_Mypods()
                 .click_pod(pod_id)
                 .accept_pod()
                 .click_confirm_pod()
                 .wait_for_message()
                 .validate_closed_status()
                 .go_back()
                 .click_closed_tab()
                 .validate_closed_status_in_closed_tab(pod_id);
     }

}
