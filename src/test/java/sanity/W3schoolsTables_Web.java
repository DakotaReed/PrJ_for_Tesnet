package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workFlows.WebFlows;

@Listeners(utilities.ListenersAuto.class)
public class W3schoolsTables_Web extends CommonOps {

    @Test(description = "Test 01: Verify Country of Company", dataProvider = "data-provider-indexesAndSearchTextS", dataProviderClass = utilities.ManageDDT.class)
    @Description("Verify Country of Company in CLIENT VS Country of Company in DATA (using LOOPs)")
    public void test01_verifyCountryOfCompany(String searchColumn, String searchCompany, String returnColumn, String companyCountry) {
        Verifications.verifyCountryOfCompany(UIActions.convertStrToInt(searchColumn), searchCompany, UIActions.convertStrToInt(returnColumn), companyCountry);
    }

    @Test(description = "Test 02: Verify Country of Company", dataProvider = "data-provider-indexesAndSearchTextS", dataProviderClass = utilities.ManageDDT.class)
    @Description("Verify Country of Company in CLIENT VS Country of Company in DATA (using XPath)")
    public void test02_verifyCountryOfCompany(String searchColumn, String searchCompany, String returnColumn, String companyCountry) {
        Verifications.verifyCountryOfCompanyByXPath(WebFlows.wrappingCountryName(UIActions.convertStrToInt(searchColumn), searchCompany, UIActions.convertStrToInt(returnColumn)), companyCountry);
    }

}
