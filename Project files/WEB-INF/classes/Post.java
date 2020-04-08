import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Post implements Serializable {
   
   	String postid;
    String username;
    String text;
    String firstname;
    String lastname;
	
    public Post(){
        }

	void setpostid(String postid) {
		this.postid = postid;
	}

	public String getpostid()
	{
		return postid;
	}

	void setusername(String username) {
		this.username = username;
	}

	public String getusername()
	{
		return username;
	}

	void settext(String text) {
		this.text = text;
	}

	public String gettext()
	{
		return text;
	}

	void setfirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getfirstname()
	{
		return firstname;
	}

	void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String getlastname()
	{
		return lastname;
	}

}
