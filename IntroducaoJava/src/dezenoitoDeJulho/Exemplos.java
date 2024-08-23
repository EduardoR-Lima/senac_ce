package dezenoitoDeJulho;

import java.util.LinkedList;

public class Exemplos {
	
	public static void call() {
		linkedList1();
	}
	
	public static void linkedList1() {
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.addFirst("Alice");
		linkedList.addLast("Bob");
		linkedList.add(1, "Charlie");
		
		// Acessand ovalores especificado na minha LinkedList
		System.out.println("Primeiro nome " + linkedList.getFirst());
		System.out.println("Último nome" + linkedList.getLast());
		System.out.println("Cachorro 2 " + linkedList.get(1));
		
		// Remover o primeiro valor da LinkedList
		linkedList.removeFirst();
		System.out.println();
		System.out.println(linkedList);
		
	}
	
	public static void linkedList2() {
		
	}
	
	public static void linkedList3() {
		
	}
	
}
