package application;

public class Challenger {
	private String name;
	private Integer seed;
	
	public Challenger(String name, Integer seed) {
		this.name = name;
		this.seed = seed;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getSeed() {
		return seed;
	}
}
