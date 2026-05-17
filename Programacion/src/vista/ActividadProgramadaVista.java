package vista;

import controlador.ActividadProgramadaControlador;
import modelo.ActividadProgramada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ActividadProgramadaVista extends JPanel {
    private ActividadProgramadaControlador controlador = new ActividadProgramadaControlador();

    private JTextField txtId = new JTextField();
    private JTextField txtSala = new JTextField();
    private JTextField txtActividad = new JTextField();
    private JTextField txtEntrenador = new JTextField();
    private JTextField txtFecha = new JTextField();
    private JTextField txtHoraInicio = new JTextField();
    private JTextField txtHoraFin = new JTextField();
    private JTextField txtCapacidad = new JTextField();
    private JTextField txtPlazas = new JTextField();

    private JTable tabla = new JTable();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public ActividadProgramadaVista() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(9, 2, 5, 5));

        txtId.setEditable(false);

        formulario.add(new JLabel("ID"));
        formulario.add(txtId);
        formulario.add(new JLabel("Sala"));
        formulario.add(txtSala);
        formulario.add(new JLabel("Actividad"));
        formulario.add(txtActividad);
        formulario.add(new JLabel("Entrenador"));
        formulario.add(txtEntrenador);
        formulario.add(new JLabel("Fecha"));
        formulario.add(txtFecha);
        formulario.add(new JLabel("Hora inicio"));
        formulario.add(txtHoraInicio);
        formulario.add(new JLabel("Hora fin"));
        formulario.add(txtHoraFin);
        formulario.add(new JLabel("Capacidad"));
        formulario.add(txtCapacidad);
        formulario.add(new JLabel("Plazas libres"));
        formulario.add(txtPlazas);

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
        modeloTabla.addColumn("Sala");
        modeloTabla.addColumn("Actividad");
        modeloTabla.addColumn("Entrenador");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Inicio");
        modeloTabla.addColumn("Fin");
        modeloTabla.addColumn("Capacidad");
        modeloTabla.addColumn("Plazas");

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
        controlador.agregar(txtSala.getText(), txtActividad.getText(), txtEntrenador.getText(), txtFecha.getText(), txtHoraInicio.getText(), txtHoraFin.getText(), Integer.parseInt(txtCapacidad.getText()), Integer.parseInt(txtPlazas.getText()));
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        int id = Integer.parseInt(txtId.getText());
        ActividadProgramada a = new ActividadProgramada(id, txtSala.getText(), txtActividad.getText(), txtEntrenador.getText(), txtFecha.getText(), txtHoraInicio.getText(), txtHoraFin.getText(), Integer.parseInt(txtCapacidad.getText()), Integer.parseInt(txtPlazas.getText()));
        controlador.actualizar(a);
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
        ArrayList<ActividadProgramada> lista = controlador.listar();

        for (ActividadProgramada a : lista) {
            modeloTabla.addRow(new Object[]{
                    a.getId(),
                    a.getSala(),
                    a.getActividad(),
                    a.getEntrenador(),
                    a.getFecha(),
                    a.getHoraInicio(),
                    a.getHoraFin(),
                    a.getCapacidad(),
                    a.getPlazasLibres()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtSala.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtActividad.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtEntrenador.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtFecha.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtHoraInicio.setText(modeloTabla.getValueAt(fila, 5).toString());
            txtHoraFin.setText(modeloTabla.getValueAt(fila, 6).toString());
            txtCapacidad.setText(modeloTabla.getValueAt(fila, 7).toString());
            txtPlazas.setText(modeloTabla.getValueAt(fila, 8).toString());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtSala.setText("");
        txtActividad.setText("");
        txtEntrenador.setText("");
        txtFecha.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");
        txtCapacidad.setText("");
        txtPlazas.setText("");
    }
}