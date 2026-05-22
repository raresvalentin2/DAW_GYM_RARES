package controlador;

import accesoDatos.ActividadDAO;
import modelo.Actividad;
import java.util.ArrayList;

// conecta la vista de actividades con los datos
/**
 * Controlador sencillo para actividades
 */
public class ActividadControlador {
    private ActividadDAO dao = new ActividadDAO();

    public ArrayList<Actividad> listar() {
        return dao.listar();
    }

    public void agregar(String nombre, String descripcion, String nivel) {
        dao.agregar(nombre, descripcion, nivel);
    }

    public void actualizar(Actividad actividad) {
        dao.actualizar(actividad);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
