package persistencia;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import negocio.Moneda;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Historial {

    //Metodos

    public void anadirAArchivo(Moneda monedaParaHistorial) throws IOException {
        File archivo = new File( monedaParaHistorial.getFechaCreacion() + ".json");
        List<Moneda> monedasConvertida;

        if (archivo.exists()) {
            FileReader reader = new FileReader(archivo);
            Type listType = new TypeToken<ArrayList<Moneda>>() {}.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            monedasConvertida = gson.fromJson(reader, listType);
            reader.close();
        } else {
            monedasConvertida = new ArrayList<>();
        }

        monedasConvertida.add(monedaParaHistorial);

        FileWriter escritura = new FileWriter(archivo, false);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(monedasConvertida, escritura);
        escritura.close();
    }
    public void andirmonedaFecha(Moneda moneda) throws IOException {

        File archivo = new File("historico_fechas.json");
        List<String> fechas;

        if (archivo.exists()) {
            FileReader reader = new FileReader(archivo);
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fechas = gson.fromJson(reader, listType);
            reader.close();
        } else {
            fechas = new ArrayList<>();
        }

        if (!fechas.contains(moneda.getFechaCreacion())) {
            fechas.add(moneda.getFechaCreacion());
        }

        FileWriter escritura = new FileWriter(archivo, false);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(fechas, escritura);
        escritura.close();
    }
    public String opcionesHistorial(String nombreArchivo, int opcion) throws FileNotFoundException {

        File archivo = new File(nombreArchivo + ".json");

        if (!archivo.exists()) {
            return "El archivo " + nombreArchivo + ".json no existe.";
        }

        try (FileReader reader = new FileReader(archivo)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            int totalElementos = jsonArray.size();

            if (totalElementos == 0) {
                return "No hay registros disponibles en " + nombreArchivo + ".json.";
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (opcion == 1) {
                String elementoReciente = jsonArray.get(totalElementos - 1).getAsString();
                return elementoReciente;

            } else if (opcion == 2) {
                int elementosAMostrar = Math.min(totalElementos, 5);
                List<JsonElement> ultimosElementos = new ArrayList<>();

                for (int i = 0; i < elementosAMostrar; i++) {
                    ultimosElementos.add(jsonArray.get(totalElementos - 1 - i));
                }
                return "Últimos " + elementosAMostrar + " elementos:\n" + gson.toJson(ultimosElementos);

            } else if (opcion == 3) {
                return "Historial completo:\n" + gson.toJson(jsonArray);

            } else {
                return "Opción inválida. Elija 1 para el más reciente, 2 para los últimos 5 elementos, o 3 para todo el historial.";
            }

        } catch (Exception e) {
            return "Error al leer el archivo " + nombreArchivo + ".json: " + e.getMessage();
        }
    }
    public String historicoTotal() throws FileNotFoundException {
        String resultado = opcionesHistorial("historico_fechas", 3);

        JsonArray jsonArray = JsonParser.parseString(resultado.replace("Historial completo:\n", "")).getAsJsonArray();

        StringBuilder listaNumerada = new StringBuilder("Registros:\n");

        for (int i = 0; i < jsonArray.size(); i++) {
            listaNumerada.append((i + 1) + ". " + jsonArray.get(i).getAsString() + "\n");
        }

        System.out.println("Ingrese un numero entero de acuerdo a las fechas de la lista");
        System.out.println(listaNumerada.toString());

        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese un número entero (1 a " + jsonArray.size() + "): ");

        if (entrada.hasNextInt()) {
            int input = entrada.nextInt();

            if (input >= 1 && input <= jsonArray.size()) {
                String fechaSeleccionada = jsonArray.get(input - 1).getAsString();
                String historialCompleto = opcionesHistorial(fechaSeleccionada, 3);
                return "Historial completo para " + fechaSeleccionada + ":\n" + historialCompleto;
            } else {
                return "Número no válido. Debe ser entre 1 y " + jsonArray.size() + ".";
            }
        } else {
            return "Entrada no válida. Por favor, ingrese un número entero.";
        }
    }

}
