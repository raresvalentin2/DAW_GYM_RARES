package accesoDatos;

import modelo.ActividadProgramada;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorJSON {
    public void generarActividades() throws IOException {
        String ruta = "Lenguaje de Marcas/Web Definitiva/WEB/data/actividades.json";
        ActividadProgramadaDAO dao = new ActividadProgramadaDAO();
        ArrayList<ActividadProgramada> lista = dao.listar();

        FileWriter archivo = new FileWriter(ruta);
        archivo.write("[\n");

        for (int i = 0; i < lista.size(); i++) {
            ActividadProgramada a = lista.get(i);

            String fecha = a.getFecha();
            String[] partes = fecha.split("/");
            String fechaInput = partes[2] + "-" + partes[1] + "-" + partes[0];

            archivo.write("  {\n");
            archivo.write("    \"sala\": \"" + a.getSala() + "\",\n");
            archivo.write("    \"actividad\": \"" + a.getActividad() + "\",\n");
            archivo.write("    \"entrenadores\": [\"" + a.getEntrenador() + "\"],\n");
            archivo.write("    \"fecha\": \"" + fechaInput + "\",\n");
            archivo.write("    \"fechaTexto\": \"" + a.getFecha() + "\",\n");
            archivo.write("    \"hora\": \"" + a.getHoraInicio() + "\",\n");
            archivo.write("    \"capacidad\": " + a.getCapacidad() + ",\n");
            archivo.write("    \"plazasLibres\": " + a.getPlazasLibres() + "\n");

            if (i == lista.size() - 1) {
                archivo.write("  }\n");
            } else {
                archivo.write("  },\n");
            }
        }

        archivo.write("]");
        archivo.close();
    }
}