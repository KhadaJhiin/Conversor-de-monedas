package negocio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exepciones.ValorFueraDeRangoExepcion;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Moneda {

    //Atributos

    private String fechaCreacion;
    private String horaDeConversion;
    private String monedaBase;
    private double valorAConvertir;
    private String monedaExtranjera;
    private double tasaDeConversion;
    private double resultado;

    //Constructor

    public Moneda() {}
    public Moneda(MonedaExchange monedaEx) {
        this.fechaCreacion = fechaCreacionFormateada("dd-MM-yyyy");
        this.horaDeConversion = fechaCreacionFormateada("HH:mm:ss");
        this.monedaBase = monedaEx.base_code();
        this.monedaExtranjera = monedaEx.target_code();
        this.tasaDeConversion = monedaEx.conversion_rate();
    }

    //GETTERS AND SETTERS

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getHoraDeConversion() {
        return horaDeConversion;
    }
    public void setHoraDeConversion(String horaDeConversion) {
        this.horaDeConversion = horaDeConversion;
    }
    public String getMonedaBase() {
        return monedaBase;
    }
    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }
    public double getValorAConvertir() {
        return valorAConvertir;
    }
    public void setValorAConvertir(double valorAConvertir) {
        this.valorAConvertir = valorAConvertir;
    }
    public String getMonedaExtranjera() {
        return monedaExtranjera;
    }
    public void setMonedaExtranjera(String monedaExtranjera) {
        this.monedaExtranjera = monedaExtranjera;
    }
    public double getTasaDeConversion() {
        return tasaDeConversion;
    }
    public void setTasaDeConversion(double tasaDeConversion) {
        this.tasaDeConversion = tasaDeConversion;
    }
    public double getResultado() {
        return resultado;
    }
    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    //Metodos

    public String imprimir(Moneda moneda){
        DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
        DecimalFormat formatoCientifico = new DecimalFormat("0.######E0");
        return "El valor de " + formatearNumero(valorAConvertir,formatoDecimal,formatoCientifico) + " " + nombrePais(moneda.monedaBase, valorAConvertir) + " corresponde al valor final de : " + formatearNumero(moneda.resultado,formatoDecimal,formatoCientifico)
                + " " + nombrePais(moneda.monedaExtranjera, moneda.resultado);
    }
    public String nombrePais(String codigoPais, double pesos){
        switch (codigoPais){
            case "ARS" :
                if (pesos == 1 || pesos == -1){
                    return "peso Argentino";
                }else
                    return "pesos Argentinos";
            case "USD" :
                if (pesos == 1 || pesos == -1){
                    return "dolar";
                }else
                    return "dolares";
            case "BRL" :
                if (pesos == 1 || pesos == -1){
                    return "real Brazilero";
                }else
                    return "reales brazileros";
            case "COP" :
                if (pesos == 1 || pesos == -1){
                    return "peso Colombiano";
                }else
                    return "pesos Colombianos";
            default:
                return "";
        }
    }
    public String formatearNumero(double numero, DecimalFormat formatoDecimal, DecimalFormat formatoCientifico) {
        String numeroStr = formatoDecimal.format(numero);
        if (numeroStr.replace(".", "").length() > 20){
            return formatoCientifico.format(numero);
        } else {
            return numeroStr;
        }
    }
    public String fechaCreacionFormateada(String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return LocalDateTime.now().format(formatter);
    }
}
