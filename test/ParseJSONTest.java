import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParseJSONTest {
    Map<Integer, Account> accountMap;
    List<String> assessmentClassList;

    @BeforeEach
    void setUp() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://data.edmonton.ca/resource/q7d6-ambg.json"))
                .GET()
                .build();
        String jsonString;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonString = response.body();
        } catch (IOException | InterruptedException e) {
            jsonString = "";
        }
        ParseJSON parseJSON = new ParseJSON(jsonString);
        accountMap = parseJSON.getAccountMap();
        assessmentClassList = parseJSON.getAssessmentClassList();

    }

    @Test
    void getAccountMap() {
        assertTrue(accountMap.containsKey(1017870));
    }

    @Test
    void getAssessmentClassList() {
        assertTrue(assessmentClassList.contains("RESIDENTIAL"));
    }
}