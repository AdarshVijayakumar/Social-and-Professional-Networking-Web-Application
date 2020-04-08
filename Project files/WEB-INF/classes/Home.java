import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Home extends HttpServlet
{
	MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();
	PrintWriter pw= null;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		pw= response.getWriter();
		String username = request.getParameter("username");
		HttpSession session=request.getSession();  
		
		init();
		
		Users user = (Users)session.getAttribute("user");
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		Utilities utility = new Utilities(request,pw);
		if(session.getAttribute("user")!= null)  
		{
			Users users  = new Users();
			users= (Users)session.getAttribute("user");

			//check if the user is of type Recruiter
			if(user.getusertype().equals("Recruiter"))
			{
				HashMap<String, Users> followers = mySQL.getFollowers(user.getusername());
				HashMap<String, Post> posts = mySQL.getPosts();

				utility.printHtml("HeaderRecruiter.html");
				pw.print("<section id='content'>");
				pw.print("<center><h1 style='color:#008080; font-weight:bold;'>Timeline</h1></center>");

				for (Map.Entry<String,Post> entry : posts.entrySet())
				{
					Post post =(Post)entry.getValue();
					if(followers.containsKey(post.getusername()) | post.getusername().equals(user.getusername()))
					{
						pw.println("<div class='timeline'>" +
							"<div class='timeline-feed'>" +
							"<h3 style='font-size:17px; font-weight:bold; color:#008080;'>" + post.getfirstname() + " " + post.getlastname() + "</h3>" +
							"<span style='font-size:16px; color:#00b3b3; padding-left:20px; padding-bottom:20px; text-align:justify;'>" + post.gettext() + "</span></div></div>");
					}
				}

				utility.printHtml("WritePost.html");
				pw.print("</section>");
				utility.printHtml("SidebarManager.html");
				utility.printHtml("Footer.html");
			}
			//check if the user is of type Administrator
			else if(user.getusertype().equals("Administrator"))
			{
				utility.printHtml("HeaderAdministrator.html");
				utility.printHtml("Job.html");
				utility.printHtml("SidebarAdmin.html");
				utility.printHtml("Footer.html");
			}
			//check if the user is of type Candidate
			else
			{
				HashMap<String, Users> followers = mySQL.getFollowers(user.getusername());
				HashMap<String, Post> posts = mySQL.getPosts();
				utility.printHtml("HeaderLogout.html");
				
				pw.print("<section id='content'>");
				pw.print("<center><h1 style='color:#008080; font-weight:bold;'>Timeline</h1></center>");

				for (Map.Entry<String,Post> entry : posts.entrySet())
				{
					Post post =(Post)entry.getValue();

					//display the posts made by the people the logged in candidate is following
					if(followers.containsKey(post.getusername()) | post.getusername().equals(user.getusername()))
					{
						pw.println("<div class='timeline'>" +
							"<div class='timeline-feed'>" +
							"<h3 style='font-size:17px; font-weight:bold; color:#008080;'>" + post.getfirstname() + " " + post.getlastname() + "</h3>" +
							"<span style='font-size:16px; color:#00b3b3; padding-left:20px; padding-bottom:20px; text-align:justify;'>" + post.gettext() + "</span></div></div>");
					}
				}

				utility.printHtml("WritePost.html");
				pw.print("</section>");
				
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
			}
		}
		else
		{
			utility.printHtml("Header.html");
			utility.printHtml("Job.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}
	}	

	public void init()
	{
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		Saxpaser handler = new Saxpaser(TOMCAT_HOME+"\\webapps\\project\\JobCatalog.xml");
		HashMap<String, Job> hm2 = handler.getJobs();
		for(Map.Entry<String,Job> entry : hm2.entrySet())
		{
			Job job =(Job)entry.getValue();
			mySQL.insertJob(job.getId(), job.getCategory(),job.getCompanyName(),job.getJobType(),job.getJobPosition(),job.getSalary(),job.getJobDescription(),job.getExperienceLevel(),job.getState());
		}
	}	

}