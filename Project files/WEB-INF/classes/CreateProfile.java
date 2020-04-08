import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class CreateProfile extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		HttpSession session=request.getSession(); 
		Utilities utility = new Utilities(request,out);
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = (Users)session.getAttribute("user");
		String uname = session.getAttribute("uname").toString();

		HashMap<String, UserProfile> userprofile = mySQL.getProfile(uname);
		System.out.println(userprofile);

		if(session.getAttribute("user")!= null )  
		{
			Users users  = new Users();
			users= (Users)session.getAttribute("user");
			if(user.getusertype().equals("Candidate"))
			{
				utility.printHtml("HeaderLogout.html");
				utility.printHtml("Profile.html");             //display the form to enter the profile information 
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
			}
		}
		else
		{
				utility.printHtml("Header.html");
				out.println("<section id=\"content\">");                  
				out.println("<h2 style=\"color:red\"> Please login First </h2>"); 
				out.println("<h2 style=\"color:blue\"> Click Here for Login : <a href=\"Login\" color=\"red\">Login</a></h2>");             
				out.println("</section>");
				
				
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
		}
	}		

}