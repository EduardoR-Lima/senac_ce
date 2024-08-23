package TrezeDeAgosto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tela {
	
	private JFrame frame;
	private JFormattedTextField txtCPF;
	private JLabel cpfLabel;
	private JFormattedTextField txtDN;
	private JLabel dnLabel;
	private JFormattedTextField txtTelefone;
	private JLabel telefoneLabel;
	private JLabel cnpjLabel;
	private JFormattedTextField txtCNPJ;
	private JFormattedTextField txtNome;
	private JLabel nomeLabel;
	private JFormattedTextField txtEmail;
	private JLabel emailLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}
	
	private static void setMask(JFormattedTextField textField, String maskPattern) {
		try {
			MaskFormatter mask = new MaskFormatter(maskPattern);
			textField.setFormatterFactory(new DefaultFormatterFactory(mask));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao formatar a mask \"%s\"".formatted(maskPattern), "Error", JOptionPane.ERROR);
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 507, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel cepLabel = new JLabel("CEP");
		cepLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cepLabel.setBounds(20, 11, 241, 14);
		frame.getContentPane().add(cepLabel);
		
		JFormattedTextField txtCEP = new JFormattedTextField();
		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtCPF.requestFocus();
				}
			}
		});
		setMask(txtCEP, "##.###-###");
		txtCEP.setBounds(20, 36, 241, 20);
		frame.getContentPane().add(txtCEP);
		
		txtCPF = new JFormattedTextField();
		setMask(txtCPF, "###.###.###-##");
		txtCPF.setBounds(20, 92, 241, 20);
		frame.getContentPane().add(txtCPF);
		
		cpfLabel = new JLabel("CPF");
		cpfLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cpfLabel.setBounds(20, 67, 241, 14);
		frame.getContentPane().add(cpfLabel);
		
		txtDN = new JFormattedTextField();
		setMask(txtDN, "##/##/####");
		txtDN.setBounds(20, 148, 241, 20);
		frame.getContentPane().add(txtDN);
		
		dnLabel = new JLabel("Data de Nascimento");
		dnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dnLabel.setBounds(20, 123, 241, 14);
		frame.getContentPane().add(dnLabel);
		
		txtTelefone = new JFormattedTextField();
		setMask(txtTelefone, "(##)#####-####");
		txtTelefone.setBounds(20, 204, 241, 20);
		frame.getContentPane().add(txtTelefone);
		
		telefoneLabel = new JLabel("Telefone");
		telefoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		telefoneLabel.setBounds(20, 179, 241, 14);
		frame.getContentPane().add(telefoneLabel);
		
		cnpjLabel = new JLabel("CNPJ");
		cnpjLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cnpjLabel.setBounds(20, 235, 241, 14);
		frame.getContentPane().add(cnpjLabel);
		
		txtCNPJ = new JFormattedTextField();
		setMask(txtCNPJ, "##.###.###/####-##");
		txtCNPJ.setBounds(20, 260, 241, 20);
		frame.getContentPane().add(txtCNPJ);
		
		JButton cadButton = new JButton("Cadastrar");
		cadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText().trim();
				String email = txtEmail.getText().trim();
				
				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "O campo nome não pode estar vazio", "Nome inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!nome.matches("[A-Za-z ]+")) {
					JOptionPane.showMessageDialog(frame, "O campo nome deve conter apenas letras e espaços", "Nome inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "O campo e-mail não pode estar vazio", "E-mail inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!email.matches("[a-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
					JOptionPane.showMessageDialog(frame, "O e-mail fornecido não é válido", "E-mail inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		cadButton.setBounds(302, 192, 153, 45);
		frame.getContentPane().add(cadButton);
		
		txtNome = new JFormattedTextField();
		txtNome.setBounds(20, 316, 241, 20);
		frame.getContentPane().add(txtNome);
		
		nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nomeLabel.setBounds(20, 291, 241, 14);
		frame.getContentPane().add(nomeLabel);
		
		txtEmail = new JFormattedTextField();
		txtEmail.setBounds(20, 372, 241, 20);
		frame.getContentPane().add(txtEmail);
		
		emailLabel = new JLabel("E-mail");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setBounds(20, 347, 241, 14);
		frame.getContentPane().add(emailLabel);
	}
}
