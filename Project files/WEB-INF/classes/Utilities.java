
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.lang.Object;
import java.net.URLConnection;
import java.net.*;
import java.util.*;

public class Utilities extends HttpServlet{
	
	HttpServletRequest req;
	PrintWriter pw;
	HttpSession session;
	String url;
  Users user;
	
	public Utilities(HttpServletRequest req, PrintWriter pw)
	{
		this.req=req;
		this.pw=pw;
		this.url=this.fetchURL();
	}
	
	public void printHtml(String htmlFile)
	{	
		String result=HtmlToString(htmlFile);
		StringBuffer sb = new StringBuffer();
		sb.append("");			
	}
	
	public String fetchURL()
	{
		String scheme=req.getScheme();
		String server=req.getServerName();
		int port=req.getServerPort();
		String path=req.getContextPath();
		StringBuffer sb=new StringBuffer();
		sb.append(scheme).append("://").append(server);
		if((port!=80))
		{
			sb.append(":").append(port);
		}
		sb.append(path).append("/Html/");
		return sb.toString();
	}
	
	public String HtmlToString(String htmlFile)
	{
		String text=null;
		String newPage=url+ htmlFile;
		try
		{
			URL url = new URL(newPage);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader inr= new InputStreamReader(in);
			int readChars;
			char[] arr=new char[1024];
			StringBuffer sb=new StringBuffer();
			while((readChars=inr.read(arr)) > 0)
			{
				sb.append(arr,0,readChars);
			}
			
			text=sb.toString();
			pw.println(text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return text;
	}
	
}