package teste_automatico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {
	Calculadora calc = new Calculadora();
	
	@Test
	void testSoma5() {
		assertTrue(calc.somar(2, 3)==5);
	}
	
	@Test
	void testSoma6() {
		assertTrue(calc.somar(3, 3)==6);
	}
	
	@Test
	void testSoma13() {
		assertTrue(calc.somar(7, 6)==13);
	}

}
