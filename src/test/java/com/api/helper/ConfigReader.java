package com.api.helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

    public class ConfigReader {
        private static Properties props;

        static {
            try {
                props = new Properties();
                FileInputStream fis = new FileInputStream("src/main/java/com/apiautomation/config.properties");
                props.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }

        public static String get(String key) {
            return props.getProperty(key);
        }
    }


