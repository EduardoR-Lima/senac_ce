package DozeDeAgosto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TratamentoDeErro {

	public static void main(String[] args) {
//		Scanner string = new Scanner(System.in);
//		while (true) {
//			try {
//				System.out.println("Digite um texto:");
//				String texto = string.nextLine().trim();
//				if (texto.isEmpty()) {
//					System.out.println("Digite alguma coisa >:(");
//					continue;
//				}
//
////				if (texto.equals("o")) {
////					System.out.println("Não pode \"o\"");
////					continue;
////				}
//				
////				if (!texto.matches("[a-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
////					System.out.println("Digite um e-mail válido!");
////					continue;
////				}
//				
////				if (!texto.matches("^(?=...\\....\\....-..$|\\d{11}$)[0-9.-]*")) {
////					System.out.println("Digite um CPF válido!");
////					continue;
////				}
//				
//				System.out.println(texto);
//				break;
//			} catch (Exception e) {
//				e.printStackTrace();
//				string.next();
//			}
//		}
//		string.close();

		Cadastro cad = new Cadastro();
		cad.askFor("CPF", "^(?=...\\....\\....-..$|\\d{11}$)[0-9.-]*", "Digite um cpf válido");
		cad.askFor("Telefone", "[a-z0-9().-]{14}", "{no_hint}");
		cad.startLoop();
		cad.close();
		System.out.println(cad.resultMap);
	}

}

class Cadastro {
	private static final String DEFAULT_HINT = "Digite um dado válido!";
	
	Scanner scanner;
	List<Field> fields;
	HashMap<String, String> resultMap;

	public Cadastro() {
		scanner = new Scanner(System.in);
		fields = new ArrayList<>();
		resultMap = new HashMap<>();
	}

	void askFor(String name, Verifier method, String hint) {
		fields.add(new Field(name, method, hint));
	}
	
	void askFor(String name, String regex, String hint) {
		this.askFor(name, input -> {
			return input.matches(regex);
		}, hint);
	}
	
	void askFor(String name, String regex) {
		this.askFor(name, regex, DEFAULT_HINT);
	}
	
	void askFor(String name, Verifier method) {
		this.askFor(name, method, DEFAULT_HINT);
	}

	void startLoop() {
		int index = 0;
		while (true) {
			Field currentField;
			
			if (index > fields.size()) {
				break;
			}
			
			try {
				currentField = fields.get(index);
			} catch (IndexOutOfBoundsException e) {
				break;
			}

			System.out.println("Digite o valor do campo \"%s\"".formatted(currentField.getName()));
			String input = scanner.nextLine();

			if (!currentField.isValidPrompt(input)) {
				if (!currentField.getHint().contains("{no_hint}")) {
					System.out.println(currentField.getHint());
				}
				continue;
			}

			resultMap.put(currentField.getName(), input);
			index++;
		}
	}

	void close() {
		scanner.close();
	}

	private class Field {	
		private String name;
		private String hint;
		private Verifier verifier;

		public Field(String name, Verifier method, String hint) {
			this.name = name;
			this.hint = hint;
			this.verifier = method;
		}

		boolean isValidPrompt(String input) {
			return verifier.verify(input);
		}

		String getName() {
			return name;
		}

		String getHint() {
			return hint;
		}

	}

	public interface Verifier {

		boolean verify(String input);

	}
}