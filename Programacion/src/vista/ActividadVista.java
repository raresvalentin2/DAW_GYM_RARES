package vista;

import controlador.ActividadControlador;
import modelo.Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// pantalla sencilla para actividades
/**
 * Pantalla para gestionar actividades
 */
public class ActividadVista extends JPanel {
    private ActividadControlador controlador = new ActividadControlador();

    private JTextField txtId = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtDescripcion = new JTextField();
    private JComboBox<String> cmbNivel = new JComboBox<String>(new String[]{"Bajo", "Medio", "Alto"});

    private JTable tabla = new JTable();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public ActividadVista() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(4, 2, 5, 5));

        txtId.setEditable(false);

        formulario.add(new JLabel("ID"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Descripcion"));
        formulario.add(txtDescripcion);
        formulario.add(new JLabel("Nivel"));
        formulario.add(cmbNivel);

        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);

        JPanel arriba = new JPanel(new BorderLayout());
        arriba.add(formulario, BorderLayout.CENTER);
        arriba.add(botones, BorderLayout.SOUTH);

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Nivel");

        tabla.setModel(modeloTabla);

        add(arriba, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccion());

        cargarTabla();
    }

    private void agregar() {
        controlador.agregar(txtNombre.getText(), txtDescripcion.getText(), cmbNivel.getSelectedItem().toString());
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        int id = Integer.parseInt(txtId.getText());
        Actividad actividad = new Actividad(id, txtNombre.getText(), txtDescripcion.getText(), cmbNivel.getSelectedItem().toString());

        controlador.actualizar(actividad);
        cargarTabla();
        limpiar();
    }

    private void eliminar() {
        int id = Integer.parseInt(txtId.getText());
        controlador.eliminar(id);
        cargarTabla();
        limpiar();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);

        ArrayList<Actividad> actividades = controlador.listar();

        for (Actividad a : actividades) {
            modeloTabla.addRow(new Object[]{
                    a.getId(),
                    a.getNombre(),
                    a.getDescripcion(),
                    a.getNivel()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 2).toString());
            cmbNivel.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }
}
