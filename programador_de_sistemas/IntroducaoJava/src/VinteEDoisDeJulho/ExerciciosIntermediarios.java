package VinteEDoisDeJulho;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExerciciosIntermediarios {
	public static void call() {
		String resultado = ex1("Olá, Eduardo. Você gostaria de tomar um café?", List.of("olá", "você"));
		System.out.println(resultado);
		
		System.out.println(ex2("Bom dia"));
		System.out.println(ex3("aaee"));
	}

	public static String ex1(String texto, List<String> palavrasParaRemover) {
		String pattern = palavrasParaRemover.stream().map(str -> {
			return String.format("[%s%s]%s",
					String.valueOf(str.charAt(0)).toUpperCase(),
					String.valueOf(str.charAt(0)).toLowerCase(),
					str.substring(1));
		}).collect(Collectors.joining("|"));
		
		return texto.replaceAll(pattern, "");
	}

	public static String ex2(String texto) {
		String resultado = Stream.of(texto.split(" ")).map(str -> {
			StringBuilder resultBuilder = new StringBuilder(str);
			LinkedList<Integer> charLinkedList = new LinkedList<Integer>(str.chars().boxed().toList());
			int i = 0;
			while (i < str.length()/2) {
				int k = charLinkedList.size() - 1;
				int headChar = charLinkedList.removeFirst();
				int tailChar = charLinkedList.removeLast();
				resultBuilder.setCharAt(k, (char) headChar);
				resultBuilder.setCharAt(i, (char) tailChar);
				
				i++;
			}
			
			return resultBuilder.toString();
		}).collect(Collectors.joining(" "));
		
		return resultado;
	}

	public static String ex3(String str) {
		StringBuilder builder = new StringBuilder(str.length());
		return ex3Aux(builder, str, 0, 1);
	}
	
	public static String ex3Aux(StringBuilder resultBuilder, String str, int currentIndex, int charCounter) {
		if (currentIndex == str.length()) {
			resultBuilder.append(charCounter);
			if (resultBuilder.length() > str.length()) {
				return str;
			}
			
			return resultBuilder.toString();
		};
		
		char currentChar = str.charAt(currentIndex);
		char lastChar;
		try {
			lastChar = str.charAt(currentIndex - 1);
		} catch (IndexOutOfBoundsException e) {
			resultBuilder.append(currentChar);
			return ex3Aux(resultBuilder, str, currentIndex + 1, 1);
		}
		
		if (currentChar == lastChar) {
			return ex3Aux(resultBuilder, str, currentIndex + 1, charCounter + 1);
		} else {
			resultBuilder.append(charCounter).append(currentChar);
			return ex3Aux(resultBuilder, str, currentIndex + 1, 1);
		}
		
	}
}
