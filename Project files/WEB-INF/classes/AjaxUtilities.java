import java.sql.*;
import java.io.*; 
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AjaxUtilities
{	
	Connection con = null;
	PreparedStatement ps= null;

	public boolean getConnection()
	{
		try
		{
			//Get connection to the mySQL database server
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard","root","root"); 
			if(con!=null)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} 
	}

    public HashMap<String, UserProfile> getJob()
	{
		HashMap<String, UserProfile> hm = new HashMap<String, UserProfile>();
        UserProfile job = new UserProfile();
        try 
        {
            getConnection(); 
			ps = con.prepareStatement("SELECT * FROM userprofile"); 
			ResultSet rs= ps.executeQuery();
            while (rs.next()) 
            {
                UserProfile item = new UserProfile();
                item.setusername(rs.getString("username"));
                item.setfirstname(rs.getString("firstname"));
                item.setlastname(rs.getString("lastname"));
                item.seteducation(rs.getString("education"));
                item.setsummary(rs.getString("summary"));
                item.setcompanyname(rs.getString("companyname"));
                item.setexperiencePeriod(rs.getString("experiencePeriod"));
                item.setskills(rs.getString("skills"));
                item.setcertifications(rs.getString("certifications"));
                item.setprojects(rs.getString("projects"));
                hm.put(item.getfirstname(), item);
        	}
        
        	return hm;
        }
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
    }
}
