import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class AddJobPosting extends HttpServlet
{
	
	PrintWriter out;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		out= response.getWriter();
		Utilities utility = new Utilities(request,out);	
		utility.printHtml("HeaderRecruiter.html");
		utility.printHtml("AddJobPosting.html");
		utility.printHtml("SidebarManager.html");
		utility.printHtml("Footer.html");
	}
}