package dezDeJulho;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExercicioFrames extends JFrame {
	protected List<TextFieldInfo> textFields = new ArrayList<>();
	protected JPanel mainPanel;
	protected JPanel formPanel;

	public ExercicioFrames(String title, int width, int height) {
		// Informações e configurações iniciais da janela que vamos
		// utilizar no formulário.
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Informações do Painel principal da aplicação criada em Java Swing
		this.mainPanel = new JPanel(new BorderLayout(10, 10));
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Informações iniiciais do painel que vai ficar no formulário
		this.formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
	}

	protected void addTextField(String fieldLable) {
		JLabel label = new JLabel(fieldLable);
		JTextField field = new JTextField();

		this.formPanel.add(label);
		this.formPanel.add(field);
		
		textFields.add(new TextFieldInfo(fieldLable, field));
	}

	protected void addSubimitButton(String buttonTitle) {
		JButton submitButton = new JButton(buttonTitle);
		submitButton.addActionListener(new SubmitButtonListener());
		this.mainPanel.add(submitButton, BorderLayout.SOUTH);
	}

	protected void conclude() {
		this.mainPanel.add(this.formPanel, BorderLayout.CENTER);
		add(this.mainPanel);
		setVisible(true);
	}

	private class SubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder message = new StringBuilder("<html>Informações:");
			 
			for (TextFieldInfo textField : textFields) {
				message.append(String.format("<li>%s  %s", textField.getLabel(), textField.getTextField().getText()));
			}
			
			message.append("<html>");

			JOptionPane.showMessageDialog(ExercicioFrames.this, message.toString(), "Informações do Formulário",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private static class TextFieldInfo {
		private final String label;
		private final JTextField textField;

		public TextFieldInfo(String label, JTextField textField) {
			this.label = label;
			this.textField = textField;
		}

		public String getLabel() {
			return label;
		}

		public JTextField getTextField() {
			return textField;
		}
	}
}
