import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class Timeline extends HttpServlet
{

  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

    Users user = (Users)session.getAttribute("user");
    if(session.getAttribute("user")!= null)  
    {		
        utility.printHtml("HeaderLogout.html");	
        out.print("<section id=\"content\">");

        out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>Search for trending tweets on Twitter based on Screen name or of any keyword</h2>");
        
        //display the text field to read the screenname or the query string regarding which the user wants to get the tweets about from Twitter
        out.println("<form action =\"TrendingTwitterTweets\"  method=\"get\"  class=\"my-form\">");
        out.println("<div class=\"container jobs\">");

        out.println("<div class=\"job-group\" style=\"padding-top: 20px; padding-left:40px; width:250px;\">");
        out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"Screen Name\" name=\"screenname\" style=\"width:200px; float:left;\">");
        out.println("</div>");

        out.println("<div class=\"job-group\" style=\"padding-left:260px;\">");
        out.println("<input type=\"text\" id=\"searchjob\" class=\"form-control\" placeholder=\"Keyword\" name=\"querystring\" style=\"width:200px; float:left;\">");
        out.println("</div>");

        out.println("<div class=\"job-group\" style=\"padding-left:30px;\">");
        out.println("<button class=\"btn btn-primary\" type=\"submit\" value=\"login\" id=\"submitButton\" style=\"width:100px; margin:0 30px 0;\">Go</button>");
        out.println("</div></div></form>");

        out.println("</section>");
        utility.printHtml("Sidebar.html");
        utility.printHtml("Footer.html");
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
