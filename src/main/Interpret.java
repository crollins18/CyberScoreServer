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
@WebServlet("/go")
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
		writer = response.getWriter();
		if(command == null) {
			writer.println("<html>"
					+ "<body>"
					+ "<script>"
					+ "alert('Please go to the main menu to send a command first');\n" + 
					"window.location = 'index.jsp';"
					+ "</script>"
					+ "</body>"
					+ "</html>");
		}
		else {
			p = new processScores(getServletContext().getRealPath(""));
			this.printOut();
		}
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
			writer.println("<html>"
					+ "<body>"
					+ "<script>"  
					+ "window.location = 'help.html';"
					+ "</script>"
					+ "</body>"
					+ "</html>");
		}
		else {			
	        TreeMap<String, ArrayList<String>> tempImages = null;

			if(command.contains("!team")) {
				TreeMap<String, Team> sorted_map = null;
				String teamID = command.split(" ")[1];
				p.getTeamboard(teamID);
				try {
			    sorted_map = new TreeMap<String, Team>(p.getTeamInfo());

			    scoreboardHTML1();
				writer.println("<h4>Showing Team Detail for Team " + teamID + " as of " + new Date() + "</h4>");
				writer.println("</div><div class=\"bs-component\"><table class=\"table table-hover\"><thead>");
				writer.println("<tr><th scope=\"col\">Team Number</th><th scope=\"col\">Location/Category</th><th scope=\"col\">Division</th><th scope=\"col\">Tier</th><th scope=\"col\">Scored<br>Images</th><th scope=\"col\">Play Time<br>hh:mm:ss</th><th scope=\"col\">Score Time<br>hh:mm:ss</th><th scope=\"col\">**</th><th scope=\"col\">CCS<br>Score</th></tr>");
				writer.println("</thead><tbody>");
				
				for (Team row: sorted_map.values()) {
					writer.println("<tr class=\"table-secondary\">");
					for(int i=0; i<row.getInfo().size(); i++) {
						writer.print("<td>"+row.getInfo().get(i)+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</tbody>");
				writer.println("</table>");
				
				writer.println("<table class=\"table table-hover\"><thead>");
				writer.println("<tr><th scope=\"col\">Image</th><th scope=\"col\">Time</th><th scope=\"col\">Found</th><th scope=\"col\">Remaining</th><th scope=\"col\">Penalties</th><th scope=\"col\">Score</th><th scope=\"col\">**</th></tr>");
				writer.println("</thead><tbody>");

				tempImages = new TreeMap<String, ArrayList<String>>(p.getTeamImages());
				
				for (ArrayList<String> row: tempImages.values()) {
					writer.println("<tr class=\"table-secondary\">");
					for(int i=0; i<row.size(); i++) {
						writer.print("<td>"+row.get(i)+"</td>");
					}
					writer.println("</tr>");
				}
				writer.println("</tbody>");
				writer.println("</table>");
				writer.println("</div></div></div></div>");

				
				writer.println("</body>");
				writer.println("</html>");
				}
				catch (Exception e){
					writer.println("<html>"
							+ "<body>"
							+ "<script>"
							+ "alert('The team number you entered does not exist or the URL you specified for a Cyberpatriot Scoreboard is invalid.');\n" + 
							"window.location = 'index.jsp';"
							+ "</script>"
							+ "</body>"
							+ "</html>");
				}
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
						
						scoreboardHTML1();
						writer.println("<h4 id=\"tables\">Showing National Scoreboard as of " +  new Date() + "</h4>");
						writer.println("</div><div class=\"bs-component\"><table id=\"generated\" class=\"table table-striped table-bordered\" style=\"width:100%\"><thead>");
						writer.println("<tr><th scope=\"col\"></th><th scope=\"col\">Team Number</th><th scope=\"col\">Location/Category</th><th scope=\"col\">Division</th><th scope=\"col\">Tier</th><th scope=\"col\">Scored Images</th><th scope=\"col\">Play Time hh:mm:ss</th><th scope=\"col\">**</th><th scope=\"col\">CCS Score</th></tr></thead>");
						writer.println("<tbody>");
						for (Team row: sorted_map.values()) {
							writer.println("<tr class=\"table-secondary\">");
							for(int i=0; i<row.getInfo().size(); i++) {
								writer.print("<td>"+row.getInfo().get(i)+"</td>");
							}
							writer.println("</tr>");
						}
						scoreboardHTML2();
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
						
					scoreboardHTML1();
					writer.println("<h4>Showing Filtered Scoreboard as of " +  new Date() + "</h4>");
					writer.println("</div><div class=\"bs-component\"><table id=\"generated\" class=\"table table-striped table-bordered\" style=\"width:100%\"><thead>");
					writer.println("<tr><th scope=\"col\">National Place</th><th scope=\"col\">Team Number</th><th scope=\"col\">Location/Category</th><th scope=\"col\">Division</th><th scope=\"col\">Tier</th><th scope=\"col\">Score Images</th><th scope=\"col\">Play Time (HH:MM)</th><th scope=\"col\">Warnings</th><th scope=\"col\">CCS Score</th><th scope=\"col\">Filtered Place</th></tr>");
					writer.println("</thead><tbody>");
					
					
					int count = 1;
					for (Team row: sorted_map.values()) {
						writer.println("<tr class=\"table-secondary\">");
						for(int i=0; i<row.getInfo().size(); i++) {
							writer.print("<td>"+row.getInfo().get(i)+"</td>");
						}
						writer.println("<td>");
						writer.println(count);
						writer.println("</td>");

						writer.println("</tr>");
						count++;
					}
					scoreboardHTML2();
					}
				}
			}
		}
	}
	
	public void scoreboardHTML1() {
		writer.println("<html>");
		writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.css\"><meta http-equiv=\"refresh\" content=\"30\"><script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n" + 
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\n" + 
				"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>"
				+ "<link href=\"https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css\" rel=stylesheet>\n" + 
				"<script src=\"https://code.jquery.com/jquery-3.5.1.js\"></script>\n" + 
				"<script src=\"https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js\"></script>\n" + 
				"<script src=\"https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js\"></script>"
				+ "</head>");
		writer.println("<body>");
		writer.println("<script>\n" + 
				"$(document).ready(function() {\n" + 
				"    $('#generated').DataTable();\n" + 
				"} );\n" + 
				"</script>");
		writer.println("<div class=\"navbar navbar-expand-lg fixed-top navbar-dark bg-dark\">\n" + 
				"      <div class=\"container\">\n" + 
				"        <a href=\"\" class=\"navbar-brand\">CyberScoreServer</a>\n" + 
				"        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" + 
				"          <span class=\"navbar-toggler-icon\"></span>\n" + 
				"        </button>\n" + 
				"        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" + 
				"          <ul class=\"navbar-nav\">\n" + 
				"            <li class=\"nav-item\">\n" + 
				"              <a class=\"nav-link\" href=\"index.jsp\">Back to Main Menu</a>\n" + 
				"            </li>\n" + 
				"          </ul>\n" + 
				"        </div>\n" + 
				"      </div>\n" + 
				"    </div>");
		writer.println("<div class=\"container\">");
		writer.println("<div class=\"row\"><div class=\"col-lg-12\"><div class=\"page-header\">");
	}
	
	public void scoreboardHTML2() {
		writer.println("</tbody>");
		writer.println("</table>");
		writer.println("</div></div></div></div>");
		writer.println("</body>");
		writer.println("</html>");
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
