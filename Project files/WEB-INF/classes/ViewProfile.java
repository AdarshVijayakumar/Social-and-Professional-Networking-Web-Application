import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class ViewProfile extends HttpServlet
{
  HttpSession session;
  public PrintWriter out;
	Utilities utility;
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	HttpSession session=request.getSession();  
	PrintWriter out= response.getWriter();
    utility = new Utilities(request,out);

    String follower = session.getAttribute("uname").toString();
		
    UserProfile job = (UserProfile)request.getAttribute("Job_obj");

    MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();

    HashMap<String, Users> or = mySQL.CheckFollowers(follower,job.getusername());

    if (session.getAttribute("user")!= null)
    {
      utility.printHtml("HeaderLogout.html");
    }
    else
    {
      utility.printHtml("Header.html");
    }


    if(or.toString()=="{}")
    {
        out.println("<section id=\"content\">");
        
        out.println("<div class='view-profile'>" +
                    "<h2 style='font-size:36px; text-align:center; color:#008080;'>" + job.getfirstname() + "  " + job.getlastname() + "</h2>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Summary</h3>" +
                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + job.getsummary() + "</div>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Education</h3>" +
                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + job.geteducation() + "</div>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Company Name</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getcompanyname() + "</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Experience Period</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getexperiencePeriod() + "  years</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Skills</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getskills() + "</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Certifications</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getcertifications() + "</span><br>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Projects</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getprojects() + "</span><br>" +
                    "<div style='padding-left:270px; padding-top:20px'><a href=\"Follow?username=" + job.getusername() + "&&lastname="+ job.getlastname() + "&&firstname="+ job.getfirstname() +  "\" class=\"btn btn-pri\" role=\"button\">Follow</a></div></div>");
        out.println("</section>");
    }
    else
    {
        out.println("<section id=\"content\">");

        out.println("<div class='view-profile'>" +
                    "<h2 style='font-size:36px; text-align:center; color:#008080;'>" + job.getfirstname() + "  " + job.getlastname() + "</h2>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Summary</h3>" +
                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + job.getsummary() + "</div>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Education</h3>" +
                    "<div style='font-size:18px; padding-left:50px; color:#00b3b3; width:620px;'>&nbsp&nbsp&nbsp&nbsp" + job.geteducation() + "</div>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Company Name</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getcompanyname() + "</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Experience Period</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getexperiencePeriod() + "  years</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Skills</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getskills() + "</span>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Certifications</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getcertifications() + "</span><br>" +
                    "<h3 style='font-size:20px; padding-left:20px; color:#008080; font-weight:bold;'>Projects</h3>" +
                    "<span style='font-size:18px; padding-left:30px; color:#00b3b3;'>&nbsp&nbsp&nbsp&nbsp" + job.getprojects() + "</span><br>" +
                    "<div style='padding-left:270px; padding-top:20px'><a href=\"Unfollow?username=" + job.getusername() + "&&firstname="+ job.getfirstname() + "\" class=\"btn btn-pri\" role=\"button\">UnFollow</a></div></div>");

        out.println("</section>");
    }

    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }

}
