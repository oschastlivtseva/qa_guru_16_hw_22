package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.drivers.BrowserstackDriver;
import guru.qa.drivers.EmulatorDriver;
import guru.qa.drivers.RealDeviceDriver;
import guru.qa.helpers.TestAttachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        String deviceHost = System.getProperty("deviceHost");
        switch (deviceHost) {
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = EmulatorDriver.class.getName();
                break;
            case "real":
                Configuration.browser = RealDeviceDriver.class.getName();
                break;
            default: throw new RuntimeException(
                    "Invalid value for 'deviceHost'. Valid values are: browserstack / emulator / real"
            );
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void addTestAttachments() {
        String deviceHost = System.getProperty("deviceHost");
        String sessionId = sessionId().toString();

        TestAttachment.screenshotAs("Screenshot");
        TestAttachment.pageSource("Page source");

        closeWebDriver();

        if (deviceHost.equals("browserstack")) {
            TestAttachment.addVideo("Video", sessionId);
        }
    }

}
