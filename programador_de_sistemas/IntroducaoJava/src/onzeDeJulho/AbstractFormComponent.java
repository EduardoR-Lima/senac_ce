package onzeDeJulho;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractFormComponent {
		protected final JPanel componentPanel;
		protected final JLabel componentLabel;
		
		
	public <T extends JComponent> AbstractFormComponent(String labelText, T component) {
		
		componentPanel = new JPanel(new GridLayout(1, 2));
		componentLabel = new JLabel(labelText);
		
		componentPanel.add(componentLabel);
		componentPanel.add(component);
	}
	
	public JPanel getComponentPanel() {
		return componentPanel;
	}
	
	public String getLabelText() {
		return componentLabel.getText();
	}
	
	public abstract String getInfo();
	
	public abstract boolean hasVerifier();
	
	public abstract boolean hasValidInput();
	
	
}
