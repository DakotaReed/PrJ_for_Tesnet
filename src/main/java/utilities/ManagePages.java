package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.tables.MainPage;

public class ManagePages extends Base {

    public static void initW3() {
        W3_MainPage = PageFactory.initElements(driver, MainPage.class);
    }

}
