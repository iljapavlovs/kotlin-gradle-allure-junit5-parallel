import com.codeborne.selenide.CollectionCondition.size
import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.getElement
import com.codeborne.selenide.Selenide.getElements
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.close
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.logevents.LogEventListener

import org.openqa.selenium.By
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach


/*
 * I tried to create a sample Selenide test in Kotlin, but encountered few problems:
 *
 * 1. Selenide.$(By.name("q")); // $ doesn't compile in Kotlin :(
 * 2. Selenide.getElement(By.name("q")).val("selenide+kotlin"); // val doesn't compile in Kotlin :(
 */
class GoogleTest {

    @BeforeEach fun setUp() {


        SelenideLogger.addListener("AllureSelenide", AllureSelenide().screenshots(true).savePageSource(false))

        Configuration.startMaximized = false;
        open("https://google.com/ncr")
    }


    @AfterEach
    fun tearDown() {
        SelenideLogger.removeListener<LogEventListener>("AllureSelenide")
        close()
    }

    @Test
    fun usingDollarsWithBackticks() {
        `$`(By.name("q")).setValue("selenide").pressEnter()
        `$$`("#ires .g").shouldHave(sizeGreaterThan(5))
        `$`("#ires .g").shouldHave(text("concise ui tests in Java"));
    }

    @Test fun notUsingDollars() {
        getElement(By.name("q")).setValue("selenide").pressEnter()
        getElements(By.cssSelector("#ires .g")).shouldHave(sizeGreaterThan(5))
        getElement(By.cssSelector("#ires .g")).shouldHave(text("concise ui tests in Java"));
    }

    @Test fun usingAliases_failForScreenshot() {
        get("[name=q]").setValue("selenide").pressEnter()
        all("#ires .g").shouldHave(sizeGreaterThan(5))
        get("#ires .g").shouldHave(text("text will not match in order to fail this test for screenshot generation"));

        //Selenide will not generate screenshot if using non-Selenide assertions
        // assertTrue(false) // will not generate screenshot automatically
    }


    fun get(selector: String) : SelenideElement {
        return `$`(selector);
    }

    fun all(selector: String) : ElementsCollection {
        return `$$`(selector);
    }
}