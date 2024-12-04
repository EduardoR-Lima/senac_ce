package dezenoitoDeJulho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Exercicios {
	
	public static void call() {
		hashMapQ2();
	}
	
	public static double generateRandomGrade(double min, double max) {
		double nota = new Random().nextDouble(min, max);
		return new BigDecimal(nota).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static void hashMapQ1() {
		HashMap<String, Double> alunoNotas = new HashMap<>(
				Map.of("Eduardo", generateRandomGrade(5.0, 10.0),
						"Maria", generateRandomGrade(5.0, 10.0),
						"Marcos", generateRandomGrade(5.0, 10.0),
						"Eliel", generateRandomGrade(5.0, 10.0),
						"Luan", generateRandomGrade(5.0, 10.0))
				);
		
		alunoNotas.forEach((nome, nota) -> {
			System.out.println("%s, sua nota foi %.2f".formatted(nome, nota));
		});
	}
	
	public static void hashMapQ2(String frase) {
		HashMap<String, Integer> frequencia_palavras = new HashMap<>();
		
		String[] palavras_frase = frase.split(" ");
			
		for (String palavra : palavras_frase) {
			if (frequencia_palavras.containsKey(palavra)) {
				
			}
		}
		
	}
 }
