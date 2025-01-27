package com.solvd.task.utils;

import jakarta.mail.*;
import jakarta.mail.search.SubjectTerm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;

import static com.solvd.task.utils.PropertiesLoader.PropertySource.*;

public class EmailVerification {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailVerification.class);
    private static final String EMAIL = PropertiesLoader.getProperty(USER, "email");
    private static final String APP_PASS = PropertiesLoader.getProperty(USER, "password");
    private static final String INBOX_FOLDER_NAME = "INBOX";

    public boolean isMessagePresent(String token) {
        Properties props = PropertiesLoader.getProperties(APP);

        try (Store store = Session.getInstance(props).getStore("imap")) {
            store.connect(EMAIL, APP_PASS);
            Folder inbox = store.getFolder(INBOX_FOLDER_NAME);
            inbox.open(Folder.READ_ONLY);

            Message[] search = inbox.search(new SubjectTerm(token));
            return search.length > 0;
        } catch (MessagingException e) {
            LOGGER.error("Error while searching message occurred: ", e);
        }
        return false;
    }

}
