package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import workFlows.ApiFlows;
import workFlows.WebFlows;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import javax.mail.*;

public class CommonOps extends Base {

    public static String getData(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/DataConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);        }
        catch(Exception e) {
            System.out.println("Exception in reading XML file: " + e);        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public void initBrowser(String browserType) {
        if (browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if (browserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver();
        else
            throw new RuntimeException("Invalid browser type");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(getData("URL"));
        wait = new WebDriverWait(driver, 5);
        actions = new Actions(driver);
        ManagePages.initW3();
    }

    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public static void initJavaxMail() {
        properties = new Properties();
        properties.put("mail.pop3.host", getData("Host"));
        properties.put("mail.pop3.port", getData("Port"));
        properties.put("mail.pop3.starttls.enable", "true");
        emailSession = Session.getDefaultInstance(properties);
    }

    @BeforeClass
    public void startSession() {
        if (getData("PlatformNameOfTest").equalsIgnoreCase("web"))
            initBrowser(getData("BrowserType"));
        else if (getData("PlatformNameOfTest").equalsIgnoreCase("api") && getData("JAVAX_MAIL").equalsIgnoreCase("YES"))
            initJavaxMail();
        else
            throw new RuntimeException("Invalid platform");
        objClass_WebFlows = new WebFlows();
        objClass_APIFlows = new ApiFlows();
    }

    @AfterClass
    public void closeSession() {
        if (!getData("PlatformNameOfTest").equalsIgnoreCase("api")) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        if (!getData("PlatformNameOfTest").equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.startRecord(method.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}