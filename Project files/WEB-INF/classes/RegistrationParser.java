import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class RegistrationParser extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	}


  	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{      
		PrintWriter out= response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String usertype=request.getParameter("usertype");
		String gender = request.getParameter("gender");

		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		//check the the entered username already exists in the database
		boolean error = mySQL.CheckRegistration(username);
		
		if(error)
		{
			//display a message if the username is already being used for registering
			Utilities utility = new Utilities(request,out);
			utility.printHtml("Header.html");
			out.println("<section id=\"content\">");
			out.println("<h1>This username is already registered</h1>");
			out.println("</section>");
			utility.printHtml("Register.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}
		else
		{	
			//if the username is not being used before then store the registration information in mySQL database
			mySQL.insert(username,password,firstname,lastname,usertype,gender);
			response.sendRedirect("Login"); 
			return;
		}	
	}
}