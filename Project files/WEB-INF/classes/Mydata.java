
public class Mydata implements java.io.Serializable{
	
	private String usertype;
	
	public Mydata(String usertype){
		
		this.usertype=usertype;
	}
	
	
	public Mydata() {
		
	}
	
	public String getusertype() {
		return usertype;
	}

	public void setusertype(String usertype) {
		this.usertype = usertype;
	}

}