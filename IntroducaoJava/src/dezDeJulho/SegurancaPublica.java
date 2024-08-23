package dezDeJulho;

public class SegurancaPublica extends ExercicioFrames{
	public SegurancaPublica() {
		super("Segurança pública", 400, 300);
		addTextField("Qual o nome");
		addTextField("Será que vale a pena");
		addSubimitButton("Enviar");
		conclude();
	}
}
