package main;

import java.util.ArrayList;

public class Team {
	
	private String place;
	private String teamNum;
	private String state;
	private String division;
	private String tier;
	private String images;
	private String playTime;
	private String scoreTime;
	private String warnings;
	private String totScore;
	
	private ArrayList<String> teamInfo;
	
	public Team (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int construct) {
		if(construct == 1) {
			teamInfo = new ArrayList<String>();
			this.place = str1;
			this.teamNum = str2;
			this.state = str3;
			this.division = str4;
			this.tier = str5;
			this.images = str6;
			this.playTime = str7;
			this.warnings = str8;
			this.totScore = str9;
			teamInfo.add(place);
			teamInfo.add(teamNum);
			teamInfo.add(state);
			teamInfo.add(division);
			teamInfo.add(tier);
			teamInfo.add(images);
			teamInfo.add(playTime);
			teamInfo.add(warnings);
			teamInfo.add(totScore);
		}
		else if(construct == 2) {
			teamInfo = new ArrayList<String>();
			this.teamNum = str1;
			this.state = str2;
			this.division = str3;
			this.tier = str4;
			this.images = str5;
			this.playTime = str6;
			this.scoreTime = str7;
			this.warnings = str8;
			this.totScore = str9;
			teamInfo.add(teamNum);
			teamInfo.add(state);
			teamInfo.add(division);
			teamInfo.add(tier);
			teamInfo.add(images);
			teamInfo.add(playTime);
			teamInfo.add(scoreTime);
			teamInfo.add(warnings);
			teamInfo.add(totScore);
		}
	}
	
	public String get(int row) {
		String result = "";
		if(row == 2) {
			result = this.state;
		}
		if(row == 3) {
			result = this.division;
		}
		if(row == 4) {
			result = this.tier;
		}
		return result;
	}
	
	public ArrayList<String> getInfo() {
		return teamInfo;
	}
	
	public int getPlace() {
		return Integer.parseInt(place);
	}
	
	public static void main (String[] args) {
		Team t = new Team("3", "13-1000", "MO", "Open", "High School", "3", "04:51:56", "", "300", 1);
		Team u = new Team("4", "13-1243", "CA", "Open", "High School", "3", "05:34:02", "", "300", 1);
	}
}