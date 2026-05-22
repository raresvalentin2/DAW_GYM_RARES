package accesoDatos;

import modelo.Sala;
import java.util.ArrayList;

// guardo salas en memoria
/**
 * Clase donde se guardan y manejan las salas
 */
public class SalaDAO {
    private ArrayList<Sala> salas = new ArrayList<Sala>();
    private int ultimoId = 3;

    public SalaDAO() {
        salas.add(new Sala(1, "Sala 1", 80, 20));
        salas.add(new Sala(2, "Sala 2", 60, 15));
        salas.add(new Sala(3, "Almacen", 40, 0));
    }

    public ArrayList<Sala> listar() {
        return salas;
    }

    public void agregar(String nombre, double metros, int aforo) {
        ultimoId++;
        salas.add(new Sala(ultimoId, nombre, metros, aforo));
    }

    public void actualizar(Sala salaNueva) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getId() == salaNueva.getId()) {
                salas.set(i, salaNueva);
            }
        }
    }

    public void eliminar(int id) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getId() == id) {
                salas.remove(i);
                break;
            }
        }
    }
}
