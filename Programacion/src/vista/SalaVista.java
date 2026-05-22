package vista;

import controlador.SalaControlador;
import modelo.Sala;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// pantalla sencilla para salas
/**
 * Pantalla para gestionar salas
 */
public class SalaVista extends JPanel {
    private SalaControlador controlador = new SalaControlador();

    private JTextField txtId = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtMetros = new JTextField();
    private JTextField txtAforo = new JTextField();

    private JTable tabla = new JTable();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public SalaVista() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(4, 2, 5, 5));

        txtId.setEditable(false);

        formulario.add(new JLabel("ID"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Metros"));
        formulario.add(txtMetros);
        formulario.add(new JLabel("Aforo"));
        formulario.add(txtAforo);

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
        modeloTabla.addColumn("Metros");
        modeloTabla.addColumn("Aforo");

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
        controlador.agregar(txtNombre.getText(), Double.parseDouble(txtMetros.getText()), Integer.parseInt(txtAforo.getText()));
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        int id = Integer.parseInt(txtId.getText());
        Sala sala = new Sala(id, txtNombre.getText(), Double.parseDouble(txtMetros.getText()), Integer.parseInt(txtAforo.getText()));

        controlador.actualizar(sala);
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

        ArrayList<Sala> salas = controlador.listar();

        for (Sala s : salas) {
            modeloTabla.addRow(new Object[]{
                    s.getId(),
                    s.getNombre(),
                    s.getMetros(),
                    s.getAforo()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtMetros.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtAforo.setText(modeloTabla.getValueAt(fila, 3).toString());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtMetros.setText("");
        txtAforo.setText("");
    }
}
