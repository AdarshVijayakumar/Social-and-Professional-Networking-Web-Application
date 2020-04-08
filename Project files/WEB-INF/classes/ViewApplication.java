import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class ViewApplication extends HttpServlet
{

  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

    MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

    String username = request.getParameter("username");
		Users user =(Users)session.getAttribute("user");
		String uname = user.username;

		HashMap<String, Application> or = mySQL.ViewApplication(uname);
		
    utility.printHtml("HeaderLogout.html");	
    out.println("<section id=\"content\">");
    out.println("<h3 style='text-align:center;'>Application History</h3>");
    out.println("<table cellspacing=\"0\" class=\"application-history\"'>");
    out.println("<thead>");
    out.println("<tr class=\"headings\">");
    out.println("<th class=\"\">OrderId</td>");
    out.println("<th class=\"\">Company Name</td>");
    out.println("<th class=\"\">Job Type</td>");
    out.println("<th class=\"\">State</td>");
    out.println("<th class=\"\">Status</td>");
    out.println("<th class=\"\">Withdraw Application</td>");

    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");

    for (Map.Entry<String,Application> entry : or.entrySet())
    {
      Application job =(Application)entry.getValue();
      out.println("<tr>");
      out.println("<td class=\"productName\">");
      out.println(job.getOrderId());
      out.println("</td>");

      out.println("<td class=\"productName\">");
      out.println(job.getCompanyName());
      out.println("</td>");

      out.println("<td class=\"productName\">");
      out.println(job.getcategory());
      out.println("</td>");

      out.println("<td class=\"productName\">");
      out.println(job.getstate());
      out.println("</td>");

      out.println("<td class=\"productName\">");
      out.println("In Process");
      out.println("</td>");

      out.println("<td class=\"productName\">");
      out.println("<a href=\"CancalApplication?id=" + job.getOrderId()+"&&companyname="+job.getCompanyName()+ "\" class=\"btn btn-app\" role=\"button\">Withdraw</a></p>");
      out.println("</td>");
      out.println("</tr>");
    }
    
    out.println("</tbody>");
    out.println("</table>");
    out.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  
}

}
