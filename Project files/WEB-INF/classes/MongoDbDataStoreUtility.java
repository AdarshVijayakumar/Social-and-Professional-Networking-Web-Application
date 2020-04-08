import com.mongodb.*;
import java.util.*;
import java.io.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.*;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import static java.util.Arrays.asList;

public class MongoDbDataStoreUtility
{
	  DBCollection myApplications;
	public  void getConnection()
	{
		
		MongoClient mongo;
		mongo = new MongoClient("localhost",27017);
		
		DB db = mongo.getDB("Applications");
		myApplications =db.getCollection("myApplications");	
	}


	public  void FullApply(String username,String companyname, String salary,String category, String jobtype,String jobposition,String state,String fullname,
		String email,String gender,String occupation, String age,String address, String experience, String education,String projects)
	{
		getConnection();

		BasicDBObject doc = new BasicDBObject("title","myApplications").
		append("username",username).
		append("companyname",companyname).
		append("salary",salary).
		append("category",category).
		append("jobtype",jobtype).
		append("jobposition",jobposition).
		append("state",state).
		append("fullname",fullname).
		append("email",email).
		append("gender",gender).
		append("occupation",occupation).
		append("age",age).
		append("address",address).
		append("experience",experience).
		append("education",education).
		append("projects",projects);
		
		myApplications.insert(doc);
	}


	public void WriteReview(String username,String companyname, String id,String category,String jobposition,String state,String fullname,
		String email,String occupation, String age,String rating, String reviewtext)
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost",27017);
		
		DB db = mongo.getDB("Applications");
		DBCollection myReviews = db.getCollection("myReviews");

		BasicDBObject doc = new BasicDBObject("title","myReviews").
		append("username",username).
		append("companyname",companyname).
		append("id",id).
		append("category",category).
		append("jobposition",jobposition).
		append("state",state).
		append("fullname",fullname).
		append("email",email).
		append("occupation",occupation).
		append("age",age).
		append("rating",rating).
		append("reviewtext",reviewtext);
		
		myReviews.insert(doc);
	}


	public  HashMap<String, ArrayList<JobApply>> selectJobApplication()
	{
		getConnection();

		HashMap<String, ArrayList<JobApply>> application=new HashMap<String, ArrayList<JobApply>>();
		DBCursor cursor = myApplications.find();

		while (cursor.hasNext())						
		{							
			BasicDBObject obj = (BasicDBObject) cursor.next();		
			if(!application.containsKey(obj.getString("username")))
			{							
				ArrayList<JobApply> arr = new ArrayList<JobApply>();				
				application.put(obj.getString("username"), arr);		
			}							
			ArrayList<JobApply> listReview = application.get(obj.getString("username"));
			JobApply review = new JobApply(obj.getString("username"),obj.getString("companyname"),obj.getString("salary"),obj.getString("category"),obj.getString("jobtype"),obj.getString("jobposition"),obj.getString("state"),obj.getString("fullname"), obj.getString("email"),obj.getString("gender"),obj.getString("occupation"),obj.getString("age"),obj.getString("address"),obj.getString("education"), obj.getString("projects"));		
			listReview.add(review);															
		}
		return application;
	}


	public  HashMap<String, ArrayList<Review>> getReviews()
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost",27017);
		
		DB db = mongo.getDB("Applications");
		DBCollection myReviews = db.getCollection("myReviews");

		HashMap<String, ArrayList<Review>> reviews=new HashMap<String, ArrayList<Review>>();
		DBCursor cursor = myReviews.find();

		while (cursor.hasNext())						
		{							
			BasicDBObject obj = (BasicDBObject) cursor.next();		
			if(!reviews.containsKey(obj.getString("id")))
			{							
				ArrayList<Review> arr = new ArrayList<Review>();				
				reviews.put(obj.getString("id"), arr);		
			}							
			ArrayList<Review> listReview = reviews.get(obj.getString("id"));
			Review review = new Review(obj.getString("username"),obj.getString("companyname"),obj.getString("id"),obj.getString("category"),obj.getString("jobposition"),obj.getString("state"),obj.getString("fullname"), obj.getString("email"),obj.getString("occupation"),obj.getString("age"),obj.getString("rating"),obj.getString("reviewtext"));		
			listReview.add(review);															
		}
		return reviews;
	}


	public static ArrayList <MostApplied> mostAppliedJobs()
	{

		ArrayList <MostApplied> mostApplied = new ArrayList <MostApplied>();
		try
		{
    		MongoClient mongo;
			mongo = new MongoClient("localhost",27017);
			
			DB db = mongo.getDB("Applications");
			DBCollection myReviews = db.getCollection("myReviews");

    		DBObject groupCompany = new BasicDBObject("_id","$companyname"); 
			groupCompany.put("count",new BasicDBObject("$sum",1));
			DBObject group = new BasicDBObject("$group",groupCompany);
			DBObject limit=new BasicDBObject();
    		limit=new BasicDBObject("$limit",5);
	  
			DBObject sortFields = new BasicDBObject("count",-1);
			DBObject sort = new BasicDBObject("$sort",sortFields);
			AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  
    		for (DBObject res : output.results()) 
    		{	  
				String company_name =(res.get("_id")).toString();
        		String count = (res.get("count")).toString();	
        		MostApplied most_app = new MostApplied(company_name,count);
				mostApplied.add(most_app);
	  		}
		}
		catch (Exception e)
		{ 
			System.out.println(e.getMessage());
		}
      	return mostApplied;
  	}


  	public static ArrayList <MostAppliedState> mostAppliedState()
	{
		ArrayList <MostAppliedState> mostapplied_state = new ArrayList <MostAppliedState> ();
		try{
		  
			MongoClient mongo;
			mongo = new MongoClient("localhost",27017);
			
			DB db = mongo.getDB("Applications");
			DBCollection myReviews = db.getCollection("myReviews");

    		DBObject groupState = new BasicDBObject("_id","$state"); 
			groupState.put("count",new BasicDBObject("$sum",1));
			DBObject group = new BasicDBObject("$group",groupState);
			DBObject limit=new BasicDBObject();
    		limit=new BasicDBObject("$limit",5);
	  
			DBObject sortFields = new BasicDBObject("count",-1);
			DBObject sort = new BasicDBObject("$sort",sortFields);
			AggregationOutput output = myReviews.aggregate(group,sort,limit);
    		for (DBObject res : output.results()) 
    		{
				String state =(res.get("_id")).toString();
	        	String count = (res.get("count")).toString();	
	        	MostAppliedState mostapp_state = new MostAppliedState(state,count);
				mostapplied_state.add(mostapp_state);
	  		}
		}
		catch (Exception e)
		{ 
			System.out.println(e.getMessage());
		}
      	return mostapplied_state;
	}			
	
}