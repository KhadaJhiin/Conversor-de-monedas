package presentacion;
import negocio.Moneda;

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
        Moneda moneda = new Moneda();
        switch (opcion){
            case 1:
                return moneda.convertirMoneda("USD","ARS",input);
            case 2:
                return moneda.convertirMoneda("ARS","USD",input);
            case 3:
                return moneda.convertirMoneda("USD","BRL",input);
            case 4:
                return moneda.convertirMoneda("BRL","USD",input);
            case 5:
                return moneda.convertirMoneda("USD","COP",input);
            case 6:
                return moneda.convertirMoneda("COP","USD",input);
            case 7:
                return "Seleccionaste la opcion 7";
            case 8:
                return "Seleccionaste la opcion 8";
            default:
                return "Opcion no valida";
        }
    }

    public void despedida(){
        System.out.println("Gracias por usar la aplicacion, ten un buen dia..!");
    }
}
