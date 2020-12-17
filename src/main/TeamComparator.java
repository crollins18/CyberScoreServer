package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TeamComparator implements Comparator<String> {
	Map<String, Team> base;
	
	public TeamComparator (Map<String, Team> base) {
		this.base = base;
	}

	@Override
	public int compare(String a, String b) {
		// TODO Auto-generated method stub
		if (!base.get(a).getPlace().equals("") && !base.get(b).getPlace().equals("")) {
	        if (Integer.parseInt(base.get(a).getPlace()) > Integer.parseInt(base.get(b).getPlace())) {
	            return 1;
	        } 
	        else {
	            return -1;
	        } // returning 0 would merge keys
		}
		else {
			return 1;
		}

	}
	
}
