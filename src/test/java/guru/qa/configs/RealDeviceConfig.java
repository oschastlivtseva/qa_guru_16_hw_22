package guru.qa.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:real.properties"
})

public interface RealDeviceConfig extends Config {

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
