import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class SubmitFullApplication extends HttpServlet
{
	Utilities utility;
	PrintWriter out;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		out= response.getWriter();
		HttpSession session=request.getSession();
		MongoDbDataStoreUtility mongoDB = new MongoDbDataStoreUtility();

		//read all the details entered by the candidate while applying for a job
		String username = session.getAttribute("uname").toString();
		String companyname = request.getParameter("companyname");
		String salary = request.getParameter("salary");
		String category = request.getParameter("category");
		String jobtype = request.getParameter("jobtype");
		String jobposition = request.getParameter("jobposition");
		String state = request.getParameter("state");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String experience = request.getParameter("experience");
		String education = request.getParameter("education");
		String projects = request.getParameter("projects");
		String manufacture= request.getParameter("manufacture");

		//store the details in mySQL database
		mongoDB.FullApply(username,companyname, salary, category, jobtype, jobposition, state, fullname, email, gender, occupation, age, address, experience, education, projects);
		request.getRequestDispatcher("/View?companyname="+companyname).forward(request, response);	
	}
}