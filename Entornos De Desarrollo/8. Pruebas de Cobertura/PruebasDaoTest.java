package test;

import accesoDatos.SocioDAO;
import accesoDatos.SalaDAO;
import accesoDatos.ActividadDAO;
import accesoDatos.MaquinariaDAO;
import accesoDatos.ActividadProgramadaDAO;
import modelo.Socio;
import modelo.Sala;
import modelo.Actividad;
import modelo.Maquinaria;
import org.junit.Test;
import static org.junit.Assert.*;

public class PruebasDaoTest {

    @Test
    public void probarListadoSocios() {
        SocioDAO dao = new SocioDAO();

        assertTrue(dao.listar().size() >= 2);
    }

    @Test
    public void probarAgregarSocio() {
        SocioDAO dao = new SocioDAO();
        int totalAntes = dao.listar().size();

        Socio socio = new Socio("11112222A", "Ana", "Prueba", "600000000", "ana@gym.com", "10/05/2026", "activo", "Full");
        dao.agregar(socio);

        assertEquals(totalAntes + 1, dao.listar().size());
    }

    @Test
    public void probarListadoSalas() {
        SalaDAO dao = new SalaDAO();

        assertTrue(dao.listar().size() >= 3);
    }

    @Test
    public void probarAgregarSala() {
        SalaDAO dao = new SalaDAO();
        int totalAntes = dao.listar().size();

        dao.agregar("Sala prueba", 50, 10);

        assertEquals(totalAntes + 1, dao.listar().size());
    }

    @Test
    public void probarListadoActividades() {
        ActividadDAO dao = new ActividadDAO();

        assertTrue(dao.listar().size() >= 3);
    }

    @Test
    public void probarAgregarActividad() {
        ActividadDAO dao = new ActividadDAO();
        int totalAntes = dao.listar().size();

        dao.agregar("Prueba", "Actividad de prueba", "Bajo");

        assertEquals(totalAntes + 1, dao.listar().size());
    }

    @Test
    public void probarListadoMaquinaria() {
        MaquinariaDAO dao = new MaquinariaDAO();

        assertTrue(dao.listar().size() >= 3);
    }

    @Test
    public void probarAgregarMaquinaria() {
        MaquinariaDAO dao = new MaquinariaDAO();
        int totalAntes = dao.listar().size();

        dao.agregar("Maquina prueba", "Marca prueba", "SER999", "operativa");

        assertEquals(totalAntes + 1, dao.listar().size());
    }

    @Test
    public void probarListadoActividadesProgramadas() {
        ActividadProgramadaDAO dao = new ActividadProgramadaDAO();

        assertTrue(dao.listar().size() >= 1);
    }
}
