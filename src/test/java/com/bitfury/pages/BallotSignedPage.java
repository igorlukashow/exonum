package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BallotSignedPage extends TestBase {

    @Step("Check Ballot signed nessage")
    public BallotSignedPage checkSuccessMessageBallotSigned() {
        $("div.toolbar-title span").shouldHave(text("Ballot has been signed"));
        return this;
    }

    @Step("Check Button appeared on ballot signed page")
    public BallotSignedPage checkButtonAppearedOnBallotSignedPage() {
        $(byXpath("//div[text()='SUBMIT BALLOT']")).shouldHave(cssClass("button-green")).shouldBe(visible);
        $(byXpath("//div[@ng-click='electionWizardReset()']")).shouldHave(text("DISCARD BALLOT")).shouldBe(visible);
        return this;
    }

    @Step("Click DISCARD BALLOT")
    public WelcomePage clickDiscardBallot() {
        $(byXpath("//div[@ng-click='electionWizardReset()']")).click();
        return new WelcomePage();
    }

    @Step("Set email value")
    public BallotSignedPage setEmail(String email) {
        $(byXpath("//input[@ng-model='inputs.email']")).setValue(email);
        return this;
    }

    @Step("Click Submit Ballot")
    public ElectionSubmittedPage clickSubmitBallot() {
        $(byXpath("//div[text()='SUBMIT BALLOT']")).click();
        return new ElectionSubmittedPage();
    }
}
