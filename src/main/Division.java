package main;

public class Division extends Sortable{
	
	private String div;
	
	public Division(String div) {
		this.div = div;
	}
	
	public String toString() {
		return div;
	}
	
	public int getRow() {
		return 3;
	}

}
