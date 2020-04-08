import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Profile extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		HttpSession session=request.getSession(); 
		Utilities utility = new Utilities(request,out);
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = (Users)session.getAttribute("user");
		String uname = session.getAttribute("uname").toString();

		//get the profile information of the user from mySQL database
		HashMap<String, UserProfile> userprofile = mySQL.getProfile(uname);

		if(session.getAttribute("user")!= null )  
		{
			Users users  = new Users();
			users= (Users)session.getAttribute("user");
			if(user.getusertype().equals("Candidate"))
			{
				utility.printHtml("HeaderLogout.html");

				out.println("<section id=\"content\">");

				//display the profile information of the other user whose profile is selected from the search bar
				for(Map.Entry<String,UserProfile> entry : userprofile.entrySet())
		        {
		        	UserProfile u=(UserProfile)entry.getValue();
			        out.println("<div class='view-profile'>" +
			                    "<h2 style='font-size:36px; text-align:center; color:#008080;'>" + u.getfirstname() + "  " + u.getlastname() + "</h2>" +
			                    "<div style='padding-right:40px; float:left; padding-left:200px; padding-top:20px;'><a href=\"Followers\"class=\"btn btn-pri\" role=\"button\">Followers</a></div>" +
			                    "<div style=' padding-top:20px;'><a href=\"Following\"class=\"btn btn-pri\" role=\"button\">Following</a></div></div>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold; padding-top:20px;'>Summary</h3>" +
			                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + u.getsummary() + "</div>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Education</h3>" +
			                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + u.geteducation() + "</div>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Company Name</h3>" +
			                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + u.getcompanyname() + "</span>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Experience Period</h3>" +
			                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + u.getexperiencePeriod() + "  years</span>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Skills</h3>" +
			                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + u.getskills() + "</span>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Certifications</h3>" +
			                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + u.getcertifications() + "</span><br>" +
			                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Projects</h3>" +
			                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + u.getprojects() + "</span><br>");
		    	}
		    	out.println("<div style='padding-left:270px; padding-top:20px;'><a href=\"CreateProfile\" class=\"btn btn-app\" role=\"button\">Update Profile</a></div>");
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
				out.println("<h2 style=\"color:blue\"> Click Here for Login : <a href=\"Login\" color=\"red\">Login</a></h2>");             
				out.println("</section>");
				
				
				utility.printHtml("Sidebar.html");
				utility.printHtml("Footer.html");
			
		}
	}		

}