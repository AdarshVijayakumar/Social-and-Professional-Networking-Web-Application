

public class JobApply implements java.io.Serializable{

		String username;
		String companyname;
		String salary;
		String category;
		String jobtype;
		String jobposition;
		String state;
		String fullname;
		String email;
		String gender;
		String occupation;
		String age;
		String address;
		String education;
		String projects;

	
	public JobApply(String username,String companyname,String salary, String category, String jobtype,String jobposition,String state, String fullname,String email, String gender, String occupation, String age, String address, String education, String projects)
	{
		this.username = username;
		this.companyname=companyname;
		this.salary = salary;
		this.category = category;
		this.jobtype = jobtype;
		this.jobposition = jobposition;
		this.state = state;
		this.fullname= fullname;
		this.email = email;
		this.gender = gender;
		this.occupation= occupation;
		this.age= age;
		this.address = address;
		this.education = education;
		this.projects = projects;
	}
	
	
	public JobApply() {
		
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

	public String getsalary() {
		return salary;
	}

	public void setsalary(String salary) {
		this.salary = salary;
	}
	public String getjobtype() {
		return jobtype;
	}

	public void setjobtype(String jobtype) {
		this.jobtype = jobtype;
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
		public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
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
	
	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}
	
	public String geteducation() {
		return education;
	}

	public void seteducation(String education) {
		this.education = education;
	}

	public String getprojects() {
		return projects;
	}

	public void setprojects(String projects) {
		this.projects = projects;
	}
}

      