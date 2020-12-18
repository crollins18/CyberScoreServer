package main;
//Comment Here
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Map.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class processScores {
	
	private Scanner in;
	private String base;
	private String extended;
	private String prefix;
	private HashMap<String, Team> scoreboard;
	private HashMap<String, Team> teamInfo;
	private HashMap<String, ArrayList<String>> teamImages;
	private HashMap<String, Team> filtered;
	
	public processScores(String context) throws FileNotFoundException {
		in = new Scanner(new File(context + "/board-admin/url.dat"));
		base = in.nextLine();
		prefix = in.nextLine();
	}
	
	public void getTeamboard(String teamID) {
		String mid = "";
		if(!base.substring(base.length()-1).equals("/")) {
			mid = "/";
		}
		extended = base + mid + "team.php?team=" + prefix + teamID;
		
		Document document = null;
		try {
			document = Jsoup.connect(extended).followRedirects(false).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(document.getElementsByTag("body").get(0).toString().equals("<body></body>")) {
			teamInfo = null;
			teamImages = null;
		}

		else {
			teamInfo = new HashMap<String, Team>();
			teamImages = new HashMap<String, ArrayList<String>>();
			for (Element row : document.select("table.CSSTableGenerator:nth-of-type(1) tr")) {
				
				Team t = new Team();
				Elements cols = row.select("td");
				for(int i=0; i<cols.size(); i++) {
					t.add(cols.get(i).text(), (i+1), 2);
				}
				teamInfo.put(t.getTeamNum(), t);
	
			}	
			
			for (Element row : document.select("table.CSSTableGenerator:nth-of-type(2) tr")) {
				
				final String img = row.select("td:nth-of-type(1)").text();
				final String time = row.select("td:nth-of-type(2)").text();
				final String found = row.select("td:nth-of-type(3)").text();
				final String remain = row.select("td:nth-of-type(4)").text();
				final String pen = row.select("td:nth-of-type(5)").text();
				final String score = row.select("td:nth-of-type(6)").text();
				final String warn = row.select("td:nth-of-type(7)").text();
				
				ArrayList<String> tempImages = new ArrayList<String>() {{ add(img); add(time); add(found); add(remain); add(pen); add(score); add(warn);}};
				teamImages.put(tempImages.get(0), tempImages);
				if(tempImages.get(0).equals("Image")) {
					teamImages.remove(tempImages.get(0));
				}
			}
		}
	}
		
	
	public HashMap<String, Team> getScoreboard() throws FileNotFoundException {
		Document document = null;
		try {
			document = Jsoup.connect(base).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scoreboard = new HashMap<String, Team>();
		
		for (Element row : document.select("table.CSSTableGenerator tr")) {
			Team t = new Team();
			Elements cols = row.select("td");
			for(int i=0; i<cols.size(); i++) {
				t.add(cols.get(i).text(), (i+1), 1);
			}
			
			scoreboard.put(t.getPlace(), t);
		}
		//scoreboard.remove("");
		return scoreboard;
	}
	
	public HashMap<String, Team> trim(Object parm1) {
		filtered = new HashMap<String, Team>();
		Sortable s1 = null;
		if(parm1 instanceof Sortable) {
			s1 = (Sortable) parm1;
		}
		int i = 1;
	    for (Entry<String, Team> entry : scoreboard.entrySet()) {
	    	if(entry.getValue().getPlace().equals("")) {
	    		filtered.put(entry.getValue().getPlace(), entry.getValue());
	    	}
	        if(s1.toString().equals(entry.getValue().get(s1.getRow()))){
	            filtered.put(String.valueOf(i), entry.getValue());
	            i += 1;
	        }
	    }
		return filtered;
	}
	
	public HashMap<String, Team> trim(Object parm1, Object parm2) {
		filtered = new HashMap<String, Team>();
		Sortable s1 = null;
		Sortable s2 = null;
		if(parm1 instanceof Sortable) {
			s1 = (Sortable) parm1;
		}
		if(parm2 instanceof Sortable) {
			s2 = (Sortable) parm2;
		}
		int i = 1;
	    for (Entry<String, Team> entry : scoreboard.entrySet()) {
	    	if(entry.getValue().getPlace().equals("")) {
	    		filtered.put(entry.getValue().getPlace(), entry.getValue());
	    	}
	        if(s1.toString().equals(entry.getValue().get(s1.getRow())) && s2.toString().equals(entry.getValue().get(s2.getRow()))){
	            filtered.put(String.valueOf(i), entry.getValue());
	            i += 1;
	        }
	    }
		return filtered;
	}
	
	public HashMap<String, Team> trim(Object parm1, Object parm2, Object parm3) {
		filtered = new HashMap<String, Team>();
		Sortable s1 = null;
		Sortable s2 = null;
		Sortable s3 = null;
		if(parm1 instanceof Sortable) {
			s1 = (Sortable) parm1;
		}
		if(parm2 instanceof Sortable) {
			s2 = (Sortable) parm2;
		}
		if(parm3 instanceof Sortable) {
			s3 = (Sortable) parm3;
		}
		int i = 1;
	    for (Entry<String, Team> entry : scoreboard.entrySet()) {
	    	if(entry.getValue().getPlace().equals("")) {
	    		filtered.put(entry.getValue().getPlace(), entry.getValue());
	    	}
	        if(s1.toString().equals(entry.getValue().get(s1.getRow())) && s2.toString().equals(entry.getValue().get(s2.getRow())) && s3.toString().equals(entry.getValue().get(s3.getRow()))){
	            filtered.put(String.valueOf(i), entry.getValue());
	            i += 1;
	        }
	    }
		return filtered;
	}
	
	public HashMap<String, Team> getTeamInfo() {
		return teamInfo;
	}
	
	public HashMap<String, ArrayList<String>> getTeamImages() {
		return teamImages;
	}
	
	public HashMap<String, Team> getMain() {
		return scoreboard;
	}
	
	public static void main(String[] args) {
		processScores p = null;
		try {
			p = new processScores("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
}
