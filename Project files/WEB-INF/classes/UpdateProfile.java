import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class UpdateProfile extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	}


  	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{      
		PrintWriter out= response.getWriter();
		MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

		HttpSession session=request.getSession(); 

		String username = session.getAttribute("uname").toString();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String education = request.getParameter("education");
		String summary = request.getParameter("summary");
		String companyname=request.getParameter("companyname");
		String experiencePeriod = request.getParameter("experiencePeriod");
		String skills = request.getParameter("skills");
		String certifications = request.getParameter("certifications");
		String projects = request.getParameter("projects");

		boolean checkuserprofile = mySQL.CheckUserProfile(username);

		if(checkuserprofile)
		{
			//if user profile already exists then update the existing profile
			mySQL.UpdateUserProfile(username,firstname,lastname,education,summary,companyname,experiencePeriod,skills,certifications,projects);
			response.sendRedirect("Profile"); 
			return;
		}
		else
		{
			//if the user profile does not exist(creating the profile for the first time) then store the profile information to mySQL database
			mySQL.AddUserProfile(username,firstname,lastname,education,summary,companyname,experiencePeriod,skills,certifications,projects);
			response.sendRedirect("Profile"); 
			return;
		}
	}
}