package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BallotReceiptPage extends TestBase {

    @Step("Check ballot receipt page")
    public BallotReceiptPage checkBallotReceiptPage() {
        $("div.app-content-header span").shouldHave(text("Ballot Receipt"));
        $(byXpath("//*[contains(text(), 'Ballot  SHA256 hash')]")).shouldBe(visible);
        $(byXpath("//*[contains(text(), 'Ballot  SHA256 hash')]/following-sibling::div[1]")).shouldBe(visible);
        $(byXpath("//div[contains(text(), 'Ballot reciept 3-word memo')]")).shouldBe(visible);
        $(byXpath("//div[contains(text(), 'Ballot reciept 3-word memo')]/following-sibling::div[1]")).shouldBe(visible);
        $(byXpath("//div[@ng-click='electionWizardReset()']")).shouldHave(cssClass("button-red")).shouldHave(text("DISCARD"));
        $(byXpath("//div[@ng-click='decryptModal()']")).shouldHave(cssClass("button-orange")).shouldHave(text("DECRYPT"));
        $(byXpath("//div[@ng-click='signModal()']")).shouldHave(cssClass("button-green")).shouldHave(text("SIGN"));
        $(byXpath("//div[text()='Save 3-word memo and ballot hash']")).shouldBe(visible);
        return this;
    }

    @Step("Click Sign button")
    public Pin2Page clickSign() {
        $(byXpath("//div[@ng-click='signModal()']")).scrollIntoView(true).click();
        return new Pin2Page();
    }

    @Step("Check PIN2 disappeared")
    public BallotReceiptPage checkPIN2Disappeared() {
        $("#signModal").shouldBe(disappear);
        return this;
    }

    @Step("Get memo text")
    public String getMemoText() {
        return $(byXpath("//div[contains(text(), 'Ballot reciept 3-word memo')]/following-sibling::div[1]")).getText();
    }

    @Step("Get hash text")
    public String getHashText() {
        return $(byXpath("//*[contains(text(), 'Ballot  SHA256 hash')]/following-sibling::div[1]")).getText();
    }
}
