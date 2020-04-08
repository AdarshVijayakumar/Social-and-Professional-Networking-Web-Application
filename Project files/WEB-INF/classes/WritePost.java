import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class WritePost extends HttpServlet
{

  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{      
		PrintWriter out= response.getWriter();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		HttpSession session=request.getSession(); 

		Users user =(Users)session.getAttribute("user");
		String username = session.getAttribute("uname").toString();
		String text = request.getParameter("posttext");
		String firstname = user.getfirstname();
		String lastname = user.getlastname();
		
		try
		{	
			Integer count = mySQL.getCount();
			mySQL.WritePost(count,username,text,firstname,lastname);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	
		request.getRequestDispatcher("/Home").forward(request, response);
	}
}