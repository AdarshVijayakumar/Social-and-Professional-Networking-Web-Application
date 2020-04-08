import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class QuickApply extends HttpServlet
{
  Utilities utility;
  PrintWriter out;
  java.util.List<AddJob> arraylist;
  
  public QuickApply() {}
  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
		out= response.getWriter();
    Utilities utility = new Utilities(request,out);
    HttpSession session=request.getSession();

		arraylist = (ArrayList<AddJob>)session.getAttribute("cartitem");  
		String check = request.getParameter("input");
    
    if (arraylist != null)
    {
      utility.printHtml("HeaderLogout.html");
      
      out.println("<section id=\"content\">");
      out.println("<table cellspacing=\"0\" class=\"shopping-cart\">");
      out.println("<thead>");
      out.println("<tr class=\"headings\">");
      out.println("<th class=\"link\">&nbsp;</th>");
      out.println("<th class=\"link\">&nbsp;</th>   ");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");
      out.println("<tr>");
      int j = 0;
      for (AddJob job : arraylist)
      {
        out.println("<article>");
        out.println("<ul>");
        out.println("<li class=\"desc\">Company Name : " + job.getCompanyName() + "</li>");
        out.println("<li>Salary: $" + job.getSalary() + "</li>");
        out.println("<li>Category:" + job.getCategory() + "</li>");
        out.println("<li>JobType:" + job.getJobType() + "</li>");
        out.println("<li>JobPosition:" + job.getJobPosition() + "</li>");
        out.println("<li>State:" + job.getState() + "</li>");
        out.println("</ul>");
        out.println("</article>");
      }
      out.println("<form action=\"SubmitQuickApplication\" method=\"get\">");
      out.println("<div class=\"container username\">");
      out.println("<div class=\"username1\">");
      out.println("<a class=\"btn btn-pri\"  ><button type=\"submit\">Submit</button></a>");
      out.println("</div>");
      out.println("</div>");
      out.println("</form>");
      out.println("</tr>");
      out.println("</tbody>");
      out.println("</table>");
      out.println("</section>");
      utility.printHtml("Sidebar.html");
	    utility.printHtml("Footer.html");
    }
    else
    {
			utility.printHtml("HeaderLogout.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
    }
  }
}
