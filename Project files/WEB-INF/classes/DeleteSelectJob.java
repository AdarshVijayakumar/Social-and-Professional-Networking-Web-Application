import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DeleteSelectJob extends HttpServlet
{
    Job jobup;
    PrintWriter out;
    HashMap<String, Job> jobdelete = null;
  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { 
    	HttpSession session=request.getSession();  
    	out= response.getWriter();
    	
        String product_id = request.getParameter("pid");
        String product_category = request.getParameter("category");
        System.out.println(product_id);
        System.out.println(product_category);
        
        Utilities utility = new Utilities(request,out);
        
    	utility.printHtml("HeaderRecruiter.html");
    	MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();
    	int hm2;

        //delete the job corresponding to the job id
    	hm2 = mydata.deletejob(product_id,product_category);
    	
    	out.println("<section id=\"content\">");
    	out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px; text-align:center;'>This job is successfully deleted</h2>"); 
    	out.println("</section>"); 
    	utility.printHtml("SidebarManager.html");
    	utility.printHtml("Footer.html");
    }
}
