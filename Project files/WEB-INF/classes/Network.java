import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class Network extends HttpServlet
{

  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

		
    utility.printHtml("HeaderLogout.html");	
    out.print("<section id=\"content\">");

    out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>View the Twitter network graph between two people</h2>");
    out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>Enter the Twitter Handles of the two users</h2>");

    //read the names of the two users whose network graph is to be displayed
    out.println("<form action =\"Friends\"  method=\"get\"  class=\"my-form\">");
    out.println("<div class=\"container jobs\">");

    out.println("<div class=\"job-group\" style=\"padding-top: 20px; padding-left:40px; width:250px;\">");
    out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"Person1 Name\" name=\"name1\" style=\"width:200px; float:left;\" required>");
    out.println("</div>");

    out.println("<div class=\"job-group\" style=\"padding-left:260px;\">");
    out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"Person2 Name\" name=\"name2\" style=\"width:200px; float:left;\" required>");
    out.println("</div>");

    out.println("<div class=\"job-group\" style=\"padding-left:30px;\">");
    out.println("<button class=\"btn btn-primary\" type=\"submit\" value=\"login\" id=\"submitButton\" style=\"width:100px; margin:0 30px 0;\">Go</button>");
    out.println("</div></div></form>");

    out.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }
}
