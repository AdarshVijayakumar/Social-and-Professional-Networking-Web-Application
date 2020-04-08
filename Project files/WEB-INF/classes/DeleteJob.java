import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class DeleteJob extends HttpServlet {
	Job job;
	PrintWriter out;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{      
		out= response.getWriter();
		String product_id = request.getParameter("pid");
		Utilities utility = new Utilities(request,out);
		utility.printHtml("HeaderRecruiter.html");
		utility.printHtml("DeleteJob.html");				//display the form to read the job id of the job to be deleted.
		utility.printHtml("SidebarManager.html");
		utility.printHtml("Footer.html");
	}
}

