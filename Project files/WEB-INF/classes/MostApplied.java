import java.io.*;

public class MostApplied
{
	String companyname ;
	String count;


	public  MostApplied(String companyname,String count)
	{
		
		this.companyname = companyname;
	    this.count = count;
	}


	public String getCompanyName()
	{
	 return companyname;
	}

	public String getCount () 
	{
	 return count;
	}
}