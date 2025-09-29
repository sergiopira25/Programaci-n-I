import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EPSApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private AppointmentPanel appointmentPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    private ArrayList<Cita> citas = new ArrayList<>();

    public EPSApp() {
        setTitle("Gestión EPS - Pacientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        appointmentPanel = new AppointmentPanel(this);

        cardsPanel.add(loginPanel, "Login");
        cardsPanel.add(registerPanel, "Register");
        cardsPanel.add(appointmentPanel, "Appointments");
        add(cardsPanel);

        cardLayout.show(cardsPanel, "Login");
    }

    public void showPanel(String name) {
        cardLayout.show(cardsPanel, name);
    }

    class Cita {
        String pacienteId;
        String especialidad;
        String hora;
        boolean cancelada;
        String motivoCancelacion;

        public Cita(String pacienteId, String especialidad, String hora) {
            this.pacienteId = pacienteId;
            this.especialidad = especialidad;
            this.hora = hora;
            this.cancelada = false;
            this.motivoCancelacion = "";
        }
    }

    class LoginPanel extends JPanel {
        private JTextField idField;
        private JPasswordField passwordField;
        private JLabel messageLabel;
        private EPSApp mainApp;

        public LoginPanel(EPSApp app) {
            this.mainApp = app;
            setLayout(new BorderLayout());
            setBackground(new Color(41, 128, 185));

            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(new Color(41, 128, 185));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel logoLabel = new JLabel();
            try {
                ImageIcon logoIcon = new ImageIcon("logo.png");
                Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                logoLabel.setIcon(new ImageIcon(img));
                logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            } catch (Exception e) {
                logoLabel.setText("Logo EPS");
                logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
                logoLabel.setForeground(Color.white);
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            formPanel.add(logoLabel, gbc);

            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;

            JLabel idLabel = new JLabel("Número de identificación:");
            idLabel.setForeground(Color.white);
            idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            idField = new JTextField(20);

            JLabel passLabel = new JLabel("Contraseña:");
            passLabel.setForeground(Color.white);
            passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            passwordField = new JPasswordField(20);

            JButton loginButton = new JButton("Ingresar");
            JButton toRegisterButton = new JButton("Crear cuenta");
            messageLabel = new JLabel(" ");
            messageLabel.setForeground(Color.YELLOW);
            messageLabel.setHorizontalAlignment(JLabel.CENTER);

            gbc.gridx = 0; gbc.gridy = 1; formPanel.add(idLabel, gbc);
            gbc.gridx = 1; formPanel.add(idField, gbc);
            gbc.gridx = 0; gbc.gridy = 2; formPanel.add(passLabel, gbc);
            gbc.gridx = 1; formPanel.add(passwordField, gbc);
            gbc.gridx = 0; gbc.gridy = 3; formPanel.add(loginButton, gbc);
            gbc.gridx = 1; formPanel.add(toRegisterButton, gbc);
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; formPanel.add(messageLabel, gbc);

            add(formPanel, BorderLayout.CENTER);

            toRegisterButton.setBackground(new Color(230, 126, 34));
            toRegisterButton.setForeground(Color.white);
            loginButton.setBackground(new Color(52, 152, 219));
            loginButton.setForeground(Color.white);

            toRegisterButton.addActionListener(e -> mainApp.showPanel("Register"));

            loginButton.addActionListener(e -> {
                String id = idField.getText().trim();
                String pass = new String(passwordField.getPassword());
                if (!id.isEmpty() && !pass.isEmpty()) {
                    messageLabel.setText("Ingreso exitoso");
                    appointmentPanel.setPacienteId(id);
                    mainApp.showPanel("Appointments");
                } else {
                    messageLabel.setText("Por favor ingrese todos los datos");
                }
            });
        }
    }

    class RegisterPanel extends JPanel {
        private EPSApp mainApp;

        public RegisterPanel(EPSApp app) {
            this.mainApp = app;
            setLayout(new GridBagLayout());
            setBackground(new Color(44, 62, 80));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel correoLabel = createLabel("Correo:");
            JTextField correoField = new JTextField(20);
            JLabel nombreLabel = createLabel("Nombre:");
            JTextField nombreField = new JTextField(20);
            JLabel apellidoLabel = createLabel("Apellido:");
            JTextField apellidoField = new JTextField(20);
            JLabel fechaNacimientoLabel = createLabel("Fecha de nacimiento:");
            JTextField fechaNacimientoField = new JTextField(20);
            JLabel barrioLabel = createLabel("Barrio:");
            JTextField barrioField = new JTextField(20);
            JLabel telefonoLabel = createLabel("Teléfono:");
            JTextField telefonoField = new JTextField(20);
            JLabel idLabel = createLabel("Número de identificación:");
            JTextField idField = new JTextField(20);
            JLabel passLabel = createLabel("Contraseña:");
            JPasswordField passField = new JPasswordField(20);

            JButton registerButton = new JButton("Registrar");
            JButton backButton = new JButton("Volver al login");
            JLabel messageLabel = new JLabel(" ");
            messageLabel.setForeground(Color.ORANGE);

            gbc.gridx = 0; gbc.gridy = 0; add(correoLabel, gbc);
            gbc.gridx = 1; add(correoField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(nombreLabel, gbc);
            gbc.gridx = 1; add(nombreField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(apellidoLabel, gbc);
            gbc.gridx = 1; add(apellidoField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(fechaNacimientoLabel, gbc);
            gbc.gridx = 1; add(fechaNacimientoField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(barrioLabel, gbc);
            gbc.gridx = 1; add(barrioField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(telefonoLabel, gbc);
            gbc.gridx = 1; add(telefonoField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(idLabel, gbc);
            gbc.gridx = 1; add(idField, gbc);
            gbc.gridx = 0; gbc.gridy++; add(passLabel, gbc);
            gbc.gridx = 1; add(passField, gbc);

            gbc.gridx = 0; gbc.gridy++; add(registerButton, gbc);
            gbc.gridx = 1; add(backButton, gbc);
            gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2; add(messageLabel, gbc);

            backButton.setBackground(new Color(231, 76, 60));
            backButton.setForeground(Color.white);
            registerButton.setBackground(new Color(39, 174, 96));
            registerButton.setForeground(Color.white);

            backButton.addActionListener(e -> mainApp.showPanel("Login"));

            registerButton.addActionListener(e -> {
                if (correoField.getText().isEmpty() || nombreField.getText().isEmpty() ||
                        apellidoField.getText().isEmpty() || idField.getText().isEmpty() ||
                        new String(passField.getPassword()).isEmpty()) {
                    messageLabel.setText("Por favor, complete todos los campos obligatorios.");
                } else {
                    messageLabel.setText("Registro exitoso. Vuelve a ingresar.");
                }
            });
        }
        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setForeground(Color.white);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            return label;
        }
    }

    class AppointmentPanel extends JPanel {
        private EPSApp mainApp;
        private JComboBox<String> specialtiesCombo;
        private JComboBox<String> hoursCombo;
        private DefaultListModel<String> appointmentsListModel;
        private JList<String> appointmentsList;
        private String pacienteId;

        private JButton bookButton, cancelButton, reassignButton, logoutButton;

        public AppointmentPanel(EPSApp app) {
            this.mainApp = app;

            setBackground(new Color(236, 240, 241));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel titleLabel = new JLabel("Gestión de Citas");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            titleLabel.setForeground(new Color(44, 62, 80));

            JLabel specialtyLabel = new JLabel("Especialidad:");
            specialtiesCombo = new JComboBox<>(new String[]{"Medicina General", "Pediatría", "Ginecología", "Ortopedia", "Cardiología"});

            JLabel hourLabel = new JLabel("Hora de la cita:");
            String[] hours = {
                    "07:00 AM", "07:40 AM", "08:20 AM", "09:00 AM",
                    "09:40 AM", "10:20 AM", "11:00 AM", "11:40 AM",
                    "12:20 PM", "01:00 PM", "01:40 PM", "02:20 PM",
                    "03:00 PM", "03:40 PM", "04:20 PM", "05:00 PM",
                    "05:40 PM", "06:20 PM", "07:00 PM", "07:40 PM",
                    "08:20 PM"
            };
            hoursCombo = new JComboBox<>(hours);

            bookButton = new JButton("Pedir Cita");
            cancelButton = new JButton("Cancelar Cita");
            reassignButton = new JButton("Reasignar Cita");
            logoutButton = new JButton("Cerrar Sesión");

            appointmentsListModel = new DefaultListModel<>();
            appointmentsList = new JList<>(appointmentsListModel);
            appointmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(appointmentsList);

            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3; add(titleLabel, gbc);

            gbc.gridy++; gbc.gridwidth = 1; add(specialtyLabel, gbc);
            gbc.gridx = 1; add(specialtiesCombo, gbc);
            gbc.gridx = 0; gbc.gridy++; add(hourLabel, gbc);
            gbc.gridx = 1; add(hoursCombo, gbc);

            gbc.gridx = 0; gbc.gridy++; add(bookButton, gbc);
            gbc.gridx = 1; add(cancelButton, gbc);
            gbc.gridx = 2; add(reassignButton, gbc);

            gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1; gbc.weighty = 1;
            add(scrollPane, gbc);

            gbc.gridy++; gbc.gridwidth = 1; gbc.weighty = 0; gbc.fill = GridBagConstraints.NONE;
            add(logoutButton, gbc);

            bookButton.setBackground(new Color(39, 174, 96));
            bookButton.setForeground(Color.white);
            cancelButton.setBackground(new Color(231, 76, 60));
            cancelButton.setForeground(Color.white);
            reassignButton.setBackground(new Color(241, 196, 15));
            reassignButton.setForeground(Color.black);
            logoutButton.setBackground(new Color(149, 165, 166));
            logoutButton.setForeground(Color.white);

            bookButton.addActionListener(e -> {
                String especialidad = specialtiesCombo.getSelectedItem().toString();
                String hora = hoursCombo.getSelectedItem().toString();

                boolean existeHora = false;
                for (Cita c : citas) {
                    if (c.pacienteId.equals(pacienteId) && c.hora.equals(hora) && !c.cancelada) {
                        existeHora = true;
                        break;
                    }
                }

                if (existeHora) {
                    JOptionPane.showMessageDialog(this, "Ya tienes una cita a esa hora.");
                } else {
                    Cita nuevaCita = new Cita(pacienteId, especialidad, hora);
                    citas.add(nuevaCita);
                    actualizarListaCitas();
                    JOptionPane.showMessageDialog(this, "Cita programada correctamente en " + especialidad + " a las " + hora + ".");
                }
            });

            cancelButton.addActionListener(e -> {
                int index = appointmentsList.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una cita para cancelar.");
                    return;
                }
                Cita cita = citas.get(index);
                if (cita.cancelada) {
                    JOptionPane.showMessageDialog(this, "Esa cita ya está cancelada.");
                    return;
                }
                String motivo = JOptionPane.showInputDialog(this, "Motivo de cancelación:");
                if (motivo != null && !motivo.trim().isEmpty()) {
                    cita.cancelada = true;
                    cita.motivoCancelacion = motivo;
                    actualizarListaCitas();
                    JOptionPane.showMessageDialog(this, "Cita cancelada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un motivo para cancelar.");
                }
            });

            reassignButton.addActionListener(e -> {
                int index = appointmentsList.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una cita para reasignar.");
                    return;
                }
                Cita cita = citas.get(index);
                if (cita.cancelada) {
                    JOptionPane.showMessageDialog(this, "No puedes reasignar una cita cancelada.");
                    return;
                }
                String[] newSpecialties = {"Medicina General", "Pediatría", "Ginecología", "Ortopedia", "Cardiología"};
                String newEspecialidad = (String) JOptionPane.showInputDialog(this, "Seleccione nueva especialidad:", "Reasignar Especialidad", JOptionPane.PLAIN_MESSAGE, null, newSpecialties, cita.especialidad);
                String[] newHours = {
                        "07:00 AM", "07:40 AM", "08:20 AM", "09:00 AM",
                        "09:40 AM", "10:20 AM", "11:00 AM", "11:40 AM",
                        "12:20 PM", "01:00 PM", "01:40 PM", "02:20 PM",
                        "03:00 PM", "03:40 PM", "04:20 PM", "05:00 PM",
                        "05:40 PM", "06:20 PM", "07:00 PM", "07:40 PM",
                        "08:20 PM"
                };
                String newHora = (String) JOptionPane.showInputDialog(this, "Seleccione nueva hora:", "Reasignar Hora", JOptionPane.PLAIN_MESSAGE, null, newHours, cita.hora);

                boolean conflicto = false;
                for (Cita c : citas) {
                    if (c != cita && c.pacienteId.equals(pacienteId) && c.hora.equals(newHora) && !c.cancelada) {
                        conflicto = true;
                        break;
                    }
                }

                if (conflicto) {
                    JOptionPane.showMessageDialog(this, "Ya tienes otra cita en esa hora.");
                } else {
                    cita.especialidad = newEspecialidad;
                    cita.hora = newHora;
                    actualizarListaCitas();
                    JOptionPane.showMessageDialog(this, "Cita reasignada correctamente.");
                }
            });

            logoutButton.addActionListener(e -> {
                pacienteId = null;
                appointmentsListModel.clear();
                mainApp.showPanel("Login");
            });
        }

        public void setPacienteId(String id) {
            this.pacienteId = id;
            actualizarListaCitas();
        }

        private void actualizarListaCitas() {
            appointmentsListModel.clear();
            for (Cita c : citas) {
                if (c.pacienteId.equals(pacienteId)) {
                    String estado = c.cancelada ? " (Cancelada - " + c.motivoCancelacion + ")" : "";
                    appointmentsListModel.addElement(c.especialidad + " - " + c.hora + estado);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EPSApp app = new EPSApp();
            app.setVisible(true);
        });
    }
}
