package onzeDeJulho;

import java.awt.Color;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormTextField extends AbstractFormComponent {
	
	private static final Border NORMAL_BORDER;
	private static final Border VALID_INPUT_BORDER;
	private static final Border INVALID_INPUT_BORDER;
	
	private JTextField textField;
	private String verifierPattern;
	
	private FormTextField(String labelText, JTextField textField) {
		super(labelText, textField);
		
		this.verifierPattern = ".+";
		
		this.textField = textField;
		this.textField.setBorder(NORMAL_BORDER);
		
		this.textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				verifyInput();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				verifyInput();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
	}
	
	public FormTextField(String labelText) {
		this(labelText, new JTextField());
	}
	
	@Override
	public String getInfo() {
		return textField.getText();
	}

	@Override
	public boolean hasVerifier() {
		return true;
	}

	@Override
	public boolean hasValidInput() {
		return verifyInput();
	}
	
	public boolean verifyInput() {
		if (Pattern.matches(verifierPattern, textField.getText())) {
			textField.setBorder(VALID_INPUT_BORDER);
			return true;
		} else {
			textField.setBorder(INVALID_INPUT_BORDER);
			return false;
		}
	}
	
	public void setVerifierPattern(String regexPattern) {
		verifierPattern = regexPattern;
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

}
