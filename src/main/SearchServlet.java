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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	private String teamID;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		teamID = request.getParameter("teamID");
		PrintWriter writer = response.getWriter();
		processScores s = new processScores();
		writer.println("<html>");
		writer.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"><meta http-equiv=\"refresh\" content=\"30\"></head>");
		writer.println("<body>");
		writer.println("<h2>Showing Team Detail as of " + new Date() + "</h2>");
		writer.println("<h3>Fetching from " + s.getBase() + "</h3>");
		writer.println("<hr>");
		writer.println("<table border=\"4\" class='CSSTableGenerator'>");
		ArrayList<String[]> temp = s.getTeamboard(teamID);
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

}
