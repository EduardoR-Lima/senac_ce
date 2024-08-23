import java.util.Scanner;

public class Calculadora {
	public Calculadora() {
		// TODO Auto-generated constructor stub
	}
	
	public void mostrarValores(int n) {
		for(int i = n; i > 0; i--) {
			System.out.println(i);
		}
	}
	
	public void mostrarPares(int n) {
		for(int i = 0; i < n; i++) {
			if (i%2 == 0) {
				System.out.println(i + " é par");
			}
		}
	}
	
	public void mostrarImpares(int n) {
		for(int i = 0; i < n; i++) {
			if (i%2 != 0) {
				System.out.println(i + " é ímpar");
			}
		}
	}
	
	/** Apresenta a frase "Hello World" na tela n vezes
	 * @param n -> número de vezes que a frase será printada
	 */
	public void mostrarFrases(int n) {
		int i = n;
		while(i > 0) {
			System.out.println("Hello World");
			i--;
		}
	}
	
	public double somarIterativamente() {
		Scanner scanner = new Scanner(System.in);
		double soma = 0;
		double valor = 1;
		while(valor != 0) {
			System.out.print("Digite o valor: ");
			valor = scanner.nextDouble();
			soma += valor;
		}
		scanner.close();
		System.out.println("O valor da soma é: " + soma);
		return soma;
	}
	
	public double multiplicarIterativamente() {
		//try catch garante que scanner seja fechado, independente de erros
		//durante a execução do código
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.print("Digite o valor: ");
			double valor = scanner.nextDouble();
			
			if (valor == 0) {
				return 0;
			}
			
			//Mult só precisa ser inicializado se passar do primeiro caso
			double mult = valor;
			
			while (true) {
				System.out.print("Digite o valor: ");
				valor = scanner.nextDouble();
				
				if (valor == 0) {
					return mult;
				}
				
				mult = mult*valor;
			}
			
		}
		
		
	}
	
	
	
}
