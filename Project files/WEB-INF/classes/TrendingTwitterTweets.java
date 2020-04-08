import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

public class TrendingTwitterTweets extends HttpServlet
{

  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
    PrintWriter out = response.getWriter();
    HttpSession session=request.getSession();
    Utilities utility = new Utilities(request,out);

    String TOMCAT_HOME = System.getProperty("catalina.home");

    String screenname = request.getParameter("screenname");
    String querystring = request.getParameter("querystring");

    utility.printHtml("HeaderLogout.html"); 
    out.print("<section id=\"content\">");
    out.print("<h3 style='text-align:center; color:#00B3B3; margin-top:30px; margin-bottom:30px; font-size:34px; font-weight:bold;'>Trending Tweets on Twitter</h3>");

    if(querystring == "")
    {
        //exectue the python file to get the tweets by passing the screenname or the querystring as parameter
        ProcessBuilder pb = new ProcessBuilder("python", "\"" + TOMCAT_HOME + "\"" + "\\webapps\\project\\TrendingTweets.py",""+screenname,""+"ScreenName");
        java.lang.Process process = pb.start();

        //wait for the completion of python code execution
        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        //read the tweets from the text file and display
        BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\TweetsByScreenName.txt")));
        String line=reader.readLine();
        while(line!=null)
        {
          out.print("<p style='padding-left:20px; color:#00B3B3; font-size:16px;'>" + line + "</p></br>");
          line = reader.readLine();
        }
        reader.close();
    }
    else if(screenname == "")
    {
        ProcessBuilder pb = new ProcessBuilder("python", "\"" + TOMCAT_HOME + "\"" + "\\webapps\\project\\TrendingTweets.py",""+querystring,""+"QueryString");
        java.lang.Process process = pb.start();

        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME + "\\webapps\\project\\Tweets.txt")));
        String line=reader.readLine();
        while(line!=null)
        {
          out.print("<p style='padding-left:20px; color:#00B3B3; font-size:16px;'>" + line + "</p></br>");
          line = reader.readLine();
        }
        reader.close();
    }

    out.println("</section>");
    utility.printHtml("Sidebar.html");
    utility.printHtml("Footer.html");
  }
}
