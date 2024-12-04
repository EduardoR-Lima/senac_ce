package quatorzeDeAgosto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import TrezeDeAgosto.ValidaCPF;


public class MainWindow {
	
	private JFrame frame;
	private List<CadFieldPanel> fields;

	/**
	 * Set the frame to visible
	 */
	public void run() {
		this.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fields = new ArrayList<>();

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

		JLabel Titulo = new JLabel("Cadastro");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		header.add(Titulo);

		JPanel footer = new JPanel();
		FlowLayout flowLayout = (FlowLayout) footer.getLayout();
		flowLayout.setVgap(15);
		frame.getContentPane().add(footer, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridBagLayout());
		
		CadFieldPanel nomeField = new CadFieldPanel("Nome");
		nomeField.setVerifier((input) -> {
			
			if (input.isEmpty()) {
				return false;
			}
			
			if (input.matches(".*\\d.*")) {
				return false;
			}
			
			return true;
		});
		nomeField.setHelpText("O campo nome não pode estar vazio e não deve conter números");
		fields.add(nomeField);

		CadFieldPanel cpfField = new CadFieldPanel("CPF");
		cpfField.addMask("###.###.###-##");
		cpfField.setVerifier(input -> {
			
			if(input.isEmpty()) {
				return false;
			}
			
			String tempInput = input.replaceAll("[.-]", "");
			
			if (!ValidaCPF.isCpf(tempInput)) {
				return false;
			}
			
			return true;
		});
		cpfField.setHelpText("O CPF informado não é válido");
		fields.add(cpfField);

		CadFieldPanel rgField = new CadFieldPanel("RG");
		fields.add(rgField);

		CadFieldPanel dnField = new CadFieldPanel("Data de Nascimento");
		fields.add(dnField);

		CadFieldPanel enderecoField = new CadFieldPanel("Endereço");
		fields.add(enderecoField);

		CadFieldPanel numeroField = new CadFieldPanel("Número");
		fields.add(numeroField);

		CadFieldPanel bairroField = new CadFieldPanel("Bairro");
		fields.add(bairroField);

		CadFieldPanel cidadeField = new CadFieldPanel("Cidade");
		fields.add(cidadeField);

		CadFieldPanel cepField = new CadFieldPanel("CEP");
		fields.add(cepField);

		CadFieldPanel estadoField = new CadFieldPanel("Estado");
		fields.add(estadoField);

		CadFieldPanel complementoField = new CadFieldPanel("Complemento");
		fields.add(complementoField);

		CadFieldPanel paiField = new CadFieldPanel("Nome do Pai (Opcional)");
		fields.add(paiField);

		CadFieldPanel maeField = new CadFieldPanel("Nome da Mãe (Opcional)");
		fields.add(maeField);
		
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < fields.size(); i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			
			panel.add(fields.get(i), gbc);
		}

		JButton enviarButton = new JButton("Enviar");
		enviarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean canSubmit = true;

				for (CadFieldPanel field : fields) {

					if (!field.hasValidInput()) {
						field.setInvalidState();
						canSubmit = false;
						continue;
					}
					field.setValidState();
					
				}

				if (!canSubmit) {
					JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos adequadamente",
							"Campo Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(frame, "Informações enviadas com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		enviarButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		footer.add(enviarButton);
	}
}
