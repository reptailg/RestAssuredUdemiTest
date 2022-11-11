package config;

public enum TestConfig {
    URI("uri");
    public final String value;
    TestConfig(String value){
        this.value = Config.getConfig().getProperty(value);
    }
}
