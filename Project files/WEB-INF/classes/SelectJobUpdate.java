import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class SelectJobUpdate extends HttpServlet
{
Job job;
PrintWriter out;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		out= response.getWriter();
		String job_id = request.getParameter("pid");
		String job_category = request.getParameter("category");
		
		Utilities utility = new Utilities(request,out);
		utility.printHtml("HeaderRecruiter.html");
		MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();
		HashMap<String, Job> hm2;
		hm2 = mydata.selectjob(job_id,job_category);
		job = new Job();
		System.out.println(job.getCompanyName());
		for(Map.Entry<String,Job> entry : hm2.entrySet())
		{
			Job job=(Job)entry.getValue();
			out.println("<section id=\"content\">");
			out.println("<h2 align=\"center\" margin-top=\"60px\">Update Job</h2>");
			out.println("<form action =\"UpdateJobParser\"  method=\"get\"  class=\"my-form\">");
			out.println("<div class=\"container username\">");
			
	out.println("<div class=\"form-group\" style=\"text-align:center;\">");
      out.println("<input type=\"hidden\" id=\"exampleInputEmail1\"    class=\"form-control\" placeholder=\"Enter Itemcondition\" value= '" + job.getId() + "' name=\"id\" required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\" style=\"padding-top: 20px;\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter CompanyName\" value= '" + job.getCompanyName() + "' name=\"companyname\" required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Category\" value= '" + job.getCategory() + "' name=\"category\" required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Itemcondition\" value= '" + job.getJobType() + "' name=\"jobtype\" required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Salary\" name=\"salary\" value= '" + job.getSalary() + "' required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter image name only\" name=\"state\" value= '" + job.getState() + "' required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Quantity\" name=\"jobposition\" value= '" + job.getJobPosition() + "' required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Manufacture\" name=\"jobdescription\" value= '" + job.getJobDescription() + "' required>");
      out.println("</div>");
      
      out.println("<div class=\"form-group\">");
      out.println("<input type=\"text\" id=\"exampleInputEmail1\" class=\"form-control\" placeholder=\"Enter Manufacture\" name=\"experiencelevel\" value= '" + job.getExperienceLevel() + "' required>");
      out.println("</div>");
      

      out.println("<div class=\"form-group\"> ");
      out.println("<button style=\"margin: 0 60px 20px;\" class=\"btn btn-primary\" type=\"submit\" value=\"login\" id=\"submitButton\">Update</button>");
      out.println("</div>");
      
      out.println("</div>");
      out.println("</form>");
      out.println("</section>");
		}
			utility.printHtml("SidebarManager.html");
			utility.printHtml("Footer.html");
	}
}