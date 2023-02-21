package extensions;

import io.qameta.allure.Step;
import utilities.CommonOps;

public class UIActions extends CommonOps {

    @Step("Convert String to Int")
    public static int convertStrToInt(String text) {
        return Integer.parseInt(text);
    }

}
