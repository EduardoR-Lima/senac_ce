package dezDeJulho;

public class Futebol extends ExercicioFrames {
	public Futebol() {
		super("Cadastro do time", 500, 300);

		addTextField("Nome do time: ");
		addTextField("A seleção é feminina ou masculina? ");
		addTextField("Número de jogadores: ");
		addTextField("De onde é o time: ");
		addTextField("Esse time é bom ou ruim? ");
		addSubimitButton("Enviar");
		conclude();
	}
}
