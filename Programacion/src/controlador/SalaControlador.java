package controlador;

import accesoDatos.SalaDAO;
import modelo.Sala;
import java.util.ArrayList;

// conecta la vista de salas con los datos
public class SalaControlador {
    private SalaDAO dao = new SalaDAO();

    public ArrayList<Sala> listar() {
        return dao.listar();
    }

    public void agregar(String nombre, double metros, int aforo) {
        dao.agregar(nombre, metros, aforo);
    }

    public void actualizar(Sala sala) {
        dao.actualizar(sala);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}
