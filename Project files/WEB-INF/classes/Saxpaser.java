import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Saxpaser extends DefaultHandler {
		Job job;
		String consoleXmlFileName;
		String elementValueRead;
		int a;
		HashMap<String,Job> hm;

    public Saxpaser(String consoleXmlFileName){
		a=1;
		hm=new HashMap<String, Job>();
        this.consoleXmlFileName = consoleXmlFileName;

        parseDocument();
        prettyPrint();
    }
	
   	private void parseDocument()
   	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
			SAXParser parser = factory.newSAXParser();
            parser.parse(this.consoleXmlFileName, this);
            
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

	public HashMap getJobs(){
		return hm;
	}
	
    private void prettyPrint(){
	
       
    }

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
        if (qName.equalsIgnoreCase("Job")) {
			job = new Job();
            job.setId(attributes.getValue("id"));
            job.setCategory(attributes.getValue("category"));
		}	
		
	}	
    @Override
    public void endElement(String uri, String localName, String element) throws SAXException 
	{
 
        if(element.equals("Job"))
		{
            hm.put("job" + a+"", job);
			a++;
	        return;
        }
        if(element.equalsIgnoreCase("companyname"))
		{
            job.setCompanyName(elementValueRead);
	        return;
        }
        if(element.equalsIgnoreCase("category"))
		{
            job.setCategory(elementValueRead);
	        return;
        }
		if(element.equalsIgnoreCase("jobtype"))
		{
			job.setJobType(elementValueRead);
			return;
        }
        if(element.equalsIgnoreCase("id"))
		{
           job.setId(elementValueRead);
			return;
        }
	  
		if(element.equalsIgnoreCase("jobdescription"))
		{
           job.setJobDescription(elementValueRead);
			return;
        }
		if(element.equalsIgnoreCase("jobposition"))
		{
           job.setJobPosition(elementValueRead);
			return;
        }
        if(element.equalsIgnoreCase("Salary"))
		{
            job.setSalary(Integer.parseInt(elementValueRead));
			return;
        }
 		if(element.equalsIgnoreCase("experiencelevel"))
		{
            job.setExperienceLevel(elementValueRead);
			return;
        } 
		if(element.equalsIgnoreCase("state"))
		{
            job.setState(elementValueRead);
			return;
        } 
    }

    @Override
    public void characters(char content[], int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);	
		
    }
	public static void main(String[] args)
	{
		String TOMCAT_HOME = System.getProperty("catalina.home");
		new Saxpaser(TOMCAT_HOME+"\\webapps\\project\\JobCatalog.xml");
    }
}
