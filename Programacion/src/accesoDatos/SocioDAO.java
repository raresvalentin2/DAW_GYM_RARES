package accesoDatos;

import modelo.Socio;
import java.util.ArrayList;

// guardo socios en memoria para probar el CRUD
/**
 * Clase donde se guardan y manejan los socios
 */
public class SocioDAO {
    private ArrayList<Socio> socios = new ArrayList<Socio>();

    public SocioDAO() {
        socios.add(new Socio("12345678A", "Mario", "Lozano", "600111222", "mario@gym.com", "01/05/2026", "activo", "Full"));
        socios.add(new Socio("87654321B", "Laura", "Perez", "600333444", "laura@gym.com", "02/05/2026", "moroso", "Flexible"));
    }

    public ArrayList<Socio> listar() {
        return socios;
    }

    public void agregar(Socio socio) {
        socios.add(socio);
    }

    public void actualizar(Socio socioNuevo) {
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getDni().equals(socioNuevo.getDni())) {
                socios.set(i, socioNuevo);
            }
        }
    }

    public void eliminar(String dni) {
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getDni().equals(dni)) {
                socios.remove(i);
                break;
            }
        }
    }
}
