package accesoDatos;

import modelo.Actividad;
import java.util.ArrayList;

// guardo actividades en memoria
/**
 * Clase donde se guardan y manejan las actividades
 */
public class ActividadDAO {
    private ArrayList<Actividad> actividades = new ArrayList<Actividad>();
    private int ultimoId = 3;

    public ActividadDAO() {
        actividades.add(new Actividad(1, "Spinning", "Clase de bicicleta estatica", "Medio"));
        actividades.add(new Actividad(2, "Yoga", "Actividad de movilidad y relajacion", "Bajo"));
        actividades.add(new Actividad(3, "Crossfit", "Entrenamiento funcional intenso", "Alto"));
    }

    public ArrayList<Actividad> listar() {
        return actividades;
    }

    public void agregar(String nombre, String descripcion, String nivel) {
        ultimoId++;
        actividades.add(new Actividad(ultimoId, nombre, descripcion, nivel));
    }

    public void actualizar(Actividad actividadNueva) {
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getId() == actividadNueva.getId()) {
                actividades.set(i, actividadNueva);
            }
        }
    }

    public void eliminar(int id) {
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getId() == id) {
                actividades.remove(i);
                break;
            }
        }
    }
}
