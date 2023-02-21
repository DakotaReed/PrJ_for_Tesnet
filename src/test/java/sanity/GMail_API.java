package sanity;

import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workFlows.ApiFlows;

@Listeners(utilities.ListenersAuto.class)
public class GMail_API extends CommonOps {

    @Test(description = "Test 01: Print New Email (only once)")
    @Description("Print New Email (working ONLY ONCE on New Email)")
    public void test01() throws Exception {
        ApiFlows.retrieveMessagesAndPrint(getData("Host"), getData("UserName"), getData("UserPassword"));
        //--------  Sorry, but that's as far as I could go  --------
    }

}
