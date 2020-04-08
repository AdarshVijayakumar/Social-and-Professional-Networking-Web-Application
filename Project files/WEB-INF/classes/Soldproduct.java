import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Soldproduct extends HttpServlet
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Utilities util= new Utilities(request, out);
		LinkedHashMap<String,List<String>> hm2 = new LinkedHashMap();
		MySqlDataStoreUtilities mydata = new MySqlDataStoreUtilities();
		hm2= mydata.SoldProduct();
		util.printHtml("HeaderLogout.html");
		out.println("<section id=\"content\">");
		out.println("<h2 align=\"center\" margin-top=\"60px\">Top five most job applied!</h2>");
		out.println("<table cellspacing=\"0\" class=\"shopping-cart\"  style=\"Height:50px\"> ");
		out.println("<thead>");
		out.println("<tr class=\"headings\">");
		out.println("<th class=\"\">Product Id</td>");
		out.println("<th class=\"\">Product Name</td>");
		out.println("<th class=\"\"># Count</td>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		Set objKeys = hm2.keySet();
		System.out.println("objectd keyset: "+objKeys);
		for(Iterator j = objKeys.iterator(); j.hasNext();)
		{
			String objKey = (String) j.next();
			List<String> item = (List<String>) hm2.get(objKey);
			out.println("<tr>"); 
			out.println("<td class=\"productName\">");
			out.println(objKey);
			out.println("</td>");
			out.println("<td class=\"productName\">");
			out.println(item.get(0));
			out.println("</td>");
			out.println("<td class=\"productName\">");
			out.println(item.get(1));
			out.println("</td>");
			out.println("</tr>");
							
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</section>");
		util.printHtml("Sidebar.html");
		util.printHtml("Footer.html");

	}
}