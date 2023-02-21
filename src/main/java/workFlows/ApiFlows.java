package workFlows;

import extensions.ApiActions;
import io.qameta.allure.Step;
import utilities.CommonOps;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

public class ApiFlows extends CommonOps {

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }
        if (message.isMimeType("multipart/*")) {
            mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                return result + bodyPart.getContent();
            }
            result += this.parseBodyPart(bodyPart);
        }
        return result;
    }

    private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
        if (bodyPart.isMimeType("text/html")) {
            return "\n" + org.jsoup.Jsoup
                    .parse(bodyPart.getContent().toString())
                    .text();
        }
        if (bodyPart.getContent() instanceof MimeMultipart){
            return getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }
        return "";
    }

    @Step("Business Flow: Print Content of Email")
    public static void printContentOfEmail(String content) {
        System.out.println("Text: " + "\n" + content);
    }

    @Step("Business Flow: Retrieve the Messages from the Folder in Array and Print It")
    public static void retrieveMessagesAndPrint(String host, String userName, String userPassword) throws MessagingException, IOException {
        ApiActions.connectToPopServer(host, userName, userPassword);
        ApiActions.createFolderAndOpen();
        messages = emailFolder.getMessages();
        System.out.println("messages.length---" + messages.length);
        for (int i = 0, n = messages.length; i < n; i++) {
            message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + "\t" + (i + 1));
            System.out.println("Subject: " + "\t" + message.getSubject());
            System.out.println("From: " + "\t" + message.getFrom()[0] + "\n");
            printContentOfEmail(objClass_APIFlows.getTextFromMessage(message));
        }
        ApiActions.closeStoreAndFolder();
    }

}
