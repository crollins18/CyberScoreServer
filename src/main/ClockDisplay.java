package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClockDisplay
 */
@WebServlet("/ClockDisplay")
public class ClockDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Clock c;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClockDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter p = response.getWriter();
		if(c == null) {
			p.println("<html><body><h1>Please start the timer by going to /startTimer.html</h1></body></html>");
		}
		else {
			response.addHeader("Refresh", "5");
			p.println("<html><body><h1>Elapsed Time: " + c.getElapsedTime() + "</h1><hr>");
			p.println("</body></html>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		c = new Clock();
		c.start();
		doGet(request, response);
	}

}
