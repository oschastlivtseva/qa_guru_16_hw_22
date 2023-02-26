package guru.qa.drivers;

import com.codeborne.selenide.WebDriverProvider;
import guru.qa.configs.AuthConfig;
import guru.qa.configs.BrowserstackConfig;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

        MutableCapabilities caps = new MutableCapabilities();
        caps.merge(capabilities);

        // Set your access credentials
        caps.setCapability("browserstack.user", authConfig.getBrowserstackUser());
        caps.setCapability("browserstack.key", authConfig.getBrowserstackKey());

        // Set URL of the application under test
        caps.setCapability("app", config.getApp());
        caps.setCapability("browserstack.networkLogs", config.browserstackNetworkLogs());

        // Specify device and os_version for testing
        caps.setCapability("device", config.getDeviceName());
        caps.setCapability("os_version", config.getOSVersion());

        // Set other BrowserStack capabilities
        caps.setCapability("project", config.getProjectName());
        caps.setCapability("build", config.getBuildName());
        caps.setCapability("name", config.getTestName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and mutable capabilities defined above
        return new RemoteWebDriver(new URL(config.getRemoteURL()), caps);
    }
}
