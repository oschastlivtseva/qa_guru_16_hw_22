package guru.qa.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})

public interface AuthConfig extends Config {

    @Key("browserstack.user")
    String getBrowserstackUser();

    @Key("browserstack.key")
    String getBrowserstackKey();

}
