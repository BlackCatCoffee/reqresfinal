package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    protected static RequestSpecification req;
    static PrintStream log;

    static {
        try {
            log = new PrintStream(Files.newOutputStream(Paths.get("logging.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Utils() throws IOException {
    }

    public RequestSpecification requestSpecification() throws IOException {


        req = new RequestSpecBuilder()
                .setBaseUri(getGlobalProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
        return req;

    }

    public static String getGlobalProperty(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                ("C:\\Users\\brett.dixon\\Downloads\\BasicTestProject" +
                        "\\reqresfinal\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
