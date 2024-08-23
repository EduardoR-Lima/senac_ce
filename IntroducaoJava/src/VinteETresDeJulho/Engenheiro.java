package VinteETresDeJulho;

public class Engenheiro extends Funcionario {
	
	Integer CREA;
	String nivel;
	String area;
	
	@Override
	public String toString() {
		return "Engenheiro [CREA=" + CREA + ", nivel=" + nivel + ", area=" + area + ", nome=" + nome + ", salario="
				+ salario + ", matricula=" + matricula + ", peso=" + peso + ", idade=" + idade + ", getCREA()="
				+ getCREA() + ", getNivel()=" + getNivel() + ", getArea()=" + getArea() + ", getNome()=" + getNome()
				+ ", getSalario()=" + getSalario() + ", getMatricula()=" + getMatricula() + ", getPeso()=" + getPeso()
				+ ", getIdade()=" + getIdade() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public Integer getCREA() {
		return CREA;
	}

	public void setCREA(Integer cREA) {
		CREA = cREA;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Engenheiro(String nome, double salario, Integer matricula, double peso, int idade,
			Integer CREA, String nivel, String area) {
		super(nome, salario, matricula, peso, idade);
		this.CREA = CREA;
		this.nivel = nivel;
		this.area = area;
	}
	
}
