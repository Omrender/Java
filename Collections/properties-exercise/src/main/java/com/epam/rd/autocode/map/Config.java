package com.epam.rd.autocode.map;

import java.io.*;
import java.util.Properties;

public class Config {
    private Properties config;

    public Config() {
        this.config = new Properties();
        reset();
    }

    public void reset() {
        // Clear current properties
        config.clear();

        // Load properties from default files and the main config file
        Properties defaults = new Properties();
        
        // Load default properties files
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            Properties mainConfig = new Properties();
            mainConfig.load(inputStream);
            String defaultFiles = mainConfig.getProperty("default.filenames");
            
            if (defaultFiles != null) {
                String[] defaultFileNames = defaultFiles.split(",");
                // Load default properties files in reverse order
                for (int i = defaultFileNames.length - 1; i >= 0; i--) {
                    String fileName = defaultFileNames[i].trim();
                    try (InputStream defaultInputStream = new FileInputStream(fileName + ".properties")) {
                        Properties defaultProperties = new Properties();
                        defaultProperties.load(defaultInputStream);
                        defaults.putAll(defaultProperties);
                    } catch (IOException e) {
                        // Handle file not found or other IO exceptions
                        // We might want to log this exception but not fail the entire process
                    }
                }
            }
            
            // Load main config properties
            this.config = new Properties(defaults);
            this.config.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            // Handle file not found or other IO exceptions
            // We might want to log this exception but not fail the entire process
        }
    }

    public String get(String key) {
        return config.getProperty(key);
    }

    public void set(String key, String value) {
        config.setProperty(key, value);
    }

    public void save() {
        try (OutputStream outputStream = new FileOutputStream("config.properties")) {
            config.store(outputStream, null);
        } catch (IOException e) {
            // Handle file IO exceptions
        }
    }

    public void remove(String key) {
        config.remove(key);
    }
}
