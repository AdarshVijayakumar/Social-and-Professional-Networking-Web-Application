import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReviewForm extends HttpServlet 
{
	Utilities utility;
	PrintWriter out;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		out= response.getWriter();
		HttpSession session=request.getSession();  
		Utilities utility = new Utilities(request,out);

		String id = request.getParameter("id");
		String companyname = request.getParameter("companyname");
		String category = request.getParameter("category");
		String jobposition = request.getParameter("jobposition");
		String state = request.getParameter("state");
		
		Users user =(Users)session.getAttribute("user");

		utility.printHtml("HeaderLogout.html");
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		
		//display the review form to enter the review information
		out.println("<div class='fully-apply-form'>");
		out.println("<h3 style='text-align:center; padding-bottom:20px;'>Review Form</h3>");
		out.println("<form action =\"WriteReview\"  method=\"get\">");
		out.println("<div class=\"container username\">");
		out.println("<input type=\"hidden\"  value= "+ companyname +" name=\"companyname\" >");
		out.println("<input type=\"hidden\"  value= "+ id +" name=\"id\" >");
		out.println("<input type=\"hidden\"  value= "+ category +" name=\"category\" >");
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
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Occupation\" name=\"occupation\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Age\" name=\"age\" required>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<select class=\"form-control\" id=\"exampleFormControlSelect1\" name = \"rating\" style=\"height:45px; width:500px;\">");
		out.println("<option value>Rating</option>");
		out.println("<option>1</option>");
		out.println("<option>2</option>");
		out.println("<option>3</option>");
		out.println("<option>4</option>");
		out.println("<option>5</option>");
		out.println("</select>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\">");
		out.println("<textarea rows=\"4\" cols=\"20\" class=\"form-control\" name =\"reviewtext\" placeholder=\"Review Text\" required>");
		out.println("</textarea>");
		out.println("</div>");
		
		out.println("<div class=\"form-group\" style=\"padding-left:260px; padding-top:20px;\"> ");
		out.println("<a class=\"lbutton\"><button type=\"submit\" class=\"btn btn-app\">Submit Review</button></a>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form></div>");

		out.println("</section>");
		utility.printHtml("Sidebar.html");
		utility.printHtml("Footer.html");	
	}
}