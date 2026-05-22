package vista;

import controlador.JsonControlador;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla para exportar el JSON
 */
public class JsonVista extends JPanel {
    private JsonControlador controlador = new JsonControlador();

    public JsonVista() {
        setLayout(new BorderLayout());

        JLabel texto = new JLabel("Generar actividades.json para la web", SwingConstants.CENTER);
        JButton boton = new JButton("Generar JSON");

        add(texto, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);

        boton.addActionListener(e -> generar());
    }

    private void generar() {
        try {
            controlador.generar();
            JOptionPane.showMessageDialog(this, "JSON generado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se ha podido generar el JSON.");
        }
    }
}