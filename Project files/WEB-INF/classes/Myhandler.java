import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Myhandler extends HttpServlet
{
	public 	PrintWriter out;
	Utilities utility;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		PrintWriter out= response.getWriter();
		utility = new Utilities(request,out);
		HttpSession session=request.getSession();

		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		String makers= request.getParameter("category");

		//get all the jobs from the mySQL database to display on the webpage
		HashMap<String, Job> hm2 = mySQL.getJobs(makers);
		Users user = (Users)session.getAttribute("user");

		if(session.getAttribute("user")!= null)  
		{
			utility.printHtml("HeaderLogout.html");
			out.println("<div id=\"body\">");
			out.println("<section id=\"content\">");
			out.println("<article>");
			out.println("<table cellspacing=\"0\" class=\"shopping-cart\">");
			out.println("<thead>");
			out.println("<tr class=\"headings\">");
			out.println("<th class=\"link\">&nbsp;</th>");
			out.println("<th class=\"link\">&nbsp;</th>   ");  
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			out.println("<tr>");
			

			for(Map.Entry<String,Job> entry : hm2.entrySet())
			{
				Job job=(Job)entry.getValue();
				out.println("<div class='job-listing'>" +
							"<div class='job-title'>" +
							"<h3 style='font-size:24px; font-weight:bold; color:#008080;'>" + job.getCompanyName() + "</h3>" +
							"<span style='font-size:18px; color:#00b3b3; padding-left:20px;'>" + job.getJobPosition() + "</span>" +
							"<div class='job-loc'>" + job.getState() + "</div></div>" +
							"<div class='job-button'>" +
							"<a href=\"ViewJob?id="+job.getId()+"&&category=" +job.getCategory()+"\"class=\"btn btn-primary\" role=\"button\">View Job</a></div></div>");
			}

			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</article>");
			out.println("</section>");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}
		else
		{
			utility.printHtml("Header.html");
			out.println("<div id=\"body\">");
			out.println("<section id=\"content\">");
			out.println("<article>");
			out.println("<table cellspacing=\"0\" class=\"shopping-cart\">");
			out.println("<thead>");
			out.println("<tr class=\"headings\">");
			out.println("<th class=\"link\">&nbsp;</th>");
			out.println("<th class=\"link\">&nbsp;</th>   ");  
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			out.println("<tr>");
			
			for(Map.Entry<String,Job> entry : hm2.entrySet())
			{
				Job job=(Job)entry.getValue();
				out.println("<div class='job-listing'>" +
							"<div class='job-title'>" +
							"<h3 style='font-size:24px; font-weight:bold; color:#008080;'>" + job.getCompanyName() + "</h3>" +
							"<span style='font-size:18px; color:#00b3b3; padding-left:20px;'>" + job.getJobPosition() + "</span>" +
							"<div class='job-loc'>" + job.getState() + "</div></div>" +
							"<div class='job-button'>" +
							"<a href=\"ViewJob?id="+job.getId()+"&&category=" +job.getCategory()+"\"class=\"btn btn-primary\" role=\"button\">View Job</a></div></div>");
			}

			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</article>");
			out.println("</section>");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}

	}
}
    
