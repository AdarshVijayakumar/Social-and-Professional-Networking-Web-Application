import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class CancalApplication extends HttpServlet
 {

  
public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
{
    PrintWriter pw = response.getWriter();
    HttpSession session=request.getSession(false);

    MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

    String username = request.getParameter("username");
    String OrderId = request.getParameter("id");
    String companyname = request.getParameter("companyname");

    Utilities utility = new Utilities(request,pw);
	utility.printHtml("HeaderLogout.html");
    

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0L);
    

    pw.println("<section id=\"content\">");

    //Cancel the job application that the user wants to withdraw 
	mySQL.CancelApplication(OrderId);
    pw.println("<br>");
    pw.println("<br>");
    pw.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px;'> Your job application is successfully withdrawn from " + companyname + "</h2>");

    
    pw.println("</section>");
 	utility.printHtml("Sidebar.html");	
	utility.printHtml("Footer.html");
  }
}
