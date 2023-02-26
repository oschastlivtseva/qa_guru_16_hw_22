package guru.qa.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:emulator.properties"
})

public interface EmulatorConfig extends Config {

    @Key("appiumServer")
    String getAppiumServer();

    @Key("deviceName")
    String getDeviceName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("appURL")
    String getAppURL();

    @Key("appPath")
    String getAppPath();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();

}
