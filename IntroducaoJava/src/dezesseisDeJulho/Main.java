package dezesseisDeJulho;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeList;

public class Main {

	public static void main(String[] args) {

		// Criando lista
		List<String> list = new ArrayList<>();

		// Adicionando elementos na lista
		list.add("Marcos Mahalanobis");
		list.add("João Paulo II");
		list.add("Eliel Sousa");
		list.add("Luciano Cavalcante");
		list.add("Edward Snowden");
		list.add("Maria de Jesus");
		list.add("Luan");

//		// Recuperar e apresentar o útlimo elemento da lista
//		System.out.println(list.get(list.size() - 1));
//
//		// Apresentar o objeto list completo
//		System.out.println(list);
//
//		// for tradicional
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("Elemento \"" + list.get(i) + "\" de índice " + i);
//		}
//		System.out.println("\n");
//
//		list.add(1, "Leo");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("Elemento \"" + list.get(i) + "\" de índice " + i);
//		}
//
//		System.out.println("\n");
//
//		System.out.println(list);
//		list.remove(0);
//		System.out.println(list);
//		list.remove("Leo");
//		System.out.println(list);
//
//		System.out.println("\n");
//		
//		list.set(0, "João Paulo III");
//		System.out.println(list);
//		
//		// for each
//		for (String elemento : list) {
//			System.out.println("Elemento: " + elemento);
//		}
//		
//		// Retornar a primeira ocorrência
//		int primeiroValorEncontrado = list.indexOf("Maria de Jesus");
//		System.out.println("Primeiro " + primeiroValorEncontrado);
//		
//		int ultimoValorEncontrado = list.lastIndexOf("Eliel Sousa");
//		System.out.println("Ultimo " + ultimoValorEncontrado);

//		int listSize = list.size();
//		System.out.println(listSize);
//		
//		boolean isEmptyList = list.isEmpty();
//		System.out.println("A lista está vazia? " + (isEmptyList? "Sim":"Não"));
//	
//		
//		List<String> subList = list.subList(2, 4);
//		System.out.println(subList);
//		
//		boolean contains = list.contains("Eliel Sousa");
//		System.out.println(contains);
		
//		// Testando exercícios
		Exercicios.exercicio1();
		Exercicios.exercicio2();
		Exercicios.exercicio3();
		Exercicios.exercicio4();
		Exercicios.exercicio5();
		Exercicios.exercicio6();
		Exercicios.exercicio7();
		Exercicios.exercicio8();
		Exercicios.exercicio9();
	}

}
