package VinteEDoisDeJulho;

public class FuncoesString {
	
	public String iniciarStringManeiraUm(String nome) {
		String nomeFinal = nome;
		System.out.println("Maneira 1: " + nomeFinal);
		return nomeFinal;
	}
	
	public String iniciarStringManeiraDois(String nome) {
		String nomeFinal = new String(nome);
		System.out.println("Maneira 2: " + nomeFinal);
		return nomeFinal;
	}
	
	public int retornarTamanhoString(String nome) {
		int tamanho = nome.length();
		System.out.println("O tamanho da String é " + tamanho);
		return tamanho;
	}
	
	public char retornarCaracterNaPosicao(String nome, int i) {
		char letra = nome.charAt(i);
		System.out.println("A letra na posição " + (i+1) + " é " + letra);
		return letra;
	}
	
	public String retornarParteDaString(String nome, int indiceDoInicio, int indiceDoFinal) {
		String novaString = nome.substring(indiceDoInicio, indiceDoFinal);
		System.out.println("A nova String é " + novaString);
		return novaString;
	}
	
	public String retornarParteDaString(String nome, int indiceDoInicio) {
		String novaString = nome.substring(indiceDoInicio);
		System.out.println("A nova String é " + novaString);
		return novaString;
	}
	
	public int retornarPrimeiroIndice(String nome, String letra) {
		int posicao = nome.indexOf(letra);
		System.out.println("A posição encontrada é " + (posicao+1));
		return posicao;
	}
	
	public int retornarUltimoIndice(String nome, String letra) {
		int posicao = nome.lastIndexOf(letra);
		System.out.println("A posição encontrada é " + (posicao+1));
		return posicao;
	}
	
	public boolean contemAString(String principal, String substring) {
		boolean contem = principal.contains(substring);
		System.out.println("\"%s\" %s a substring \"%s\"".formatted(principal, contem?"contém":"não contém", substring));
		return contem;
	}
	
	public boolean eIgualA(String nome1, String nome2) {
		boolean igual = nome1.equals(nome2);
		System.out.println("\"%s\" %s igual a \"%s\"".formatted(nome1, igual?"é":"não é", nome2));
		return igual;
	}
}
