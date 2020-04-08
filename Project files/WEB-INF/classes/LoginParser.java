import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class LoginParser extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	}


	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		String username = request.getParameter("username");
		String password = request.getParameter("password"); 		

		HashMap<String,Users> hm=new HashMap<String,Users>();
		try
		{
			hm=mySQL.selectUser();
		}
		catch(Exception e)
		{
			
		}

		Users user = hm.get(username);
		if(user!=null)
		{
			//check whether the user has entered the credentials to login correctly 
			String user_password = user.getpassword();
		 	String user_usertype = user.getusertype();
		 	if (password.equals(user_password))
		 	{
		 		HttpSession session=request.getSession();  
				session.setAttribute("uname",username);
				session.setAttribute("user",user);
				response.sendRedirect("Home"); 
				return;
		 	}
		 	else
			{
				//display incorrect passowrd message if the passwords do not match
				Utilities utility = new Utilities(request,pw);
				utility.printHtml("Header.html");
				pw.println("<section id=\"content\">");
				pw.println("<h1>Incorrect Password!!! Please try again!!!</h1>");
				pw.println("</section>");
				utility.printHtml("login.html");
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
			}	
		}
		else
		{
			//display the username does not exist incase of wrong username entered or if the user has not registered
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			pw.println("<section id=\"content\">");
			pw.println("<h1>This user does not exist!!</h1>");
			pw.println("</section>");
			utility.printHtml("login.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}	
	}
}