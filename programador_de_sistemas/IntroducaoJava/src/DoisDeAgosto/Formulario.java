package DoisDeAgosto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Formulario extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField dobField;

    private JToggleButton contactPrefToggle;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JCheckBox newsletterCheckBox;
    private JButton submitButton;

    private DefaultTableModel tableModel;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Atividade";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public Formulario() {
        setTitle("Formulário de Contato Avançado");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        JLabel nameLabel = new JLabel("Nome:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Telefone:");
        phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Endereço:");
        addressField = new JTextField();
        JLabel dobLabel = new JLabel("Data de Nascimento:");
        dobField = new JTextField();

        JLabel contactPrefLabel = new JLabel("Preferência de Contato:");
        contactPrefToggle = new JToggleButton("Email");
        contactPrefToggle.addItemListener(e -> {
            if (contactPrefToggle.isSelected()) {
                contactPrefToggle.setText("Telefone");
            } else {
                contactPrefToggle.setText("Email");
            }
        });

        JLabel genderLabel = new JLabel("Gênero:");
        maleRadio = new JRadioButton("Masculino");
        femaleRadio = new JRadioButton("Feminino");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);

        JLabel newsletterLabel = new JLabel("Assinar Newsletter:");
        newsletterCheckBox = new JCheckBox();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(contactPrefLabel);
        formPanel.add(contactPrefToggle);
        formPanel.add(genderLabel);
        formPanel.add(genderPanel);
        formPanel.add(newsletterLabel);
        formPanel.add(newsletterCheckBox);

        submitButton = new JButton("Enviar");
        submitButton.addActionListener(new SubmitButtonListener());

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);

        tableModel = new DefaultTableModel(new String[] { "Nome", "Email", "Telefone", "Endereço", "Data de Nascimento",
                "Preferência de Contato", "Gênero", "Newsletter" }, 0);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String dob = dobField.getText();
            String contactPref = contactPrefToggle.isSelected() ? "Telefone" : "Email";
            String gender = maleRadio.isSelected() ? "Masculino"
                    : femaleRadio.isSelected() ? "Feminino" : "Não especificado";
            String newsletter = newsletterCheckBox.isSelected() ? "Sim" : "Não";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO formulario (nome, email, telefone, endereco, data_nascimento, preferencia_contato, genero, newsletter) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, email);
                    pstmt.setString(3, phone);
                    pstmt.setString(4, address);
                    pstmt.setString(5, dob); 
                    pstmt.setString(6, contactPref);
                    pstmt.setString(7, gender);
                    pstmt.setBoolean(8, "Sim".equals(newsletter));
                    pstmt.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            tableModel.setRowCount(0); 
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM formulario";
                try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("endereco"),
                            rs.getString("data_nascimento"),
                            rs.getString("preferencia_contato"),
                            rs.getString("genero"),
                            rs.getBoolean("newsletter") ? "Sim" : "Não"
                        });
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            new InfoFrame(tableModel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formulario());
    }
}

class InfoFrame extends JFrame {
    public InfoFrame(DefaultTableModel tableModel) {
        setTitle("Informações do Formulário");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
