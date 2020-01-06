package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Interpret
 */
@WebServlet("/Interpret")
public class Interpret extends HttpServlet {
	private String command;
	private PrintWriter writer = null;
	private processScores p = null;
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
		p = new processScores(getServletContext().getRealPath(""));
		writer = response.getWriter();
		if(command.equals("!help")) {
			writer.println("<html><body><h2>Help Page</h2><hr><p>!team <last four of team ID> - show team ID<br>" + 
					"!monitor <last four of team ID #1> <last four of team ID #2> (coming soon) - monitor two teams with notifications<br>" + 
					"!scoreboard <state, tier or division> <state, tier or division> <state, tier or division> - show a filtered view of the public scoreboard<br>" + 
					"!help - for help</p></body></html>");		}
		else {
			ArrayList<String[]> temp = null;
			
			if(command.contains("!team")) {
				String teamID = command.split(" ")[1];
				temp = p.getTeamboard(teamID);
				writer.println("<html>");
				writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
				writer.println("<body>");
				writer.println("<h2>Showing Team Detail for Team " + teamID + " as of " + new Date() + "</h2>");
				writer.println("<hr>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				for(int r=0; r<2; r++) {
					writer.println("<tr>");
					for(int c=0; c<temp.get(0).length; c++) {
						writer.print("<td>"+temp.get(r)[c]+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</table>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				writer.println("<br>");
				for(int r=2; r<temp.size(); r++) {
					writer.println("<tr>");
					for(int c=0; c<temp.get(2).length; c++) {
						writer.print("<td>"+temp.get(r)[c]+"</td>");
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
					if(parmLength == 0) {
						ArrayList<String[]> m = new ArrayList<String[]>();
						String[][] n = p.getArray();
						for(int r=0; r<n.length; r++) {
							m.add(n[r]);
						}
						temp = m;
						writer.println("<html>");
						writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
						writer.println("<body>");
						writer.println("<h2>Showing National Scoreboard as of " +  new Date() + "</h2>");
						writer.println("<hr>");
						writer.println("<table border=\"4\" class='CSSTableGenerator'>");

						for(int r=0; r<temp.size(); r++) {
							writer.println("<tr>");
							for(int c=0; c<temp.get(0).length-1; c++) {
								writer.print("<td>" + temp.get(r)[c] + "</td>");
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
						temp = p.trim(parm1);
					}
					if(parmLength == 2) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						temp = p.trim(parm1, parm2);
					}
					if(parmLength == 3) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						String cmd3 = parms[3];
						Object parm3 = this.determineCmd(cmd3);
						temp = p.trim(parm1, parm2, parm3);
					}
					if(parmLength != 0) {
						
					writer.println("<html>");
					writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
					writer.println("<body>");
					writer.println("<h2>Showing Filtered Scoreboard as of " +  new Date() + "</h2>");
					writer.println("<hr>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					writer.println("<tr><td>National Place</td><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Score Images</td><td>Play Time (HH:MM)</td><td>Warnings</td><td>CCS Score</td><td>Filtered Place</td></tr>");

					for(int r=0; r<temp.size(); r++) {
						writer.println("<tr>");
						for(int c=0; c<temp.get(0).length; c++) {
							writer.print("<td>" + temp.get(r)[c] + "</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					writer.println("</body>");
					writer.println("</html>");
					}
				}
				if(command.contains("!monitor")) {
					
				}
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		p = new processScores(getServletContext().getRealPath(""));
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		command = request.getParameter("commandIn");
		if(command.equals("!help")) {
			writer.println("<html><body><h2>Help Page</h2><hr><p>!team <last four of team ID> - show team ID<br>" + 
					"!monitor <last four of team ID #1> <last four of team ID #2> (coming soon) - monitor two teams with notifications<br>" + 
					"!scoreboard <state, tier or division> <state, tier or division> <state, tier or division> - show a filtered view of the public scoreboard<br>" + 
					"!help - for help</p></body></html>");		}
		else {
			ArrayList<String[]> temp = null;
			
			if(command.contains("!team")) {
				String teamID = command.split(" ")[1];
				temp = p.getTeamboard(teamID);
				writer.println("<html>");
				writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
				writer.println("<body>");
				writer.println("<h2>Showing Team Detail for Team " + teamID + " as of " + new Date() + "</h2>");
				writer.println("<hr>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				for(int r=0; r<2; r++) {
					writer.println("<tr>");
					for(int c=0; c<temp.get(0).length; c++) {
						writer.print("<td>"+temp.get(r)[c]+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</table>");
				writer.println("<table border=\"4\" class='CSSTableGenerator'>");
				writer.println("<br>");
				for(int r=2; r<temp.size(); r++) {
					writer.println("<tr>");
					for(int c=0; c<temp.get(2).length; c++) {
						writer.print("<td>"+temp.get(r)[c]+"</td>");
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
					if(parmLength == 0) {
						ArrayList<String[]> m = new ArrayList<String[]>();
						String[][] n = p.getArray();
						for(int r=0; r<n.length; r++) {
							m.add(n[r]);
						}
						temp = m;
						writer.println("<html>");
						writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
						writer.println("<body>");
						writer.println("<h2>Showing National Scoreboard as of " +  new Date() + "</h2>");
						writer.println("<hr>");
						writer.println("<table border=\"4\" class='CSSTableGenerator'>");

						for(int r=0; r<temp.size(); r++) {
							writer.println("<tr>");
							for(int c=0; c<temp.get(0).length-1; c++) {
								writer.print("<td>" + temp.get(r)[c] + "</td>");
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
						temp = p.trim(parm1);
					}
					if(parmLength == 2) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						temp = p.trim(parm1, parm2);
					}
					if(parmLength == 3) {
						String cmd1 = parms[1];
						Object parm1 = this.determineCmd(cmd1);
						String cmd2 = parms[2];
						Object parm2 = this.determineCmd(cmd2);
						String cmd3 = parms[3];
						Object parm3 = this.determineCmd(cmd3);
						temp = p.trim(parm1, parm2, parm3);
					}
					if(parmLength != 0) {
						
					writer.println("<html>");
					writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
					writer.println("<body>");
					writer.println("<h2>Showing Filtered Scoreboard as of " +  new Date() + "</h2>");
					writer.println("<hr>");
					writer.println("<table border=\"4\" class='CSSTableGenerator'>");
					writer.println("<tr><td>National Place</td><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Score Images</td><td>Play Time (HH:MM)</td><td>Warnings</td><td>CCS Score</td><td>Filtered Place</td></tr>");

					for(int r=0; r<temp.size(); r++) {
						writer.println("<tr>");
						for(int c=0; c<temp.get(0).length; c++) {
							writer.print("<td>" + temp.get(r)[c] + "</td>");
						}
						writer.println("</tr>");
					}
					writer.println("</table>");
					writer.println("</body>");
					writer.println("</html>");
					}
				}
				if(command.contains("!monitor")) {
					
				}
				
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
		if(parm.equals("MS")) {
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
