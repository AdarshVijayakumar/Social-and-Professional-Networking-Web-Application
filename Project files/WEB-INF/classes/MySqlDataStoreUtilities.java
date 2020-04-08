import java.sql.*;
import java.io.*; 
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MySqlDataStoreUtilities
{
	Connection con = null;
	PreparedStatement ps= null;
	public MySqlDataStoreUtilities() {}

	public boolean getConnection()
	{
		try
		{
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


public void insert(String username, String password, String firstname, String lastname, String usertype,String gender)
{
	try
	{
		getConnection();
		String insertQuery = "INSERT INTO registration(username,password,firstname,lastname,usertype,gender)"  
		+ "VALUES(?,?,?,?,?,?)";
		ps = con.prepareStatement(insertQuery); 
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, firstname);
		ps.setString(4, lastname);
		ps.setString(5, usertype);
		ps.setString(6, gender);
		ps.execute();
		con.close();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	} 
}


public boolean CheckRegistration(String username)
{
	boolean st =false;
	try 
	{
		getConnection();
		String checkRegistration = "SELECT * FROM registration WHERE username =?";
		ps = con.prepareStatement(checkRegistration); 
		ps.setString(1, username);
		ResultSet rs= ps.executeQuery();
		st = rs.next();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	return st;

}

 
public HashMap<String,Users> selectUser()
{
	HashMap<String,Users> hm=new HashMap<String,Users>();
	try 
	{
		getConnection();
		String selectUserQuery = "SELECT * FROM registration";
		ps = con.prepareStatement(selectUserQuery);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{	
			Users user = new Users(rs.getString("username"),rs.getString("password"),rs.getString("usertype"),rs.getString("firstname"),rs.getString("lastname"));
			hm.put(rs.getString("username"), user);
		}
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	return hm;
}


public boolean CheckUserProfile(String username)
{
	boolean st =false;
	try 
	{
		getConnection();
		String checkUserProfile = "SELECT * FROM userprofile WHERE username=?";
		ps = con.prepareStatement(checkUserProfile); 
		ps.setString(1, username);
		ResultSet rs= ps.executeQuery();
		st = rs.next();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	return st;
}


public void AddUserProfile(String username,String firstname, String lastname, String education,String summary,String companyname,String experiencePeriod,
	String skills,String certifications,String projects)
{
	try
	{
		getConnection();
		String userProfileQuery = "INSERT INTO userprofile(username,firstname,lastname,education,summary,companyname,experiencePeriod,skills,certifications,projects)"
		+ "VALUES(?,?,?,?,?,?,?,?,?,?)";

		ps = con.prepareStatement(userProfileQuery); 
		ps.setString(1, username);
		ps.setString(2, firstname);
		ps.setString(3, lastname);
		ps.setString(4, education);
		ps.setString(5, summary);
		ps.setString(6, companyname);
		ps.setString(7, experiencePeriod);
		ps.setString(8, skills);
		ps.setString(9, certifications);
		ps.setString(10, projects);	
		ps.execute();
		con.close();
		System.out.println("DONE");
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void UpdateUserProfile(String username,String firstname, String lastname, String education,String summary,String companyname,String experiencePeriod,
	String skills,String certifications,String projects)
{
	try
	{
		getConnection();
		String userProfileQuery = "UPDATE userprofile SET firstname=?,lastname=?,education=?,summary=?,companyname=?,experiencePeriod=?,skills=?,certifications=?,projects=? WHERE username=?;";
		ps = con.prepareStatement(userProfileQuery); 
		
		ps.setString(1, firstname);
		ps.setString(2, lastname);
		ps.setString(3, education);
		ps.setString(4, summary);
		ps.setString(5, companyname);
		ps.setString(6, experiencePeriod);
		ps.setString(7, skills);
		ps.setString(8, certifications);
		ps.setString(9, projects);	
		ps.setString(10, username);
		ps.execute();
		con.close();
		System.out.println("DONE");
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	} 
}


public  void truncateTable() 
{
  	HashMap<String, Job> hm = new HashMap<String, Job>();
    try {
		getConnection();
        ps = con.prepareStatement("truncate table Job");
        ps.execute();
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void  insertJob(String id, String category, String companyname, String jobtype, String jobposition,int salary, String jobdescription, String experiencelevel,String state)
{
	try
	{
		getConnection(); 
		String insertJobsQuery = "INSERT INTO job(id,category,companyname,jobtype,jobposition,salary,jobdescription ,experiencelevel,state)"
		+ "VALUES(?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(insertJobsQuery); 

		ps.setString(1, id);
		ps.setString(2, category);
		ps.setString(3, companyname);
		ps.setString(4, jobtype);
		ps.setString(5, jobposition);
		ps.setInt(6, salary);
		ps.setString(7, jobdescription);
		ps.setString(8, experiencelevel);
		ps.setString(9, state);
		ps.execute();
		con.close();

	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public HashMap<String, Job> getAllJobs() 
{
	HashMap<String, Job> hm = new HashMap<String, Job>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM job");
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setSalary(rs.getInt("salary"));
			item.setId(rs.getString("id"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setCategory(rs.getString("category"));
			item.setJobType(rs.getString("jobtype"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setJobPosition(rs.getString("jobposition"));
			item.setState(rs.getString("state"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public HashMap<String, Job> getJobsCategory(String category) 
{
	HashMap<String, Job> hm = new HashMap<String, Job>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM job WHERE category=?");
		ps.setString(1, category);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setSalary(rs.getInt("salary"));
			item.setId(rs.getString("id"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setCategory(rs.getString("category"));
			item.setJobType(rs.getString("jobtype"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setJobPosition(rs.getString("jobposition"));
			item.setState(rs.getString("state"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public HashMap<String, Job> getJobsState(String state) 
{
	HashMap<String, Job> hm = new HashMap<String, Job>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM job WHERE state=?");
		ps.setString(1, state);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setSalary(rs.getInt("salary"));
			item.setId(rs.getString("id"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setCategory(rs.getString("category"));
			item.setJobType(rs.getString("jobtype"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setJobPosition(rs.getString("jobposition"));
			item.setState(rs.getString("state"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public HashMap<String, Job> getJobsState_Category(String category, String state) 
{
	HashMap<String, Job> hm = new HashMap<String, Job>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM job WHERE category=? and state=?");
		ps.setString(1, category);
		ps.setString(2, state);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setSalary(rs.getInt("salary"));
			item.setId(rs.getString("id"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setCategory(rs.getString("category"));
			item.setJobType(rs.getString("jobtype"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setJobPosition(rs.getString("jobposition"));
			item.setState(rs.getString("state"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}



public HashMap<String, Job> getJobs(String makers) 
{
	HashMap<String, Job> hm = new HashMap<String, Job>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM job WHERE category = ?"); 
        ps.setString(1, makers);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setSalary(rs.getInt("salary"));
			item.setId(rs.getString("id"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setCategory(rs.getString("category"));
			item.setJobType(rs.getString("jobtype"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setJobPosition(rs.getString("jobposition"));
			item.setState(rs.getString("state"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public HashMap<String, UserProfile> getProfile(String username) 
{
	HashMap<String, UserProfile> hm = new HashMap<String, UserProfile>();
    try 
    {
    	getConnection();
		ps = con.prepareStatement("SELECT * FROM userprofile WHERE username=?");
		ps.setString(1, username);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
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
			hm.put(item.getusername(),item);
		}
		return hm;	
    }
    catch (Exception e) 
    {
        e.printStackTrace();
		return null;
    }
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void insertJobOrder(String OrderId,java.sql.Date sqlDate,String companyname,String username, String salary,String category,String jobid,String jobtype,String jobposition,String state) 
{
    try {
		getConnection(); 
		String insertJobOrderQuery = "INSERT INTO joborders(OrderId,AddressDate,companyname,username,salary,category,jobid,jobtype,jobposition,state)"
		+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
        ps = con.prepareStatement(insertJobOrderQuery); 

        ps.setString(1, OrderId);
		ps.setString(2, (sqlDate).toString());
		ps.setString(3, companyname);
		ps.setString(4, username);
        ps.setInt(5, Integer.parseInt(salary));
		ps.setString(6,category);
		ps.setString(7,jobid);
		ps.setString(8,jobtype);
		ps.setString(9,jobposition);
		ps.setString(10,state);
        ps.execute();

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	} 
}


public  HashMap<String, Application> ViewApplication(String username) 
{
  	HashMap<String, Application> hm = new HashMap<String, Application>();
    try 
    {
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM joborders WHERE username = ?"); 
        ps.setString(1, username);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Application item = new Application();
			item.setUsername(rs.getString("username"));
			item.setsalary(rs.getInt("salary"));
			item.setOrderId(rs.getString("OrderId"));
			item.setCompanyName(rs.getString("companyname"));
			item.setstate(rs.getString("state"));
			item.setjobtype(rs.getString("jobtype"));
			item.setcategory(rs.getString("category"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void CancelApplication(String OrderId) 
{
	try 
	{
		getConnection(); 
		ps = con.prepareStatement("DELETE FROM joborders WHERE OrderId = ?"); 
		ps.setString(1, OrderId);
		ps.executeUpdate();	

	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public  HashMap<String, Users> CheckFollowers(String follower,String following) 
{
  	HashMap<String, Users> hm = new HashMap<String, Users>();
    try 
    {	
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM follow WHERE follower=? and following=?"); 
        ps.setString(1, follower);
        ps.setString(2, following);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Users item = new Users();
			item.setusername(rs.getString("following"));
			hm.put(item.getusername(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void  AddFollower(String follower, String following,String firstname,String lastname)
{
	try
	{	
		getConnection(); 
		String addFollowerQuery = "INSERT INTO follow(follower,following,firstname,lastname)"
		+ "VALUES(?,?,?,?)";
		ps = con.prepareStatement(addFollowerQuery); 

		ps.setString(1, follower);
		ps.setString(2, following);
		ps.setString(3, firstname);
		ps.setString(4, lastname);
		ps.execute();
		con.close();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public void RemoveFollower(String follower, String following) 
{
	try 
	{
		getConnection(); 
		ps = con.prepareStatement("DELETE FROM follow WHERE follower=? and following=?"); 
		ps.setString(1, follower);
		ps.setString(2, following);
		ps.executeUpdate();	
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public  HashMap<String, Users> getFollowersList(String username) 
{
  	HashMap<String, Users> hm = new HashMap<String, Users>();
    try 
    {		
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM userprofile WHERE username in (SELECT follower FROM follow WHERE following=?)"); 
        ps.setString(1, username);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Users item = new Users();
			item.setusername(rs.getString("username"));
			item.setfirstname(rs.getString("firstname"));
			item.setlastname(rs.getString("lastname"));
			hm.put(item.getusername(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public  HashMap<String, Users> getFollowingList(String username) 
{
  	HashMap<String, Users> hm = new HashMap<String, Users>();
    try 
    {		
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM follow WHERE follower=?"); 
        ps.setString(1, username);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Users item = new Users();
			item.setfollowing(rs.getString("following"));
			item.setfirstname(rs.getString("firstname"));
			item.setlastname(rs.getString("lastname"));
			hm.put(item.getfollowing(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public Integer getCount()
{
	try
	{	
		getConnection(); 
        ps = con.prepareStatement("SELECT count(*) AS count FROM posts"); 
        ResultSet rs= ps.executeQuery();

        Integer count = 0;
        while(rs.next())
        {
			count = rs.getInt("count");
		}
		return count;
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	return null;
}


public void  WritePost(Integer count,String username, String text,String firstname,String lastname)
{
	try
	{	
		getConnection(); 
		String writePostQuery = "INSERT INTO posts(postid,username,text,firstname,lastname)"
		+ "VALUES(?,?,?,?,?)";
		ps = con.prepareStatement(writePostQuery); 

		ps.setInt(1, count);
		ps.setString(2, username);
		ps.setString(3, text);
		ps.setString(4, firstname);
		ps.setString(5, lastname);
		ps.execute();
		con.close();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public  HashMap<String, Users> getFollowers(String username) 
{
  	HashMap<String, Users> hm = new HashMap<String, Users>();
    try 
    {		
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM follow WHERE follower=?"); 
        ps.setString(1, username);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Users item = new Users();
			item.setfollower(rs.getString("follower"));
			item.setfollowing(rs.getString("following"));
			hm.put(item.getfollowing(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public  HashMap<String, Post> getPosts() 
{
  	HashMap<String, Post> hm = new HashMap<String, Post>();
    try 
    {		
		getConnection(); 
        ps = con.prepareStatement("SELECT * FROM posts"); 
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {
			Post item = new Post();
			item.setpostid(rs.getString("postid"));
			item.setusername(rs.getString("username"));
			item.settext(rs.getString("text"));
			item.setfirstname(rs.getString("firstname"));
			item.setlastname(rs.getString("lastname"));
			hm.put(item.getpostid(),item);
		}
		return hm;	
  	} 
  	catch (Exception e) 
  	{
        e.printStackTrace();
		return null;
    } 
    finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public int  deletejob(String pid,String category)
{
    try 
	{
		getConnection();
		HashMap<String, Job> hm = new HashMap<String, Job>();
        ps = con.prepareStatement("DELETE FROM job WHERE id=? AND category=?;"); 
		ps.setString(1, pid);
		ps.setString(2,category);
        int  rs= ps.executeUpdate();
		return rs;	
    }
	catch (Exception e)
	{
        e.printStackTrace();
		return 0;
    }
	finally 
	{
        try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}



public void insertJob1(String id, String category, String companyname, String jobtype, String jobposition,int salary, String jobdescription,String state,String experiencelevel) {
    try {
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard?useSSL=false","root","root"); 
		ps = con.prepareStatement("insert into job(id,category,companyname,jobtype,jobposition,salary,jobdescription,state,experiencelevel)  values(?,?,?,?,?,?,?,?,?)"); 
		ps.setString(1, id);
		ps.setString(2, category);
		ps.setString(3, companyname);
		ps.setString(4, jobtype);
		ps.setString(5, jobposition);
		ps.setInt(6, salary);
		ps.setString(7, jobdescription);
		ps.setString(8, state);
		ps.setString(9,experiencelevel);
		ps.executeUpdate();
		con.close();

    } catch (Exception e) {
		
        e.printStackTrace();
    } 
    finally {
        try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

}

	
public LinkedHashMap<String, List<String>>  SoldProduct()
{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard","root","root"); 
			LinkedHashMap<String,List<String>> hm = new LinkedHashMap();	  
			ps = con.prepareStatement("SELECT *,count(*) FROM jobboard.joborders,jobboard.job WHERE jobboard.joborders.jobid = jobboard.job.id group by jobboard.joborders.jobid order by Count(jobboard.joborders.jobid) desc  limit 5 ;  "); 
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
						List<String> topp = new ArrayList<String>();
						System.out.println(rs.getString("id")+rs.getString("companyname")+rs.getString("category")+rs.getString("count(*)"));
						//item.setDiscount(rs.getString("discount"));
						topp.add(rs.getString("companyname"));
						topp.add(rs.getString("count(*)"));
						hm.put(rs.getString("id"),topp);
					}
				return hm;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

public LinkedHashMap<String, String>  ZipCode()
{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard","root","root"); 
		LinkedHashMap<String, String> hm = new LinkedHashMap();
		ps = con.prepareStatement("SELECT *,count(*) FROM jobboard.joborders group by jobboard.joborders.state order by Count(jobboard.joborders.jobid) desc  limit 5 ;  "); 
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getString("state") + rs.getString("count(*)"));
			hm.put(rs.getString("state"),rs.getString("count(*)"));;
		}
		return hm;	
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}

public void insertcart(AddJob cart)
{
	try
	{
		getConnection();
		ps = con.prepareStatement("insert into cart(companyname,salary,category,jobposition, jobtype, state, username , jobid)  values(?,?,?,?,?,?,?,?)"); 
		ps.setString(1, cart.getCompanyName());
		ps.setString(2, cart.getSalary());
		ps.setString(3, cart.getCategory());
		ps.setString(4, cart.getJobPosition());
		ps.setString(5, cart.getJobType());
		ps.setString(6, cart.getState());
		ps.setString(7, cart.getUsername());
		ps.setString(8, cart.getJobId());
		ps.execute();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}



public HashMap<String, Job>  selectjob(String pid,String category)
{
	ResultSet rs = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard","root","root"); 
		HashMap<String, Job> hm = new HashMap<String, Job>();
		ps = con.prepareStatement("SELECT * FROM jobboard.job where id= ? and category = ? ; "); 
		ps.setString(1, pid);
		ps.setString(2,category);
		rs= ps.executeQuery();
		while(rs.next())
		{
			Job item = new Job();
			item.setCompanyName(rs.getString("companyname"));
			item.setCategory(rs.getString("category"));
			item.setSalary(rs.getInt("salary"));
			item.setJobPosition(rs.getString("jobposition"));
			//item.setSalary(rs.getString("salary"));
			item.setId(rs.getString("id"));
			item.setJobDescription(rs.getString("jobdescription"));
			item.setExperienceLevel(rs.getString("experiencelevel"));
			item.setState(rs.getString("state"));
			item.setJobType(rs.getString("jobtype"));
			hm.put(item.getCompanyName(),item);
		}
		return hm;	
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	} 
	finally 
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public ArrayList<Job> checkInventory() 
{
	ArrayList<Job> jobs = new ArrayList<Job>();
	try 
	{
		getConnection(); 
		ps = con.prepareStatement("select companyname,salary,category,jobtype from job; "); 
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			Job job = new Job();
			job.setCompanyName(rs.getString("companyname"));
			job.setJobType(rs.getString("jobtype"));
			job.setSalary(rs.getInt("salary"));
			job.setCategory(rs.getString("category"));
			jobs.add(job);
		}
		return jobs;	
	} 
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	} 
	finally
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}

}

public void  updatejob(String id,String category,String companyname,String jobtype,String jobposition,String jobdescription,String state,int salary,String experiencelevel)
{
    try
	{
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/jobboard","root","root"); 
		HashMap<String, Job> hm = new HashMap<String, Job>();
        ps = con.prepareStatement("UPDATE  job set companyname=?,jobtype=?,jobposition=?,salary=?,jobdescription=?,experiencelevel=?,state=? where id=? and category=?; "); 
		ps.setString(1, companyname);
		ps.setString(2,jobtype);
		ps.setString(3,jobposition);
		ps.setString(4,jobdescription);
		ps.setString(5,state);
		ps.setInt(6,salary);
		ps.setString(7,experiencelevel);
		ps.setString(8,id);
		ps.setString(9,category);
        ps.execute();
    }
	catch (Exception e)
	{
        e.printStackTrace();
		// return 0;
    }
	finally
	{
        try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}


public ArrayList<Job> categoryWise_jobs()
{
	//ArrayList<OrderP> orders = new ArrayList<OrderP>();
	ArrayList<Job> jobs = new ArrayList<Job>();
	try 
	{
		getConnection(); 
		ps = con.prepareStatement("Select AddressDate, jobtype, count(*) as Count from joborders group by jobtype;"); 
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			Job job = new Job();
			job.setDate(rs.getString("AddressDate"));
			//job.setCompanyName(rs.getString("companyname"));
			//job.setJobType(rs.getString("jobtype"));
			job.setJobType(rs.getString("jobtype"));
			job.setCount(rs.getInt("Count"));
			//job.setCategory(rs.getString("category"));
			jobs.add(job);
		}
		return jobs;	
	} catch (Exception e)
	{
		e.printStackTrace();
		return null;
	} 
	finally
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}


public ArrayList<Job> checkSalesdate()
{
	ArrayList<Job> jobs = new ArrayList<Job>();
	try 
	{
		getConnection(); 
		ps = con.prepareStatement("Select AddressDate, count(*) as Count from joborders group by AddressDate;"); 
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{

			Job job = new Job();
			job.setDate(rs.getString("AddressDate"));
			job.setCount(rs.getInt("Count"));
			jobs.add(job);
		}
		return jobs;	
	} catch (Exception e)
	{
		e.printStackTrace();
		return null;
	} 
	finally
	{
		try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}

}




