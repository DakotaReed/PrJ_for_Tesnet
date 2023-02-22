package workFlows;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;
import java.util.List;

public class WebFlows extends CommonOps {

    @Step("Business Flow: Building List of Table Rows")
    public static void buildListRowsOfTable(WebElement table) {
        listOfRows = table.findElements(By.tagName("tr"));
    }

    @Step("Business Flow: Building List of Row Values (tagname = 'td')")
    public static List<WebElement> buildListRowValues(int row) {
        return listOfRows.get(row).findElements(By.tagName("td"));
    }

    @Step("Business Flow: Identification Number of Needed Row")
    public static int searchRow(int searchColumn, String searchText) {
        for (int i=1; i < listOfRows.size(); i++) {
            if (buildListRowValues(i).get(searchColumn).getText().equalsIgnoreCase(searchText)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Step("Business Flow: Getting Value from Column in Needed Row")
    public static String getValueInNeededRow(int neededRow, int returnColumnText) {
        return buildListRowValues(neededRow).get(returnColumnText).getText();
    }

//--------DON'T TOUCH--------
    @Step("Business Flow: Getting Country Name from Table (return String) DON'T TOUCH")
    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        buildListRowsOfTable(table);
        return getValueInNeededRow(searchRow(searchColumn, searchText), returnColumnText);
    }

//--------DON'T TOUCH--------
    @Step("Business Flow: Return TRUE or FALSE of Verifying Country Name DON'T TOUCH")
    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        return getTableCellText(table, searchColumn, searchText, returnColumnText).equalsIgnoreCase(expectedText);
    }

    @Step("Business Flow: Wrapping Result of Verifying Country Name")
    public static boolean wrappingResultCountryOfCompany(int searchColumn, String searchText, int returnColumnText, String expectedText) {
        return objClass_WebFlows.verifyTableCellText(W3_MainPage.table_customers, searchColumn, searchText, returnColumnText, expectedText);
    }

//--------DON'T TOUCH--------
    @Step("Business Flow: Getting Country Name from Table by XPath (return String) DON'T TOUCH")
    public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        return table.findElement(By.xpath("//td[normalize-space()='" + searchText + "']//parent::tr//td[" + (returnColumnText+1) + "]")).getText();
    }

    @Step("Business Flow: Wrapping Country Name")
    public static String wrappingCountryName(int searchColumn, String searchText, int returnColumnText) {
        return objClass_WebFlows.getTableCellTextByXpath(W3_MainPage.table_customers, searchColumn, searchText, returnColumnText);
    }

}
