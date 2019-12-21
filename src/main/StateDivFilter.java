package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "State and Div Scoreboard", urlPatterns = { "/StateDivFilter" , "/StateDivFilter.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="pankaj")})
public class StateDivFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StateDivFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		processScores s = new processScores();
		out.println(HTML_START);
		out.println("<table border=\"4\">");
		ArrayList<String[]> stateArray = s.trimStateDiv();
		out.println("<tr><th>National Place</th><th>Team Number</th><th>Location/Category</th><th>Division</th><th>Tier</th><th>Score Images</th><th>Play Time (HH:MM)</th><th>Warnings</th><th>CCS Score</th><th>Filtered Place</th></tr>");

		for(int r=0; r<stateArray.size(); r++) {
			out.println("<tr>");
			for(int c=0; c<stateArray.get(0).length; c++) {
				out.print("<td>" + stateArray.get(r)[c] + "</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println(HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
