package pw.ee.testowanie2;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pw.ee.testowanie2.Testowanie2Application;
import pw.ee.testowanie2.repositories.FlashcardRepository;
import pw.ee.testowanie2.repositories.SetRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = Testowanie2Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static HttpResponse<String> latestResponse;
    final String SERVER_URL = "http://localhost:8080/";

    String bodyJSON = "";

    @Autowired
    protected SetRepository setRepository;

    @Autowired
    FlashcardRepository flashcardRepository;

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return headers;
    }

    void executeGet(String url) throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL + url))
                .build();

        try {
            latestResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IOException();
        }
    }

    void executePost(String url) throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL + url))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyJSON))
                .build();

        try {
            latestResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
