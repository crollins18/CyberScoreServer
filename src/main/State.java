package main;

public class State extends Sortable{
	private String state;
	
	public State(String state) {
		this.state = state;
	}
	
	public String toString() {
		return state;
	}
	
	public int getRow() {
		return 2;
	}

}
