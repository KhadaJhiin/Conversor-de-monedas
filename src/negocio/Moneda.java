package negocio;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

public class Moneda {
    //@SerializedName("base_code")
    private String monedaNacional;
    //@SerializedName("conversion_result")
    private double valorExtranjero;
    //@SerializedName("target_code")
    private String monedaExtranjera;

    public Moneda() {}

    public Moneda(String nacionalidad, Double valor, String monedaExtranjera) {
        this.monedaNacional = nacionalidad;
        this.valorExtranjero = valor;
        this.monedaExtranjera = monedaExtranjera;
    }

    public Moneda(MonedaExchange monedaEx) {
        this.monedaNacional = monedaEx.base_code();
        this.valorExtranjero = monedaEx.conversion_result();
        this.monedaExtranjera = monedaEx.target_code();
    }

    public String getMonedaExtranjera() {
        return monedaExtranjera;
    }

    public String getMonedaNacional() {
        return monedaNacional;
    }

    public Double getValorExtranjero() {
        return valorExtranjero;
    }

    public Moneda info(String monedaBase, String monedaTransformada, double valorAconvertir)
            throws IOException, InterruptedException {
        String URL = "https://v6.exchangerate-api.com/v6/0b901fbc2e54ed5edf3a9f32/pair/" +
                monedaBase + "/" + monedaTransformada + "/" + valorAconvertir;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        //Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        Gson gson = new Gson();
        MonedaExchange monedaEx = gson.fromJson(response.body(), MonedaExchange.class);
        return new Moneda(monedaEx);
    }

    public String convertirMoneda(String monBase, String monAConvertir, Scanner input)
            throws IOException, InterruptedException {
        System.out.println("Ingrese el valor a convertir: ");
        double valorcop = Double.parseDouble(input.nextLine());
        Moneda miMoneda = new Moneda();
        miMoneda = miMoneda.info(monBase,monAConvertir,valorcop);
        return "El valor de " + valorcop + " " + nombrePais(miMoneda.getMonedaNacional(),valorcop) + " corresponde al valor final de : " + miMoneda.getValorExtranjero()
                + " " + nombrePais(miMoneda.getMonedaExtranjera(), miMoneda.getValorExtranjero());
    }

    public String nombrePais(String codigoPais, double pesos){
        switch (codigoPais){
            case "ARS" :
                if (pesos == 1){
                    return "peso Argentino";
                }else
                    return "pesos Argentinos";
            case "USD" :
                if (pesos == 1){
                    return "dolar";
                }else
                    return "dolares";
            case "BRL" :
                if (pesos == 1){
                    return "real Brazilero";
                }else
                    return "reales brazileros";
            case "COP" :
                if (pesos == 1){
                    return "peso Colombiano";
                }else
                    return "pesos Colombianos";
            default:
                return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda = (Moneda) o;
        return Double.compare(valorExtranjero, moneda.valorExtranjero) == 0 && Objects.equals(monedaNacional, moneda.monedaNacional) && Objects.equals(monedaExtranjera, moneda.monedaExtranjera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monedaNacional, valorExtranjero, monedaExtranjera);
    }
}
