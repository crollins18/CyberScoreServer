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
        if (base.get(a).getPlace() > base.get(b).getPlace()) {
            return 1;
        } else {
            return -1;
        } // returning 0 would merge keys
	}

    public static void main(String[] args) {
        HashMap<String, Team> map = new HashMap<String, Team>();
        TeamComparator bvc = new TeamComparator(map);
        TreeMap<String, Team> sorted_map = new TreeMap<String, Team>(bvc);

        map.put("39", new Team("39", "13-1000", "MO", "Open", "High School", "3", "04:51:56", "", "300", 1));
        map.put("36", new Team("36", "13-1000", "MO", "Open", "High School", "3", "04:51:56", "", "300", 1));
        map.put("4", new Team("4", "13-1243", "CA", "Open", "High School", "3", "05:34:02", "", "300", 1));

        System.out.println("unsorted map: " + map);
        sorted_map.putAll(map);
        System.out.println("results: " + sorted_map);
    }
	
}
