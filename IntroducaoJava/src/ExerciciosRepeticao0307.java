import java.util.Scanner;

public class ExerciciosRepeticao0307 {

	public static void printar1ao1000() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(i + 1);
		}
	}

	public static int[] escreverNumeros(int n, Scanner scanner) {
		int[] numeros = new int[n];
		System.out.println("Escreva %d números:".formatted(n));
		for (int i = 0; i < n; i++) {
			numeros[i] = scanner.nextInt();
		}
		return numeros;
	}
	
	public static String[] escreverNomes(int n, Scanner scanner) {
		String[] nomes = new String[n];
		System.out.println("Escreva %d nomes:".formatted(n));
		for (int i = 0; i < n; i++) {
			nomes[i] = scanner.nextLine();
		}
		return nomes;
	}

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			
		}
	}

}
