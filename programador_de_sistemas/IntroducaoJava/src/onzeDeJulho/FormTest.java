package onzeDeJulho;

public class FormTest extends FormWindow {
	
	public FormTest() {
		super("Formulário", 400, 280);
		
		addTextField("Nome: ");
		addTextField("Idade: ").setVerifierPattern("[0-9][0-9]?");
		addTextField("CPF: ").setVerifierPattern("(?=...\\....\\....-..|\\d{11})[0-9-.]{11,14}");
		addTextField("Telefone: ");
		
		run();
	}
}
