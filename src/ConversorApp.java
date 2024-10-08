import presentacion.Menu;
import java.io.IOException;

public class ConversorApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        Menu menu = new Menu();
        while (menu.mostrarMenu()){}
        menu.despedida();

    }
}




