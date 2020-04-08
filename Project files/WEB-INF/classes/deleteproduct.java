import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class deleteproduct extends HttpServlet
{
  Job job;
  PrintWriter out;
  
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	out= response.getWriter();
	String product_id = request.getParameter("pid");
	String product_category = request.getParameter("category");
	Utilities utility = new Utilities(request,out);
	utility.printHtml("HeaderRecruiter.html");
	MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();	
    
	HashMap<String, Job> hm2;
	hm2 = mydata.selectjob(product_id,product_category);
	job = new Job();
	
    
	for(Map.Entry<String,Job> entry : hm2.entrySet())
    {
      Job job=(Job)entry.getValue();
      out.println("<section id=\"content\">");
      out.println("<h2 align=\"center\" margin-top=\"60px\">Add product details below</h2>");
      out.println("<form action =\"dproduct\"  method=\"get\">");
      out.println("<div class=\"container username\">");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Job id</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Itemcondition\" value= '" + job.getId() + "' name=\"id\" required>");
      out.println(job.getId());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Company Name</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter CompanyName\" value= '" + job.getCompanyName() + "' name=\"companyname\" required>");
      out.println(job.getCompanyName());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Job Category</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Category\" value= '" + job.getCategory() + "' name=\"category\" required>");
      out.println(job.getCategory());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>JobType</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Itemcondition\" value= '" + job.getJobType() + "' name=\"jobtype\" required>");
      out.println(job.getJobType());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Salary</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Salary\" name=\"salary\" value= '" + job.getSalary() + "' required>");
      out.println(job.getSalary());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>State</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter image name only\" name=\"state\" value= '" + job.getState() + "' required>");
      out.println(job.getState());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Job Position</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Quantity\" name=\"jobposition\" value= '" + job.getJobPosition() + "' required>");
      out.println(job.getJobPosition());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Manufacture</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Manufacture\" name=\"jobdescription\" value= '" + job.getJobDescription() + "' required>");
      out.println(job.getJobDescription());
      out.println("</div>");
      
      out.println("<div class=\"username\">");
      out.println("<label><b>Manufacture</b></label>");
      out.println("<input type=\"hidden\" class=\"uname\" placeholder=\"Enter Manufacture\" name=\"experiencelevel\" value= '" + job.getExperienceLevel() + "' required>");
      out.println(job.getExperienceLevel());
      out.println("</div>");
      



      out.println("<div class=\"username1\">");
      out.println("<button class=\"lbutton\" type=\"submit\" value=\"login\">Delete Product</button>");
      out.println("</div>");
      
      out.println("</div>");
      out.println("</form>");
      out.println("</section>");
    }
   utility.printHtml("SidebarManager.html");
   utility.printHtml("Footer.html");
  }
}
