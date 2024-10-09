package presentacion;

import exepciones.ValorFueraDeRangoExepcion;
import negocio.Conversor;
import negocio.Moneda;
import persistencia.Historial;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    //Atributos

    Moneda moneda;
    Scanner input = new Scanner(System.in);
    Historial historial = new Historial();
    Conversor conversor = new Conversor();

    //Metodos

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
        try {
            int opcion = Integer.parseInt(input.nextLine());
            if (opcion == 9){
                return false;
            }
            System.out.println(procesarOpcion(opcion));
            if (moneda != null){
                historial.anadirAArchivo(moneda);
                historial.andirmonedaFecha(moneda);
            }
            return true;

        }catch (NumberFormatException e){
            System.out.println("Por favor ingrese solo numeros enteros de acuerdo al menu de opciones.");
            return true;
        }
    }
    private String procesarOpcion(int opcion) throws IOException, InterruptedException {
        try {
            switch (opcion) {
                case 1:
                    this.moneda = conversor.convertirMoneda("USD", "ARS", input);
                    return moneda.imprimir(moneda);
                case 2:
                    this.moneda = conversor.convertirMoneda("ARS", "USD", input);
                    return moneda.imprimir(moneda);
                case 3:
                    this.moneda = conversor.convertirMoneda("USD", "BRL", input);
                    return moneda.imprimir(moneda);
                case 4:
                    this.moneda = conversor.convertirMoneda("BRL", "USD", input);
                    return moneda.imprimir(moneda);
                case 5:
                    this.moneda = conversor.convertirMoneda("USD", "COP", input);
                    return moneda.imprimir(moneda);
                case 6:
                    this.moneda = conversor.convertirMoneda("COP", "USD", input);
                    return moneda.imprimir(moneda);
                case 7:
                    return historial.opcionesHistorial(historial.opcionesHistorial("historico_fechas",1),2);
                case 8 :
                    return historial.historicoTotal();
                default:
                    return "Opción no válida";
            }
        } catch (ValorFueraDeRangoExepcion e) {
            return "Error: " + e.getMensaje();
        } catch (IOException | InterruptedException e) {
            throw e;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public void despedida(){
        System.out.println("Gracias por usar la aplicacion, ten un buen dia..!");
    }
}
