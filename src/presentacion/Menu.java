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
                return convertirMoneda("USD","ARS");
            case 2:
                return convertirMoneda("ARS","USD");
            case 3:
                return convertirMoneda("USD","BRL");
            case 4:
                return convertirMoneda("BRL","USD");
            case 5:
                return convertirMoneda("USD","COP");
            case 6:
                return convertirMoneda("COP","USD");
            case 7:
                return "Seleccionaste la opcion 7";
            case 8:
                return "Seleccionaste la opcion 8";
            default:
                return "Opcion no valida";
        }
    }

    public String convertirMoneda(String monBase, String monAConvertir)
            throws IOException, InterruptedException {
        System.out.println("Ingrese el valor a convertir: ");
        double valorcop = Double.parseDouble(input.nextLine());
        //return convertirMoneda("USD","COP", valorcop);
        ApiMonedas api = new ApiMonedas();
        Moneda miMoneda = api.info(monBase,monAConvertir,valorcop);
        return valorcop + " " + miMoneda.getMonedaNacional() + " equivalen a : " + miMoneda.getValorExtranjero()
                + " " + miMoneda.getMonedaExtranjera();
    }

    public void despedida(){
        System.out.println("Gracias por usar la aplicacion, ten un buen dia..!");
    }
}
