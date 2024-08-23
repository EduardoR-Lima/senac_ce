package unitario;
import java.util.logging.Logger;

public class CalculadoraTeste {
	
	static Logger logger = Logger.getLogger(CalculadoraTeste.class.getName());

	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
		int resultadoEsperado = 5;
		int resultado = calc.somar(2, 3);
		if (resultado == resultadoEsperado) {
			logger.info("Teste ok");
		} else {
			logger.info("Teste falhou");
		}
		
		
	}

}
