package extensions;

import io.qameta.allure.Step;
import utilities.CommonOps;
import javax.mail.Folder;
import javax.mail.MessagingException;

public class ApiActions extends CommonOps {

    @Step("Business Flow: Create the POP3 StoreObject and Connect With the PopServer")
    public static void connectToPopServer(String host, String userName, String userPassword) throws MessagingException {
        store = emailSession.getStore("pop3s");
        store.connect(host, userName, userPassword);
    }

    @Step("Business Flow: Create the FolderObject and Open It")
    public static void createFolderAndOpen() throws MessagingException {
        emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
    }

    @Step("Business Flow: Close the Store and FolderObject")
    public static void closeStoreAndFolder() throws MessagingException {
        emailFolder.close(false);
        store.close();
    }

}
