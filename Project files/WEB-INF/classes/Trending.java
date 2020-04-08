import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	ArrayList <MostApplied> mostApplied = new ArrayList <MostApplied> ();
    ArrayList <MostAppliedState> mostAppliedState = new ArrayList <MostAppliedState> ();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		Utilities utility = new Utilities(request, out);

		mostApplied = MongoDbDataStoreUtility.mostAppliedJobs();
		mostAppliedState = MongoDbDataStoreUtility.mostAppliedState();

		String name = "Trending";

		Users user = (Users)session.getAttribute("user");
		if(session.getAttribute("user")!= null)  
		{
		
		utility.printHtml("HeaderLogout.html");
		out.print("<section id=\"content\">");

		out.println("<h3 style='text-align:center; color:#00B3B3; font-weight:bold; padding-top:20px;'>Most Applied Jobs by State</h3>");
		out.println("<table cellspacing=\"0\" class=\"application-history\"'>");
	    out.println("<thead>");
	    out.println("<tr class=\"headings\">");
	    out.println("<th class=\"\" style='width:40%;'>State</td>");
	    out.println("<th class=\"\" style='width:40%;'>Count</td>");
	    out.println("</tr>");
	    out.println("</thead>");
	    out.println("<tbody>");

	    Iterator itr1 = mostAppliedState.iterator();
        while(itr1.hasNext()) 
        {
        	MostAppliedState most = (MostAppliedState)itr1.next();

        	out.println("<tr>");
	      	out.println("<td class=\"productName\" style='width:40%;'>");
	      	out.println(most.getState());
	      	out.println("</td>");

	      	out.println("<td class=\"productName\" style='width:40%;'>");
	      	out.println(most.getCount());
	      	out.println("</td></tr>");
        }
        out.println("</tbody>");
    	out.println("</table>");



		out.println("<h3 style='text-align:center; color:#00B3B3; font-weight:bold; padding-top:70px;'>Most Applied Jobs by Company</h3>");
		out.println("<table cellspacing=\"0\" class=\"application-history\"'>");
	    out.println("<thead>");
	    out.println("<tr class=\"headings\">");
	    out.println("<th class=\"\" style='width:40%;'>CompanyName</td>");
	    out.println("<th class=\"\" style='width:40%;'>Count</td>");
	    out.println("</tr>");
	    out.println("</thead>");
	    out.println("<tbody>");

	    Iterator itr2 = mostApplied.iterator();
        while(itr2.hasNext()) 
        {
        	MostApplied most = (MostApplied)itr2.next();

        	out.println("<tr>");
	      	out.println("<td class=\"productName\" style='width:40%;'>");
	      	out.println(most.getCompanyName());
	      	out.println("</td>");

	      	out.println("<td class=\"productName\" style='width:40%;'>");
	      	out.println(most.getCount());
	      	out.println("</td></tr>");
        }
        out.println("</tbody>");
    	out.println("</table>");
		
		out.println("</section>");
    	utility.printHtml("Sidebar.html");
		utility.printHtml("Footer.html");
	}
	else
	{
		utility.printHtml("Header.html");
		out.println("<section id=\"content\">");                  
		out.println("<h2 style=\"color:red\"> Please login First </h2>"); 
		out.println("<h2 style=\"color:blue\"> Click Here for Login : <a href=\"Login\" color=\"red\">Login</a> </h2>");             
		out.println("</section>");
		utility.printHtml("Sidebar.html");
		utility.printHtml("Footer.html");
	}
}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
