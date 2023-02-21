package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pageObjects.tables.MainPage;
import workFlows.ApiFlows;
import workFlows.WebFlows;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

//    allure serve allure-results
public class Base {

//--------General--------
    protected static WebDriverWait wait;
    protected static Actions actions;
    protected static List<WebElement> listOfRows;
    protected static int index;

//--------Web--------
    protected static WebDriver driver;
    protected static WebFlows objClass_WebFlows;

//--------Rest API--------
    protected static Properties properties;
    protected static Session emailSession;
    protected static Store store;
    protected static Folder emailFolder;
    protected static Message[] messages;
    protected static Message message;
    protected static ApiFlows objClass_APIFlows;
    protected static MimeMultipart mimeMultipart;
    protected static BodyPart bodyPart;

//--------Page Objects - Web--------
    protected static MainPage W3_MainPage;

}
