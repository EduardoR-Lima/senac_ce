package VinteETresDeJulho;

public class Desenvolvedor extends Funcionario {
	
	String linguagemProgramacao;
	String nivel;
	
	public Desenvolvedor(String nome, double salario, Integer matricula, double peso, int idade,
			String linguagemProgramacao, String nivel) {
		super(nome, salario, matricula, peso, idade);
		
		this.linguagemProgramacao = linguagemProgramacao;
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Desenvolvedor [linguagemProgramacao=" + linguagemProgramacao + ", nivel=" + nivel + ", nome=" + nome
				+ ", salario=" + salario + ", matricula=" + matricula + ", peso=" + peso + ", idade=" + idade
				+ ", getLinguagemProgramacao()=" + getLinguagemProgramacao() + ", getNivel()=" + getNivel()
				+ ", getNome()=" + getNome() + ", getSalario()=" + getSalario() + ", getMatricula()=" + getMatricula()
				+ ", getPeso()=" + getPeso() + ", getIdade()=" + getIdade() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	public String getLinguagemProgramacao() {
		return linguagemProgramacao;
	}

	public void setLinguagemProgramacao(String linguagemProgramacao) {
		this.linguagemProgramacao = linguagemProgramacao;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
