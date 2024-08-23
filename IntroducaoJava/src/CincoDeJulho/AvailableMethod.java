package CincoDeJulho;

import java.util.HashMap;

public class AvailableMethod {
	private final String name;
	private final HashMap<String, Argument<?>> argMap = new HashMap<String, Argument<?>>();
	
	public AvailableMethod(String name, Argument<?>... args) {
		this.name = name;
		for (Argument<?> arg:args) {
			argMap.put(arg.getArgName(), arg);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public HashMap<String, Argument<?>> getArgMap() {
		return argMap;
	}
}
