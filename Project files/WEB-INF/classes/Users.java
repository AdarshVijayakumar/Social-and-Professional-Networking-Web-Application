
public class Users implements java.io.Serializable{
	 String username;
	 String password;
	 String firstname;
	 String lastname;
	 String usertype;
	 String gender;
	 String follower;
	 String following;
	
	public Users(String username, String password, String usertype,String firstname,String lastname){
		this.username=username;
		this.password=password;
		this.usertype = usertype;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Users(String username, String password, String firstname,String lastname,String usertype, String gender){
		this.username=username;
		this.password=password;
		this.firstname=firstname;
		this.lastname=lastname;
		this.gender= gender;
		this.usertype = usertype;
	}
	
	
	public Users() {
		
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getfirstname() {
		return firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getlastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	public String getusertype() {
		return usertype;
	}

	public void setusertype(String usertype) {
		this.usertype = usertype;
	}

	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
	}

	public String getfollower() {
		return follower;
	}

	public void setfollower(String follower) {
		this.follower = follower;
	}

	public String getfollowing() {
		return following;
	}

	public void setfollowing(String following) {
		this.following = following;
	}
}

      