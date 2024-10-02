package presentacion;
import negocio.Moneda;
import persistencia.ApiMonedas;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);

    public boolean mostrarMenu() throws IOException, InterruptedException {
        System.out.println("""
                
                *************** Conversor de Moneda ***************
                Bienvenidos !
               
                1)          Dólar   ===>  Peso Argentino
                2) Peso Argentino   ===>  Dolar
                3)          Dólar   ===>  Real Brazileño
                4) Real Brazileño   ===>  Dolar
                5)          Dolar   ===>  Peso Colombiano
                6) Peso Colombiano  ===>  Dolar
                7) Ultimas conversiones
                8) Historial completo 
                9) Salir 
                
                Presione un numero, de acuerdo a la conversion que desea realizar: 
                *****************************************************
                """);
        int opcion = Integer.parseInt(input.nextLine());
        if (opcion == 9){
            return false;
        }
        System.out.println(procesarOpcion(opcion));
        return true;
    }

    private String procesarOpcion(int opcion) throws IOException, InterruptedException {
        switch (opcion){
            case 1:
                System.out.println("Ingrese el valor a convertir: ");
                double valorcop = Double.parseDouble(input.nextLine());
                return convertirMoneda("USD","COP", valorcop);
            case 2:
                return "EUR";
            case 3:
                return "Seleccionaste la opcion 3";
            case 4:
                return "Seleccionaste la opcion 4";
            case 5:
                return "Seleccionaste la opcion 5";
            case 6:
                return "Seleccionaste la opcion 6";
            case 7:
                return "Seleccionaste la opcion 7";
            case 8:
                return "Seleccionaste la opcion 8";
            default:
                return "Opcion no valida";
        }
    }

    public String convertirMoneda(String monBase, String monAConvertir, double montoIngresado)
            throws IOException, InterruptedException {
        ApiMonedas api = new ApiMonedas();
        Moneda miMoneda = api.info(monBase,monAConvertir,montoIngresado);
        return "Base money: " + miMoneda.getValorNacional() + "Converted money: " + miMoneda.getValorExtranjero();
    }

    public void despedida(){
        System.out.println("Gracias por usar la aplicacion, ten un buen dia..!");
    }
}
