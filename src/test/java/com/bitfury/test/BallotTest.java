package com.bitfury.test;

import com.bitfury.TestBase;
import com.bitfury.pages.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.Arrays;

public class BallotTest extends TestBase {

    @DisplayName("Check Ballot Receipt content")
    @Test
    public void CheckBallotReceiptContentTest(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                checkBallotReceiptPage();
    }

    @DisplayName("PIN2 required test")
    @Test
    public void CheckRequiredPIN2Test(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                clickSign().
                checkDisabledSignBallotButton().
                clickOnKeyboardButtonDigit("1").
                checkDisabledSignBallotButton().
                clickOnKeyboardButtonDigit("2").
                checkDisabledSignBallotButton().
                clickOnKeyboardButtonDigit("7").
                checkDisabledSignBallotButton().
                clickOnKeyboardButtonDigit("9").
                checkEnabledSignBallotButton();
    }

    @DisplayName("Check close PIN2 page")
    @Test
    public void CheckClosePIN2PageTest(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                clickSign().
                clickClosePIN2Window().
                checkPIN2Disappeared();
    }

    @DisplayName("Check Ballot signed content")
    @Test
    public void CheckBallotSignedContentTest(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                clickSign().
                setPIN2(Arrays.asList("6","5","4","3")).
                clickSignBallot().
                checkSuccessMessageBallotSigned().
                checkButtonAppearedOnBallotSignedPage();
    }

    @DisplayName("Check Discard Ballot")
    @Test
    public void CheckDiscardBallotTest(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                clickSign().
                setPIN2(Arrays.asList("0","9","8","7")).
                clickSignBallot().
                clickDiscardBallot().
                checkWelcomePageOpened();
    }

    @DisplayName("Check Ballot signed email")
    @Test
    public void CheckBallotSignedEmailTest(){
        String name = "Siim Kallas";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickYes().
                clickSign().
                setPIN2(Arrays.asList("6","5","4","3")).
                clickSignBallot();
        String memo = new BallotReceiptPage().getMemoText();
        String hash = new BallotReceiptPage().getHashText();
        EmailSteps emailSteps = new EmailSteps().initialEmail();
        new BallotSignedPage().setEmail(emailSteps.sessionInfo.getEmail_addr()).
                clickSubmitBallot();
        new BallotEmailHelper().checkBallotEmail(emailSteps, memo, hash);
        emailSteps.forgetMe();
    }
}
