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
 * Servlet implementation class TeamMonitor
 */
@WebServlet("/TeamMonitor")
public class TeamMonitor extends HttpServlet {
	
	private String teamMon1;
	private String teamMon2;
	private int team1Start;
	private int team2Start;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamMonitor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		processScores s = new processScores(getServletContext().getRealPath(""));
		writer.println("<html>");
		writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\">");
		writer.println("<script type=\"text/javascript\" src=\"notif.js\"></script>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h2><b>Monitoring</b> Team Detail for " + teamMon1 + " as of " + new Date() + "</h2>");
		writer.println("<h3>Fetching from " + s.getBase() + "</h3>");
		writer.println("<hr>");
		writer.println("<table border=\"4\" class='CSSTableGenerator'>");
		ArrayList<String[]> temp1 = s.getTeamboard(teamMon1);
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
		writer.println("<br>");
		writer.println("<hr>");
		writer.println("<h2><b>Monitoring</b> Team Detail for " + teamMon2 + " as of " + new Date() + "</h2>");
		writer.println("<table border=\"4\" class='CSSTableGenerator'>");
		ArrayList<String[]> temp2 = s.getTeamboard(teamMon2);
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
		if(s.getTeamTot(teamMon1) != team1Start) {
			writer.println("<script>notifyMe(\"Team "+ teamMon1 + " has scoring changes\")</script>");
			team1Start = s.getTeamTot(teamMon1);
		}
		if(s.getTeamTot(teamMon2) != team2Start) {
			writer.println("<script>notifyMe(\"Team "+ teamMon2 + " has scoring changes\")</script>");
			team2Start = s.getTeamTot(teamMon2);
		}
		writer.println("</body>");
		writer.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		teamMon1 = request.getParameter("teamID1");
		teamMon2 = request.getParameter("teamID2");
		PrintWriter writer = response.getWriter();
		processScores s = new processScores(getServletContext().getRealPath(""));
		team1Start = s.getTeamTot(teamMon1);
		team2Start = s.getTeamTot(teamMon2);
		writer.println("<html>");
		writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
		writer.println("<body>");
		writer.println("<h2><b>Monitoring</b> Team Detail for " + teamMon1 + " as of " + new Date() + "</h2>");
		writer.println("<h3>Fetching from " + s.getBase() + "</h3>");
		writer.println("<hr>");
		writer.println("<table border=\"4\" class='CSSTableGenerator'>");
		ArrayList<String[]> temp1 = s.getTeamboard(teamMon1);
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
		writer.println("<br>");
		writer.println("<hr>");
		writer.println("<h2><b>Monitoring</b> Team Detail for " + teamMon2 + " as of " + new Date() + "</h2>");
		writer.println("<table border=\"4\" class='CSSTableGenerator'>");
		ArrayList<String[]> temp2 = s.getTeamboard(teamMon2);
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
	}

}
