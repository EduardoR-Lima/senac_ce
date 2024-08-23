package CincoDeJulho;

public class Pessoa {
	
	private String nome;
	private int idade;
	private double altura;
	
	public Pessoa(String nome, int idade, double altura) {
		this.nome = nome;
		this.idade = idade;
		this.altura = altura;
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public double getAltura() {
		return this.altura;
	}
	
	public int getIdade() {
		return this.idade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public double apresentarSalario(double salario) {
		double salarioFinal = salario - salario*0.27;
		System.out.println("O salário líquido é: " + salarioFinal);
		return salarioFinal;
	}
	
}
