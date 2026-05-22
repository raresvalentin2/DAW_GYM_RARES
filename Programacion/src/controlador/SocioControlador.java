package controlador;

import accesoDatos.SocioDAO;
import modelo.Socio;
import java.util.ArrayList;

// conecta la vista de socios con los datos
/**
 * Controlador sencillo para socios
 */
public class SocioControlador {
    private SocioDAO dao = new SocioDAO();

    public ArrayList<Socio> listar() {
        return dao.listar();
    }

    public void agregar(Socio socio) {
        dao.agregar(socio);
    }

    public void actualizar(Socio socio) {
        dao.actualizar(socio);
    }

    public void eliminar(String dni) {
        dao.eliminar(dni);
    }
}
