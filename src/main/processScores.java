package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
	private String[][] copiedArray;
	private ArrayList<String[]> stateArray;
	private ArrayList<String[]> divArray;
	private ArrayList<String[]> teamStats;
	private ArrayList<String[]> teamImages;


	
	public processScores(String context) throws FileNotFoundException {
		in = new Scanner(new File(context + "/board-admin/url.dat"));
		base = in.nextLine();
		state = in.nextLine();
		div = in.nextLine();
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
		extended = base + mid + "team.php?team=" + teamID;
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
	
	public ArrayList<String[]> trimStateDiv() {
		stateArray = new ArrayList<String[]>();
		int i = 1;
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][2].equals(state) && copiedArray[r][3].equals(div)) {
				copiedArray[r][9] = Integer.toString(i);
				stateArray.add(copiedArray[r]);
				i++;
			}
		}
		return stateArray;
	}
	
	public ArrayList<String[]> trimDiv() {
		divArray = new ArrayList<String[]>();
		int i = 1;
		for(int r=0; r<copiedArray.length; r++) {
			if(copiedArray[r][3].equals(div)) {
				copiedArray[r][9] = Integer.toString(i);
				divArray.add(copiedArray[r]);
				i++;
			}
		}
		return divArray;
	}
	
	public String[][] getArray() {
		return copiedArray;
	}
	
	public static void main(String[] args) {
		System.out.print("here");
	}
 
}
