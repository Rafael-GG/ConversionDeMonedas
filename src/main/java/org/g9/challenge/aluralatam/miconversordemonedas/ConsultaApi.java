package org.g9.challenge.aluralatam.miconversordemonedas;

import org.g9.challenge.aluralatam.miconversordemonedas.ApiKeyProvider;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    private static final String BASE = "https://v6.exchangerate-api.com/v6/";

    public static Moneda ConvertirMoneda(String de, String a, Double monto){
        String apiKey = ApiKeyProvider.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("API key no encontrada. Configurar la variable de entorno API_KEY o crear src/main/resources/config.properties con la clave (no subirla al repo).\nVea config.properties.example para el formato.");
        }

        URI direccion = URI.create(BASE + apiKey + "/pair/"
                + de + "/" + a + "/" + monto);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri((direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static CodigoDeMonedas consultarCodigosDeMonedas(){
        String apiKey = ApiKeyProvider.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("API key no encontrada. " +
                    "Configurar la variable de entorno API_KEY o " +
                    "crear src/main/resources/config.properties con la clave (no subirla al repo)." +
                    "\nVea config.properties.example para el formato.");
        }

        URI direccion = URI.create(BASE + apiKey + "/codes");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri((direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), CodigoDeMonedas.class);


        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
