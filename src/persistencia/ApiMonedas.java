package persistencia;

import com.google.gson.Gson;
import negocio.Moneda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiMonedas {

    public Moneda info(String opcion) throws IOException, InterruptedException {
        String URL = "https://v6.exchangerate-api.com/v6/0b901fbc2e54ed5edf3a9f32/latest/"+opcion;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        Moneda moneda = gson.fromJson(response.body(), Moneda.class);
        return moneda;
    }
}
