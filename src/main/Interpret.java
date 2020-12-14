package main;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class Interpret
 */
@WebServlet("/Interpret")
public class Interpret extends HttpServlet {
	private String command;
	private PrintWriter writer = null;
	private processScores p = null;
	//private int team1Start = 0;
	//private int team2Start = 0;
	//private String tID1 = null;
	//private String tID2 = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Interpret() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writerSp = response.getWriter();
		if(command == null) {
			writerSp.println("<html><body><h1>Please go to the home (root) page to send a command first</h1></body></html>");
		}
		else {
			p = new processScores(getServletContext().getRealPath(""));
			writer = response.getWriter();
		}
		this.printOut();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		p = new processScores(getServletContext().getRealPath(""));
		writer = response.getWriter();
		command = request.getParameter("commandIn");
		this.printOut();
	}
	
	public void printOut() throws FileNotFoundException {
		if(command.equals("!help")) {
			writer.println("<html><body><h2>Help Page</h2><hr><p>!team <last four of team ID> - show team ID<br>" + 
					"!monitor <last four of team ID #1> <last four of team ID #2> (coming soon) - monitor two teams with notifications<br>" + 
					"!scoreboard <state, tier or division> <state, tier or division> <state, tier or division> - show a filtered view of the public scoreboard<br>" + 
					"!help - for help</p></body></html>");		}
		else {			
	        TreeMap<String, ArrayList<String>> tempImages = null;
	        
			if(command.contains("!team")) {
				String teamID = command.split(" ")[1];
				p.getTeamboard(teamID);
				
		        TreeMap<String, Team> sorted_map = new TreeMap<String, Team>(p.getTeamInfo());

				writer.println("<html>");
				writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
				writer.println("<body>");
				writer.println("<h2>Showing Team Detail for Team " + teamID + " as of " + new Date() + "</h2>");
				writer.println("<hr>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				writer.println("<tr><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Scored<br>Images</td><td>Play Time<br>hh:mm:ss</td><td>Score Time<br>hh:mm:ss</td><td>**</td><td>CCS<br>Score</td></tr>");
				for (Team row: sorted_map.values()) {
					writer.println("<tr>");
					for(int i=0; i<row.getInfo().size(); i++) {
						writer.print("<td>"+row.getInfo().get(i)+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</table>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				writer.println("<br>");
				writer.println("<tr><td>Image</td><td>Time</td><td>Found</td><td>Remaining</td><td>Penalties</td><td>Score</td><td>**</td></tr>");
				tempImages = new TreeMap<String, ArrayList<String>>(p.getTeamImages());
				for (ArrayList<String> row: tempImages.values()) {
					writer.println("<tr>");
					for(int i=0; i<row.size(); i++) {
						writer.print("<td>"+row.get(i)+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</table>");
				writer.println("</body>");
				writer.println("</html>");
			}
			else {
				
				if(command.contains("!scoreboard")) {
					String[] parms = command.split(" ");
					int parmLength = parms.length - 1;
					
			        HashMap<String, Team> map = p.getScoreboard();
			        TeamComparator comp = null;
			        TreeMap<String, Team> sorted_map = null;
					
					if(parmLength == 0) {

						comp = new TeamComparator(map);
						sorted_map = new TreeMap<String, Team>(comp);
						sorted_map.putAll(map);
						
						writer.println("<html>");
						writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
						writer.println("<body>");
						writer.println("<h2>Showing National Scoreboard as of " +  new Date() + "</h2>");
						writer.println("<hr>");
						writer.println("<table border=\"4\" class='CSSTableGenerator'>");
						writer.println("<tr><td></td><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Scored Images</td><td>Play Time hh:mm:ss</td><td>**</td><td>CCS Score</td></tr>");
						for (Team row: sorted_map.values()) {
							writer.println("<tr>");
							for(int i=0; i<row.getInfo().size(); i++) {
								writer.print("<td>"+row.getInfo().get(i)+"</td>");
							}
							writer.println("</tr>");
						}
						
						writer.println("</table>");
						writer.println("</body>");
						writer.println("</html>");
					}
					if(parmLength == 1) {
						
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						map = p.trim(parm1);
						comp = new TeamComparator(map);
						sorted_map = new TreeMap<String, Team>(comp);
						sorted_map.putAll(map);
						
					}
					if(parmLength == 2) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						map = p.trim(parm1, parm2);
						comp = new TeamComparator(map);
						sorted_map = new TreeMap<String, Team>(comp);
						sorted_map.putAll(map);
					}
					if(parmLength == 3) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						String cmd3 = parms[3];
						Object parm3 = this.determineCmd(cmd3);
						map = p.trim(parm1, parm2, parm3);
						comp = new TeamComparator(map);
						sorted_map = new TreeMap<String, Team>(comp);
						sorted_map.putAll(map);
					}
					if(parmLength != 0) {
						
					writer.println("<html>");
					writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
					writer.println("<body>");
					writer.println("<h2>Showing Filtered Scoreboard as of " +  new Date() + "</h2>");
					writer.println("<hr>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					writer.println("<tr><td>National Place</td><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Score Images</td><td>Play Time (HH:MM)</td><td>Warnings</td><td>CCS Score</td><td>Filtered Place</td></tr>");
					
					int count = 1;
					for (Team row: sorted_map.values()) {
						writer.println("<tr>");
						for(int i=0; i<row.getInfo().size(); i++) {
							writer.print("<td>"+row.getInfo().get(i)+"</td>");
						}
						writer.println("<td>");
						writer.println(count);
						writer.println("</d>");

						writer.println("</tr>");
						count++;
					}
					
					writer.println("</table>");
					writer.println("</body>");
					writer.println("</html>");
					}
				}
				//TODO
				/*if(command.contains("!monitor")) {
					int team1Current = p.getTeamTot(tID1);
					int team2Current = p.getTeamTot(tID2);
					Date team1Time = p.getTeamTime(tID1);
					Date team2Time = p.getTeamTime(tID2);
					//System.out.println(team1Current);
					//System.out.println(team2Current);
					
					ArrayList<String[]> temp1 = null;
					ArrayList<String[]> temp2 = null;
					DateFormat dateFormat = new SimpleDateFormat("hh:mm");
					String warningTime1 = "05:45";
					Date warn1 = null;
					try {
						warn1 = dateFormat.parse(warningTime1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					temp1 = p.getTeamboard(tID1);
					writer.println("<html>");
					writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\">");
					writer.println("<script type=\"text/javascript\" src=\"notif.js\"></script>");
					writer.println("</head>");
					writer.println("<body>");
					if(team1Current != team1Start) {
						writer.println("<script>notifyMe(\"Team "+ tID1 + " has scoring changes\")</script>");
						team1Start = team1Current;
					}
					if(team2Current != team2Start) {
						writer.println("<script>notifyMe(\"Team "+ tID2 + " has scoring changes\")</script>");
						team2Start = team2Current;
					}
					if(team1Time.compareTo(warn1) > 0) {
						writer.println("<script>notifyMe(\"Team "+ tID1 + " has 15 minutes left!\")</script>");
					}
					if(team2Time.compareTo(warn1) > 0) {
						writer.println("<script>notifyMe(\"Team "+ tID2 + " has 15 minutes left!\")</script>");
					}
					writer.println("<h2>Monitoring Team Detail for Team " + tID1 + " as of " + new Date() + "</h2>");
					writer.println("<hr>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					for(int r=0; r<2; r++) {
						writer.println("<tr>");
						for(int c=0; c<temp1.get(0).length; c++) {
							writer.print("<td>"+temp1.get(r)[c]+"</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					writer.println("<br>");
					for(int r=2; r<temp1.size(); r++) {
						writer.println("<tr>");
						for(int c=0; c<temp1.get(2).length; c++) {
							writer.print("<td>"+temp1.get(r)[c]+"</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					
					temp2 = p.getTeamboard(tID2);
					writer.println("<h2>Monitoring Team Detail for Team " + tID2 + " as of " + new Date() + "</h2>");
					writer.println("<hr>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					for(int r=0; r<2; r++) {
						writer.println("<tr>");
						for(int c=0; c<temp2.get(0).length; c++) {
							writer.print("<td>"+temp2.get(r)[c]+"</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					writer.println("<br>");
					for(int r=2; r<temp2.size(); r++) {
						writer.println("<tr>");
						for(int c=0; c<temp2.get(2).length; c++) {
							writer.print("<td>"+temp2.get(r)[c]+"</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					writer.println("</body>");
					writer.println("</html>");
					}*/
				
				
			}
		}
	}
	
	public Object determineCmd(String parm) {
		if(parm.equals("Open")) {
			Division d = new Division(parm);
			return d;
		}
		if(parm.equals("AS")) {
			Division d = new Division("All Service");
			return d;
		}
		if(parm.equals("Mid")) {
			Division d = new Division("Middle School");
			return d;
		}
		if(parm.equals("Gold") || parm.equals("Silver") || parm.equals("Platinum")) {
			Tier t = new Tier(parm);
			return t;
		}
		else {
			State s = new State(parm);
			return s;
		}
	}

}
