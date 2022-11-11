package config;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties properties;

    public static Properties getConfig(){
        return getConfig("config.properties");
    }

    @SneakyThrows
    public static Properties getConfig(String filename){
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(filename)){

            if(input == null){
                throw new IllegalArgumentException("Unable to find " + filename);
            }
            properties = new Properties();
            properties.load(input);

        }
        return properties;
    }
}
