package vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Ventana principal de la aplicacion
 */
public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("RARES GYM - Gestion");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane pestanas = new JTabbedPane();

        pestanas.addTab("Socios", new SocioVista());
        pestanas.addTab("Salas", new SalaVista());
        pestanas.addTab("Actividades", new ActividadVista());
        pestanas.addTab("Maquinaria", new MaquinariaVista());
        pestanas.addTab("Act. Programadas", new ActividadProgramadaVista());
        pestanas.addTab("Exportar JSON", new JsonVista());

        add(pestanas);

        setVisible(true);
    }
}