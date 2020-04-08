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


public class AllJobs extends HttpServlet
{
	public 	PrintWriter out;
	Utilities utility;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{	
		PrintWriter out= response.getWriter();
		utility = new Utilities(request,out);
		HttpSession session=request.getSession();

		String category = request.getParameter("category");
		String state = request.getParameter("state");

		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();
		HashMap<String, Job> alljobs = null;
		HashMap<String, Job> jobcategory = null;
		HashMap<String, Job> jobstate = null;
		HashMap<String, Job> jobstate_category = null;

		Users user = (Users)session.getAttribute("user");

		if(session.getAttribute("user")!= null)  
		{
			utility.printHtml("HeaderLogout.html");
			out.println("<div id=\"body\">");
			out.println("<section id=\"content\">");

			out.println("<form action =\"AllJobs\"  method=\"get\"  class=\"my-form\">");
			out.println("<div class=\"container jobs\">");

			out.println("<div class=\"job-group\" style=\"padding-top: 20px; padding-left:40px; width:250px;\">");
	    	out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"Job Category\" name=\"category\" style=\"width:200px; float:left;\">");
	    	out.println("</div>");

	    	out.println("<div class=\"job-group\" style=\"padding-left:260px;\">");
	    	out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"State\" name=\"state\" style=\"width:200px; float:left;\">");
	    	out.println("</div>");

	    	out.println("<div class=\"job-group\" style=\"padding-left:30px;\">");
      		out.println("<button class=\"btn btn-primary\" type=\"submit\" value=\"login\" id=\"submitButton\" style=\"width:100px; margin:0 30px 0;\">Search</button>");
     		out.println("</div></div></form>");

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

			if(category==null && state==null)
			{
				//Get all the jobs that are in the mySQL database
				alljobs = mySQL.getAllJobs();
				TreeMap<String, Job> sorted_alljobs = new TreeMap<>();
				sorted_alljobs.putAll(alljobs);

				for(Map.Entry<String,Job> entry : sorted_alljobs.entrySet())
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
			}	
			//Get jobs matching the Category type entered by the user
			if(category!=null && state=="")
			{
				jobcategory = mySQL.getJobsCategory(category);
				TreeMap<String, Job> sorted_jobcategory = new TreeMap<>();
				sorted_jobcategory.putAll(jobcategory);

				for(Map.Entry<String,Job> entry : sorted_jobcategory.entrySet())
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
			}
			//Get jobs matching the state entered by the user
			if(state!=null && category=="")
			{
				jobstate = mySQL.getJobsState(state);
				TreeMap<String, Job> sorted_jobstate = new TreeMap<>();
				sorted_jobstate.putAll(jobstate);

				for(Map.Entry<String,Job> entry : sorted_jobstate.entrySet())
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
			}
			//Get jobs matching the Category type and the state entered by the user
			if(state!=null && category!=null)
			{
				jobstate_category = mySQL.getJobsState_Category(category, state);
				TreeMap<String, Job> sorted_jobstate_category = new TreeMap<>();
				sorted_jobstate_category.putAll(jobstate_category);

				for(Map.Entry<String,Job> entry : sorted_jobstate_category.entrySet())
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
    
