package guru.qa.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:browserstack.properties"
})

public interface BrowserstackConfig extends Config {

    @Key("app")
    String getApp();

    @Key("browserstack.networkLogs")
    Boolean browserstackNetworkLogs();

    @Key("device")
    String getDeviceName();

    @Key("os_version")
    String getOSVersion();

    @Key("project")
    String getProjectName();

    @Key("build")
    String getBuildName();

    @Key("name")
    String getTestName();

    @Key("remote_url")
    String getRemoteURL();

}
