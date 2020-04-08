import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class JobAdd extends HttpServlet
{
	PrintWriter pw;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		pw= response.getWriter();
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("HeaderRecruiter.html");
		pw.println("<section id=\"content\">");				
		pw.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>Job has been successfully posted.</h2>");
		pw.println("</section>");
		utility.printHtml("SidebarManager.html");
		utility.printHtml("Footer.html");	
	}
}