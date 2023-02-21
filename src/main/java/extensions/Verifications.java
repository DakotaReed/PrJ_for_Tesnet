package extensions;

import io.qameta.allure.Step;
import utilities.CommonOps;
import workFlows.WebFlows;
import static org.testng.Assert.*;

public class Verifications extends CommonOps {

    @Step("Verify Country of Company")
    public static void verifyCountryOfCompany(int searchColumn, String searchText, int returnColumnText, String expectedText) {
        assertTrue(WebFlows.wrappingResultCountryOfCompany(searchColumn, searchText, returnColumnText, expectedText));
    }

    @Step("Verify Country of Company by ONLY XPath")
    public static void verifyCountryOfCompanyByXPath(int searchColumn, String searchText, int returnColumnText, String expectedText) throws Exception {
        assertTrue(WebFlows.wrappingCountryName(searchColumn, searchText, returnColumnText).equalsIgnoreCase(expectedText));
    }

}
