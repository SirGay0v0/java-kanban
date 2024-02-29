import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class KVTaskClient {
    String API_TOKEN;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    URI url;

    public KVTaskClient(URI url) throws InterruptedException, IOException {
        KVServer server = new KVServer();
        server.start();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        API_TOKEN = response.body();
    }

    private void put(String key, String json) throws IOException {
        url = URI.create("http://localhost:8078/save/" + key + "?API_TOKEN=" + API_TOKEN);

        request = HttpRequest.newBuilder()
                .uri(url)
                .POST(HttpRequest.BodyPublishers.ofByteArray(json.getBytes()))
                .build();
    }

    private String load(String key) throws IOException, InterruptedException {
        url = URI.create("http://localhost:8078/load/" + key + "?API_TOKEN=" + API_TOKEN);
        request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
