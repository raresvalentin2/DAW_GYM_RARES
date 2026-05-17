package vista;

import controlador.MaquinariaControlador;
import modelo.Maquinaria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MaquinariaVista extends JPanel {
    private MaquinariaControlador controlador = new MaquinariaControlador();

    private JTextField txtId = new JTextField();
    private JTextField txtTipo = new JTextField();
    private JTextField txtMarca = new JTextField();
    private JTextField txtSerie = new JTextField();
    private JComboBox<String> cmbEstado = new JComboBox<String>(new String[]{"operativa", "revision", "fuera de servicio"});

    private JTable tabla = new JTable();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public MaquinariaVista() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(5, 2, 5, 5));

        txtId.setEditable(false);

        formulario.add(new JLabel("ID"));
        formulario.add(txtId);
        formulario.add(new JLabel("Tipo"));
        formulario.add(txtTipo);
        formulario.add(new JLabel("Marca"));
        formulario.add(txtMarca);
        formulario.add(new JLabel("N Serie"));
        formulario.add(txtSerie);
        formulario.add(new JLabel("Estado"));
        formulario.add(cmbEstado);

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
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Serie");
        modeloTabla.addColumn("Estado");

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
        controlador.agregar(txtTipo.getText(), txtMarca.getText(), txtSerie.getText(), cmbEstado.getSelectedItem().toString());
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        int id = Integer.parseInt(txtId.getText());
        Maquinaria m = new Maquinaria(id, txtTipo.getText(), txtMarca.getText(), txtSerie.getText(), cmbEstado.getSelectedItem().toString());
        controlador.actualizar(m);
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
        ArrayList<Maquinaria> lista = controlador.listar();

        for (Maquinaria m : lista) {
            modeloTabla.addRow(new Object[]{
                    m.getId(),
                    m.getTipo(),
                    m.getMarca(),
                    m.getNumeroSerie(),
                    m.getEstado()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtTipo.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtMarca.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtSerie.setText(modeloTabla.getValueAt(fila, 3).toString());
            cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtTipo.setText("");
        txtMarca.setText("");
        txtSerie.setText("");
    }
}