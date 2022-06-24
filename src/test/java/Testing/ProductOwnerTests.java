package Testing;


import Pages.*;
import org.testng.annotations.Test;

public class ProductOwnerTests extends BaseClass{
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
    public void TC01_check_po_able_to_create_newpod() throws InterruptedException {
        login.login_to_website();

        // create a new pod
        create_newpod();

        // validation
        PO.validate_move_to_allocation();

        System.out.println("pod id = "+PO.pod_id);
    }
    @Test
    public void TC15_check_nomination_count_update() throws InterruptedException{

        // PO.pod_id="POD-518";
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .click_add_hasher()
                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        // po
        ProductOwner.click_Mypods()
                    .click_pod(PO.pod_id)
                    .validate_count(1);

    }
    @Test
    public void TC16_check_nominations_count_donot_change() throws InterruptedException{
        // PO.pod_id="POD-528";

        String hasher1 = "sallyrutherford_test@deloitte.com";
        String hasher2 = "katherinepullman_test@deloitte.com";
        String hasher3 = "evanjames_test@deloitte.com";

       login.login_to_website();
       create_newpod();
       login.go_to_allocation_engine();

        // AO nominates 3 hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()

                .search_hasher(hasher1)
                .nominate_hasher(hasher1)
                .close_search()

                .search_hasher(hasher2)
                .nominate_hasher(hasher2)
                .close_search()

                .search_hasher(hasher3)
                .nominate_hasher(hasher3)

                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        // po
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .count_nominations()
                .wait_for_consideration()
                .validate_count(ProductOwner.count);
    }
    @Test
    public void TC18_check_confirmed_status() throws InterruptedException {

        // PO.pod_id = "POD-526";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        //AO nominates a hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .filter_b8_band()
                .change_page_size()
                .nominate_unallocated_hasher()
                .click_ConfirmNomination()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                    .click_pod(PO.pod_id)
                    .click_nominations()
                    .provide_ConfidenceLevel()
                    .select_hasher()
                    .wait_for_message()
                    .close_feedback();

        // Ao allocates hasher
         AllocationOwner.click_allocationsTab()
                        .click_pod(PO.pod_id)
                        .click_allocation_tab()
                        .click_view_response()
                        .select_hasher()
                        .wait_for_message()
                        .click_close()
                        .accept_pod()
                        .click_confirm_pod();

        // validation in mypods
        ProductOwner.click_Mypods()
                .validate_confirmed_status(PO.pod_id);

    }
    @Test
    public void TC19_check_PO_accept_pod() throws InterruptedException {

       //  PO.pod_id = "POD-454";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        //AO nominates a hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .filter_b8_band()
                .change_page_size()
                .nominate_unallocated_hasher()
                .click_ConfirmNomination()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_Mypods()
                .click_pod(PO.pod_id)
                .click_nominations()
                .provide_ConfidenceLevel()
                .select_hasher()
                .wait_for_message()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocationsTab()
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
                .click_confirm_pod()
                .validate_success_msg();
    }
     @Test
    public void TC20_check_closed_status() throws InterruptedException {

        // PO.pod_id = "POD-525";

        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

         //AO nominates a hasher
         AllocationOwner.click_allocationsTab()
                 .click_pod(PO.pod_id)
                 .click_nominate_btn()
                 .filter_b8_band()
                 .change_page_size()
                 .nominate_unallocated_hasher()
                 .click_ConfirmNomination()
                 .wait_for_message()
                 .wait_for_consideration()
                 .go_back();

        // PO gives confidence level to hasher
         ProductOwner.click_Mypods()
                 .click_pod(PO.pod_id)
                 .click_nominations()
                 .provide_ConfidenceLevel()
                 .select_hasher()
                 .wait_for_message()
                 .close_feedback();

        // Ao allocates hasher
         AllocationOwner.click_allocationsTab()
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
                 .click_confirm_pod()
                 .wait_for_message()
                 .validate_closed_status()
                 .go_back()
                 .click_closed_tab()
                 .validate_closed_status_in_closed_tab(PO.pod_id);
     }

}
