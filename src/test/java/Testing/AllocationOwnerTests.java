package Testing;

import Pages.*;
import org.testng.annotations.Test;

public class AllocationOwnerTests extends BaseClass {
    Login login = new Login();
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();
    PodsPlatform PO = new PodsPlatform();

    public void create_newpod() throws InterruptedException {
        Thread.sleep(7000);
        PO
                .click_test_btn()
                .click_edit_btn()
                .set_delivery_leader()
                .set_product_manager()
                .click_update_btn()
                .wait_for_message()
                .go_back()
                .click_new_config()
                .set_config_name()
                .click_config_start_date()
                .set_todays_date()
                .set_role()
                .set_skill()
                .click_save_config()
                .wait_for_message()
                .click_new_pod()
                .set_pod_name()
                .click_onboard_date()
                .set_onboard_start_date()
                .click_billing_date()
                .set_billing_start_date()
                .set_SKU()
                .click_confirm_pod_btn()
                .select_config()
                .click_continue_btn()
                .click_confirm_btn()
                .wait_for_message()
                .click_latest_created_pod()
                .click_move_to_allocation()
                .click_confirm_btn();
    }


    @Test
    public void TC11_check_nomination_count() throws InterruptedException{

        // PO.pod_id="468";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
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

        // PO.pod_id="461";
        String hasher1 = "sallyrutherford_test@deloitte.com";
        String hasher2 = "katherinepullman_test@deloitte.com";
        String hasher3 = "evanjames_test@deloitte.com";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates 3 hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
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

        // PO.pod_id="461";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_nominate()
                .filter_b8_band()
                .change_page_size()
                .nominate_unallocated_hasher()
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .click_nominate()
                .give_confidence()
                .confirm_feedback()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
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

        // PO.pod_id="465";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_nominate()
                .filter_b8_band()
                .change_page_size()
                .nominate_unallocated_hasher()
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        String hasher_nominated = AllocationOwner.name;

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .click_nominations()
                .give_confidence()
                .confirm_feedback()
                .close_feedback();

        // Ao allocates hasher
         AllocationOwner.click_allocations()
                        .click_pod(PO.pod_id)
                        .click_allocation_tab()
                        .click_view_response()
                        .select_hasher()
                        .wait_for_message()
                        .click_close()
                        .accept_pod()
                        .click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .accept_pod()
                .click_confirm_pod();

        // create a new pod
        login.go_to_pods_platform();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_nominate()
                .reset_filter()
                .search_hasher(hasher_nominated)
                .nominate_hasher(hasher_nominated)
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .click_nominations()
                .give_confidence()
                .confirm_feedback()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_allocation_tab()
                .click_view_response()
                .select_hasher()
                .validate_already_allocated();
    }

    @Test
    public void TC20_check_closed_status() throws InterruptedException {

        // PO.pod_id = "468";
        String hasher = "melanierutherford";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_nominate()
                .filter_b8_band()
                .change_page_size()
                .nominate_unallocated_hasher()
                .confirm_nominations()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .click_nominate()
                .give_confidence()
                .confirm_feedback()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocations()
                .click_pod(PO.pod_id)
                .click_allocation_tab()
                .click_view_response()
                .select_hasher()
                .wait_for_message()
                .click_close()
                .accept_pod()
                .click_confirm_pod();

        // po Accepts pod
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .accept_pod()
                .click_confirm_pod();

        // check closed status
         AllocationOwner.click_allocations()
                        .click_closed_tab()
                        .validate_closed_status(PO.pod_id);
    }
}
