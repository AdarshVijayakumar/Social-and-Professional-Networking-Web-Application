import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class Friends extends HttpServlet
{

  
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

    String TOMCAT_HOME = System.getProperty("catalina.home");
    // String path = TOMCAT_HOME+"\\webapps\\project\\TwitterFollowingList.py";

    String name1 = request.getParameter("name1");
    String name2 = request.getParameter("name2");

    //make a call to python file to get the list of people the user is following 
    ProcessBuilder pb = new ProcessBuilder("python", "\"" + TOMCAT_HOME + "\"" + "\\webapps\\project\\TwitterFollowingList.py",""+name1,""+name2);
    java.lang.Process process = pb.start();

    try
    {
        Thread.sleep(2500);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }

    
    utility.printHtml("HeaderLogout.html");	
    out.print("<section id=\"content\" style='height:500px;'>");
    out.print("<h3 style='text-align:center; color:#00B3B3;'>Connections</h3>");
    out.print("<script type='text/javascript'>");
    

    out.print("var data = {"+
              "nodes: [");
    //get the list of users who the entered user is following
    BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\FollowingList1.txt")));
    String name=reader.readLine();
    while(name!=null){
      out.print("{id: " + "\"" + name + "\"" + "},");
      name = reader.readLine();
    }
    reader.close();

    //get the list of users who the entered user is following
    BufferedReader reader1 = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\FollowingList2.txt")));
    name=reader1.readLine();
    while(name!=null){
      out.print("{id: " + "\"" + name + "\"" + "},");
      name = reader1.readLine();
    }
    out.print("{id: " + "\"" + name1 + "\"" + "}, {id: " + "\"" + name2 + "\"" + "}], edges:[");
    reader1.close();

    BufferedReader reader2 = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\FollowingList1.txt")));
    name=reader2.readLine();
    while(name!=null){
      out.print("{from: " + "\"" + name1 + "\"" + ", to: " + "\"" + name + "\"" + "},");
      name = reader2.readLine();
    }
    reader2.close();

    BufferedReader reader3 = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\FollowingList2.txt")));
    name=reader3.readLine();
    while(name!=null){
      out.print("{from: " + "\"" + name2 + "\"" + ", to: " + "\"" + name + "\"" + "},");
      name = reader3.readLine();
    }
    reader3.close();
    out.print("]};");
      
    out.print("var chart = anychart.graph(data);");
    out.print("chart.title(\"Network Graph\");");
    out.print("chart.container(\"content\");");
    out.print("chart.draw();");
    out.print("</script>");

    out.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }
}
