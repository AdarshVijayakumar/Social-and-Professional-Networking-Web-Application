import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AjaxAutoCompleteServlet extends HttpServlet
{
	AjaxUtilities au = new AjaxUtilities();
	String searchKeyword;
	String action;
	HashMap<String, UserProfile> jobs ;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StringBuffer sb = new StringBuffer();
		boolean prod  = false;
		PrintWriter out= response.getWriter();
		Utilities utility = new Utilities(request,out);
		jobs = au.getJob();
		
		action = request.getParameter("action");
		searchKeyword = request.getParameter("id");	  
		if(searchKeyword!= "" || !searchKeyword.equals(""))
		{
			if(action.equals("complete"))
			{
				searchKeyword = searchKeyword.trim().toLowerCase();  
				for(Map.Entry<String,UserProfile> entry : jobs.entrySet())
				{
					UserProfile job=(UserProfile)entry.getValue();

					//Search for the user that starts with the name entered in the search bar
					if(job.getfirstname().toLowerCase().startsWith(searchKeyword))
					{
						sb.append("<product>");
						sb.append("<id>" + job.getusername() + "</id>");
						sb.append("<name>" + job.getfirstname() + "</name>");
						sb.append("</product>");
						prod = true;
					}
				}	
				if(prod)
				{
					response.setContentType("text/xml");
					response.setHeader("Cache-Control","no-cache");
					out.write("<products>" + sb.toString() +"</products>");
				}
				else
				{
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			}
			if(action.equals("lookup"))
			{
				request.setAttribute("Job_obj",jobs.get(searchKeyword));
				request.getRequestDispatcher("/ViewProfile").forward(request, response);
			}
		}
	}
}