package Manager;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class KVTaskClient {
    private final String API_TOKEN;
    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private URI url;

    public KVTaskClient(URI url) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/register"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        API_TOKEN = response.body();
    }

    public void put(String key, String json) throws IOException, InterruptedException {
        url = URI.create("http://localhost:8078/save/" + key + "?API_TOKEN=" + API_TOKEN);

        request = HttpRequest.newBuilder()
                .uri(url)
                .POST(HttpRequest.BodyPublishers.ofByteArray(json.getBytes()))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String load(String key) throws InterruptedException {
        url = URI.create("http://localhost:8078/load/" + key + "?API_TOKEN=" + API_TOKEN);
        request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException ignore) {
        }
        return "";
    }
}
