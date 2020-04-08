import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class ViewReview extends HttpServlet
{

  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

    MongoDbDataStoreUtility mongoDB = new MongoDbDataStoreUtility();
    String RRating = "";

    String username = session.getAttribute("uname").toString();
    String id = request.getParameter("id");
    HashMap<String, ArrayList<Review>> hm =mongoDB.getReviews();
    System.out.println(hm);
		
    utility.printHtml("HeaderLogout.html");	
    out.print("<section id=\"content\" style='height:500px;'>");
    out.print("<h3 style='text-align:center; color:#008080'>Reviews</h3>");

    if(hm==null)
    {
        out.println("<h2 style='color:#00b3b3; font-size:22px; text-align:center; padding-top:30px;'>Mongo DB server is not up and running</h2>");
    }
    else
    {   
        if(!hm.containsKey(id)){
            out.println("<h2 style='color:#00b3b3; font-size:22px; text-align:center; padding-top:30px;'>There are no reviews for this company</h2>");
        }   
        else
        {
            int count = 0;
            //display the review information
            for (Review r : hm.get(id)) 
            {   
                String temp="";
                String space = "";
                if(count == 0){
                    out.print("<p style='color:#008080; padding-top:20px; font-size:16px; margin-bottom:3px; line-height:0.5em;'><strong>Company Name:</strong>"+"&nbsp&nbsp&nbsp&nbsp&nbsp<strong>"+r.getcompanyname()+"</strong></p>");
                    out.print("<p style='color:#008080; padding-top:20px; font-size:16px; margin-bottom:3px; line-height:0.5em;'><strong>Job Category:</strong>"+"&nbsp&nbsp&nbsp&nbsp&nbsp<strong>"+r.getcategory()+"</strong></p>");
                    out.print("<p style='color:#008080; padding-top:20px; font-size:16px; margin-bottom:3px; line-height:0.5em;'><strong>Job Position:</strong>"+"&nbsp&nbsp&nbsp&nbsp&nbsp<strong>"+r.getjobposition()+"</strong></p>");
                    out.print("<p style='color:#008080; padding-top:20px; font-size:16px; margin-bottom:3px; line-height:0.5em;'><strong>State:</strong>"+"&nbsp&nbsp&nbsp&nbsp&nbsp<strong>"+r.getstate()+"</strong></p><br><br>");
                    count++;
                }

                RRating = r.getrating().toString();
                for(int i=0; i<Integer.parseInt(RRating); i++){
                    temp += "<span>&#9733;</span>";
                }

                out.print("<strong><p style='color:#008080; font-size:18px; margin-bottom:3px; line-height:1.3em;'>"+r.getfullname()+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+temp+"</p></strong>");
                out.print("<p style='color:#008080; font-size:12px; margin-bottom:3px; line-height:1.8em;'>");
                out.print(r.getoccupation()+"&nbsp&nbsp&nbsp&nbsp&nbsp");
                out.print(r.getage()+"&nbsp&nbsp&nbsp&nbsp&nbsp");
                out.print("</p>");

                out.print("<p style='color:#008080; font-size:16px; margin-bottom:3px; line-height:1.3em;'>"+r.getreviewtext()+"</p>");

                out.print("<br>");
            }                   
                    
        }
    }
    

    out.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }
}
