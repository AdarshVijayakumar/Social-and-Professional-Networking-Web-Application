import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class ViewJob extends HttpServlet 
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		Utilities utility = new Utilities(request,out);
		HttpSession session=request.getSession();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();
		
		String id = request.getParameter("id");
		String category = request.getParameter("category");

		HashMap<String, Job> hm = mySQL.getAllJobs();		 
		 
		Users user = (Users)session.getAttribute("user");
		if(session.getAttribute("user")!= null)  
		{
			Users users  = new Users();
			users= (Users)session.getAttribute("user");
			if(user.getusertype().equals("Candidate"))
			{
				utility.printHtml("HeaderLogout.html");
				out.println("<section id=\"content\">");
				for(Map.Entry<String,Job> entry : hm.entrySet())
				{
					Job job=(Job)entry.getValue();
					if(job.getId().equals(id) && job.getCategory().equals(category))
					{
						out.println("<div class='view-job'>" +
									"<h2 style='font-size:36px; text-align:center; color:#008080;'>" + job.getCompanyName() + "</h2></div>" +
									"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>" +
									"<h3 style='font-size:20px; text-align:center; color:#00b3b3; padding-bottom:15px;'>" + job.getJobPosition() + "&nbsp&nbsp|&nbsp&nbsp<i class='fa fa-map-marker'></i>&nbsp"  + job.getState() + "</h3>" +
									"<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Job Description</h3>" +
									"<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px; text-align:justify;'>&nbsp&nbsp&nbsp&nbsp" + job.getJobDescription() + "</div>" +
									"<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Job Category</h3>" +
									"<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getCategory() + "</span>" +
									"<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Job Type</h3>" +
									"<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getJobType() + "</span>" +
									"<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Experience Level</h3>" +
									"<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getExperienceLevel() + "</span>" +
									"<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Salary</h3>" +
									"<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp$" + job.getSalary() + "</span><br>" +
									"<div style='padding-top:30px;'><div style='padding-right:40px; float:left;'><a href=\"SubmitQuickApplication?id="+job.getId()+"&&companyname="+job.getCompanyName()+"&&salary=" +job.getSalary()+"&&category=" +job.getCategory()+ "&&description="+job.getJobDescription()+"&&jobtype="+job.getJobType()+"&&jobposition="+job.getJobPosition()+"&&state="+job.getState()+"\"class=\"btn btn-pri\" role=\"button\">Easy Apply</a></div>" +
									"<div style='padding-right:40px; float:left;'><a href=\"FullApply?id="+job.getId()+"&&companyname="+job.getCompanyName()+"&&salary=" +job.getSalary()+"&&category=" +job.getCategory()+ "&&description="+job.getJobDescription()+"&&jobtype="+job.getJobType()+"&&jobposition="+job.getJobPosition()+"&&state="+job.getState()+"\"class=\"btn btn-primary\" role=\"button\">Apply</a></div>" +
									"<div style='padding-right:40px; float:left;'><a href=\"ReviewForm?id="+job.getId()+"&&companyname="+job.getCompanyName()+"&&category=" +job.getCategory()+"&&jobposition="+job.getJobPosition()+"&&state="+job.getState()+"\"class=\"btn btn-pri\" role=\"button\">Write Review</a></div>" +
									"<div><a href=\"ViewReview?id="+job.getId()+"&&companyname="+job.getCompanyName()+"&&category=" +job.getCategory()+"&&jobposition="+job.getJobPosition()+"&&state="+job.getState()+"\"class=\"btn btn-pri\" role=\"button\">View Review</a></div></div>");
					}			   
				}
				out.println("</section>");
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
			}
		}
		else
		{
			utility.printHtml("Header.html");
			out.println("<section id=\"content\">");                  
			out.println("<h2 style=\"color:red\"> Please login First </h2>"); 
			out.println("<h2 style=\"color:blue\"> Click Here for Login : <a href=\"Login\" color=\"red\">Login</a> </h2>");             
			out.println("</section>");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}
	}		
}