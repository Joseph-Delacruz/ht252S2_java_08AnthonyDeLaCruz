package Anthony.De_La_Cruz.view;

import Anthony.De_La_Cruz.model.Computer;
import Anthony.De_La_Cruz.service.ComputerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

public class MainFrame extends JFrame {

    private JTextField tfCodigo, tfMarca, tfModelo, tfSO, tfRAM, tfAlmacenamiento;
    private JComboBox<String> cbTipo;
    private JRadioButton rbActivo, rbInactivo;
    private JCheckBox chkMantenimiento;
    private JTable table;
    private DefaultTableModel model;

    private final ComputerService service = new ComputerService();
    private Integer selectedId = null;

    public MainFrame() {
        setTitle("Inventario de Equipos - Valle Grande");
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadTable();
    }

    private void initUI() {

        /* ---------- FORM ---------- */
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        tfCodigo = new JTextField();
        tfMarca = new JTextField();
        tfModelo = new JTextField();
        tfSO = new JTextField();
        tfRAM = new JTextField();
        tfAlmacenamiento = new JTextField();

        cbTipo = new JComboBox<>(new String[]{"PC Escritorio", "Laptop", "Servidor", "Tablet"});

        rbActivo = new JRadioButton("Activo", true);
        rbInactivo = new JRadioButton("Inactivo");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbActivo);
        bg.add(rbInactivo);

        chkMantenimiento = new JCheckBox("Registrar mantenimiento hoy");

        JButton btnCrear = new JButton("Crear");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        int y = 0;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Código:"), c);
        c.gridx = 1; form.add(tfCodigo, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Tipo:"), c);
        c.gridx = 1; form.add(cbTipo, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Marca:"), c);
        c.gridx = 1; form.add(tfMarca, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Modelo:"), c);
        c.gridx = 1; form.add(tfModelo, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Sistema Operativo:"), c);
        c.gridx = 1; form.add(tfSO, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("RAM:"), c);
        c.gridx = 1; form.add(tfRAM, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Almacenamiento:"), c);
        c.gridx = 1; form.add(tfAlmacenamiento, c); y++;

        c.gridx = 0; c.gridy = y; form.add(chkMantenimiento, c); y++;

        c.gridx = 0; c.gridy = y; form.add(new JLabel("Estado:"), c);
        c.gridx = 1;
        JPanel pEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pEstado.add(rbActivo);
        pEstado.add(rbInactivo);
        form.add(pEstado, c); y++;

        c.gridx = 0; c.gridy = y; form.add(btnCrear, c);
        c.gridx = 1; form.add(btnActualizar, c); y++;

        c.gridx = 0; c.gridy = y; form.add(btnEliminar, c);
        c.gridx = 1; form.add(btnLimpiar, c);

        /* ---------- TABLE ---------- */
        model = new DefaultTableModel(
                new String[]{"ID", "Código", "Tipo", "Marca", "Modelo", "SO", "RAM", "Alm", "F. Mant.", "F. Reg.", "Estado"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, form, scroll);
        split.setDividerLocation(380);
        add(split);

        /* ---------- EVENTS ---------- */
        btnCrear.addActionListener(e -> create());
        btnActualizar.addActionListener(e -> update());
        btnEliminar.addActionListener(e -> delete());
        btnLimpiar.addActionListener(e -> clear());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loadSelected();
            }
        });
    }

    /* ---------- LOGIC ---------- */

    private void loadTable() {
        try {
            List<Computer> list = service.listar();
            model.setRowCount(0);
            for (Computer c : list) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getCodigo(),
                        c.getTipoEquipo(),
                        c.getMarca(),
                        c.getModelo(),
                        c.getSistema(),
                        c.getRam(),
                        c.getAlmacenamiento(),
                        c.getFechaMantenimiento(),
                        c.getFechaRegistro(),
                        c.getEstado()
                });
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private void create() {
        try {
            service.registrar(readForm());
            loadTable();
            clear();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void update() {
        if (selectedId == null) return;
        try {
            Computer c = readForm();
            c.setId(selectedId);
            service.actualizar(c);
            loadTable();
            clear();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void delete() {
        if (selectedId == null) return;

        int opt = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este equipo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opt == JOptionPane.YES_OPTION) {
            try {
                service.eliminar(selectedId); // eliminado lógico
                loadTable();
                clear();
            } catch (Exception e) {
                showError(e);
            }
        }
    }

    private Computer readForm() {
        Computer c = new Computer();
        c.setCodigo(tfCodigo.getText().trim());
        c.setTipoEquipo(cbTipo.getSelectedItem().toString());
        c.setMarca(tfMarca.getText().trim());
        c.setModelo(tfModelo.getText().trim());
        c.setSistema(tfSO.getText().trim());
        c.setRam(tfRAM.getText().trim());
        c.setAlmacenamiento(tfAlmacenamiento.getText().trim());
        c.setEstado(rbActivo.isSelected() ? "Activo" : "Inactivo");
        c.setFechaRegistro(LocalDate.now());

        if (chkMantenimiento.isSelected()) {
            c.setFechaMantenimiento(LocalDate.now());
        }

        return c;
    }

    private void loadSelected() {
        int r = table.getSelectedRow();
        if (r < 0) return;

        selectedId = (Integer) model.getValueAt(r, 0);
        tfCodigo.setText(model.getValueAt(r, 1).toString());
        cbTipo.setSelectedItem(model.getValueAt(r, 2));
        tfMarca.setText(model.getValueAt(r, 3).toString());
        tfModelo.setText(model.getValueAt(r, 4).toString());
        tfSO.setText(model.getValueAt(r, 5).toString());
        tfRAM.setText(model.getValueAt(r, 6).toString());
        tfAlmacenamiento.setText(model.getValueAt(r, 7).toString());
    }

    private void clear() {
        selectedId = null;
        tfCodigo.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        tfSO.setText("");
        tfRAM.setText("");
        tfAlmacenamiento.setText("");
        chkMantenimiento.setSelected(false);
        rbActivo.setSelected(true);
        cbTipo.setSelectedIndex(0);
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
