package noveDeJulho;

public class CPF {
	private final long intCPF;
	private final String strCPF;
	
	public long getIntCPF() {
		return intCPF;
	}
	
	public String getStrCPF() {
		return strCPF;
	}
	
	@Override
	public String toString() {
		return strCPF;
	}
	
	
	
	public class CPFInvalido extends Exception {
		public CPFInvalido() {
			super();
		}
		
		public CPFInvalido(String mensagem) {
			super(mensagem);
		}
	}
}
