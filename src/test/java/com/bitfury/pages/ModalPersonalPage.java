package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ModalPersonalPage extends TestBase {

    @Step("Check Modal window content")
    public ModalPersonalPage checkModalWindow(String name) {
        $("div.confirm-choise-block-title").shouldHave(text("Are you sure you want to prepare\nan anonymous ballot with\nfollowing candidate selected?"));
        $("div.confirm-choise-block-name.ng-binding").shouldHave(text(name));
        $(byXpath("//div[@ng-click='submitCandidate()']")).shouldHave(cssClass("button-red")).shouldHave(text("YES"));
        $(byXpath("//div[@data-dismiss='modal' and text()='CANCEL']")).shouldBe(visible);
        return this;
    }

    @Step("Click Cancel button")
    public CandidatesPage clickCancel() {
        $(byXpath("//div[@data-dismiss='modal' and text()='CANCEL']")).click();
        return new CandidatesPage();
    }

    @Step("Click close modal window")
    public CandidatesPage clickCloseModal() {
        $("div.modal-cross").scrollIntoView(true).shouldBe(visible).click();
        return new CandidatesPage();
    }

    @Step("Click YES")
    public BallotReceiptPage clickYes() {
        $(byXpath("//div[@ng-click='submitCandidate()']")).click();
        return new BallotReceiptPage();
    }
}
