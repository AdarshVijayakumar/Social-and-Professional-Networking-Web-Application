import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class WriteReview extends HttpServlet
{
	Utilities utility;
	PrintWriter out;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		out= response.getWriter();
		HttpSession session=request.getSession();
		MongoDbDataStoreUtility mongoDB = new MongoDbDataStoreUtility();

		String username = session.getAttribute("uname").toString();
		String companyname = request.getParameter("companyname");
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String jobposition = request.getParameter("jobposition");
		String state = request.getParameter("state");

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String occupation = request.getParameter("occupation");
		String age = request.getParameter("age");
		String rating = request.getParameter("rating");
		String reviewtext = request.getParameter("reviewtext");

		//write the review information into mongoDB
		mongoDB.WriteReview(username,companyname,id,category,jobposition,state,fullname,email,occupation,age,rating,reviewtext);
		request.getRequestDispatcher("/ReviewForm").forward(request, response);	
	}
}