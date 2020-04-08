import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddApply extends HttpServlet
{

    PrintWriter out;
  
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	  {
      out = response.getWriter();
      HttpSession session=request.getSession();

      String username = session.getAttribute("uname").toString();
      String jobid = request.getParameter("id");
      String companyname = request.getParameter("companyname");
      String salary = request.getParameter("salary");
      String jobtype = request.getParameter("jobtype");
      String category = request.getParameter("category");
      String state = request.getParameter("state");
      String jobposition = request.getParameter("jobposition");
    
      addJob(companyname,username,jobid,salary,jobtype,category,state,jobposition,request);
      request.getRequestDispatcher("/QuickApply").forward(request, response);
}
  
public void addJob(String companyname,String username,String jobid,String salary,String jobtype,String category,String state,String jobposition,HttpServletRequest request)
{
    HttpSession session = request.getSession();

    int id=0;       
    List<AddJob> list;
    list = new ArrayList<AddJob>();

    AddJob job = new AddJob(companyname,username,salary,category,jobid,jobtype,jobposition,state);
    list.add(job);
    session.setAttribute("cartitem", list);
  }
}
