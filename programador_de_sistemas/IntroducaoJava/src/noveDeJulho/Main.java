package noveDeJulho;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Exercícios :)

public class Main {

	public static void main(String[] args) {
		new Mamifero("gato", "gato", 1, 1.0).toString();
		
		System.out.println(((Integer) 10));
		
		Pattern p = Pattern.compile("[0-9]");
		String resultados = p.matcher("Bom3232 dia 6666grupo").results()
				.map(MatchResult::group)
				.collect(Collectors.joining());
		
		System.out.println(resultados);
		Stream.of(new Valor<Integer>(10), new Valor<Integer>(20))
		.map(ValorResult::transform)
		.forEach(System.out::println);;

	}

}

class Valor<T> implements ValorResult{
	T valor;
	
	public Valor(T valor) {
		this.valor = valor;
	}
	
	@Override
	public String transform() {
		return String.valueOf(valor)+"numero";
	}
	
	
}

interface ValorResult {
	String transform();
}