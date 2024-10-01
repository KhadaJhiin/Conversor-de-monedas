package negocio;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Moneda {
    @SerializedName("base_code")
    private String valorNacional;
    @SerializedName("AFN")
    private Double valorExtranjero;

    public Moneda() {}

    public Moneda(String nacionalidad, Double valor) {
        this.valorNacional = nacionalidad;
        this.valorExtranjero = valor;
    }

    public String getValorNacional() {
        return valorNacional;
    }

    public Double getValorExtranjero() {
        return valorExtranjero;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "nacionalidad='" + valorNacional + '\'' +
                ", valor=" + valorExtranjero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda = (Moneda) o;
        return Double.compare(valorExtranjero, moneda.valorExtranjero) == 0 && Objects.equals(valorNacional, moneda.valorNacional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorNacional, valorExtranjero);
    }
}
