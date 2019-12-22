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
@WebServlet(description = "National Scoreboard", urlPatterns = { "/National" , "/National.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="pankaj")})
public class NationalBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html>";
	public static final String HTML_END="</html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NationalBoard() {
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
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
		out.println("<body>");
		out.println("<h2>Showing National Scoreboard as of " +  new Date() + "</h2>");
		out.println("<h3>Fetching from " + s.getBase() + "</h3>");
		out.println("<hr>");
		out.println("<table border=\"4\" class='CSSTableGenerator'>");
		String[][] tempArr = s.getArray();
		out.println("<tr><td>National Place</td><td>Team Number</td><td>Location/Category</td><td>Division</td><td>Tier</td><td>Score Images</td><td>Play Time (HH:MM)</td><td>Warnings</td><td>CCS Score</td></tr>");

		for(int r=1; r<tempArr.length; r++) {
			out.println("<tr>");
			for(int c=0; c<tempArr[0].length-1; c++) {
				out.print("<td>" + tempArr[r][c] + "</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println(HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
