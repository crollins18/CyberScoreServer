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
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
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
		out.println("<table border=\"4\">");
		String[][] tempArr = s.getArray();
		out.println("<tr><th>National Place</th><th>Team Number</th><th>Location/Category</th><th>Division</th><th>Tier</th><th>Score Images</th><th>Play Time (HH:MM)</th><th>Warnings</th><th>CCS Score</th></tr>");

		for(int r=1; r<tempArr.length; r++) {
			out.println("<tr>");
			for(int c=0; c<tempArr[0].length-1; c++) {
				out.print("<td>" + tempArr[r][c] + "</td>");
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
