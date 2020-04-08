import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;


public class AddJobParser extends HttpServlet
{
	HashMap<String,Job> productadd = null;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();  
		PrintWriter out= response.getWriter();
		String id = request.getParameter("id");
		String companyname = request.getParameter("companyname");
		String category = request.getParameter("Category");
		String jobtype = request.getParameter("jobtype");
		String jobposition =request.getParameter("jobposition");
		String jobdescription = request.getParameter("jobdescription");
		String state = request.getParameter("state");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String experiencelevel = request.getParameter("experiencelevel");
		
		Job prod = new Job();
		prod.setId(id);
		prod.setCompanyName(companyname);
		prod.setCategory(category);
		prod.setJobType(jobtype);
		prod.setJobPosition(jobposition);
		prod.setJobDescription(jobdescription);
		prod.setState(state);
		prod.setSalary(salary);
		prod.setExperienceLevel(experiencelevel);
		
		if(session.getAttribute("JobMap")!= null)
		{	
			productadd = (HashMap<String,Job>)session.getAttribute("JobMap");
			productadd.put(companyname,prod);
			session.setAttribute("JobMap",productadd);
		}
		MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();

		//Insert jobs into mySQL database
		mydata.insertJob1(id,category,companyname,jobtype,jobposition,salary,jobdescription,state,experiencelevel);
		request.getRequestDispatcher("/JobAdd").forward(request, response);
	}
}

 
