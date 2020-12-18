package main;

import java.util.ArrayList;

public class Team {
	
	private String place = "";
	private String teamNum;
	private String state;
	private String division;
	private String tier;
	private String images;
	private String playTime;
	private String scoreTime;
	private String warnings;
	private String totScore;
	
	private String adjustment;
	private String cisco;
	private String finalscore;
	
	private ArrayList<String> teamInfo;
	
	public Team() {
		teamInfo = new ArrayList<String>();
	}
	
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
	
	public void add(String in, int colNum, int construct) {
		if(construct == 1) {
			if(colNum == 1) {
				place = in;
				teamInfo.add(place);
			}
			if(colNum == 2) {
				teamNum = in;
				teamInfo.add(teamNum);
			}
			if(colNum == 3) {
				state = in;
				teamInfo.add(state);
			}
			if(colNum == 4) {
				division = in;
				teamInfo.add(division);
			}
			if(colNum == 5) {
				tier = in;
				teamInfo.add(tier);
			}
			if(colNum == 6) {
				images = in;
				teamInfo.add(images);
			}
			if(colNum == 7) {
				playTime = in;
				teamInfo.add(playTime);
			}
			if(colNum == 8) {
				warnings = in;
				teamInfo.add(warnings);
			}
			if(colNum == 9) {
				totScore = in;
				teamInfo.add(totScore);
			}
			if(colNum == 10) {
				adjustment = in;
				teamInfo.add(adjustment);
			}
			if(colNum == 11) {
				cisco = in;
				teamInfo.add(cisco);
			}
			if(colNum == 12) {
				finalscore = in;
				teamInfo.add(finalscore);
			}
		}
		if(construct == 2) {
			if(colNum == 1) {
				teamNum = in;
				teamInfo.add(teamNum);
			}
			if(colNum == 2) {
				state = in;
				teamInfo.add(state);
			}
			if(colNum == 3) {
				division = in;
				teamInfo.add(division);
			}
			if(colNum == 4) {
				tier = in;
				teamInfo.add(tier);
			}
			if(colNum == 5) {
				images = in;
				teamInfo.add(images);
			}
			if(colNum == 6) {
				playTime = in;
				teamInfo.add(playTime);
			}
			if(colNum == 7) {
				scoreTime = in;
				teamInfo.add(scoreTime);
			}
			if(colNum == 8) {
				warnings = in;
				teamInfo.add(warnings);
			}
			if(colNum == 9) {
				totScore = in;
				teamInfo.add(totScore);
			}
			if(colNum == 10) {
				adjustment = in;
				teamInfo.add(adjustment);
			}
			if(colNum == 11) {
				cisco = in;
				teamInfo.add(cisco);
			}
			if(colNum == 12) {
				finalscore = in;
				teamInfo.add(finalscore);
			}
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
	
	public String getPlace() {
		return place;
	}
	public String getTeamNum() {
		return teamNum;
	}
	
}