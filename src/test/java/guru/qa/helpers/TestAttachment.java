package guru.qa.helpers;

import guru.qa.configs.AuthConfig;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.helpers.CustomAPIListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class TestAttachment {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static byte[] pageSource(String attachName) {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/html", fileExtension = ".html")
    public static String addVideo(String attachName, String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl(String sessionId) {
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic(authConfig.getBrowserstackUser(), authConfig.getBrowserstackKey())
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
