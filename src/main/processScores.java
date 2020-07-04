package main;
//Comment Here
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class processScores {
	
	private Scanner in;
	private String base;
	private String extended;
	private String state;
	private String div;
	private String tier;
	private String prefix;
	private String[][] copiedArray;
	private ArrayList<String[]> stateArray;
	private ArrayList<String[]> divTierArray;
	private ArrayList<String[]> divArray;
	private ArrayList<String[]> teamStats;
	private ArrayList<String[]> teamImages;


	
	public processScores(String context) throws FileNotFoundException {
		in = new Scanner(new File(context + "/board-admin/url.dat"));
		base = in.nextLine();
		prefix = in.nextLine();
		this.getScoreboard();
	}
	
	public String getBase() {
		return base.substring(base.indexOf("//")+2);
	}
	
	public ArrayList<String[]> getTeamboard(String teamID) {
		String mid = "";
		if(!base.substring(base.length()-1).equals("/")) {
			mid = "/";
		}
		extended = base + mid + "team.php?team=" + prefix + teamID;
		Document document = null;
		try {
			document = Jsoup.connect(extended).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teamImages = new ArrayList<String[]>();
		
		for (Element row : document.select("table.CSSTableGenerator:nth-of-type(1) tr")) {
			
			String[] stats = new String[9];
			stats[0] = row.select("td:nth-of-type(1)").text();
			stats[1] = row.select("td:nth-of-type(2)").text();
			stats[2] = row.select("td:nth-of-type(3)").text();
			stats[3] = row.select("td:nth-of-type(4)").text();
			stats[4] = row.select("td:nth-of-type(5)").text();
			stats[5] = row.select("td:nth-of-type(6)").text();
			stats[6] = row.select("td:nth-of-type(7)").text();
			stats[7] = row.select("td:nth-of-type(8)").text();
			stats[8] = row.select("td:nth-of-type(9)").text();


			teamImages.add(stats);
		}	
		
		for (Element row : document.select("table.CSSTableGenerator:nth-of-type(2) tr")) {
			
			String[] imgArray = new String[7];
			String img = row.select("td:nth-of-type(1)").text();
			imgArray[0] = img;
			String time = row.select("td:nth-of-type(2)").text();
			imgArray[1] = time;
			String found = row.select("td:nth-of-type(3)").text();
			imgArray[2] = found;
			String remain = row.select("td:nth-of-type(4)").text();
			imgArray[3] = remain;
			String pen = row.select("td:nth-of-type(5)").text();
			imgArray[4] = pen;
			String score = row.select("td:nth-of-type(6)").text();
			imgArray[5] = score;
			String warn = row.select("td:nth-of-type(7)").text();
			imgArray[6] = warn;
			teamImages.add(imgArray);
		}	
		return teamImages;
	}
		
	
	public void getScoreboard() throws FileNotFoundException {
		Document document = null;
		try {
			document = Jsoup.connect(base).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int countTeams = 0;
		for (Element row : document.select("table.CSSTableGenerator tr")) {
			
			countTeams = countTeams + 1;
			
		}
		
		int i = 0;
		copiedArray = new String[countTeams][10];
		
		
		for (Element row : document.select("table.CSSTableGenerator tr")) {
			
				if (i>= copiedArray.length) {
					
					break;
				}
				String place = row.select("td:nth-of-type(1)").text();
				copiedArray[i][0] = place;
				String teamNums = row.select("td:nth-of-type(2)").text();
				copiedArray[i][1] = teamNums;
				String state = row.select("td:nth-of-type(3)").text();
				copiedArray[i][2] = state;
				String division = row.select("td:nth-of-type(4)").text();
				copiedArray[i][3] = division;
				String tier = row.select("td:nth-of-type(5)").text();
				copiedArray[i][4] = tier;
				String images = row.select("td:nth-of-type(6)").text();
				copiedArray[i][5] = images;
				String playTime = row.select("td:nth-of-type(7)").text();
				copiedArray[i][6] = playTime;
				String warnings = row.select("td:nth-of-type(8)").text();
				copiedArray[i][7] = warnings;
				String totScore = row.select("td:nth-of-type(9)").text();
				copiedArray[i][8] = totScore;
				copiedArray[i][9] = "";
				i = i + 1;
		}
		copiedArray[0][0] = "Place";
		

	}
	
	public ArrayList<String[]> trim(Object parm1) {
		divArray = new ArrayList<String[]>();
		int i = 1;
		Sortable s1 = null;
		if(parm1 instanceof Sortable) {
			s1 = (Sortable) parm1;
		}
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][s1.getRow()].equals(s1.toString())) {
				copiedArray[r][9] = Integer.toString(i);
				divArray.add(copiedArray[r]);
				i++;
			}
		}
		return divArray;
	}
	
	public ArrayList<String[]> trim(Object parm1, Object parm2) {
		divArray = new ArrayList<String[]>();
		int i = 1;
		Sortable s1 = null;
		Sortable s2 = null;
		if(parm1 instanceof Sortable) {
			s1 = (Sortable) parm1;
		}
		if(parm2 instanceof Sortable) {
			s2 = (Sortable) parm2;
		}
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][s1.getRow()].equals(s1.toString()) && copiedArray[r][s2.getRow()].equals(s2.toString())) {
				copiedArray[r][9] = Integer.toString(i);
				divArray.add(copiedArray[r]);
				i++;
			}
		}
		return divArray;
	}
	
	public ArrayList<String[]> trim(Object parm1, Object parm2, Object parm3) {
		divArray = new ArrayList<String[]>();
		int i = 1;
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
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][s1.getRow()].equals(s1.toString()) && copiedArray[r][s2.getRow()].equals(s2.toString()) && copiedArray[r][s3.getRow()].equals(s3.toString())) {
				copiedArray[r][9] = Integer.toString(i);
				divArray.add(copiedArray[r]);
				i++;
			}
		}
		return divArray;
	}
	
	public int getTeamTot(String inTeam) {
		int tmp = 0;
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][1].equals(prefix + inTeam)) {
				tmp = Integer.parseInt(copiedArray[r][8]);	
			}
		}
		return tmp;
	}
	
	public Date getTeamTime(String inTeam) {
		String tmp = null;
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][1].equals(prefix + inTeam)) {
				tmp = copiedArray[r][6];	
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date d = null;
		try {
			d = dateFormat.parse(tmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public String[][] getArray() {
		return copiedArray;
	}
	
	public static void main(String[] args) {
		processScores p = null;
		try {
			p = new processScores("http://ahscybercompinfo.azurewebsites.net/scoreboard/");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String[]> stateArray = p.trim("NC", "Open", "Platinum");
		for(int r=0; r<stateArray.size(); r++) {
			for(int c=0; c<stateArray.get(0).length; c++) {
				System.out.print(stateArray.get(r)[c] + "\t");
			}
			System.out.println();
		}
	}
 
}
