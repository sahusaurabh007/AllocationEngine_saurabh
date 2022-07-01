package Testing;

import Pages.*;
import org.testng.annotations.Test;

public class HasherTests extends BaseClass {
    Login login = new Login();
    Allocate AllocationOwner =new Allocate();
    MyPods ProductOwner = new MyPods();
    Nominations Hasher = new Nominations();
    PodsPlatform PO = new PodsPlatform();

    // saurabh Tests
    @Test
    public void TC11_Verify_click_on_opt_out_button() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        //PO.pod_id="POD-549";
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .search_hasher(prop.getProperty("Email"))
                .nominate_hasher(prop.getProperty("Email"))
                .close_search()
                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        Hasher.click_MyNominationsTab()
                .click_1st_Nomination()
                .click_Opt_Out_btn()
                .click_optOutreason_btn()
                .select_optOutOption("Tech Stack Mismatch")
                .click_optOutConfirm_btn()
                .validate_success_msg();

    }
    @Test
    public void TC12_Verify_click_on_confirm_button_without_selecting_reason() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        //PO.pod_id="POD-549";
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .search_hasher(prop.getProperty("Email"))
                .nominate_hasher(prop.getProperty("Email"))
                .close_search()
                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        Hasher.click_MyNominationsTab()
                .click_1st_Nomination()
                .click_Opt_Out_btn()
                .click_optOutConfirm_btn()
                .validate_error_message();

    }
    @Test
    public void TC13_Verify_choosing_others_option_without_adding_reason() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

        //PO.pod_id="POD-549";
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .search_hasher(prop.getProperty("Email"))
                .nominate_hasher(prop.getProperty("Email"))
                .close_search()
                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        Hasher.click_MyNominationsTab()
                .click_1st_Nomination()
                .click_Opt_Out_btn()
                .click_optOutreason_btn()
                .select_optOutOption("Others")
                .click_optOutConfirm_btn()
                .validate_error_message();

    }
    @Test
    public void TC14_Verify_Opt_out_status() throws InterruptedException {
        login.login_to_website();
        PO.create_newpod();
        login.go_to_allocation_engine();

       // PO.pod_id="POD-598";
        AllocationOwner.click_allocationsTab()
                .click_pod(PO.pod_id)
                .click_nominate_btn()
                .reset_filter()
                .search_hasher(prop.getProperty("Email"))
                .nominate_hasher(prop.getProperty("Email"))
                .close_search()
                .click_ConfirmNomination()
                .wait_for_message()
                .go_back();

        Hasher.click_MyNominationsTab()
                .click_1st_Nomination()
                .click_Opt_Out_btn()
                .click_optOutreason_btn()
                .select_optOutOption("Tech Stack Mismatch")
                .click_optOutConfirm_btn()
                .wait_for_message()
                .go_back()
                .validate_optOut_status();

    }
}
