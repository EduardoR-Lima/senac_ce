package dezesseteDeJulho;

import java.util.Vector;

public class ArrayVector {

	public static void call() {
		exemploVector1();
	}

	public static void exemploArray1() {
		// Criação do array
		int[] numeros = new int[5];

		// Preenchimento do array com números
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = i + 1;
		}

		// Aprensentação dos valores
		System.out.println("Apresentando valores da array \"numeros\" via for each");
		for (int numero : numeros) {
			System.out.println(numero);
		}

		System.out.println("Apresentando valores da array \"numeros\" via for tradicional");
		for (int i = 0; i < numeros.length; i++) {
			System.out.println(numeros[i]);
		}

	}

	public static void exemploArray2() {
		// Criação do array
		int[] valores = { 1, 2, 3, 4, 5 };

		valores[0] = 10;
		valores[2] = 30;
		
		// Aprensentação dos valores
		System.out.println("Apresentando valores da array \"valores\" via for each");
		for (int valor : valores) {
			System.out.println(valor);
		}

		System.out.println("Apresentando valores da array \"valores\" via for tradicional");
		for (int i = 0; i < valores.length; i++) {
			System.out.println(valores[i]);
		}
		
		int primeiraPosicao = valores[0];
		int ultimaPosicao = valores[valores.length - 1];
		
		System.out.println(
				"""
				Valor na primeira posição (1): %d
				Valor na última posição (%d): %d
				""".formatted(primeiraPosicao, valores.length, ultimaPosicao));
	}
	
	public static void exemploVector1() {
		Vector<String> timeFutebol = new Vector<>();
		
		timeFutebol.add("Ferroviário");
		timeFutebol.add("Fortaleza");
		timeFutebol.add("Ceará");
		timeFutebol.add("Barcelo");
		timeFutebol.add("Novo Horizontino");
		
		for (String time: timeFutebol) {
			System.out.println(time);
		}
		
		int tamanho = 5;
		Vector<Double> salarios = new Vector<>(tamanho);
		
		salarios.add(1284.90);
		salarios.add(1456.89);
		salarios.add(3656.50);
		salarios.addElement(5000.6);
		salarios.add(0, 9000.4);
		salarios.add(1234.89);
		
		
		for (Double salario: salarios) {
			System.out.println(salario);
		}
		
		double primeiroElemento = salarios.get(0);
		String firstElemento = timeFutebol.firstElement();
		
		double ultimoElemento = salarios.get(salarios.size()-1);
		String lastElemento = timeFutebol.lastElement();
		double ultlastElemento = salarios.get(salarios.indexOf(
				salarios.get(salarios.indexOf(
						salarios.get(salarios.indexOf(
								salarios.get(salarios.indexOf(
										salarios.get(salarios.indexOf(
												salarios.get(salarios.indexOf(
														salarios.get(salarios.indexOf(
																salarios.get(salarios.indexOf(
																		salarios.get(salarios.indexOf(
																				salarios.get(salarios.indexOf(
																						salarios.get(salarios.indexOf(
																								salarios.get(salarios.indexOf(
																										salarios.get(salarios.indexOf(
																												salarios.get(salarios.indexOf(salarios.lastElement()))
																												))
																										))
																								))
																						))
																				))
																		))
																))
														))
												))
										))
								))
						))
				));
		
		System.out.println(ultimoElemento);
		System.out.println(ultlastElemento);
	}
}
