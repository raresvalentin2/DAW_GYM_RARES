package test;

import modelo.Socio;
import modelo.Sala;
import modelo.Actividad;
import modelo.Maquinaria;
import modelo.ActividadProgramada;
import org.junit.Test;
import static org.junit.Assert.*;

public class PruebasModeloTest {

    @Test
    public void probarSocio() {
        Socio socio = new Socio("12345678A", "Mario", "Lozano", "600111222", "mario@gym.com", "01/05/2026", "activo", "Full");

        assertEquals("12345678A", socio.getDni());
        assertEquals("Mario", socio.getNombre());
        assertEquals("activo", socio.getEstado());
        assertEquals("Full", socio.getTipoPlan());
    }

    @Test
    public void probarSala() {
        Sala sala = new Sala(1, "Sala 1", 80, 20);

        assertEquals(1, sala.getId());
        assertEquals("Sala 1", sala.getNombre());
        assertEquals(80, sala.getMetros(), 0.01);
        assertEquals(20, sala.getAforo());
    }

    @Test
    public void probarActividad() {
        Actividad actividad = new Actividad(1, "Spinning", "Clase de bicicleta", "Medio");

        assertEquals(1, actividad.getId());
        assertEquals("Spinning", actividad.getNombre());
        assertEquals("Medio", actividad.getNivel());
    }

    @Test
    public void probarMaquinaria() {
        Maquinaria maquina = new Maquinaria(1, "Cinta", "BH", "CIN001", "operativa");

        assertEquals(1, maquina.getId());
        assertEquals("Cinta", maquina.getTipo());
        assertEquals("operativa", maquina.getEstado());
    }

    @Test
    public void probarActividadProgramada() {
        ActividadProgramada actividad = new ActividadProgramada(1, "Sala 1", "Spinning", "Mario Lozano", "05/05/2026", "07:00h", "08:00h", 20, 7);

        assertEquals(1, actividad.getId());
        assertEquals("Sala 1", actividad.getSala());
        assertEquals("Spinning", actividad.getActividad());
        assertEquals(20, actividad.getCapacidad());
        assertEquals(7, actividad.getPlazasLibres());
    }
}
