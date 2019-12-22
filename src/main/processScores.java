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
	private String id;
	private String state;
	private String div;
	private String[][] copiedArray;
	private ArrayList<String[]> stateArray;
	private ArrayList<String[]> divArray;

	
	public processScores() throws FileNotFoundException {
		//in = new Scanner(new File("prefs.dat"));
		base = "http://ahscybercompinfo.azurewebsites.net/scoreboard/";
		//id = in.nextLine();
		//state = in.nextLine();
		//div = in.nextLine();
		this.getScoreboard();
	}
	
	public String getBase() {
		return base.substring(base.indexOf("//")+2);
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
			if(copiedArray[r][2].equals("NC") && copiedArray[r][3].equals("Open")) {
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
			if(copiedArray[r][3].equals("Open")) {
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
		try {
			processScores s = new processScores();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}
