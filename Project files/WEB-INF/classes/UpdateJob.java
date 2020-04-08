import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UpdateJob extends HttpServlet
{
	Job job;
	PrintWriter out;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	out= response.getWriter();
	Utilities utility = new Utilities(request,out);
	utility.printHtml("HeaderRecruiter.html");
	utility.printHtml("UpdateJob.html");				//display the form to enter the details to update the job
	utility.printHtml("SidebarManager.html");
	utility.printHtml("Footer.html");
	}
}
