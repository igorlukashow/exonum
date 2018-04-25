package com.bitfury.pages;

import com.bitfury.TestBase;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CandidatesPage extends TestBase {

    @Step("Check Candidates List")
    public CandidatesPage checkCandidatesList(List<String> candidates) {
        $$(byXpath("//table[@class='app-list']/tbody/tr/td[@class='ng-binding']")).shouldHave(texts(candidates));
        return this;
    }

    @Step("Click candidate name")
    public CandidatesPage clickCandidate(String name) {
        $(byXpath("//table[@class='app-list']/tbody/tr/td[@class='ng-binding' and text()='"+name+"']")).click();
        return this;
    }

    @Step("Click 'Official candidate page' link")
    public WikiPage clickOfficialCandidatePage() {
        $(byText("Official candidate page")).click();
        return new WikiPage();
    }

    @Step("Check candidate info text")
    public CandidatesPage checkCandidateInfo(String url) {
        String wikiText = new WikiPage().getFirstSentenceText(url);
        String actualText = $(byXpath("//div[@class='list-option-description ng-binding']")).getText();
        assertThat(actualText, is(wikiText));
        return this;
    }

    @Step("Click VOTE IN ELECTION")
    public ModalPersonalPage clickVote() {
        $(byXpath("//div[@ng-click='chooseCandidate()']")).click();
        return new ModalPersonalPage();
    }

    @Step("Check disappeared modal window")
    public CandidatesPage checkModalWindowDisappeared() {
        $("div.modal-dialog").shouldBe(disappear);
        return this;
    }
}
