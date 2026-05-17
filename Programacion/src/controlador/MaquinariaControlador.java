package controlador;

import accesoDatos.MaquinariaDAO;
import modelo.Maquinaria;
import java.util.ArrayList;

public class MaquinariaControlador {
    private MaquinariaDAO dao = new MaquinariaDAO();

    public ArrayList<Maquinaria> listar() {
        return dao.listar();
    }

    public void agregar(String tipo, String marca, String numeroSerie, String estado) {
        dao.agregar(tipo, marca, numeroSerie, estado);
    }

    public void actualizar(Maquinaria maquinaria) {
        dao.actualizar(maquinaria);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }
}