import presentacion.Menu;

public class ConversorApp {

    public static void main(String[] args) {
        Menu menu = new Menu();
        while (menu.menu()){}
        menu.despedida();
    }
}
