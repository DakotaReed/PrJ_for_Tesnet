package pageObjects.tables;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(xpath = "//table[@id='customers']")
    public WebElement table_customers;

}
