package controlador;

import accesoDatos.ActividadProgramadaDAO;
import modelo.ActividadProgramada;
import java.util.ArrayList;

/**
 * Controlador sencillo para actividades programadas
 */
public class ActividadProgramadaControlador {
    private ActividadProgramadaDAO dao = new ActividadProgramadaDAO();

    public ArrayList<ActividadProgramada> listar() {
        return dao.listar();
    }

    public void agregar(String sala, String actividad, String entrenador, String fecha, String horaInicio, String horaFin, int capacidad, int plazasLibres) {
        dao.agregar(sala, actividad, entrenador, fecha, horaInicio, horaFin, capacidad, plazasLibres);
    }

    public void actualizar(ActividadProgramada actividad) {
        dao.actualizar(actividad);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}