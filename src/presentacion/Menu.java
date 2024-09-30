package presentacion;

import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);

    public boolean menu(){
        System.out.println("""
                
                *************** Conversor de Moneda ***************
                Bienvenidos !
               
                1)          Dólar   ===>  Peso Argentino
                2) Peso Argentino   ===>  Dolar
                3)          Dólar   ===>  Real Brazileño
                4) Real Brazileño   ===>  Dolar
                5)          Dolar   ===>  Peso Colombiano
                6) Peso Colombiano  ===>  Dolar
                7) Salir 
                
                Presione un numero, de acuerdo a la conversion que desea realizar: 
                *****************************************************
                """);
        int opcion = Integer.parseInt(input.nextLine());
        if (opcion == 7){
            return false;
        }
        procesarOpcion(opcion);
        return true;
    }

    private void procesarOpcion(int opcion) {
        switch (opcion){
            case 1:
                System.out.println("Seleccionaste la opcion uno");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    public void despedida(){
        System.out.println("Gracias por usar la aplicacion, ten un buen dia..!");
    }
}
