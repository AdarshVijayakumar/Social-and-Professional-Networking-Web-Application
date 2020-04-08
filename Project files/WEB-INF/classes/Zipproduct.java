import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Zipproduct extends javax.servlet.http.HttpServlet
{
  public Zipproduct() {}
  
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
   response.setContentType("text/html");
	PrintWriter out = response.getWriter();
    Utilities utility = new Utilities(request, out);
    LinkedHashMap<String,String> hm2;
	MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();
	hm2= mydata.ZipCode();
    utility.printHtml("HeaderLogout.html");
    out.println("<section id=\"content\">");
    out.println("<h2 align=\"center\" margin-top=\"60px\">Top Five States Where  Maximum Number of Job Applied</h2>");
    out.println("<table cellspacing=\"0\" class=\"shopping-cart\"  style=\"Height:50px\"> ");
    out.println("<thead>");
    out.println("<tr class=\"headings\">");
    out.println("<th class=\"\">State</td>");
    out.println("<th class=\"\">Count</td>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
    Set objKeys = hm2.keySet();
    
    for (Iterator j = objKeys.iterator(); j.hasNext();)
    {
     String objKey = (String) j.next();
     String item = (String) hm2.get(objKey);
      out.println("<tr>");
      out.println("<td class=\"productName\">");
      out.println(objKey);
      out.println("</td>");
      out.println("<td class=\"productName\">");
      out.println(item);
      out.println("</tr>");
    }
    
    out.println("</tbody>");
    out.println("</table>");
    out.println("</section>");
    utility.printHtml("Sidebar.html");
    
    utility.printHtml("Footer.html");
  }
}
