import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Acknowledge extends javax.servlet.http.HttpServlet
{
  Utilities utility;
  PrintWriter pw;
  //List<Cart> arraylist;
  
  public Acknowledge() {}
  
  	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
    pw = response.getWriter();
    Utilities utility = new Utilities(request,pw);
    

    utility.printHtml("HeaderLogout.html");
    
    pw.println("<section id=\"content\">");

    //Notifying the candidate that the job has been successfully applied.
    pw.println("<h2 style='color:black'> Your job application  is  successfully completed. </h2>");
    
    pw.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }
}
