package com.bitfury.pages;

import com.bitfury.TestBase;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Pin2Page extends TestBase {

    @Step("Check disabled SIGN BALLOT button")
    public Pin2Page checkDisabledSignBallotButton() {
        $(byXpath("//div[@ng-click='submitSign()']")).shouldBe(Condition.disabled);
        return this;
    }

    @Step("Click on digit")
    public Pin2Page clickOnKeyboardButtonDigit(String digit) {
        $(byXpath("//div[@class='keyboard-button-digit' and text()='"+digit+"']")).click();
        return this;
    }

    @Step("Check enabled SIGN BALLOT button")
    public Pin2Page checkEnabledSignBallotButton() {
        $(byXpath("//div[@ng-click='submitSign()']")).shouldBe(Condition.enabled);
        return this;
    }

    @Step("Set PIN2 value")
    public Pin2Page setPIN2(List<String> pin2) {
        for (String digit : pin2) {
            clickOnKeyboardButtonDigit(digit);
        }
        return this;
    }

    @Step("Click Sign Ballot button")
    public BallotSignedPage clickSignBallot() {
        $(byXpath("//div[@ng-click='submitSign()']")).click();
        return new BallotSignedPage();
    }

    @Step("Close PIN2 window")
    public BallotReceiptPage clickClosePIN2Window() {
        $(byXpath("//div[@id='signModal']/div/div/div/div[@class='modal-cross']")).click();
        return new BallotReceiptPage();
    }

    @Step("Click CANCEL button")
    public BallotReceiptPage clickCancelPIN2() {
        $(byXpath("//div[@ng-click='submitSign()']/../../div/div[text()='CANCEL']")).scrollIntoView(false).click();
        return new BallotReceiptPage();
    }
}
