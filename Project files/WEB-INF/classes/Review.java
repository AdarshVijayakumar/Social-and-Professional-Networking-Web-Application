

public class Review implements java.io.Serializable{

		String username;
		String companyname;
		String id;
		String category;
		String jobposition;
		String state;
		String fullname;
		String email;
		String occupation;
		String age;
		String rating;
		String reviewtext;
	
	public Review(String username,String companyname,String id, String category,String jobposition,String state, String fullname,String email,
	 String occupation, String age, String rating, String reviewtext)
	{
		this.username = username;
		this.companyname=companyname;
		this.id = id;
		this.category = category;
		this.jobposition = jobposition;
		this.state = state;
		this.fullname= fullname;
		this.email = email;
		this.occupation= occupation;
		this.age= age;
		this.rating = rating;
		this.reviewtext = reviewtext;
	}
	
	
	public Review() {
		
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getcompanyname() {
		return companyname;
	}

	public void setcompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
	
	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}

	public String getjobposition() {
		return jobposition;
	}

	public void setjobposition(String jobposition) {
		this.jobposition = jobposition;
	}
	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}
	
	public String getfullname() {
		return fullname;
	}

	public void setfullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
	public void setage(String age) {
		this.age = age;
	}
	
	public String getoccupation() {
		return occupation;
	}

	public void setoccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public String getage() {
		return age;
	}

	public String getrating() {
		return rating;
	}

	public void setrating(String rating) {
		this.rating = rating;
	}
	
	
	public String getreviewtext() {
		return reviewtext;
	}

	public void setreviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}
	
}

      