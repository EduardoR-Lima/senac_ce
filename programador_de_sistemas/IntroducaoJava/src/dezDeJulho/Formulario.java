package dezDeJulho;

public class Formulario extends ExercicioFrames {

	public Formulario() {
		super("Formulário de Cadastro", 400, 300);

		addTextField("Nome: ");
		addTextField("E-mail: ");
		addTextField("Telefone: ");
		addTextField("Endereço: ");
		addSubimitButton("Enviar");
		conclude();
	}

}
