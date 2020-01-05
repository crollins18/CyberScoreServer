package main;

public class Tier extends Sortable{
	
	private String tier;
	
	public Tier (String tier) {
		this.tier = tier;
	}
	
	public String toString() {
		return tier;
	}
	
	public int getRow() {
		return 4;
	}

}
