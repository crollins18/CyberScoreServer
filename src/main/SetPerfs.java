package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetURL
 */
@WebServlet("/board-admin/SetPerfs")
public class SetPerfs extends HttpServlet {
	private String url;
	private String prefix;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetPerfs() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter p = null;
		try {
			p = new PrintWriter(new File(getServletContext().getRealPath("")+"/board-admin/url.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = request.getParameter("url");
		prefix = request.getParameter("prefix");
		p.println(url);
		p.println(prefix);
		p.close();
		writer.println("<html>");
		writer.println("<head>" + "<meta charset=\"utf-8\">\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
				+ "<link rel=\"stylesheet\" href=\"../bootstrap.css\">\n" + 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\n" + 
				"<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n" 
				+ "<script>$(window).load(function()\n" + 
				"{\n" + 
				"    $('#myModal').modal('show');\n" + 
				"});"
				+ "</script>"
				+ "</head>");
		writer.println("<body>\n" +
				"<div class=\"container\">\n" + 
				"\n" + 
				"  <!-- Modal -->\n" + 
				"  <div class=\"modal\" id=\"myModal\">\n" + 
				"    <div class=\"modal-dialog\" role=\"document\">\n" + 
				"    \n" + 
				"      <!-- Modal content-->\n" + 
				"      <div class=\"modal-content\">\n" + 
				"        <div class=\"modal-header\">\n" + 
				"          <h5 class=\"modal-title\">Yay!</h5>\n" +
				"        </div>\n" + 
				"        <div class=\"modal-body\">\n" + 
				"          <p>Server preferences have been set successfully.</p>\n" + 
				"        </div>\n" + 
				"        <div class=\"modal-footer\">\n" + 
				"          <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"window.location='../';\">Okay</button>\n" + 
				"        </div>\n" + 
				"      </div>\n" + 
				"      \n" + 
				"    </div>\n" + 
				"  </div>\n" + 
				"  \n" + 
				"</div>"
				+ "</body>"
				+ "</html>");
	}

}
