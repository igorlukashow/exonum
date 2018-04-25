package com.bitfury.test;

import com.bitfury.TestBase;
import com.bitfury.pages.EmailSteps;
import com.bitfury.pages.WelcomePage;
import com.bitfury.pages.WikiPage;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(DataProviderRunner.class)
public class CandidatesTest extends TestBase {

    @DisplayName("Check candidates list")
    @Test
    public void CheckCandidatesListTest(){
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                checkCandidatesList(Arrays.asList("Allar Jõks", "Siim Kallas", "Eiki Nestor", "Mailis Reps"));
    }

    @DataProvider
    public static Object[][] dataProviderCandidatesUrl() {
        return new Object[][]{
                {"Allar Jõks", "https://en.wikipedia.org/wiki/Allar_Jõks"},
                {"Siim Kallas", "https://en.wikipedia.org/wiki/Siim_Kallas"},
                {"Eiki Nestor", "https://en.wikipedia.org/wiki/Eiki_Nestor"},
                {"Mailis Reps", "https://en.wikipedia.org/wiki/Mailis_Reps"},
        };
    }

    @DisplayName("Check official candidate information")
    @UseDataProvider("dataProviderCandidatesUrl")
    @Test
    public void CheckOfficialCandidatePageTest(String name, String wikiUrl){
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                clickOfficialCandidatePage().
                checkCurrentUrl(wikiUrl);
    }

    @DisplayName("Check official candidate information")
    @UseDataProvider("dataProviderCandidatesUrl")
    @Test
    public void CheckCandidateInfoTest(String name, String wikiUrl){
        new WelcomePage().openWelcomePage().
                clickVoteInElection().
                setElection("Estonian Presidential Election").
                clickVoteInElection().
                clickCandidate(name).
                checkCandidateInfo(wikiUrl);
    }
}
