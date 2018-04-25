package com.bitfury.pages;

import com.bitfury.TestBase;
import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WikiPage extends TestBase {

    @Step("Check current url")
    public WikiPage checkCurrentUrl(String wikiUrl) {
        switchTo().window(1);
        assertThat(url(), is(wikiUrl));
        switchTo().window(1).close();
        switchTo().window(0);
        return this;
    }

    @Step("Get first sentence from wiki page")
    public String getFirstSentenceText(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("p");
            Element firstParagraph = paragraphs.first();
            String p = firstParagraph.text();
            return p.substring(0, p.indexOf(".")+1);
        }
        catch (IOException e) {
            return "Failed getting text";
        }
    }
}
