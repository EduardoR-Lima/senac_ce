package TrezeDeAgosto;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		List<String> ufs = Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO");
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a sigla do estado em que você mora (UF): ");
		String uf = sc.nextLine();
		
//		if (ufs.contains(uf)) {
//			System.out.println("É um estado válido!");
//		} else {
//			System.out.println("Não é um estado válido!");
//		}
		
		System.out.println(uf.matches("(?!(\\d)\\1{10})\\d{11}"));
		sc.close();
		
		
	}

}
