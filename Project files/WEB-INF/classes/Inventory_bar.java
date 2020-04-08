import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Inventory_bar extends HttpServlet
	{
		MySqlDataStoreUtilities ms = new MySqlDataStoreUtilities();
	public void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException
	{
		PrintWriter out= response.getWriter();
		String username = request.getParameter("username");
		HttpSession session=request.getSession();  
		Users user = (Users)session.getAttribute("user");
		Utilities utility = new Utilities(request,out);
		
		if(session.getAttribute("user")!= null )  
		{
			
			utility.printHtml("HeaderRecruiter.html");
			out.println("<div id=\"body\">");
			out.println("<section id=\"content\">");
			out.println("<article>");
			out.println("<div id=\"piechart\" style=\"width: 6500px; height: 1700px;\" ></div>" );
			out.println("</article>");
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