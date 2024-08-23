package dezesseteDeJulho;

import java.util.List;
import java.util.Vector;

public class Exercicios {
	public static void call() {
		exercicioVector7();
	}

	public static void exercicioArray1() {
		int[] inteiros = new int[10];
	}

	public static void exercicioArray2() {
		int[] valores = { 1, 2, 3, 4, 5 };
		System.out.println(valores[2]); // resultado = 3
	}

	public static void exercicioArray3() {
		int[] numeros = { 10, 20, 30, 40, 50 };
		numeros[3] = 100;
	}

	public static void exercicioArray4() {
		int[] numeros = { 10, 20, 30, 40, 50 };

		for (int i = 0; i < numeros.length; i++) {
			System.out.println(numeros[i]);
		}
	}

	public static void exercicioArray5() {
		int[] numeros = { 10, 20, 30, 40, 50 };

		for (int numero : numeros) {
			System.out.println(numero);
		}
	}

	public static void exercicioArray6() {
		int[] numeros = { 10, 20, 30, 40, 50 };
		System.out.println(numeros.length); // resposta = 5
	}

	public static void exercicioArray7() {
		String[] frutas = { "mação", "banana", "cereja" };
	}

	public static void exercicioArray8() {
		int[] valores = { 1, 2, 3, 4, 5 };
		// System.out.println(valores[10]); throws ArrayIndexOutOfBoundsException
	}

	public static void exercicioVector1() {
		Vector<String> frutas = new Vector<>(List.of("banana", "maçã", "abacate", "acerola", "uva"));
		
		for (String fruta : frutas) {
			System.out.println(fruta);
		}
	}

	public static void exercicioVector2() {
		Vector<String> nomes = new Vector<>(List.of("Eduardo", "Maria", "Leonardo"));
		System.out.println("A lista possui o nome Leonardo? " + (nomes.contains("Leonardo")? "Sim":"Não"));
	}

	public static void exercicioVector3() {
		Vector<Integer> inteiros = new Vector<>();
		for (int i = 0; i < 5; i++) {
			inteiros.add(i+1);
		}
		
		inteiros.remove(3);
		
		System.out.println(inteiros);
	}

	public static void exercicioVector4() {
		Vector<String> cores = new Vector<>(List.of("Vermelho", "Amarelo", "Rosa", "Branco", "Preto"));
		cores.set(2, "Azul");
		
		System.out.println(cores);
	}

	public static void exercicioVector5() {
		Vector<Integer> inteiros = new Vector<>();
		for(int i = 0; i < 10; i++) {
			inteiros.add(i+1);
		}
		
		System.out.println(inteiros.get(2));
		System.out.println(inteiros.get(6));
	}

	public static void exercicioVector6() {
		Vector<Integer> pares = new Vector<>(20);
		for (int i = 0; i < 20; i++) {
			pares.add(i*2);
		}
		
		System.out.println(pares);
	}

	public static void exercicioVector7() {
		Vector<String> nomes = new Vector<>(List.of("João", "Eduardo", "João Paulo", "Maria", "Leonardo"));
		nomes.replaceAll(nome -> {
			return nome.contains("João")?"José":nome;
		});
		
		System.out.println(nomes);
	}

	public static void exercicioVector8() {
		Vector<String> computadores = new Vector<>(List.of("Dell"));
		computadores.removeIf(computador -> {
			return true;
		});
	}

	public static void exercicioVector9() {

	}

	public static void exercicioVector10() {

	}

	public static void exercicioVector11() {

	}

}
