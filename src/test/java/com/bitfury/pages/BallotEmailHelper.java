package com.bitfury.pages;

import com.bitfury.TestBase;
import com.bitfury.utils.common.Email;
import io.qameta.allure.Step;

import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class BallotEmailHelper extends TestBase {

    @Step("Check Ballot email")
    public void checkBallotEmail(EmailSteps emailSteps, String memo, String hash) {
        Email email = searchEmailBySubject(emailSteps, "Voter, your ballot has been successfully posted on public bulletin board");
        email = getEmailBodyById(emailSteps, email);
        assertThat(email.getMail_body(), containsString(memo));
        assertThat(email.getMail_body(), containsString(hash));
    }

    @Step("Search email by subject")
    private Email searchEmailBySubject(EmailSteps emailSteps, String subject) {
        try {
            Thread.sleep(10000);
            for (int seconds = 0; ; seconds++) {
                if (seconds > 5) {
                    throw new AssertionError("Message not received within 3 minutes");
                }
                Email email = getEmailBySubject(emailSteps, subject);
                if (email != null) {
                    return email;
                }
                Thread.sleep(30000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Email getEmailBySubject(EmailSteps emailSteps, String subject){
        List<Email> emailList = emailSteps.checkEmail(emailSteps.sessionInfo, 0);
        for(Email email : emailList) {
            if(email.getMail_subject() != null) {
                if (email.getMail_subject().equals(subject)) {
                    return email;
                }
            }
        }
        return null;
    }

    @Step("Get email body")
    private Email getEmailBodyById(EmailSteps emailSteps, Email email) {
        return emailSteps.fetchEmail(emailSteps.sessionInfo, email);
    }

}
