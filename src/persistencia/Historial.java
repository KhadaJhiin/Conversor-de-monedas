package persistencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import negocio.Moneda;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    public void anadirAArchivo(Moneda monedaParaHistorial) throws IOException {
        File archivo = new File(monedaParaHistorial.getFechaCreacion() + ".json");
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

    public String buscarRegistro(String fechaRegistro) throws FileNotFoundException {

        File archivo = new File(fechaRegistro + ".json");
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement jsonElement = JsonParser.parseReader(reader);
                return "Historial del dia " + fechaRegistro + "\n" + gson.toJson(jsonElement);
            } catch (Exception e) {
                return "Ocurrió un error al leer el archivo JSON";
            }
        } else {
            return "El archivo " + fechaRegistro + ".json no existe.";
        }
    }
}
