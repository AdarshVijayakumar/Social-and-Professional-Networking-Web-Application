import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class Follow extends HttpServlet
{

  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{      
		PrintWriter out= response.getWriter();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		HttpSession session=request.getSession(); 

		String follower = session.getAttribute("uname").toString();
		String following = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		try
		{	
			//Add who is following whom into the mySQL database
			mySQL.AddFollower(follower,following,firstname,lastname);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	
		String url = "/AjaxAutoCompleteServlet?action=lookup&id=" + firstname;
		request.getRequestDispatcher(url).forward(request, response);
	}
}