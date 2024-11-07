package ApiTest.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ContactFactory {
    static Properties props = new Properties();
    private static String token;

    public static Map<String, Object> readJsonData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File("src/test/resources/testdata.json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage(), e);
        }
    }

    public static String readToken() {
        try (InputStream input = ContactFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
                token = props.getProperty("token"); // Retrieve the token property
                System.out.println("Token value: " + token);
                return token;
            } else {
                System.err.println("Config file not found.");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading config.properties file: " + e.getMessage());
            return null;
        }
    }

}
