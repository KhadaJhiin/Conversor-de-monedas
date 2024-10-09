package negocio;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
    public void setValorAConvertir(double valorAConvertir) {
        this.valorAConvertir = valorAConvertir;
    }
    public double getTasaDeConversion() {
        return tasaDeConversion;
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
