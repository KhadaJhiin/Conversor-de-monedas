package negocio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exepciones.ValorFueraDeRangoExepcion;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Conversor {

    public Moneda info(String monedaBase, String monedaExtrangera) throws IOException, InterruptedException {
        String URL = "https://v6.exchangerate-api.com/v6/0b901fbc2e54ed5edf3a9f32/pair/" +
                monedaBase + "/" + monedaExtrangera;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        //Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MonedaExchange monedaEx = gson.fromJson(response.body(), MonedaExchange.class);
        return new Moneda(monedaEx);
    }
    public Moneda convertirMoneda(String monBase, String monAConvertir, Scanner input) throws IOException, InterruptedException, ValorFueraDeRangoExepcion {

        System.out.println("Ingrese el valor a convertir: ");
        String valorCop = input.nextLine();
        double valorAConvertir;

        try {
            valorAConvertir = Double.parseDouble(valorCop);
        } catch (Exception e) {
            throw new ValorFueraDeRangoExepcion("Ingrese solo números, recuerde usar '.' (punto) en vez de ',' (coma)\n" +
                     " para representar decimales");
        }

        if (valorAConvertir > 1.7e308){
            throw new ValorFueraDeRangoExepcion("El valor de entrada supera el rango permitido");
        }

        Moneda miMoneda;
        miMoneda = info(monBase,monAConvertir);
        miMoneda.setValorAConvertir(valorAConvertir);
        miMoneda.setResultado(miMoneda.getTasaDeConversion() * valorAConvertir);

        if (miMoneda.getResultado() > 1.7e308){
            throw new ValorFueraDeRangoExepcion("El valor de salida supera el rango permitido");
        }
        return miMoneda;
    }

}
