package noveDeJulho;

import java.util.Locale;

public class Mamifero {
	private String especie;
	private String nome;
	private int idade;
	private double peso;

	public Mamifero(String especie, String nome, int idade, double peso) {
		this.especie = especie;
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
	}

	public Mamifero(String especie, int idade, double peso) {
		this(especie, "Um(a) %s".formatted(especie.toLowerCase()), idade, peso);
	}

	public void correr(double metros) {
		double pesoPerdido = metros * 0.002 * (peso / 364);
		System.out.println("%s correu %.2f metros e emagreceu %.3f kg".formatted(nome, metros, pesoPerdido));
		peso -= pesoPerdido;
	}
	
	public void correr(int metros) {
		this.correr((double) metros);
	}

	public void comer(double pesoDaComida) {
		double pesoAdquirido = pesoDaComida * (1 / 3);
		System.out
				.println("%s comeu %.2f kg de comida e engordou %.3f kg".formatted(nome, pesoDaComida, pesoAdquirido));
		peso += pesoAdquirido;
	}

	public void dormir(Double horasDormidas) {
		Double horas = Double.parseDouble(String.format(Locale.ENGLISH, "%.3f", horasDormidas));
		Double minutos = Double.parseDouble(String.format(Locale.ENGLISH, "%.3f", (horas - horas.intValue()) * 60));
		Double segundos = Double.parseDouble(String.format(Locale.ENGLISH, "%.3f", (minutos - minutos.intValue()) * 60));
		Double[] tempo = new Double[] { horas, minutos, segundos };

		String[] tempoNomes = { "horas", "minutos", "segundos" };
		String mensagem = "";

		for (int i = 0; i < tempo.length; i++) {
			int tempoAtual = tempo[i].intValue();

			if (tempoAtual == 0) {
				continue;
			}

			if (mensagem.equals("")) {
				mensagem = "%d %s".formatted(tempoAtual, tempoNomes[i]);
				continue;
			}

			String sep;

			try {
				if (tempo[i + 1] == 0) {
					sep = " e";
				} else {
					sep = ",";
				}
			} catch (IndexOutOfBoundsException e) {
				sep = " e";
			}

			mensagem = mensagem + sep + " %d %s".formatted(tempoAtual, tempoNomes[i]);

		}

		System.out.println("%s dormiu por %s".formatted(this.nome, mensagem));
	}
	
	public void dormir(int horasDormidas) {
		this.dormir((double) horasDormidas);
	}
	
	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
}

