



package onzeDeJulho;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

public class Form2 extends JFrame {
	
	private FormTextField nameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField addressField;
	private JButton submitButton;
	
	private JTextField dobField;
	private JToggleButton contactPrefToggle;
	private JRadioButton maleRadio;
	private JRadioButton famaleRadio;
	private JCheckBox newsletterCheckBox;
	
	private DefaultTableModel tableModel;
	
	public Form2() {
		//Informações e configurações iniciais da janela que vamos utilizar no formulário.
		setTitle("Formulario de cadastro");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Informações do painel principal da aplicação criada em Java Swing 
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Informações iniciais do painel que vai ficar no formulário
		JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
		
		//Campos que o formulário vai possuir.
		nameField = new FormTextField("");
		
		JLabel emailLabel = new JLabel("E-mail: ");
		emailField = new JTextField();
		
		JLabel phoneLabel = new JLabel("Telefone: ");
		phoneField = new JTextField();
		
		JLabel addressLabel = new JLabel("Endereço: ");
		addressField = new JTextField();
		
		JLabel dobLabel = new JLabel("Data de nascimento: ");
		dobField = new JTextField();
		
		
		//Estrutura para criar a preferência de contato
		JLabel contactPrefLabel = new JLabel("Preferência de Contato");
		contactPrefToggle = new JToggleButton("E-mail");
		contactPrefToggle.addItemListener(e -> {
			if(contactPrefToggle.isSelected()) {
				contactPrefToggle.setText("Telefone");
			} else {
				contactPrefToggle.setText("E-mail");
			}
		});
		
		JLabel genderLabel = new JLabel("Gênero");
		maleRadio = new JRadioButton("Masculino");
		famaleRadio = new JRadioButton("Feminino");
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(famaleRadio);
		
		JLabel newsletterLabel = new JLabel("Assinar Newsletter");
		newsletterCheckBox = new JCheckBox();
		
		formPanel.add(new JLabel("Nome: "));
		formPanel.add(nameField.getComponentPanel());
		
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
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(maleRadio);
		genderPanel.add(famaleRadio);
		formPanel.add(genderPanel);
		
		formPanel.add(newsletterLabel);
		formPanel.add(newsletterCheckBox);
		
		
		
		//Botão utilizado para enviar informações do formulário
		submitButton = new JButton("Enviar");
		submitButton.addActionListener(new SubmitButtonListener());
		
		//Adicionando elementos de layout ao painel principal da aplicação
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(submitButton, BorderLayout.SOUTH);
		
		//Adicionando painel principal e todos seus recursos na janela ou aplicação desenvolvida 
		add(mainPanel);
		
		//Tornando a janela ou aplicação visível
		setVisible(true);
		
		tableModel = new DefaultTableModel(new String[] {
				"Nome", "E-mail", "Telefone", "Endereço",
				"Data de Nascimento", "Preferência de Contato",
				"Gênero", "Assinante Newsletter"
		}, 0);
	}
	
	
	
	private class SubmitButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getInfo();
			String email = emailField.getText();
			String phone = phoneField.getText();
			String address = addressField.getText();
			
			String dob = dobField.getText();
			String contactPref = contactPrefToggle.isSelected()? "Telefone": "E-mail";
			
			String gender = maleRadio.isSelected()?"Masculino" :
				famaleRadio.isSelected()?"Feminino":"Não especificado";
			
			String newsletter = newsletterCheckBox.isSelected()? "Sim": "Não";
			
//			String message = String.format("""
//					Nome: %s
//					Email: %s
//					Telefone: %s
//					Endereço: %s
//					Data de Nascimento: %s
//					Preferência de Contato: %s
//					Gênero: %s
//					Assinar Newsletter: %s
//					""", 
//					name, email, phone, address, dob, contactPref, gender, newsletter);
//			
//			JOptionPane.showMessageDialog(
//					Formulario.this,
//					message,
//					"Informações do formulário",
//					JOptionPane.INFORMATION_MESSAGE
//				);
			
			if (!nameField.hasValidInput()) {
				JOptionPane.showMessageDialog(
						Form2.this,
						"Nome deve ser preenchido",
						"Campo não preenchido",
						JOptionPane.WARNING_MESSAGE
						);
				return;
			}
			
			tableModel.addRow(new Object[] {
					name, email, phone, address,
					dob, contactPref, gender, newsletter
			});
			
			new InfoFrame(tableModel);
		}
		
	}

}
