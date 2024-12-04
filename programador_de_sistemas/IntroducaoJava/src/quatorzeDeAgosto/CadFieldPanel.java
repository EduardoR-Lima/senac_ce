package quatorzeDeAgosto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CadFieldPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final Border NORMAL_BORDER;
	private static final Border VALID_INPUT_BORDER;
	private static final Border INVALID_INPUT_BORDER;
	
	private Dimension size;
	private SpringLayout springLayout;
	private JFormattedTextField textField;
	private JLabel titleLabel;
	private String helpText;
	private JLabel helpLabel;
	private Verifier verifier;

	/**
	 * Create the panel.
	 */
	public CadFieldPanel(String labelText) {
		
		this.verifier = (input) -> {
			return true;
		};
		helpText = "";
		size = new Dimension(480, 75);
		
		setPreferredSize(size);
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		titleLabel = new JLabel(labelText);
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 6, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, titleLabel, 30, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, titleLabel, -30, SpringLayout.EAST, this);
		titleLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		add(titleLabel);

		textField = new JFormattedTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, titleLabel);
		springLayout.putConstraint(SpringLayout.WEST, textField, 12, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -22, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textField, -12, SpringLayout.EAST, this);
		textField.setBorder(NORMAL_BORDER);
		add(textField);
		textField.setColumns(10);
		
		
		helpLabel = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, helpLabel, 6, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, helpLabel, -6, SpringLayout.SOUTH, this);
		helpLabel.setForeground(new Color(128, 128, 128));
		springLayout.putConstraint(SpringLayout.EAST, helpLabel, 0, SpringLayout.EAST, textField);
		add(helpLabel);
		
		setNormalState();
		
	}
	
	public CadFieldPanel() {
		this("Generic Title");
	}

	public void addMask(String maskPattern) {
		try {
			MaskFormatter mask = new MaskFormatter(maskPattern);
			textField.setFormatterFactory(new DefaultFormatterFactory(mask));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null, "Error durante a aplicação da mascara \"%s\" no campo \"%s\"\nCausa: %s"
							.formatted(maskPattern, this.titleLabel, e.getCause().toString()),
					"Mask Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void setHelpText(String text) {
		this.helpText = text;
	}
	
	public void addTooltip(String text) {
		textField.setToolTipText(text);
	}
	
	public void setVerifier(Verifier verifier) {
		this.verifier = verifier;
	}
	
	public boolean hasValidInput() {
		return verifier.verify(textField.getText());
	}
	
	private void placeHelpText() {
		helpLabel.setText(helpText);
		setPreferredSize(size);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -22, SpringLayout.SOUTH, this);
		updateUI();
	}
	
	private void removeHelpText() {
		helpLabel.setText("");
		setPreferredSize(new Dimension(size.width, size.height - 16));
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.SOUTH, this);
		updateUI();
	}
	
	public void setInvalidState() {
		textField.setBorder(INVALID_INPUT_BORDER);
		placeHelpText();
	}
	
	public void setValidState() {
		textField.setBorder(VALID_INPUT_BORDER);
		removeHelpText();
	}
	
	public void setNormalState() {
		textField.setBorder(NORMAL_BORDER);
		removeHelpText();
	}
	
	static {
		float[] hsbRed = Color.RGBtoHSB(230, 50, 0, null);
		float[] hsbGreen = Color.RGBtoHSB(0, 215, 50, null);
		
		Color red = Color.getHSBColor(hsbRed[0], hsbRed[1], hsbRed[2]);
		Color green = Color.getHSBColor(hsbGreen[0], hsbGreen[1], hsbGreen[2]);
		
		Border inner = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		
		NORMAL_BORDER = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1), inner);
		
		VALID_INPUT_BORDER = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(green, 1), inner);
	
		INVALID_INPUT_BORDER = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(red, 1), inner);
	}

	
	public interface Verifier {
		boolean verify(String input);
	}
}
