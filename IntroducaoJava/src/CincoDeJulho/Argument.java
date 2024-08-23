package CincoDeJulho;

class Argument<T> {
	private final String argName;
	private T value;
	public Argument(String argName) {
		this.argName = argName;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public String getArgName() {
		return argName;
	}
}
