import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FullApply extends HttpServlet 
{
	Utilities utility;
	PrintWriter out;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		out= response.getWriter();
		HttpSession session=request.getSession();  
		Utilities utility = new Utilities(request,out);

		String companyname = request.getParameter("companyname");
		String salary = request.getParameter("salary");
		String category = request.getParameter("category");
		String jobtype = request.getParameter("jobtype");
		String jobposition = request.getParameter("jobposition");
		String state = request.getParameter("state");
		
		Users user =(Users)session.getAttribute("user");

		utility.printHtml("HeaderLogout.html");
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
		//display the form to enter the candidate details to apply for a job
		out.println("<div class='fully-apply-form'>");
		out.println("<h3 style='text-align:center; padding-bottom:20px;'>Application Form</h3>");
		out.println("<form action =\"SubmitFullApplication\"  method=\"get\">");
		out.println("<div class=\"container username\">");
		out.println("<input type=\"hidden\"  value= "+ companyname +" name=\"companyname\" >");
		out.println("<input type=\"hidden\"  value= "+ salary +" name=\"salary\" >");
		out.println("<input type=\"hidden\"  value= "+ category +" name=\"category\" >");
		out.println("<input type=\"hidden\"  value= "+ jobtype +" name=\"jobtype\" >");
		out.println("<input type=\"hidden\"  value= "+ jobposition +" name=\"jobposition\" >");
		out.println("<input type=\"hidden\"  value= "+ state +" name=\"state\" >");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Full Name\" name=\"fullname\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Email\" name=\"email\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Gender\" name=\"gender\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Occupation\" name=\"occupation\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Age\" name=\"age\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<textarea rows=\"4\" cols=\"20\" class=\"form-control\" name =\"address\" placeholder =\"Address\" required>");
		out.println("</textarea>");
		out.println("</div>");
	
		out.println("<div class=\"form-group\">");
		out.println("<textarea rows=\"4\" cols=\"20\" class=\"form-control\" name =\"experience\" placeholder=\"Experience\" required>");
		out.println("</textarea>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<textarea rows=\"4\" cols=\"20\" class=\"form-control\" name =\"education\" placeholder=\"Education\" required>");
		out.println("</textarea>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<textarea rows=\"4\" cols=\"20\" class=\"form-control\" name =\"projects\" placeholder=\"Projects and Skills\" required>");
		out.println("</textarea>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\" style=\"padding-left:260px; padding-top:20px;\"> ");
		out.println("<a class=\"lbutton\"><button type=\"submit\" class=\"btn btn-app\">Submit Application</button></a>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form></div>");

		out.println("</section>");
		utility.printHtml("Sidebar.html");
		utility.printHtml("Footer.html");	
	}
}