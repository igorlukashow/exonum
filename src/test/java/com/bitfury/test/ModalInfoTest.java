package com.bitfury.test;

import com.bitfury.TestBase;
import com.bitfury.pages.WelcomePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ModalInfoTest extends TestBase {

    @DisplayName("Check modal content")
    @Test
    public void CheckModalInfoTest(){
        String name = "Allar Jõks";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                checkModalWindow(name);
    }

    @DisplayName("Check close modal window")
    @Test
    public void CheckCloseModalInfoTest(){
        String name = "Mailis Reps";
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickVote().
                clickCancel().
                checkModalWindowDisappeared().
                clickVote().
                clickCloseModal().
                checkModalWindowDisappeared();
    }

}
