import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class JobInventory extends HttpServlet
{
	MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
	{   
		PrintWriter out= response.getWriter();
		String username = request.getParameter("username");
		HttpSession session=request.getSession();  
		ArrayList<Job> jobs;
		Users user = (Users)session.getAttribute("user");
		Utilities utility = new Utilities(request,out);
		
		if(session.getAttribute("user")!= null )  
		{
			jobs = mySQL.checkInventory();
			utility.printHtml("HeaderAdministrator.html");
			out.println("<section id=\"content\">");
			out.println("<h3 style='text-align:center; color:#00B3B3;'>Job Inventory</h3>");
			out.println("<table cellspacing=\"0\" class=\"shopping-cart\">");
			out.println("<thead>");
			out.println("<tr class=\"headings\">");
			out.println("<th class=\"\">Id</td>");
			out.println("<th class=\"\">Job Type</td>");
			out.println("<th class=\"\">Company Name</td>");
			out.println("<th class=\"\">Salary</td>");
			out.println("<th class=\"\">Job Position</td>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			out.println("<tr>");
			int i= 1;
			for(Job job : jobs)
			{
				
				out.println("<td class=\"productName\">");
				out.println(i++);
				out.println("</td>");
				
				out.println("<td class=\"productName\">");
				out.println(job.getJobType());
				out.println("</td>");
				
				out.println("<td class=\"productName\">");
				out.println(job.getCompanyName());
				out.println("</td>");
				
				out.println("<td class=\"productName\">");
				out.println(job.getSalary());
				out.println("</td>");
				
				out.println("<td class=\"productName\">");
				out.println(job.getCategory());
				out.println("</td>");
				
				out.println("</tr>");
			}

				
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");
				out.println("</section>");
				utility.printHtml("SidebarAdmin.html");
				utility.printHtml("Footer.html");
		}
		else
		{
			utility.printHtml("Header.html");
			utility.printHtml("product.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}

	
	}
}