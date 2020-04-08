import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class RecommenderUtility{
	
	static Connection conn = null;
    static String message;
	
	public static String getConnection()
	{

		try
		{
			//get connection to the mySQL database server
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobboard","root","root");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}

	public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\project\\output.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}
	
	public static Job getProduct(String name){
		Job prodObj = new Job();
		try 
		{
			String msg = getConnection();
			String selectProd="SELECT * FROM job WHERE companyname LIKE ?";
			PreparedStatement pst = conn.prepareStatement(selectProd);
			pst.setString(1,"%" + name + "%");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				prodObj = new Job();
				prodObj.setId(rs.getString("id"));
				prodObj.setCategory(rs.getString("category"));
				prodObj.setCompanyName(rs.getString("companyname"));
				prodObj.setJobType(rs.getString("jobtype"));
				prodObj.setJobPosition(rs.getString("jobposition"));
				prodObj.setSalary(rs.getInt("salary"));
				prodObj.setJobDescription(rs.getString("jobdescription"));
				prodObj.setExperienceLevel(rs.getString("experiencelevel"));
				prodObj.setState(rs.getString("state"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return prodObj;	
	}
}