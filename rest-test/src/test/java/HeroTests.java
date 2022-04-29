import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HeroTests {
  private final HttpClient httpClient = HttpClient
      .newBuilder()
      .build();

  //toDo: from file
  private final String createHeroRequestBody =
      """
            {
              "name": "Levi",
              "lastName": "Ackermann",
              "age": 25,
              "heroClass": "SURVEY_CORP"
            }
          """;

  @Test
  public void createHeroTest() throws URISyntaxException, IOException, InterruptedException {
    HttpRequest createHeroRequest = HttpRequest
        .newBuilder(new URI("http://localhost:82/api/v1/hero"))
        .headers("content-type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(createHeroRequestBody))
        .build();

    HttpResponse<String> createHeroResponse = httpClient.send(createHeroRequest, HttpResponse.BodyHandlers.ofString());

    String createdHeroResourceLocation = createHeroResponse
        .headers()
        .firstValue("Location")
        .orElseThrow(() -> new RuntimeException("Hero could not be created"));

    HttpRequest getHeroRequest = HttpRequest
        .newBuilder(new URI(createdHeroResourceLocation))
        .GET()
        .build();

    HttpResponse<String> getHeroResponse = httpClient.send(getHeroRequest, HttpResponse.BodyHandlers.ofString());

    JSONObject jsonObject = new JSONObject(getHeroResponse.body());
    Assertions.assertEquals("Levi", jsonObject.getString("name"));
    Assertions.assertEquals("Ackermann", jsonObject.getString("lastName"));
    Assertions.assertEquals(25, jsonObject.getInt("age"));
    Assertions.assertEquals("SURVEY_CORP", jsonObject.getString("heroClass"));
  }
}
