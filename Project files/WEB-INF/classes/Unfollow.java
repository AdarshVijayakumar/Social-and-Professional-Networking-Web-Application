import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class Unfollow extends HttpServlet
{

  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{      
		PrintWriter out= response.getWriter();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		HttpSession session=request.getSession(); 

		String follower = session.getAttribute("uname").toString();
		String following = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		
		try
		{	
			//remove the entry of who is following whom
			mySQL.RemoveFollower(follower,following);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	
		String url = "/AjaxAutoCompleteServlet?action=lookup&id=" + firstname;
		request.getRequestDispatcher(url).forward(request, response);
	}
}