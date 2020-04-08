import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Following extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		HttpSession session=request.getSession(); 
		Utilities utility = new Utilities(request,out);
		String uname = session.getAttribute("uname").toString();

		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		//get the list of whom the user is following
		HashMap<String, Users> following = mySQL.getFollowingList(uname);
		TreeMap<String, Users> sorted_following = new TreeMap<>();
		sorted_following.putAll(following);

		utility.printHtml("HeaderLogout.html");

		out.print("<section id='content'>");
		out.print("<center><h1 style='color:#008080; font-weight:bold;'>Following</h1></center>");

		for (Map.Entry<String,Users> entry : sorted_following.entrySet())
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