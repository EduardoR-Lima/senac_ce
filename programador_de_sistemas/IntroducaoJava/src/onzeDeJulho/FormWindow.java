package onzeDeJulho;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class FormWindow extends JFrame {
	private static final Dimension MINUMUM_SIZE = new Dimension(400, 80);

	protected JPanel mainPanel;
	protected JPanel formPanel;
	protected JButton submitButton;
	private DefaultTableModel tableModel;

	private final List<AbstractFormComponent> formComponents;

	public FormWindow(String title, int width, int height) {
		setTitle(title);
		setMinimumSize(MINUMUM_SIZE);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		this.submitButton = new JButton("Enviar");

		this.mainPanel = new JPanel(new BorderLayout(10, 10));
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.formPanel = new JPanel(new GridLayout(0, 1, 10, 10));

		this.formComponents = new ArrayList<>();
		
		tableModel = new DefaultTableModel();

	}

	public FormTextField addTextField(String fieldLabel) {
		FormTextField component = new FormTextField(fieldLabel);
		formComponents.add(component);
		return component;
	}
	
	private void construct() {
		for (AbstractFormComponent formComponent : formComponents) {
			formPanel.add(formComponent.getComponentPanel());
			tableModel.addColumn(formComponent.getLabelText());
		}
		
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(submitButton, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		submitButton.addActionListener(new SubmitButtonListener());
	}

	public void run() {
		construct();
		setVisible(true);
	}
	
	private static boolean verifyComponentInput(AbstractFormComponent component) {
		if (!component.hasVerifier()) return true;
		return component.hasValidInput();
	}

	private class SubmitButtonListener implements ActionListener {
		
		Vector<String> componentsInfo = new Vector<>();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (AbstractFormComponent formComponent : formComponents) {
				if (!verifyComponentInput(formComponent)) {
					JOptionPane.showMessageDialog(
							FormWindow.this,
							"%s deve ser preenchido".formatted(formComponent.getLabelText()),
							"Campo vazio",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				componentsInfo.add(formComponent.getInfo());
			}
			
			tableModel.addRow(componentsInfo);
			
			new InfoFrame(tableModel);
		}

	}
}
