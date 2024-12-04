package VinteETresDeJulho;

public class Main {

	public static void main(String[] args) {
		Engenheiro engenheiro = new Engenheiro("Eduardo", 12000, 1927, 75, 25, 34, "Junior", "Eng. Eletricista");
		System.out.println(engenheiro);
		
		Advogado advogado = new Advogado("Marcos", 45000, 1354, 56, 45, 343, "Vivara");
		advogado.calcularSalario(1000, 300);
		System.out.println(advogado);

		Desenvolvedor desenvolvedor = new Desenvolvedor("Mai Abreu", 15000, 123334, 45, 20, "Java", "Senior");
		desenvolvedor.calcularSalario(1000, 300);
		System.out.println(desenvolvedor);
	}

}
