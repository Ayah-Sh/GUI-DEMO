package utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesReader {

    private static final String CONFIG_FILE = "config.properties";

    private PropertiesReader() {
        // Prevent object creation
    }

    private static class Holder {
        private static final Properties PROPERTIES = loadProperties();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Properties file not found: " + CONFIG_FILE);
            }

            properties.load(inputStream);
            return properties;

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Failed to load properties file: " + CONFIG_FILE,
                    exception);
        }
    }

    public static String get(String key) {
        String value = Holder.PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Required property is missing or empty: " + key);
        }

        return value.trim();
    }

    public static String get(
            String key,
            String defaultValue) {

        String value = Holder.PROPERTIES.getProperty(key);

        return value == null || value.isBlank()
                ? defaultValue
                : value.trim();
    }

    public static int getInt(String key) {
        try {
            return Integer.parseInt(get(key));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Property must be a valid integer: " + key,
                    exception);
        }
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
