package dezesseisDeJulho;

import java.util.ArrayList;
import java.util.List;

public class Exercicios {
	public static void exercicio1() {
		List<Integer> inteiros = new ArrayList<>(
				List.of(1, 2, 3, 4, 5));
		
		inteiros.forEach(System.out::println);
	}
	
	public static void exercicio2() {
		List<String> cores = new ArrayList<>(
				List.of("Amarelo", "Azul", "Vermelho", "Verde", "Rosa"));
		
		System.out.println(cores.get(2));
	}
	
	public static void exercicio3() {
		List<String> frutas = new ArrayList<>(
				List.of("Maçã", "Laranja", "Banana", "Abacate", "Melão"));
		
		frutas.remove(1);
		System.out.println(frutas);
	}

	public static void exercicio4() {
		List<Integer> inteiros = new ArrayList<>(
				List.of(1, 2, 3, 4, 5));
		
		inteiros.set(3, 10);
		System.out.println(inteiros);
	}

	public static void exercicio5() {
		List<String> animais = new ArrayList<>(
				List.of("Cachorro", "Gato", "Cavalo", "Pato", "Avestruz"));
		
		System.out.println("\"Cachorro\" está na lista? " + (animais.contains("Cachorro")? "Sim": "Não"));
	}

	public static void exercicio6() {
		List<String> paises = new ArrayList<>(
				List.of("Brasil", "China", "Rússia", "Canadá", "Alemanha"));
		System.out.println("Na lista paises há " + paises.size() + " paises");
	}

	public static void exercicio7() {
		List<String> carros = new ArrayList<String>(
				List.of("Gol", "HB20", "Onix", "Uno", "Celta"));
		carros.forEach(System.out::println);
	}

	public static void exercicio8() {
		List<String> cidades = new ArrayList<String>(
				List.of("Fortaleza", "Maracanaú", "Itaitinga", "Eusébio", "Caucaia"));
		cidades.clear();
		System.out.println("A lista cidades está vazia? " + (cidades.isEmpty()?"Sim":"Não"));
	}

	public static void exercicio9() {
		List<Integer> inteiros = new ArrayList<>(
				List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		
		List<Integer> subInteiros = inteiros.subList(3, 7);
		
		System.out.println(subInteiros);
	}

}
