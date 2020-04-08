import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class SubmitQuickApplication extends HttpServlet {
	Utilities utility;
	PrintWriter out;
	List<AddJob> arraylist;
  
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{   
    out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request, out);   

    MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();  

    String username = session.getAttribute("uname").toString();
    
    RecommenderUtility prodRecUtility = new RecommenderUtility();
    HashMap<String,String> prodRecmMap = new HashMap<String,String>();
    prodRecmMap = prodRecUtility.readOutputFile();

    //read the job information
    String companyname = request.getParameter("companyname");
    String jobtype = request.getParameter("jobtype");
    String state = request.getParameter("state");
    String jobposition = request.getParameter("jobposition");
    String salary = request.getParameter("salary");
    String category = request.getParameter("category");
    String jobid = request.getParameter("id");
            
    if (session.getAttribute("user") != null)
    {
      utility.printHtml("HeaderLogout.html");
      Users user =(Users)session.getAttribute("user");
      ArrayList<AddJob> list =(ArrayList<AddJob>)session.getAttribute("cartitem");

      Random rand = new Random();
      int  n = rand.nextInt(1000) + 1;
    	java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

      
      String ran = "OA2038" + n;
      mySQL.insertJobOrder(ran,sqlDate,companyname,username,salary,category,jobid,jobtype,jobposition,state);
      

      out.println("<section id=\"content\">");
      out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; padding-top:30px;'> Your job application is successfully submitted to " + companyname + "</h2>");
      

      for(String candidate: prodRecmMap.keySet())
    {
        if(candidate.equals(username))
        {
            String products = prodRecmMap.get(candidate);
            products=products.replace("[","");
            products=products.replace("]","");
            products=products.replace("\"", " ");
            ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));

            out.println("<h2 style='color:#00b3b3; font-size:22px; padding-left:20px; margin-bottom:30px; padding-top:70px;'>Recommending similar jobs for you</h2>");

            for(String prod : productsList)
            {
                prod= prod.replace("'", "");
                prod = prod.replace(" ", "");
                Job prodObj = new Job();
                prodObj = prodRecUtility.getProduct(prod);

                out.println("<div class='job-listing'>" +
                "<div class='job-title'>" +
                "<h3 style='font-size:24px; font-weight:bold; color:#008080;'>" + prodObj.getCompanyName() + "</h3>" +
                "<span style='font-size:18px; color:#00b3b3; padding-left:20px;'>" + prodObj.getJobPosition() + "</span>" +
                "<div class='job-loc'>" + prodObj.getState() + "</div></div>" +
                "<div class='job-button'>" +
                "<a href=\"ViewJob?id="+prodObj.getId()+"&&category=" +prodObj.getCategory()+"\"class=\"btn btn-primary\" role=\"button\">View Job</a></div></div>");

            }
        }
    }
      out.println("</section>");
      
      utility.printHtml("Sidebar.html");
      utility.printHtml("Footer.html");
    }
    else
    {
      out.println("Not e to Order Anything");
    }
  }
}
