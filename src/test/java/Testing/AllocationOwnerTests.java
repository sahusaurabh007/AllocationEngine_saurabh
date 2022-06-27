package Testing;

import Pages.*;
import org.testng.annotations.Test;

public class AllocationOwnerTests extends BaseClass {
    Login login = new Login();
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();
    PodsPlatform PO = new PodsPlatform();

    // chandana Tests
    @Test
    public void TC07_VerifyStatusOfPod() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.verify_StatusOfPod();

    }
    @Test
    public void TC08_VerifyStatusOfPodAfterNominate() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_add_hasher().
                click_ConfirmNomination().
                click_allocationsTab().
                click_confirm_pod().
                verify_StatusAfterNominate();

    }
    @Test
    public void TC09_VerifyAddingHaSher() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_add_hasher().
                click_ConfirmNomination();
    }
    @Test
    public void TC10_VerifyFilterOption() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_filter().
                click_apply().
                Verify_TheFilter();

    }
    @Test
    public void TC21_verifyStatusAfterSaveConfiguration() throws InterruptedException{
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_UpcomingPods().
                verify_StatusAfterConfigureThePod();
    }

    // saurabh Tests
    @Test
    public void TC11_check_nomination_count() throws InterruptedException{

        // PO.pod_id="468";

        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .click_add_hasher()
                .click_ConfirmNomination()
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
        PO.create_newpod();
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
                .wait_for_consideration()
                .go_back()

        // validate
                .validate_count(2);
    }

    @Test
    public void TC14_check_hasher_allocated() throws InterruptedException {

        // PO.pod_id="461";

        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
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
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .click_nominate()
                .provide_ConfidenceLevel()
                .select_hasher()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocationsTab()
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
        PO.create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
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

        String hasher_nominated = AllocationOwner.name;

        // PO gives confidence level to hasher
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .click_nominations()
                .provide_ConfidenceLevel()
                .select_hasher()
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
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .accept_pod()
                .click_confirm_pod();

        // create a new pod
        login.go_to_pods_platform();
        PO.create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .search_hasher(hasher_nominated)
                .nominate_hasher(hasher_nominated)
                .click_ConfirmNomination()
                .wait_for_message()
                .wait_for_consideration()
                .go_back();

        // PO gives confidence level to hasher
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .click_nominations()
                .provide_ConfidenceLevel()
                .select_hasher()
                .close_feedback();

        // Ao allocates hasher
        AllocationOwner.click_allocationsTab()
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
        PO.create_newpod();
        login.go_to_allocation_engine();

        // AO nominates a hasher
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
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .click_nominate()
                .provide_ConfidenceLevel()
                .select_hasher()
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
        ProductOwner.click_MyPods()
                .click_pod(PO.pod_id)
                .accept_pod()
                .click_confirm_pod();

        // check closed status
         AllocationOwner.click_allocationsTab()
                        .click_closed_tab()
                        .validate_closed_status(PO.pod_id);
    }
}
