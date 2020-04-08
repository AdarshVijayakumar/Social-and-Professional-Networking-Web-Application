import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class sales_bar extends HttpServlet
{
	MySqlDataStoreUtilities mySQL = new MySqlDataStoreUtilities();
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
	{
                         
		PrintWriter out= response.getWriter();
		String username = request.getParameter("username");
		HttpSession session=request.getSession();  
		ArrayList<Job> jobs;
		Users user = (Users)session.getAttribute("user");
		Utilities utility = new Utilities(request,out);
		if(session.getAttribute("user")!= null )  
		{
			jobs = mySQL.categoryWise_jobs();
			utility.printHtml("HeaderAdministrator.html");
			
			out.println("<section id=\"content\">");
			out.println("<div id=\"body\">");
			out.println("<article>");
			out.println("<h2 style='text-align:center; color:#00B3B3;'>Job Applications Category Wise</h2>");

			out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
            out.println("<script type='text/javascript'>");
    		out.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
    		out.println("google.charts.setOnLoadCallback(drawBasic);");
    		out.println("function drawBasic() {");
    		out.println("var data = google.visualization.arrayToDataTable([");
    		out.println("['Product Name', 'Total Sales'],");
			
            for(Job job : jobs)
            {
              
               String job_type = job.getJobType();
               int count = job.getCount();
               out.println("[' " +job_type+ " ', "+count+ "],");
            }

            out.println("]);");
            out.println("var options = {");
    		out.println("title: 'Product Names and Total Sales',");
    		out.println("chartArea: {width: '60%', height: 250},");
    		out.println("fontSize: 12,");
    		out.println("hAxis: {");
    		out.println("title: 'Total Sales',");
    		out.println("minValue: 0");
    		out.println("},");
    		out.println("vAxis: {");
    		out.println("title: 'Product Name'");
    		out.println("}");
    		out.println("};");
    		out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
    		out.println("chart.draw(data, options);");
    		out.println("}");
            out.println("</script>");
            out.println("<div id='chart_div' style='width:500px; height:600px; padding-top:20px;'></div>");

            out.println("</section>");
            utility.printHtml("SidebarAdmin.html");
			utility.printHtml("Footer.html");
		}
		else
		{
			utility.printHtml("Header.html");
			utility.printHtml("product.html");
			utility.printHtml("Sidebar.html");
			utility.printHtml("Footer.html");
		}

	
	}
}