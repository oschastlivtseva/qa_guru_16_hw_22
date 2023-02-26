package guru.qa.drivers;

import com.codeborne.selenide.WebDriverProvider;
import guru.qa.configs.EmulatorConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobileBrowserType.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class EmulatorDriver implements WebDriverProvider {

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        EmulatorConfig config = ConfigFactory.create(EmulatorConfig.class, System.getProperties());

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(config.getDeviceName())
                .setPlatformVersion(config.getPlatformVersion())
                .setApp(getAppPath())
                .setAppPackage(config.getAppPackage())
                .setAppActivity(config.getAppActivity());

        return new AndroidDriver(new URL(config.getAppiumServer()), options);
    }


    private String getAppPath() {
        EmulatorConfig config = ConfigFactory.create(EmulatorConfig.class, System.getProperties());

        String appUrl = config.getAppURL();
        String appPath = config.getAppPath();

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
