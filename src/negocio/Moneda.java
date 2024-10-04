package negocio;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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
