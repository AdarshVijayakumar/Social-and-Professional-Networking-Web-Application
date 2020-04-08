import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UpdateJobParser extends HttpServlet
{
	Job jobup;
	PrintWriter out;
	HashMap<String,Job> jobupdate = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();  
		out= response.getWriter();
		
		String product_id = request.getParameter("id");
		String product_category = request.getParameter("category");
		String product_companyname = request.getParameter("companyname");
		String product_jobtype = request.getParameter("jobtype");
		String product_jobposition = request.getParameter("jobposition");
		String product_jobdescription = request.getParameter("jobdescription");
		String product_state = request.getParameter("state");
		int product_salary = Integer.parseInt(request.getParameter("salary"));
		String product_experiencelevel = request.getParameter("experiencelevel");
		
		Utilities utility = new Utilities(request,out);
		jobup= new Job();
		
		jobup.setId(product_id);
		jobup.setCategory(product_category);
		jobup.setCompanyName(product_companyname);
		jobup.setJobType(product_jobtype);
		jobup.setJobPosition(product_jobposition);
		jobup.setJobDescription(product_jobdescription);
		jobup.setState(product_state);
		jobup.setSalary(product_salary);
		jobup.setExperienceLevel(product_experiencelevel);
		
		
		if(session.getAttribute("JobMap")!= null)
		{	
			jobupdate = (HashMap<String,Job>)session.getAttribute("JobMap");
			jobupdate.put(product_companyname,jobup); // update product in hashmap
			session.setAttribute("JobMap",jobupdate);
		}
		utility.printHtml("HeaderRecruiter.html");
		MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();
		
		mydata.updatejob(product_id,product_category,product_companyname,product_jobtype,product_jobposition,product_state,product_jobdescription,product_salary,product_experiencelevel);
		out.println("<section id=\"content\">");
		out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>This job is successfully updated.</h2>");
		out.println("</section>"); 
		utility.printHtml("SidebarManager.html");
		utility.printHtml("Footer.html");
	}
}