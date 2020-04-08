import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class View extends HttpServlet
{
	Utilities utility;
	PrintWriter out;
  	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  	{
		out= response.getWriter();
		Utilities utility = new Utilities(request,out);
		HttpSession session=request.getSession();

		MongoDbDataStoreUtility mongoDB = new MongoDbDataStoreUtility();

		String username = session.getAttribute("uname").toString();

		HashMap<String, ArrayList<JobApply>> application =mongoDB.selectJobApplication();

		utility.printHtml("HeaderLogout.html");
		if(application.containsKey(username))
		{
			ArrayList<JobApply> job = application.get(username);
			out.println("<section id=\"content\">");
			out.println("<h1 style='color:#00B3B3; font-weight:bold;'>Candidate Applications</h1>");
			out.println("<table cellspacing=\"0\" class=\"shopping-cart\">");
			out.println("<thead>");
			out.println("<tr class=\"headings\">");
			out.println("<th class=\"\">Fullname</td>");
			out.println("<th class=\"\">Category</td>");
			out.println("<th class=\"\">Company Name</td>");
			out.println("<th class=\"\">State</td>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			for(JobApply app : job)
			{
				out.println("<tr>"); 
				out.println("<td class=\"fullname\">");
				out.println(app.getfullname());
				out.println("</td>");
				out.println("<td class=\"category\">");
				out.println(app.getcategory());
				out.println("</td>");
				out.println("<td class=\"companyname\">");
				out.println(app.getcompanyname());
				out.println("</td>");
				out.println("<td class=\"state\">");
				out.println(app.getstate());
				out.println("</td>");
				out.println("</tr>");
			}
			
			out.println("</tbody>");
			out.println("</table>");
			out.println("</section>");
		}
	
	utility.printHtml("Sidebar.html");
	utility.printHtml("Footer.html");	
	}
}