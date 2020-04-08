import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Happy extends HttpServlet
{
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
PrintWriter pw= response.getWriter();
String username = request.getParameter("username");
String password = request.getParameter("password");
Utilities utility = new Utilities(request,pw);
utility.printHtml("Header.html");
utility.printHtml("Happy.html");
utility.printHtml("Sidebar.html");

utility.printHtml("Footer.html");
}		

}