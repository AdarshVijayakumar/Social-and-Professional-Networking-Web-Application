import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Followers extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		HttpSession session=request.getSession(); 
		Utilities utility = new Utilities(request,out);
		String uname = session.getAttribute("uname").toString();

		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		//get the list of all the followers for the user
		HashMap<String, Users> followers = mySQL.getFollowersList(uname);
		TreeMap<String, Users> sorted_followers = new TreeMap<>();
		sorted_followers.putAll(followers);

		utility.printHtml("HeaderLogout.html");

		out.print("<section id='content'>");
		out.print("<center><h1 style='color:#008080; font-weight:bold;'>Followers</h1></center>");

		for (Map.Entry<String,Users> entry : sorted_followers.entrySet())
		{
			Users user =(Users)entry.getValue();
				out.println("<div class='followers'>" +
					"<div class='followers-feed'>" +
					"<h3 style='font-size:24px; font-weight:bold; color:#008080;'>" + user.getfirstname() + " " + user.getlastname() + "</h3>" +
					"</div></div>");
		}

		out.print("</section>");
		utility.printHtml("Sidebar.html");
		utility.printHtml("Footer.html");
	}
}