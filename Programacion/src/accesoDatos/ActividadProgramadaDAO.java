package accesoDatos;

import modelo.ActividadProgramada;
import java.util.ArrayList;

public class ActividadProgramadaDAO {
    private static ArrayList<ActividadProgramada> actividades = new ArrayList<ActividadProgramada>();
    private static int ultimoId = 12;

    public ActividadProgramadaDAO() {
        if (actividades.size() == 0) {
            actividades.add(new ActividadProgramada(1, "Sala 1", "Spinning", "Mario Lozano", "05/05/2026", "07:00h", "08:00h", 20, 7));
            actividades.add(new ActividadProgramada(2, "Sala 2", "Yoga", "Laura Perez", "05/05/2026", "09:30h", "10:30h", 15, 3));
            actividades.add(new ActividadProgramada(3, "Sala 3", "Crossfit", "Carlos Ruiz y Ana Gomez", "05/05/2026", "10:00h", "11:00h", 25, 12));
            actividades.add(new ActividadProgramada(4, "Sala 4", "Pilates", "Marta Sanchez", "06/05/2026", "11:00h", "12:00h", 12, 0));
            actividades.add(new ActividadProgramada(5, "Sala 1", "HIIT", "Mario Lozano", "06/05/2026", "18:00h", "19:00h", 20, 9));
            actividades.add(new ActividadProgramada(6, "Sala 2", "Zumba", "Laura Perez", "06/05/2026", "19:30h", "20:30h", 30, 15));
            actividades.add(new ActividadProgramada(7, "Sala 3", "Functional Training", "Carlos Ruiz", "07/05/2026", "08:00h", "09:00h", 20, 6));
            actividades.add(new ActividadProgramada(8, "Sala 4", "Body Pump", "Ana Gomez", "07/05/2026", "10:30h", "11:30h", 25, 11));
            actividades.add(new ActividadProgramada(9, "Sala 1", "Spinning", "Mario Lozano", "07/05/2026", "19:00h", "20:00h", 20, 4));
            actividades.add(new ActividadProgramada(10, "Sala 2", "Meditacion", "Marta Sanchez", "08/05/2026", "09:00h", "10:00h", 15, 8));
            actividades.add(new ActividadProgramada(11, "Sala 3", "Boxeo", "Carlos Ruiz", "08/05/2026", "17:00h", "18:00h", 16, 2));
            actividades.add(new ActividadProgramada(12, "Sala 4", "Stretching", "Laura Perez y Ana Gomez", "08/05/2026", "20:00h", "21:00h", 20, 14));
        }
    }

    public ArrayList<ActividadProgramada> listar() {
        return actividades;
    }

    public void agregar(String sala, String actividad, String entrenador, String fecha, String horaInicio, String horaFin, int capacidad, int plazasLibres) {
        ultimoId++;
        actividades.add(new ActividadProgramada(ultimoId, sala, actividad, entrenador, fecha, horaInicio, horaFin, capacidad, plazasLibres));
    }

    public void actualizar(ActividadProgramada nueva) {
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getId() == nueva.getId()) {
                actividades.set(i, nueva);
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