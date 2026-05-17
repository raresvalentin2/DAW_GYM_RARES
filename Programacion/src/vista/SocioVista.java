package vista;

import controlador.SocioControlador;
import modelo.Socio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// pantalla sencilla para socios
public class SocioVista extends JPanel {
    private SocioControlador controlador = new SocioControlador();

    private JTextField txtDni = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellido = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtFecha = new JTextField("13/05/2026");

    private JComboBox<String> cmbEstado = new JComboBox<String>(new String[]{"activo", "baja", "moroso"});
    private JComboBox<String> cmbPlan = new JComboBox<String>(new String[]{"Full", "Flexible"});

    private JTable tabla = new JTable();
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public SocioVista() {
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(8, 2, 5, 5));

        formulario.add(new JLabel("DNI"));
        formulario.add(txtDni);
        formulario.add(new JLabel("Nombre"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Apellido"));
        formulario.add(txtApellido);
        formulario.add(new JLabel("Telefono"));
        formulario.add(txtTelefono);
        formulario.add(new JLabel("Email"));
        formulario.add(txtEmail);
        formulario.add(new JLabel("Fecha alta"));
        formulario.add(txtFecha);
        formulario.add(new JLabel("Estado"));
        formulario.add(cmbEstado);
        formulario.add(new JLabel("Plan"));
        formulario.add(cmbPlan);

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

        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Plan");

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
        Socio socio = new Socio(
                txtDni.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtFecha.getText(),
                cmbEstado.getSelectedItem().toString(),
                cmbPlan.getSelectedItem().toString()
        );

        controlador.agregar(socio);
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        Socio socio = new Socio(
                txtDni.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtFecha.getText(),
                cmbEstado.getSelectedItem().toString(),
                cmbPlan.getSelectedItem().toString()
        );

        controlador.actualizar(socio);
        cargarTabla();
        limpiar();
    }

    private void eliminar() {
        controlador.eliminar(txtDni.getText());
        cargarTabla();
        limpiar();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);

        ArrayList<Socio> socios = controlador.listar();

        for (Socio s : socios) {
            modeloTabla.addRow(new Object[]{
                    s.getDni(),
                    s.getNombre(),
                    s.getApellido(),
                    s.getTelefono(),
                    s.getEmail(),
                    s.getFechaAlta(),
                    s.getEstado(),
                    s.getTipoPlan()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            txtDni.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtApellido.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtTelefono.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtEmail.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtFecha.setText(modeloTabla.getValueAt(fila, 5).toString());
            cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 6).toString());
            cmbPlan.setSelectedItem(modeloTabla.getValueAt(fila, 7).toString());
        }
    }

    private void limpiar() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}
