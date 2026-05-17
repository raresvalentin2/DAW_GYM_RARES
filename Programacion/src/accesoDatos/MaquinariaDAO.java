package accesoDatos;

import modelo.Maquinaria;
import java.util.ArrayList;

public class MaquinariaDAO {
    private static ArrayList<Maquinaria> maquinas = new ArrayList<Maquinaria>();
    private static int ultimoId = 3;

    public MaquinariaDAO() {
        if (maquinas.size() == 0) {
            maquinas.add(new Maquinaria(1, "Cinta", "BH", "CIN001", "operativa"));
            maquinas.add(new Maquinaria(2, "Bicicleta", "Technogym", "BIC001", "operativa"));
            maquinas.add(new Maquinaria(3, "Banco", "Matrix", "BAN001", "revision"));
        }
    }

    public ArrayList<Maquinaria> listar() {
        return maquinas;
    }

    public void agregar(String tipo, String marca, String numeroSerie, String estado) {
        ultimoId++;
        maquinas.add(new Maquinaria(ultimoId, tipo, marca, numeroSerie, estado));
    }

    public void actualizar(Maquinaria maquinaNueva) {
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getId() == maquinaNueva.getId()) {
                maquinas.set(i, maquinaNueva);
            }
        }
    }

    public void eliminar(int id) {
        for (int i = 0; i < maquinas.size(); i++) {
            if (maquinas.get(i).getId() == id) {
                maquinas.remove(i);
                break;
            }
        }
    }
}